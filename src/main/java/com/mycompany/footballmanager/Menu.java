/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author afonso, milena, tânia
 */
public class Menu {
    LinkedList<Jogador> jogadores = new LinkedList<>();
    LinkedList<Arbitro> arbitros = new LinkedList<>();
    LinkedList<Treinador> treinadores = new LinkedList<>();
    Scanner scanner = new Scanner(System.in);

    public void menu() {
        int option = 0;
        int optionInsert;

        boolean validInput = false;

//        boolean sair = false;
        do {
            System.out.println("|--------------------------------------|");
            System.out.println("| FOOTBALL MANAGER 23/24:              |");
            System.out.println("|--------------------------------------|");
            System.out.println("| MENU PRINCIPAL:                      |");
            System.out.println("| 1. Inserir Dados                     |");
            System.out.println("| 2. Ler Dados de Jogadores            |");
            System.out.println("| 3. Ler Dados de Treinadores          |");
            System.out.println("| 4. Ler Dados das Equipas             |");
            System.out.println("| 5. Associar Equipa a uma Liga        |");
            System.out.println("| 6. Estatísticas de uma Equipa        |");
            System.out.println("| 7. Ver Partidas                      |");
            System.out.println("| 8. Criar Partida                     |");
            System.out.println("| 9. Sair                              |");
            System.out.println("|--------------------------------------|");
            System.out.print("Escolha uma opção: ");

            try {
                option = scanner.nextInt();
                validInput = true;

                switch (option) {
                    case 1:
                        System.out.println("|------------------------------|");
                        System.out.println("| MENU INSERIR DADOS:          |");
                        System.out.println("| 1. Inserir Jogador           |");
                        System.out.println("| 2. Inserir Arbitro           |");
                        System.out.println("| 3. Inserir Treinador         |");
                        System.out.println("| 4. Inserir Equipa            |");
                        System.out.println("| 5. Inserir Liga              |");
                        System.out.println("| 6. Voltar atrás              |");
                        System.out.println("-------------------------------|");
                        System.out.print("Escolha uma opção: ");

                        try {
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
                                    System.out.println("Voltando Atrás...");
                                    break;
                                default:
                                    printInvalidOptionError();
                                    validInput = false;
                                    break;
                            }
                        } catch (Exception e) {
                            printInvalidOptionError();
                        }

                        break;
                    case 2:
                        // Print Jogadores
                        printJogador();
                        pressEnterToContinue();
                        break;
                    case 3:
                        // Print Treinadores
                        printTreinador();
                        pressEnterToContinue();
                        break;
                    case 4:
                        // Print Equipas
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
                        printArbitro();
                        pressEnterToContinue();
                        break;
                    case 9:
                        System.out.println("Saindo do Programa...");
                        // Closes the scanner
                        scanner.close();
                        // Closes the Program
                        System.exit(1);
                        break;
                    default:
                        printInvalidOptionError();
                        break;
                }
            } catch (Exception e) {
                printInvalidOptionError();
                scanner.nextLine(); // Consume invalid input
                validInput = false; // Set flag to false for invalid input
            }
        } while (option > 0 && option < 10);
    }

    private void printInvalidOptionError() {
        System.out.println("Opção inválida! Insira um valor válido.");
    }

    public void printJogador() {
        // Instantiate jogador objects and add them to the list
        Jogador jogador1 = new Jogador();
        Jogador jogador2 = new Jogador("Cristiano Ronaldo", 34, "Avançado", "costela partida", 50, 45, 7);

        jogadores.add(jogador1);
        jogadores.add(jogador2);

        // Print the table Headers
        System.out.printf(jogador1.tableHeaders());
        // Print details of all players using a loop
        for (Jogador jogador : jogadores) {
            jogador.print();
        }
    }

    public void printArbitro() {
        // Instantiate arbitro objects and add them to the list
        Arbitro arbitro1 = new Arbitro();
        Arbitro arbitro2 = new Arbitro("Ricardo Sousa", 43, "5 anos");

        arbitros.add(arbitro1);
        arbitros.add(arbitro2);

        // Print the table Headers
        System.out.printf(arbitro1.tableHeaders());
        // Print details of all players using a loop
        for (Arbitro arbitro : arbitros) {
            arbitro.print();
        }
    }

    public void printTreinador() {
        // Instantiate jogador objects and add them to the list
        Treinador treinador1 = new Treinador();
        Treinador treinador2 = new Treinador("José Mourinho", 34, "something", "i dont know, ask him...");

        treinadores.add(treinador1);
        treinadores.add(treinador2);

        // Print the table Headers
        System.out.printf(treinador1.tableHeaders());
        // Print details of all players using a loop
        for (Treinador treinador : treinadores) {
            treinador.print();
        }
    }

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            System.out.println("Error clearing console" + e.getMessage());
        }
    }

    private void pressEnterToContinue() {
        System.out.println("Pressione ENTER para continuar...");
        try {
            System.in.read();
            scanner.nextLine();
            clearConsole();
        } catch (Exception e) {
            System.out.println("Input inválido, tente novamente" + e.getMessage());
        }
    }
}



