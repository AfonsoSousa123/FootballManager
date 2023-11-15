/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.mycompany.footballmanager.Interfaces.Dados;

import java.util.LinkedList;

/**
 * @author afonso, milena, tânia
 */
public class Treinador extends Pessoa implements Dados {
    private String especializacoes;
    private String taticas_fav;

    // BEGIN Constructors ----------------------------------------------------------------
    public Treinador() {
        super();
        especializacoes = "Estilo ofensivo";
        taticas_fav = "tatica defensiva";
    }

    public Treinador(
            String nome,
            int idade,
            String especializacoes,
            String taticas_fav
    ) {
        super(nome, idade);
        this.especializacoes = especializacoes;
        this.taticas_fav = taticas_fav;
    }
    // END Constructors ----------------------------------------------------------------

    // BEGIN Interface Methods ----------------------------------------------------------------
    @Override
    public void insert() {
        // Simulated database as a list
        LinkedList<Treinador> treinadores = new LinkedList<>();

        // Assuming you want to insert 'this' Treinador object
        treinadores.add(this);

        // Printing the inserted player for demonstration
        System.out.println("Treinador inserted into the database: " + this.toString());
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
    public void setEspecializacoes(String especializacoes) {
        this.especializacoes = especializacoes;
    }

    public void setTaticas_fav(String taticas_fav) {
        this.taticas_fav = taticas_fav;
    }

    // END Setters ----------------------------------------------------------------

    // BEGIN Getters ----------------------------------------------------------------
    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public int getIdade() {
        return super.getIdade();
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    public String getEspecializacoes() {
        return especializacoes;
    }

    public String getTaticas_fav() {
        return taticas_fav;
    }
    // END Getters ----------------------------------------------------------------

    // Print headers
    public String tableHeaders() {
        return String.format("| %-3s | %-20s | %-7s | %-20s | %-30s |%n",
                "ID", "Nome", "Idade", "Especializações", "Táticas Favoritas");
    }

    @Override
    public String toString() {

        return String.format("| %-3s | %-20s | %-7s | %-20s | %-30s |%n",
                getId(), getNome(), getIdade(), getEspecializacoes(), getTaticas_fav());
    }
}
