/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import java.util.LinkedList;

/**
 * @author afonso, milena, tânia
 */
public class Equipa {
    // BEGIN Variables ----------------------------------------------------------------
    private static int AI = 1; // Auto Increment
    private int id;
    private String nome;
    private LinkedList<Jogador> jogadores;
    private Treinador treinador;
    private Liga liga;
    private String cidade;
    private String pais;
    private String historico;
    private int golos_marcados;
    private int golos_sofridos;
    // END Variables ------------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Equipa() {
        id = AI++;
        nome = "";
        cidade = "";
        pais = "";
        historico = "";
    }

    public Equipa(
            String nome,
            LinkedList<Jogador> jogadores,
            Treinador treinador,
            Liga liga,
            String cidade,
            String pais,
            String historico
    ) {
        this.id = AI++;
        this.nome = nome;
        this.cidade = cidade;
        this.pais = pais;
        this.historico = historico;
    }
    // END Constructors ----------------------------------------------------------------
}
