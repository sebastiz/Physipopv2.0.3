package Console;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveLineInFile {

    private static File file;

    public static void main(String[] args) {

    }

    public static void removeLineFromFile(String lineToRemove) {
    	Preferences userPrefs = Preferences.userNodeForPackage(TBB_SQLBuilder.class);
        //create a new File

    	 file = new File(userPrefs.get("PathForLogger", null));

            // Construct the new temporary file that will later be renamed to the original
            File tempFile = new File(file.getAbsolutePath() + ".tmp");

           //Two Embedded Automatic Resource Managers used
            // to effectivey handle IO Responses
          try(Scanner scanner = new Scanner(file)) {
              try (PrintWriter pw = new PrintWriter(new FileWriter(tempFile))) {

                  //a declaration of a String Line Which Will Be assigned Later
                  String line;

                  // Read from the original file and write to the new
                  // unless content matches data to be removed.


                  while (scanner.hasNextLine()) {
                      line = scanner.nextLine();
                      Pattern NumDays_pattern = Pattern.compile(lineToRemove);
              		Matcher matcherNumDays_pattern = NumDays_pattern.matcher(line);
              		if (matcherNumDays_pattern.find()) {
                          pw.println(line);
                          pw.flush();
                      }
                  }
                  // Delete the original file
                  if (!file.delete()) {
                      System.out.println("Could not delete file");
                      return;
                  }

                  // Rename the new file to the filename the original file had.
                  if (!tempFile.renameTo(file))
                      System.out.println("Could not rename file");
              }
          }
        catch (IOException e)
        {
            System.out.println("IO Exception Occurred");
        }

    }



}