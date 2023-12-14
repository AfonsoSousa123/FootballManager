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

}
