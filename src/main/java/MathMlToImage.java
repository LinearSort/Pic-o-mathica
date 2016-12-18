package main.java;
import java.io.File;
import java.io.IOException;

import net.sourceforge.jeuclid.converter.Converter;

public class MathMlToImage {

	public MathMlToImage() {
	}

	public static void main(String[] args) {
	    Converter converter = Converter.getInstance();
	    String workDir = System.getProperty("user.dir");
	    try {
			converter.convert(new File(workDir + "/src/main/resources/input/matrix.xml"),
			    new File(workDir + "/src/main/resources/output/matrix.jpg"), "image/jpeg");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
