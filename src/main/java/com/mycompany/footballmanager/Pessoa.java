/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

/**
 * @author afonso, milena, tânia
 */
public class Pessoa {
    // BEGIN Variables ----------------------------------------------------------------
    private int id = 0;
    private static int AI = 1; // Auto Increment
    private String nome;
    private int idade;
    // END Variables ----------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Pessoa() {
        id = AI++;
        nome = "Pessoa";
        idade = 20;
    }

    public Pessoa(String nome, int idade) {
        id = AI++;
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
    public int getId() {
        return id;
    }

    public int getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }
    // END Getters ----------------------------------------------------------------
}
