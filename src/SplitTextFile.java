import java.io.*;

public class SplitTextFile {
    private static final int LINES_PER_FILE = 100000;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java SplitTextFile <input_file>");
            return;
        }
        String inputFilePath = args[0];
        splitFile(inputFilePath);
    }

    public static void splitFile(String inputFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            int fileCount = 1;
            int lineCount = 0;
            BufferedWriter writer = createNewWriter(inputFilePath, fileCount);

            while ((line = reader.readLine()) != null) {
                if (lineCount >= LINES_PER_FILE) {
                    writer.close();
                    fileCount++;
                    lineCount = 0;
                    writer = createNewWriter(inputFilePath, fileCount);
                }
                writer.write(line);
                writer.newLine();
                lineCount++;
            }
            writer.close();
            System.out.println("Splitting complete. Created " + fileCount + " files.");
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }

    private static BufferedWriter createNewWriter(String inputFilePath, int fileCount) throws IOException {
        String outputFileName = inputFilePath + ".part" + fileCount + ".txt";
        System.out.println("Creating file: " + outputFileName);
        return new BufferedWriter(new FileWriter(outputFileName));
    }
}
