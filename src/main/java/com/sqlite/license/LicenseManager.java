package com.sqlite.license;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bouncycastle.openpgp.PGPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sqlite.RZERPApplication;
import com.verhas.licensor.License;

public class LicenseManager {
	String home = System.getProperty("user.dir");
	String publicKeyFilePath = this.home + "/license/pub.gpg";
	String licenseFilePath = this.home + "/license/license.lic";
	
	public static Logger logger = LoggerFactory.getLogger(LicenseManager.class);
	

	public boolean checkLicense() throws FileNotFoundException, IOException, PGPException, NoSuchAlgorithmException,
			NoSuchProviderException, SignatureException, ParseException {
		
		logger.debug("checking license ...{}", "test");
		String mac;
		mac = this.getMacAddress();
		File file2 = new File(licenseFilePath);
		if (!file2.exists()) {
			//System.out.println("No license is installed!Please request license from your vendor by giving this MAC - " + mac);
			System.exit(0);
		}

		License license = new License();
		License licenseLoaded = license.loadKeyRing(new File(this.publicKeyFilePath), null)
				.setLicenseEncodedFromFile(this.licenseFilePath);
		String issueDate = licenseLoaded.getFeature("issue-date");
		String validDate = licenseLoaded.getFeature("valid-date");
	
		//System.out.println();
		if (licenseLoaded.isVerified()) {
			
			String licenseMac = license.getFeature("mac");
			
			if (licenseMac == null || !licenseMac.equals(mac)) {
				throw new RuntimeException("This pc is not licensed! - the current MAC of the computer is : " + mac);
			}
			
			this.checkDateAndVersionValidity(issueDate, validDate);
		} else {
			throw new RuntimeException("Invalid license config!");
		}
		
		//System.out.println("License for " + mac + " will expire at " + format(validDate));
		return false;
	}

	private String format(String validDate) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.valueOf(validDate)));
	}

	public String getMacAddress() throws UnknownHostException, SocketException {
		InetAddress ip = InetAddress.getLocalHost();
		NetworkInterface inetAddr = NetworkInterface.getByInetAddress(ip);
		byte[] mac = inetAddr.getHardwareAddress();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; ++i) {
			Object[] arrobject = new Object[2];
			arrobject[0] = Byte.valueOf(mac[i]);
			arrobject[1] = i < mac.length - 1 ? "-" : "";
			sb.append(String.format("%02X%s", arrobject));
		}
		return sb.toString();
	}

	private void checkDateAndVersionValidity(String issueDate, String validDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.valueOf(issueDate));
		Calendar today = Calendar.getInstance();
		if (!calendar.before(today)) {
			throw new IllegalArgumentException("Issue date is too late, probably tampered system time");
		}
		if (validDate != null) {
			Calendar valid = Calendar.getInstance();
			valid.setTimeInMillis(Long.valueOf(validDate));
			if (today.after(valid)) {
				throw new RuntimeException("License expired.");
			}
		}
	}

}
