import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class DiskOperations {

    public void writeToDisk(String filename, String input, String text) {

        String formattedInput = formatInput(input);

        try {
            File myObj = new File(filename);
            FileWriter fw = new FileWriter(myObj);
            BufferedWriter bw = new BufferedWriter(fw);
            text = text + getTimeStamp() + "\t" + formattedInput;

            bw.write(text);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createFileIfNotExists(String filename) {
        Logger logger = new Logger();

        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                writeToDisk(filename, "File created", "");
                logger.writeToLog("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            logger.writeToLog("An error occured creating file");
            e.printStackTrace();
        }
    }

    public String getTimeStamp() {
        return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date());
    }

    public String readText(String filename) {
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        createFileIfNotExists(filename);

        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return stringBuilder.toString();
    }

    private String formatInput(String input) {
        String cleanedUp = "";
        String[] strSplit = input.split("\n");
        ArrayList<String> strList = new ArrayList<String>(
                Arrays.asList(strSplit));
        String padding = "                           ";
        for (int x = 0; x < strSplit.length; x++) {
            if (strSplit[x].length() > 0) {
                if (x == 0) {
                    cleanedUp += (strSplit[x] + "\n");

                }
                else {
                    cleanedUp += (padding + strSplit[x] + "\n");
                }
            }
        }
        return cleanedUp;
    }
}
