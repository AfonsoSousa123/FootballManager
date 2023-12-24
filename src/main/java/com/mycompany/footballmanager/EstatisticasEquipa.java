/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static com.mycompany.footballmanager.Menu.*;
import static com.mycompany.footballmanager.Menu.statsEquipas;

/**
 * @author afonso, milena, tânia
 */
public class EstatisticasEquipa {
    // BEGIN Variables ------------------------------------------------------------------
    private int id;
    private double desempenhoMedio;
    private int numVitorias;
    private int numDerrotas;
    private int numEmpates;
    private int golosMarcados;
    private int golosSofridos;

    private Random random = new Random();
    private final String txtFilePath = "./src/main/java/com/mycompany/footballmanager/DB/equipaStats.txt"; // File Path
    // END Variables ------------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public EstatisticasEquipa() {
        desempenhoMedio = random.nextDouble(0, 100);
        numVitorias = random.nextInt(1, 100);
        numDerrotas = random.nextInt(1, 100);
        numEmpates = random.nextInt(1, 100);
        golosMarcados = random.nextInt(1, 500);
        golosSofridos = random.nextInt(1, 500);
    }

    public EstatisticasEquipa(int id, double desempenhoMedio, int numVitorias, int numDerrotas, int numEmpates, int golosMarcados, int golosSofridos) {
        this.id = id;
        this.desempenhoMedio = desempenhoMedio;
        this.numVitorias = numVitorias;
        this.numDerrotas = numDerrotas;
        this.numEmpates = numEmpates;
        this.golosMarcados = golosMarcados;
        this.golosSofridos = golosSofridos;
    }
    // END Constructors ----------------------------------------------------------------

    // Method to write Equipa data to a TXT file
    private void writeToTXT(EstatisticasEquipa stats) {
        checkIfFileExists(txtFilePath);

        try (FileWriter writer = new FileWriter(txtFilePath, true)) {
            BufferedWriter bw = new BufferedWriter(writer);
            StringBuilder sb = new StringBuilder();

            // Construct the TXT line
            sb.append(stats.getId()).append(";"); // get ID
            sb.append(stats.getDesempenhoMedio()).append(";"); // get Desempenho Medio
            sb.append(stats.getNumVitorias()).append(";"); // get Numero de Vitorias
            sb.append(stats.getNumDerrotas()).append(";"); // get Numero de Derrotas
            sb.append(stats.getNumEmpates()).append(";"); // get Numero de Empates
            sb.append(stats.getGolosMarcados()).append(";"); // get Golos Marcados
            sb.append(stats.getGolosMarcados()).append(";"); // get Golos Sofridos

            // Write the line to the file
            bw.append(sb.toString());
            // closes the output stream
            bw.close();

            System.out.println("Estatisticas da Equipa inserida com Sucesso!!!");
        } catch (IOException e) {
            System.out.println("Erro ao inserir Equipa no ficheiro equipaStats.txt: " + e.getMessage());
        }
    }

    public void print() {
        getStatsEquipas();
        // Print the table Headers
        System.out.printf(tableHeaders());

        // Print details of all players using a loop
        if (!statsEquipas.isEmpty()) {
            for (EstatisticasEquipa stats : statsEquipas) {
                System.out.printf(stats.toString());
            }
        } else {
            System.out.println("\nNão existem Estatisticas da Equipa!\n");
        }
    }

    public void getStatsEquipas() {
        checkIfFileExists(txtFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) {
            boolean firstLine = true; // Flag to identify the first line
            ArrayList<EstatisticasEquipa> stats = new ArrayList<>(); // Create a new list for stats
            String row;

            while ((row = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Set the flag to false after encountering the first line
                    continue; // Skip processing the first line
                }

                String[] data = row.split(";");

                // TXT format: ID, Desempenho Medio, Numero Vitorias, Numero Derrotas, Numero Empates, Golos Marcados, Golos Sofridos
                EstatisticasEquipa equipa = new EstatisticasEquipa();
                equipa.setId(Integer.parseInt(data[0])); // ID
                equipa.setDesempenhoMedio(Integer.parseInt(data[1])); // Desempenho Medio
                equipa.setNumVitorias(Integer.parseInt(data[2])); // Numero Vitorias
                equipa.setNumDerrotas(Integer.parseInt(data[3])); // Numero Derrotas
                equipa.setNumEmpates(Integer.parseInt(data[4])); // Numero Empates
                equipa.setGolosMarcados(Integer.parseInt(data[5])); // Golos Marcados
                equipa.setGolosSofridos(Integer.parseInt(data[6])); // Golos Sofridos

                // Adds the equipa to the ArrayList
                stats.add(equipa);
            }
            br.close();

            // Replaces the ArrayList from Menu class with the new ArrayList
            Menu.statsEquipas = stats;
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro equipaStats.txt: " + e.getMessage());
        }
    }

    // BEGIN Getters and Setters ----------------------------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDesempenhoMedio() {
        return desempenhoMedio;
    }

    public void setDesempenhoMedio(double desempenhoMedio) {
        this.desempenhoMedio = desempenhoMedio;
    }

    public int getNumVitorias() {
        return numVitorias;
    }

    public void setNumVitorias(int numVitorias) {
        this.numVitorias = numVitorias;
    }

    public int getNumDerrotas() {
        return numDerrotas;
    }

    public void setNumDerrotas(int numDerrotas) {
        this.numDerrotas = numDerrotas;
    }

    public int getNumEmpates() {
        return numEmpates;
    }

    public void setNumEmpates(int numEmpates) {
        this.numEmpates = numEmpates;
    }

    public int getGolosMarcados() {
        return golosMarcados;
    }

    public void setGolosMarcados(int golosMarcados) {
        this.golosMarcados = golosMarcados;
    }

    public int getGolosSofridos() {
        return golosSofridos;
    }

    public void setGolosSofridos(int golosSofridos) {
        this.golosSofridos = golosSofridos;
    }
    // END Getters and Setters ----------------------------------------------------------------

    // BEGIN toString Methods ----------------------------------------------------------------
    // Print headers
    public static String tableHeaders() {
        System.out.println("|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- ESTATISTICAS DA EQUIPA ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        return String.format("| %-3s | %-25s | %-20s | %-25s | %-20s | %-25s |%n",
            "Desempenho Medio", "Vitorias", "Empates", "Derrotas", "Golos Marcados", "Golos Sofridos");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-25s | %-20s | %-25s | %-20s | %-25s |%n",
            getDesempenhoMedio(),
            getNumVitorias(),
            getNumEmpates(),
            getNumDerrotas(),
            getGolosMarcados(),
            getGolosSofridos()
        );
    }
    // END toString Methods ----------------------------------------------------------------
}
