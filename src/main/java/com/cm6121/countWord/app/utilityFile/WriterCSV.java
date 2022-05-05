package com.cm6121.countWord.app.utilityFile;

import java.io.*;
import java.util.Map;

/**
 * Method that will create a directory in your root folder and return the directory as a String.
 * The CSV files that you are saving are expected to be in this path.
 */

public class WriterCSV {

    public static String createDirectory(){
        String pathFile = System.getProperty("user.home") + "\\StudentCSVSaved\\";
        File dir = new File(pathFile);
        if(!dir.exists()){
            dir.mkdirs();
        }
        return pathFile;
    }

    public static void writeToFile(Document document, Map<String, Integer> sortedByKey, File file) {
        try{
            FileOutputStream outputStream = new FileOutputStream(file, false);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(document.getTitle());
            bufferedWriter.write(",");
            bufferedWriter.write(document.getDateOfCreation());
            bufferedWriter.newLine();

            for(Map.Entry<String, Integer> entry : sortedByKey.entrySet()){

                bufferedWriter.write(entry.getKey());
                bufferedWriter.write(",");
                bufferedWriter.write(entry.getValue().toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void writeToFile(Map<String, Integer> reverseCompleteMap, File file) {
        try{
            FileOutputStream outputStream = new FileOutputStream(file, false);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write("words");
            bufferedWriter.write(",");
            bufferedWriter.write("count");
            bufferedWriter.newLine();

            for(Map.Entry<String, Integer> entry : reverseCompleteMap.entrySet()){

                bufferedWriter.write(entry.getKey());
                bufferedWriter.write(",");
                bufferedWriter.write(entry.getValue().toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
