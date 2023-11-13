/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.footballmanager;

/**
 * @author afonso, milena, tânia
 */
public class FootballManager {

    public static void main(String[] args) {
        // Instanciamos todos os Objetos e chamamos os Métotos

        // Create a list to hold the players
//        ArrayList<Jogador> jogadores = new ArrayList<>();
//
//        // Instantiate jogador objects and add them to the list
//        Jogador jogador1 = new Jogador();
//        Jogador jogador2 = new Jogador("Cristiano Ronaldo", 34, "Avançado", "costela partida", 50, 45, 7);
//
//        jogadores.add(jogador1);
//        jogadores.add(jogador2);
//
//        // Print details of all players using a loop
//        System.out.printf(jogador1.tableHeaders());
//        for (Jogador jogador : jogadores) {
//            System.out.printf(jogador.toString());
//        }


        // Calls the Menu method
        Menu m = new Menu();
        m.menu();
    }
}
