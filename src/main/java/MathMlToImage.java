

import java.io.File;
import java.io.IOException;

import net.sourceforge.jeuclid.converter.Converter;

public class MathMlToImage {
	
	public final static String INPUT = "/src/main/resources/input/sum.xml";
	public final static String OUTPUT = "/src/main/resources/output/sum.jpg";

	public MathMlToImage() {
	}

	public static void main(String[] args) {
	    Converter converter = Converter.getInstance();
	    String workDir = System.getProperty("user.dir");
	    try {
			converter.convert(new File(workDir + INPUT),
			    new File(workDir + OUTPUT), "image/jpeg");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
