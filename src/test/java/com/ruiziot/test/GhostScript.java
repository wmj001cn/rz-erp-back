package com.ruiziot.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.ghost4j.converter.ConverterException;
import org.ghost4j.converter.PDFConverter;
import org.ghost4j.document.DocumentException;
import org.ghost4j.document.PSDocument;

public class GhostScript {

	private final File gsExeFile;

	
	public static void main(String[] args) throws FileNotFoundException, IOException, ConverterException, DocumentException {
		PSDocument document = new PSDocument();
		document.load(new File("input.ps"));
		FileOutputStream fos = new FileOutputStream(new File("rendition.pdf"));
		PDFConverter converter = new PDFConverter();
		converter.setPDFSettings(PDFConverter.OPTION_PDFSETTINGS_PREPRESS);
		converter.convert(document, fos);
		
		
	}
	
	public GhostScript(String path) throws IOException {
		File f = checkPath(path);
		if (f == null)
			f = checkPath("/usr/bin/gs");
		if (f == null)
			f = checkPath("/usr/local/bin/gs");
		if (f == null)
			throw new IOException("GhostScript (gs) executable not found: "
					+ path);
		gsExeFile = f;
	}

	private  File checkPath(String path) {
		if (StringUtils.isEmpty(path))
			return null;
		File f = new File(path);
		return f.exists() && f.isFile() && f.canExecute() ? f : null;
	}

	private  List<String> getArgs(String device, Integer page,
			String pdfPassword, File outputFile, File pdfFile) {
		List<String> args = new ArrayList<String>();
		args.add(gsExeFile.getAbsolutePath());
		args.add("-dNOPAUSE");
		args.add("-dBATCH");
		args.add("-dSAFER");
		args.add("-sDEVICE=" + device);
		args.add("-dINTERPOLATE");
		args.add("-dNumRenderingThreads=8");
		if (page != null) {
			args.add("-dFirstPage=" + page);
			args.add("-dLastPage=" + page);
		}
		if (pdfPassword != null)
			args.add("-sPDFPassword=" + pdfPassword);
		args.add("-sOutputFile=" + outputFile.getAbsolutePath());
		return args;
	}

	private  void execute(List<String> args, File pdfFile,
			StringBuilder returnedText) throws IOException,
			InterruptedException {
		args.add(pdfFile.getAbsolutePath());
		ExecuteUtils.run(args, 180, returnedText, 0);
	}

	public  void generateImage(String password, Integer page, File pdfFile,
			int resolution, File imageFile) throws IOException,
			InterruptedException {
		List<String> args = getArgs("png16m", page, password, imageFile,
				pdfFile);
		args.add("-dTextAlphaBits=4");
		args.add("-dGraphicsAlphaBits=4");
		args.add("-r" + resolution);
		StringBuilder returnedText = new StringBuilder();
		execute(args, pdfFile, returnedText);
		if (imageFile.length() == 0)
			throw new IOException("Ghosscript did not generate the image: "
					+ returnedText);
	}

	public  void extractText(String pdfPassword, File pdfFile, File textFile)
			throws IOException, InterruptedException {
		List<String> args = getArgs("txtwrite", null, pdfPassword, textFile,
				pdfFile);
		args.add("-dTextFormat=3");
		execute(args, pdfFile, null);
	}
}