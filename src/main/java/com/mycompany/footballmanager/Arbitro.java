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
public class Arbitro extends Pessoa implements Dados {
    private String experiencia;

    // BEGIN Constructors ----------------------------------------------------------------
    public Arbitro() {
        super.setNome("Arbitro nome");
        super.setIdade(random.nextInt(20, 40));
        experiencia = "3 Anos";
    }

    public Arbitro(
            String nome,
            int idade,
            String experiencia
    ) {
        super(nome, idade);
        this.experiencia = experiencia;
    }
    // END Constructors ----------------------------------------------------------------

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
        System.out.printf(this.toString());
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

    // BEGIN Setters ----------------------------------------------------------------
    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
    // END Setters ----------------------------------------------------------------

    // BEGIN Getters ----------------------------------------------------------------
    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public int getIdade() {
        return super.getIdade();
    }

    public String getExperiencia() {
        return experiencia;
    }
    // END Getters ----------------------------------------------------------------
}
