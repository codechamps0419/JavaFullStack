import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class JavaNioFileManager {

    public static void main(String[] args) {
        // Base folder path where operations will happen
        String targetDir = "H:\\Java Notes\\IO and Files\\";
        
        // Target paths for our files
        Path originalFile = Paths.get(targetDir + "nio_demo.txt");
        Path renamedFile = Paths.get(targetDir + "nio_demo_renamed.txt");

        try {
            System.out.println("=== Starting Java NIO.2 Operations ===");

            // 1. CREATE FILE
            if (!Files.exists(originalFile)) {
                Files.createFile(originalFile);
                System.out.println("[SUCCESS] File created at: " + originalFile.toAbsolutePath());
            } else {
                System.out.println("[INFO] File already exists, skipping creation stage.");
            }

            // 2. WRITE FILE
            List<String> contentToWrite = Arrays.asList(
                "Line 1: High-performance Java NIO.2 tutorial.",
                "Line 2: Built-in non-blocking utilities.",
                "Line 3: Streamlined file management."
            );
            // CREATE ensures it makes the file if missing; TRUNCATE_EXISTING wipes old data before writing
            Files.write(originalFile, contentToWrite, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("[SUCCESS] Successfully wrote data to the file.");

            // 3. READ FILE
            System.out.println("\n--- Reading Content from File ---");
            List<String> linesFromDisk = Files.readAllLines(originalFile);
            for (String line : linesFromDisk) {
                System.out.println(" > " + line);
            }
            System.out.println("---------------------------------\n");

            // 4. RENAME (MOVE) FILE
            // REPLACE_EXISTING ensures the action succeeds even if a target file with that name already exists
            Files.move(originalFile, renamedFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("[SUCCESS] File renamed/moved to: " + renamedFile.getFileName());

            // 5. DELETE FILE
            // Pausing for a brief instant before deleting so you can see it happen if debugging step-by-step
            Files.delete(renamedFile);
            System.out.println("[SUCCESS] Renamed file cleanly deleted from local storage.");

            System.out.println("\n=== All NIO.2 Operations Executed Successfully ===");

        } catch (IOException e) {
            System.err.println("[ERROR] An I/O exception occurred during execution:");
            e.printStackTrace();
        }
    }
}