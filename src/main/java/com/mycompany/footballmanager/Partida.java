/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

/**
 * @author afonso, milena, tânia
 */
public class Partida {
    private static int AI = 1; // Auto Increment
    private int id;
    private String nome; // O nome vai ser a junção dos nomes das equipas que estão jogando
    private Arbitro arbitro;
    private Equipa equipa;
    private Equipa adversario;
    private String data; // quando formos inserir usamos um objeto de data para formatar a String para a data pretendida
    private int resultado; // por exemplo: 4:3
    private String local;
    private int golos_marcados;
    private int golos_sofridos;
}
