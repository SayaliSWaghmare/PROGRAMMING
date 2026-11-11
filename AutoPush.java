import java.io.IOException;

public class AutoPush {

    static int runCommand(String... command)
            throws IOException, InterruptedException {

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.inheritIO();

        Process process = pb.start();
        return process.waitFor();
    }

    public static void main(String[] args)
            throws IOException, InterruptedException {

        runCommand("git", "add", ".");

        int result = runCommand("git", "diff", "--cached", "--quiet");

        if (result == 0) {
            System.out.println("No new changes to upload!");
            return;
        }

        if (result != 1) {
            System.out.println("Error checking changes!");
            return;
        }

        if (runCommand("git", "commit", "-m", "Automatic update") != 0) {
            System.out.println("Commit failed!");
            return;
        }

        if (runCommand("git", "push") != 0) {
            System.out.println("Push failed!");
            return;
        }

        System.out.println("GitHub upload completed successfully!");
    }
}