/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager.DB;

import java.io.*;
import java.util.ArrayList;

/**
 * @author afonso, milena, tânia
 */
public class DB {
    public static void fileReader() {
        // Variables
        String path = "./src/main/java/com/mycompany/footballmanager/DB/database.csv";
        String row = "";

        try {
            File file = new File(path);

            // Check if the file exists; if not, creates it
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Ficheiro criado com sucesso!");
                } else {
                    System.out.println("Falha ao criar o ficheiro!");
                    return; // Exit the method if file creation fails
                }
            }

            BufferedReader reader = new BufferedReader(new FileReader(path));
            ArrayList<String> rows = new ArrayList<>();

            // Reading the file and storing rows in a ArrayList
            while ((row = reader.readLine()) != null) {
                rows.add(row);
            }

            reader.close(); // Close the reader after reading

            // Displaying the results
            for (String storedLine : rows) {
                System.out.println(storedLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
