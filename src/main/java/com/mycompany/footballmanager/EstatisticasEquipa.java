/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import java.util.ArrayList;

/**
 * @author afonso, milena, tânia
 */
public class EstatisticasEquipa extends Equipa {
    private int desempenhoMedio;
    private int numVitorias;
    private int numDerrotas;
    private int numEmpates;

    public EstatisticasEquipa() {
        desempenhoMedio = random.nextInt(1, 100);
        numVitorias = random.nextInt(1, 100);
        numDerrotas = random.nextInt(1, 100);
        numEmpates = random.nextInt(1, 100);
    }

    public EstatisticasEquipa(int desempenhoMedio, int numVitorias, int numDerrotas, int numEmpates) {
        this.desempenhoMedio = desempenhoMedio;
        this.numVitorias = numVitorias;
        this.numDerrotas = numDerrotas;
        this.numEmpates = numEmpates;
    }

    public EstatisticasEquipa(int id, String nome, ArrayList<Integer> plantel, int idTreinador, int idLiga, String cidade, String pais, String historico, int golos_marcados, int golos_sofridos, int desempenhoMedio, int numVitorias, int numDerrotas, int numEmpates) {
        super(id, nome, plantel, idTreinador, idLiga, cidade, pais, historico, golos_marcados, golos_sofridos);
        this.desempenhoMedio = desempenhoMedio;
        this.numVitorias = numVitorias;
        this.numDerrotas = numDerrotas;
        this.numEmpates = numEmpates;
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
                getGolos_marcados(),
                getGolos_sofridos()
        );
    }
    // END toString Methods ----------------------------------------------------------------
}
