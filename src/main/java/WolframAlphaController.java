
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static java.awt.SystemColor.text;
import static javax.swing.text.StyleConstants.Family;

/**
 * Created by martin on 18.12.16.
 */
public class WolframAlphaController {

    private final static String appId = "5Y5JJW-HQ6Y4Y4RUK";
    private final static String appName = "Pic-o-mathica";

    private final static String appUsageType = "Personal/Non-commercial Only";

    // The maximum numbers of Apps is 20 by default.


    public static void main(String[] args) throws IOException {
        URL url = new URL("http://api.wolframalpha.com/v2/query");

        String workingDir = System.getProperty("user.dir");

        BufferedReader brTest = new BufferedReader(new FileReader(workingDir + "/src/main/resources/latex.txt"));
        String input = brTest.readLine();

        //String input = "\\int \\frac{dx}{x} ";
        String query = "?format=mathml&input=" + URLEncoder.encode(input, "UTF-8") + "&appid=" + appId;

        System.out.println(input);
        System.out.println(URLEncoder.encode(input, "UTF-8"));

        //make connection
        URLConnection urlc = url.openConnection();

        //use post mode
        urlc.setDoOutput(true);
        urlc.setAllowUserInteraction(false);

        //send query
        PrintStream ps = new PrintStream(urlc.getOutputStream());
        ps.print(query);
        ps.close();

        //get result
        BufferedReader br = new BufferedReader(new InputStreamReader(urlc
                .getInputStream()));
        String l = null;
        while ((l=br.readLine())!=null) {
            System.out.println(l);
        }
        br.close();
    }


}
