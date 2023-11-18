/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.mycompany.footballmanager.Interfaces.Dados;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author afonso, milena, tânia
 */
public class Jogador extends Pessoa implements Dados {
    // BEGIN Variables ----------------------------------------------------------------
    private static int AI = 1; // Auto Increment
    private int id = 0;
    private String posicao;
    private String hist_lesoes;
    private double ataque; // de 0 a 100
    private double defesa; // de 0 a 100
    private int n_agressividade; // de 0 a 100
    // END Variables ----------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Jogador() {
        id = AI++;
        super.setNome("Zézinho");
        super.setIdade(random.nextInt(20, 40));
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
        super(nome, idade);
        this.id = AI++;
        this.posicao = posicao;
        this.hist_lesoes = hist_lesoes;
        this.ataque = ataque;
        this.defesa = defesa;
        this.n_agressividade = n_agressividade;
    }
    // END Constructors ----------------------------------------------------------------

    // BEGIN Interface Methods ----------------------------------------------------------------
    @Override
    public void insert() {
        // Simulated database as a list
        ArrayList<Jogador> jogadores = new ArrayList<>();

        // Assuming you want to insert 'this' Jogador object
        jogadores.add(insereJogador());

        // Printing the inserted player for demonstration
        System.out.println("Player inserted into the database! ");
//        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Jogador insereJogador() {
//        Jogador jogador = new Jogador();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Nome: ");
            String nome = scanner.nextLine();
            this.setNome(nome);

            System.out.println("Idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            this.setIdade(idade);

            System.out.println("Posição: ");
            String posicao = scanner.nextLine();
            this.setPosicao(posicao);

            System.out.println("Historico: ");
            String historico = scanner.nextLine();
            this.setHist_lesoes(historico);

            System.out.println("Ataque: ");
            double ataque = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character
            this.setAtaque(ataque);

            System.out.println("Defesa: ");
            double defesa = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character
            this.setDefesa(defesa);

            System.out.println("Agressividade: ");
            int n_agressividade = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            this.setN_agressividade(n_agressividade);

//            scanner.close(); // Closing Scanner
        } catch (Exception e) {
            System.out.println("Input inválido: " + e.getMessage());
            // Consider handling or logging the exception here
        }

        return this;
    }


    @Override
    public void print() {
        System.out.printf(this.toString());
    }

    @Override
    public void update() {
        //
    }

    @Override
    public void delete() {
        //
    }
    // END Interface Methods ----------------------------------------------------------------

    // BEGIN Setters ----------------------------------------------------------------
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

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public int getIdade() {
        return super.getIdade();
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
        return String.format("| %-3s | %-20s | %-7s | %-20s | %-30s | %-7s | %-7s | %-14d |%n",
                this.getId(), getNome(), getIdade(), getPosicao(), getHist_lesoes(), getAtaque(), getDefesa(), getN_agressividade());
    }
}
