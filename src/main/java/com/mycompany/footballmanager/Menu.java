/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.mycompany.footballmanager.DB.DB;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author afonso, milena, tânia
 */
public class Menu {
    LinkedList<Jogador> jogadores = new LinkedList<>();
    Scanner scanner = new Scanner(System.in);

    public void menu() {
        int option, optionInsert;

        boolean sair = false;
        do {
            System.out.println("| Menu:");
            System.out.println("| 1. Inserir Dados");
            System.out.println("| 2. Ler Dados de Jogadores");
            System.out.println("| 3. Ler Dados de Treinadores");
            System.out.println("| 4. Ler Dados das Equipas");
            System.out.println("| 5. Associar Equipa a uma Liga");
            System.out.println("| 6. Estatísticas de uma Equipa");
            System.out.println("| 7. Ver Partidas");
            System.out.println("| 8. Criar Partida");
            System.out.println("| 9. Sair");
            System.out.println("---------------------------------------");
            System.out.print("Escolha uma opção: ");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("| MENU INSERIR DADOS:");
                    System.out.println("| 1. Inserir Jogador");
                    System.out.println("| 2. Inserir Arbitro");
                    System.out.println("| 3. Inserir Treinador");
                    System.out.println("| 4. Inserir Equipa");
                    System.out.println("| 5. Inserir Liga");
                    System.out.println("| 9. Voltar atrás");
                    System.out.println("---------------------------------------");
                    System.out.print("Escolha uma opção: ");

                    optionInsert = scanner.nextInt();

                    // Insert a data
                    switch (optionInsert) {
                        case 1:
                            // Jogador
                            break;
                        case 2:
                            // Arbitro
                            break;
                        case 3:
                            // Treinador
                            break;
                        case 4:
                            // Equipa
                            break;
                        case 5:
                            // Liga
                            break;
                        case 6:
                            break;
                        default:
                            System.out.println("Opção inválida. Insira um valor válido.");
                            break;
                    }

                    break;
                case 2:
                    // View Jogadores
                    // Instantiate jogador objects and add them to the list
                    Jogador jogador1 = new Jogador();
                    Jogador jogador2 = new Jogador("Cristiano Ronaldo", 34, "Avançado", "costela partida", 50, 45, 7);

                    jogadores.add(jogador1);
                    jogadores.add(jogador2);

                    // Print details of all players using a loop
                    System.out.printf(jogador1.tableHeaders());
                    for (Jogador jogador : jogadores) {
                        System.out.printf(jogador.toString());
                    }
                    DB.connection();
                    break;
                case 3:
                    // View Treinadores
                    break;
                case 4:
                    // View Equipas
                    break;
                case 5:
                    // Associar uma Equipa a uma Liga
                    break;
                case 6:
                    // Estatisticas de uma Equipa
                    break;
                case 7:
                    // Ver Partidas
                    break;
                case 8:
                    // Criar Partida
                    break;
                case 9:
                    System.out.println("Saindo do Programa!");
                    // Closes the scanner
                    scanner.close();
                    // Closes the Program
                    System.exit(1);
                    break;
                default:
                    System.out.println("Opção inválida. Insira um valor válido.");
                    break;
            }
        } while (option > 0 && option < 10);
    }
}



