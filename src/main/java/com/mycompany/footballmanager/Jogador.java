/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.mycompany.footballmanager.Interfaces.InsertsRecords;

import java.util.ArrayList;

/**
 * @author afonso, milena, tânia
 */
public class Jogador implements InsertsRecords {
    // BEGIN Variables ----------------------------------------------------------------
    private int id = 0;
    private static int AI = 1; // Auto Increment
    private String nome;
    private int idade;
    private String posicao;
    private String hist_lesoes;
    private double ataque;
    private double defesa;
    private int n_agressividade;
    // END Variables ----------------------------------------------------------------

    // BEGIN Getters ----------------------------------------------------------------
    public Jogador() {
        id = AI++;
        nome = "Zézinho";
        idade = 20;
        posicao = "central";
        hist_lesoes = "perna partida, ";
        ataque = 10;
        defesa = 15;
        n_agressividade = 5;
    }

    public Jogador(
            String nome,
            int idade,
            String posicao,
            String hist_lesoes,
            double ataque,
            double defesa,
            int n_agressividade
    ) {
        this.id = AI++;
        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
        this.hist_lesoes = hist_lesoes;
        this.ataque = ataque;
        this.defesa = defesa;
        this.n_agressividade = n_agressividade;
    }
    // END Getters ----------------------------------------------------------------

    // BEGIN Methods ----------------------------------------------------------------
    @Override
    public void InsertJogador() {
        // Simulated database as a list
        ArrayList<Jogador> jogadores = new ArrayList<>();

        // Assuming you want to insert 'this' Jogador object
        jogadores.add(this);

        // Printing the inserted player for demonstration
        System.out.println("Player inserted into the database: " + this.toString());
    }
    // END Methods ----------------------------------------------------------------

    // BEGIN Setters ----------------------------------------------------------------
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public void setHist_lesoes(String hist_lesoes) {
        this.hist_lesoes = hist_lesoes;
    }

    public void setAtaque(double ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(double defesa) {
        this.defesa = defesa;
    }

    public void setN_agressividade(int n_agressividade) {
        this.n_agressividade = n_agressividade;
    }
    // END Setters ----------------------------------------------------------------

    // BEGIN Getters ----------------------------------------------------------------
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getPosicao() {
        return posicao;
    }

    public String getHist_lesoes() {
        return hist_lesoes;
    }

    public double getAtaque() {
        return ataque;
    }

    public double getDefesa() {
        return defesa;
    }

    public int getN_agressividade() {
        return n_agressividade;
    }
    // END Getters ----------------------------------------------------------------

    // Print headers
    public String tableHeaders() {
        return String.format("| %-3s | %-20s | %-7s | %-20s | %-30s | %-7s | %-7s | %-14s |%n",
                "ID", "Nome", "Idade", "Posição", "Histórico de Lesões", "Ataque", "Defesa", "Nível de Agressividade");
    }

    @Override
    public String toString() {
        String dataRow = String.format("| %-3s | %-20s | %-7s | %-20s | %-30s | %-7s | %-7s | %-14d |%n",
                getId(), getNome(), getIdade(), getPosicao(), getHist_lesoes(), getAtaque(), getDefesa(), getN_agressividade());

        return dataRow;
    }
}
