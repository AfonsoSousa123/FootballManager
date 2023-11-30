/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.mycompany.footballmanager.Interfaces.Dados;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import static com.mycompany.footballmanager.Menu.arbitros;
import static com.mycompany.footballmanager.Menu.checkIfFileExists;

/**
 * @author afonso, milena, tânia
 */
public class Arbitro extends Pessoa implements Dados {
    private final String txtFilePath = "./src/main/java/com/mycompany/footballmanager/DB/arbitros.txt"; // File Path

    private int id;
    private String experiencia;

    // BEGIN Constructors ----------------------------------------------------------------
    public Arbitro() {

        super.setNome("Arbitro nome");
        super.setIdade(random.nextInt(20, 40));
        experiencia = "3 Anos";
    }

    public Arbitro(
            int id,
            String nome,
            int idade,
            String experiencia
    ) {
        super(nome, idade);
        this.id = id;
        this.experiencia = experiencia;
    }
    // END Constructors ----------------------------------------------------------------

    public void printArbitros() {
        checkIfFileExists(txtFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) {
            String row;
            boolean firstLine = true; // Flag to identify the first line
            ArrayList<Arbitro> arbitros = new ArrayList<>(); // Create a new list for arbitros


            while ((row = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Set the flag to false after encountering the first line
                    continue; // Skip processing the first line
                }

                String[] data = row.split(";");

                // CSV format: ID, Nome, Idade, Experiencia
                Arbitro arbitro = new Arbitro();
                arbitro.setId(Integer.parseInt(data[0]));
                arbitro.setNome(data[1]);
                arbitro.setIdade(Integer.parseInt(data[2]));
                arbitro.setExperiencia(data[3]);

                // Adds the arbitro to the ArrayList
                arbitros.add(arbitro);
            }

            // Replaces the ArrayList from Menu class with the new ArrayList
            Menu.arbitros = arbitros;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // BEGIN Interface Methods ----------------------------------------------------------------
    @Override
    public void insert() {
        // Simulated database as a list
        LinkedList<Arbitro> arbitros = new LinkedList<>();

        // Assuming you want to insert 'this' Arbitro object
        arbitros.add(this);

        // Printing the inserted player for demonstration
        System.out.println("Player inserted into the database: " + this.toString());
    }

    @Override
    public void print() {
        printArbitros();
        // Print the table Headers
        System.out.printf(tableHeaders());
        
        // Print details of all Arbitros
        if (!Menu.arbitros.isEmpty()) {

            for (Arbitro arbitro : arbitros) {
                System.out.printf(arbitro.toString());
            }
        } else {
            System.out.println("\nNão existem Arbitros!\n");
        }

    }

    @Override
    public void update(int id) {
        //
    }

    @Override
    public void delete(int id) {
        //
    }

    @Override
    public void insertFaker() {

    }

    // END Interface Methods ----------------------------------------------------------------
    // BEGIN Getters ----------------------------------------------------------------
    @Override
    public String getNome() {
        return super.getNome();
    }

    private int getId() {
        return id;
    }

    @Override
    public int getIdade() {
        return super.getIdade();
    }

    public String getExperiencia() {
        return experiencia;
    }
    // END Getters ----------------------------------------------------------------
    // BEGIN Setters ----------------------------------------------------------------

    private void setId(int id) {
        this.id = id;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }


    // END Setters ----------------------------------------------------------------


    public static String tableHeaders() {
        return String.format("| %-3s | %-25s | %-7s | %-20s |%n",
                "ID", "Nome", "Idade", "Experiência");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-25s | %-7s | %-20s |%n",
                getId(), getNome(), getIdade(), getExperiencia());
    }
}



