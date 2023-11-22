/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import java.util.Random;

import static com.mycompany.footballmanager.Menu.randomFullName;

/**
 * @author afonso, milena, tânia
 */
public abstract class Pessoa {
    // BEGIN Variables ----------------------------------------------------------------
    private String nome;
    private int idade;
    protected Random random = new Random();

    // END Variables ----------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Pessoa() {
        nome = randomFullName();
        idade = random.nextInt(20, 40);
    }

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    // END Constructors ----------------------------------------------------------------

    // BEGIN Setters ----------------------------------------------------------------
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    // END Setters ----------------------------------------------------------------

    // BEGIN Getters ----------------------------------------------------------------
    public int getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }
    // END Getters ----------------------------------------------------------------
}
