package com.ruiziot.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.ghost4j.Ghostscript;
import org.ghost4j.GhostscriptException;

public class Snippet {
	public BufferedImage getRasterizedPDF() {
		if (rasterizedPDF != null) {
			return rasterizedPDF;
		}
	
		try {
			File pdfInputFile = File.createTempFile(getClass().getName() + ".testPDF", ".pdf");
			pdfInputFile.deleteOnExit();
			OutputStream pdfInput = new FileOutputStream(pdfInputFile);
			pdfProcessor.getDocument(vectorGraphics.getCommands(), getPageSize()).writeTo(pdfInput);
			pdfInput.close();
	
			File pngOutputFile = File.createTempFile(getClass().getName() + ".testPDF", "png");
			pngOutputFile.deleteOnExit();
			Ghostscript gs = Ghostscript.getInstance();
			gs.initialize(new String[] {
					"-dBATCH",
					"-dQUIET",
					"-dNOPAUSE",
					"-dSAFER",
					String.format("-g%dx%d", Math.round(getPageSize().getWidth()), Math.round(getPageSize().getHeight())),
					"-dGraphicsAlphaBits=4",
					// TODO: More robust settings for gs? DPI value is estimated.
					"-r25",
					"-dAlignToPixels=0",
					"-dPDFFitPage",
					"-sDEVICE=pngalpha",
					"-sOutputFile=" + pngOutputFile.toString(),
					pdfInputFile.toString()
			});
			gs.exit();
			rasterizedPDF = ImageIO.read(pngOutputFile);
		} catch (IOException | GhostscriptException e) {
			e.printStackTrace();
		}
		return rasterizedPDF;
	}
}

