import java.io.IOException;

public class AutoPush {

    public static void main(String[] args) throws IOException, InterruptedException {

        String[][] commands = {
            {"git", "add", "."},
            {"git", "commit", "-m", "Automatic update"},
            {"git", "push"}
        };

        for (String[] command : commands) {

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.inheritIO();

            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.out.println("Something went wrong!");
                return;
            }
        }

        System.out.println("GitHub upload completed successfully!");
    }
}