package com.cm6121.countWord.app.utilityFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FileReader {
    private Document document = null;

    public Document readFile (File file) throws IOException {
        String[] values;
        String line;

        BufferedReader br = new BufferedReader(new java.io.FileReader(file.getPath()));
        int counter = 0;

        while ((line = br.readLine()) != null) {
            if (counter == 0) {
                counter++;
                continue;
            }
            values = line.split(",");

            document = new Document();
            document.setTitle(values[0]);
            document.setText(line.substring(line.indexOf(','), line.lastIndexOf(',') + 1));
            document.setDateOfCreation(line.substring(line.lastIndexOf(',') + 1).trim());
        }
        return document;
    }

    public static void readMappedFiles(String path) throws IOException {
        File CSVFile = new File(String.valueOf(path));
        if (CSVFile.isFile()){
            BufferedReader CSVReader = new BufferedReader(new java.io.FileReader(path));
            String line;

            String specificFile = CSVFile.getAbsolutePath().substring(CSVFile.getAbsolutePath().lastIndexOf("\\") + 1);
            if (specificFile.equals("CSVAllDocuments_allWords.csv")) {
                System.out.println("Here are the number of words in the file: " + specificFile);
                int numberOfLines = 0;
                int counter = 0;
                while (numberOfLines < 21 && (line = CSVReader.readLine()) != null){
                    if (counter == 0){
                        counter ++;
                        continue;
                    }
                    String[] information = line.split(",");
                    System.out.println("Word: " + information[0] + ", occurrences: " + information[1]);

                    numberOfLines++;
                }
            } else {
                System.out.println("Here are the number of words in the file: " + specificFile);
                int counter = 0;
                while ((line = CSVReader.readLine()) != null){
                    if (counter == 0){
                        counter++;
                        continue;
                    }
                    String[] information = line.split(",");
                    System.out.println("Word: " + information[0] + ", occurrences: " + information[1]);
                }
                CSVReader.close();
            }
        }
    }

    public static void wordSearch(String path, String word, File file) throws IOException {
        File CSVFile = new File(String.valueOf(path));

        if(CSVFile.isFile()) {
            BufferedReader CSVReader = new BufferedReader(new java.io.FileReader(path));
            String line;
            int contains = 0;
            int linesInFile = 0;
            while ((line = CSVReader.readLine()) != null){
                linesInFile ++;
                String[] information = line.split(",");
                String column1 = information[0];
                String column2 = information[1];
                if (column1.equals(word)) {
                    System.out.println("The word: '" + word + "' appears: " + column2 + " times in the file: " + file.getName());
                } else {
                    contains ++;
                }
            }

            if (contains == linesInFile){
                System.out.println("Unfortunately, the word '" + word + "' Does not appear in the file: " + file.getName());
            }
        }
    }

    public HashMap<String, Integer> readAllWords(String wordsInFile) {
        HashMap<String, Integer> numberWords = new HashMap<>();
        wordsInFile = wordsInFile.replaceAll("’s", "");
        wordsInFile = wordsInFile.replaceAll("—", " ");
        String[] words = wordsInFile.split("\\s+");

        for (int i = 0; i < words.length; i++){

            words[i] = words[i].replaceAll("[^\\w]", "");

            words[i] = words[i].toLowerCase();

            if (numberWords.containsKey(words[i]) && words[i].length() > 1){
                int count = numberWords.get(words[i]) + 1;
                numberWords.put(words[i], count);
            } else if (words[i].length() > 1){
                numberWords.put(words[i], 1);
            }
        }
        return numberWords;
    }

    static HashMap<String, Integer> completeMap = new HashMap<>();
    public static HashMap<String, Integer> completeMap(HashMap<String, Integer> map) {
        map.forEach((k, v) -> completeMap.merge(k, v, (v1, v2) -> v1 + v2));

        LinkedHashMap<String, Integer> reverseCompleteMap = (LinkedHashMap<String, Integer>) SortMap.sortMap(completeMap, "DSC");

        String filePath = WriterCSV.createDirectory();

        Path path = Paths.get((filePath + "\\" + "CSVAllDocuments" + "_allWords.csv"));
        File file = new File(path.toString());
        WriterCSV.writeToFile(reverseCompleteMap, file);
        return reverseCompleteMap;
    }
}
