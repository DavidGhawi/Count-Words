package com.cm6121.countWord.app;


import com.cm6121.countWord.app.utilityFile.Document;
import com.cm6121.countWord.app.utilityFile.FileReader;
import com.cm6121.countWord.app.utilityFile.SortMap;
import com.cm6121.countWord.app.utilityFile.WriterCSV;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException, CsvValidationException {
        String documentToRead = ClassLoader.getSystemClassLoader().getResource("FolderDocumentsToRead").getPath();
        System.out.println("The counting words application");
        System.out.println(documentToRead);
        FileReader fr = new FileReader();

        // Replace this path with your own folder path.
        String folderPath = "C:\\Users\\c21049134\\OneDrive - Cardiff University\\First year\\CM6121-Java\\Assessments\\Assessment_2\\count-words-21049134-2021\\src\\main\\resources\\FolderDocumentsToRead";
        File folder = new File(folderPath);

        File[] files = folder.listFiles();

        for (File file : files) {
            Document document = fr.readFile(file);

            HashMap<String, Integer> unsortedMap = fr.readAllWords(document.getText());

            fr.completeMap(unsortedMap);

            String filePath = WriterCSV.createDirectory();

            String order = "ASC";
            Map<String, Integer> mapASC = SortMap.sortMap(unsortedMap, order);

            String title = document.getTitle().replaceAll(" ", "_");
            Path path = Paths.get((filePath + "\\" + title + "_allWords.csv"));

            File createFile = new File(path.toString());

            WriterCSV.writeToFile(document, mapASC, createFile);
        }

        while (true) {
            Scanner option = new Scanner(System.in);

            System.out.println("\nFor names and number of documents; Enter 1.\n");
            System.out.println("For number of occurrences of the words for each document; Enter 2\n");
            System.out.println("For a word search in each document; Enter 3\n");
            System.out.println("For number of occurrences in the whole corpus; Enter 4\n");
            System.out.println("To exit the program; Enter 5\n");
            String response = option.nextLine();

            if (response.equals("1")) {
                int numberOfFiles = 0;

                for (File file : files) {
                    Document document = fr.readFile(file);
                    System.out.println("File name: " + file.getName() +
                            "\nTitle: " + document.getTitle() + "\nDate: " + document.getDateOfCreation() + "\n");
                    numberOfFiles++;
                }

                System.out.println("The total number of files in this folder is: " + numberOfFiles);

            } else if (response.equals("2")){
                for (File file : files) {
                    System.out.println(file);
                    Document document = fr.readFile(file);
                    String filePath = WriterCSV.createDirectory();
                    String title = document.getTitle().replaceAll(" ", "_");
                    Path path = Paths.get((filePath + "\\" + title + "_allWords.csv"));
                    fr.readMappedFiles(path.toString());
                }

            } else if (response.equals("3")) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter a word to see the number of occurrences in each file");
                String searchedWord = sc.nextLine();
                searchedWord = searchedWord.toLowerCase();
                for (File file : files) {
                    Document document = fr.readFile(file);
                    String filePath = WriterCSV.createDirectory();

                    String title = document.getTitle().replaceAll(" ", "_");
                    Path path = Paths.get((filePath + "\\" + title + "_allWords.csv"));
                    fr.wordSearch(path.toString(), searchedWord, file);
                }

            } else if (response.equals("4")) {
                String filePath = WriterCSV.createDirectory();

                Path path = Paths.get((filePath + "\\" + "CSVAllDocuments_allWords.csv"));
                fr.readMappedFiles(path.toString());

            } else if (response.equals("5")) {
                System.out.println("/////////Program ended/////////");
                break;

            } else {
                System.out.println("Incorrect input please input number 1-4 for you desired action.\nIf you wish to end the program enter 5");
            }
        }
    }
}
