/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import java.util.Random;

/**
 * @author afonso, milena, tânia
 */
public class EstatisticasEquipa {
    // BEGIN Variables ------------------------------------------------------------------
    private int id;
    private int desempenhoMedio;
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
        desempenhoMedio = random.nextInt(1, 100);
        numVitorias = random.nextInt(1, 100);
        numDerrotas = random.nextInt(1, 100);
        numEmpates = random.nextInt(1, 100);
    }

    public EstatisticasEquipa(int id, int desempenhoMedio, int numVitorias, int numDerrotas, int numEmpates, int golosMarcados, int golosSofridos) {
        this.id = id;
        this.desempenhoMedio = desempenhoMedio;
        this.numVitorias = numVitorias;
        this.numDerrotas = numDerrotas;
        this.numEmpates = numEmpates;
        this.golosMarcados = golosMarcados;
        this.golosSofridos = golosSofridos;
    }
    // END Constructors ----------------------------------------------------------------

    // BEGIN Getters and Setters ----------------------------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDesempenhoMedio() {
        return desempenhoMedio;
    }

    public void setDesempenhoMedio(int desempenhoMedio) {
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
