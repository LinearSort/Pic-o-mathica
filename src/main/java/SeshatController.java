import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by martin on 18.12.16.
 */
public class SeshatController {

//    public static void main(String[] args) throws IOException, InterruptedException {
////        String[] cmd = { "C:\\E.M. TVCC\\TVCC.exe", "-f E:\\TestVideo\\01.avi", "-o E:\\OutputFiles\\target.3gp" };
//
////        String[] cmd = { System.getProperty("user.dir") + "/seshat/seshat",
////                "-c Config/CONFIG",
////                "-i SampleMathExps/exp.inkml",
////                "-o out.inkml",
////                "-r render.pgm ",
////                "-o out.dot" };
////        Process p = Runtime.getRuntime().exec(cmd);
////        p.waitFor();
//
//
////        Process process = new ProcessBuilder(System.getProperty("user.dir") + "/seshat/seshat",
////                "-c Config/CONFIG",
////                "-i SampleMathExps/exp.inkml",
////                "-o out.inkml",
////                "-r render.pgm ",
////                "-o out.dot" ).start();
//
//        System.out.println(System.getProperty("user.dir") + "/seshat/seshat");
//
//        try {
////            Process p = Runtime.getRuntime().exec(System.getProperty("user.dir") + "/seshat/seshat -c Config/CONFIG -i SampleMathExps/exp.inkml -o /home/martin/Documents/Pic-o-mathica/seshat/test/out.inkml -r /home/martin/Documents/Pic-o-mathica/seshat/test/render.pgm");
//
//
//            String[] cmd = { System.getProperty("user.dir") + "/seshat/seshat",
//                "-c Config/CONFIG",
//                "-i SampleMathExps/exp.inkml",
//                "-o out.inkml",
//                "-r render.pgm ",
//                "-o out.dot" };
//        Process p = new ProcessBuilder(cmd).start();
//        p.waitFor();
//
//
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(p.getInputStream()));
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(System.getProperty("sun.arch.data.model"));
//    }


//    public static void main(String[] args) {
//        String s = null;
//
//        try {
//
//            // run the Unix "ps -ef" command
//            // using the Runtime exec method:
////            Process p = Runtime.getRuntime().exec(System.getProperty("user.dir") + "/seshat/seshat "
////                    + "-c /home/martin/Documents/Pic-o-mathica/seshat/Config/CONFIG "
////                    + "-i /home/martin/Documents/Pic-o-mathica/seshat/SampleMathExps/exp.inkml "
////                    + "-o /home/martin/Documents/Pic-o-mathica/seshat/test/out.inkml "
////                    + "-r /home/martin/Documents/Pic-o-mathica/seshat/test/render.pgm");
//
////            String[] cmd = {System.getProperty("user.dir") + "/seshat/seshat",
////                    "-c /home/martin/Documents/Pic-o-mathica/seshat/Config/CONFIG",
////                    "-i /home/martin/Documents/Pic-o-mathica/seshat/SampleMathExps/exp.inkml",
////                    "-o /home/martin/Documents/Pic-o-mathica/seshat/test/out.inkml",
////                    "-r /home/martin/Documents/Pic-o-mathica/seshat/test/render.pgm "};
//            //Process p = new ProcessBuilder(cmd).start();
////            p.waitFor();
//
////            Process p = Runtime.getRuntime().exec(System.getProperty("user.dir") + "/seshat/script.sh");
//
//            String[] cmd = {"bash", System.getProperty("user.dir") + "/seshat/script.sh"};
//                    Process p = new ProcessBuilder(cmd).start();
//
//            BufferedReader stdInput = new BufferedReader(new
//                    InputStreamReader(p.getInputStream()));
//
//            BufferedReader stdError = new BufferedReader(new
//                    InputStreamReader(p.getErrorStream()));
//
//            // read the output from the command
//            System.out.println("Here is the standard output of the command:\n");
//            while ((s = stdInput.readLine()) != null) {
//                System.out.println(s);
//            }
//
//            // read any errors from the attempted command
//            System.out.println("Here is the standard error of the command (if any):\n");
//            while ((s = stdError.readLine()) != null) {
//                System.out.println(s);
//            }
//
//            System.exit(0);
//        } catch (IOException e) {
//            System.out.println("exception happened - here's what I know: ");
//            e.printStackTrace();
//            System.exit(-1);
//        }
//    }


    public static void main(String[] args) throws IOException {
//        // build my command as a list of strings
//        List<String> command = new ArrayList<String>();
//        command.add("bash");
//        command.add(System.getProperty("user.dir") + "/seshat/script.sh");
//
        String s = null;
// Sun's ProcessBuilder and Process example
        ProcessBuilder pb = new ProcessBuilder("bash", System.getProperty("user.dir") + "/seshat/script.sh");
        Map<String, String> env = pb.environment();
        env.put("VAR1", "myValue");
        env.remove("OTHERVAR");
        env.put("VAR2", env.get("VAR1") + "suffix");
        ProcessBuilder myDir = pb.directory(new File(System.getProperty("user.dir") + "/seshat"));
        Process p = pb.start();

        BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
    }
}
