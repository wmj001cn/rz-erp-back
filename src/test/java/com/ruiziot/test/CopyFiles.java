package com.ruiziot.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class CopyFiles {
	
	public static void main(String[] args) {
		File fiel = new File("e://files.txt");
		try {
			Files.lines(fiel.toPath()).forEach(l->{
				Path path = Paths.get(l);
				try {
					Files.copy(path, Paths.get("e://myjars", path.getFileName().toString()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}
