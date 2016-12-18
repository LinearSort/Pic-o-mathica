

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.wolfram.jlink.*;

public class SampleProgram {

	public static void main(String[] argv) throws IOException {

		KernelLink ml = null;

		try {
			String jLinkDir = "/home/martin/Wolfram/SystemFiles/Links/JLink";
			System.setProperty("com.wolfram.jlink.libdir", jLinkDir);
			
			ml = MathLinkFactory.createKernelLink("-linkmode launch -linkname "
					+ "'/home/martin/Wolfram/Executables/MathKernel'");

		} catch (MathLinkException e) {
			System.out.println("Fatal error opening link: " + e.getMessage());
			return;
		}

		try {
			String escape = "\\";
			
			ml.discardAnswer();

			String workDir = System.getProperty("user.dir");

			BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/main/resources/the-file-name.txt"));
			String input = br.readLine();

			String newStr = input.replaceAll("[ ]+", "");
			
			if (!newStr.contains(escape))
			{
				String strResult = ml.evaluateToOutputForm("Solve[ToExpression[\"" 
						+ newStr + "\",TeXForm]]", 0);
				
				System.out.println(strResult);
			}
			else 
			{
				String replaced = new String();
				String escaped = new String();
				
				replaced = newStr.replace(escape, escape + escape);
				if (replaced.contains("int"))
				{
					escaped = replaced.replace("int", "int ");
					if (!escaped.contains("\\,d"))
					{
						escaped = escaped.replace("d", "\\,d");
					}
					replaced = escaped;
				}
				System.out.println(replaced);
				String strResult = ml.evaluateToInputForm("Solve[ToExpression[\"" 
				+ replaced + "\",TeXForm]]", 0);
				
				if(strResult.contains("Solve["))
				{
					strResult = strResult.replace("Solve[", "");
					strResult = strResult.replace(strResult.substring(strResult.length()-1), "");
				}
				System.out.println(strResult);
			}

			br.close();

		} catch (MathLinkException e) {
			System.out.println("MathLinkException occurred: " + e.getMessage());
		} finally {
			ml.close();
		}
	}
}
