/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.github.javafaker.Faker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author afonso, milena, tânia
 */
public class Menu {
    public static ArrayList<Jogador> jogadores = new ArrayList<>();
    public static ArrayList<Arbitro> arbitros = new ArrayList<>();
    public static ArrayList<Treinador> treinadores = new ArrayList<>();
    public Scanner scanner = new Scanner(System.in);
    public static Jogador jogador = new Jogador();
    public static Treinador treinador = new Treinador();

    public void menu() {
        int option = 0;
        int optionInsert;
        int optionInsertRandom;

        getData(); // gets all the data from the CSV Files

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
            System.out.println("| 6. Ler Dados dos Árbitros            |");
            System.out.println("| 6. Associar Equipa a uma Liga        |");
            System.out.println("| 7. Estatísticas de uma Equipa        |");
            System.out.println("| 8. Ver Partidas                      |");
            System.out.println("| 9. Criar Partida                     |");
            System.out.println("| 10. Sair                             |");
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
                        System.out.println("| 2. Inserir Treinador         |");
                        System.out.println("| 3. Inserir Arbitro           |");
                        System.out.println("| 4. Inserir Equipa            |");
                        System.out.println("| 5. Inserir Liga              |");
                        System.out.println("| 6. Inserir Random            |");
                        System.out.println("| 7. Voltar atrás              |");
                        System.out.println("|------------------------------|");
                        System.out.print("Escolha uma opção: ");

                        try {
                            optionInsert = scanner.nextInt();

                            // Insert a data
                            switch (optionInsert) {
                                case 1:
                                    // Jogador
                                    jogador.insert();
                                    break;
                                case 2:
                                    // Treinador
                                    treinador.insert();
                                    break;
                                case 3:
                                    // Arbitro
                                    break;
                                case 4:
                                    // Equipa
                                    break;
                                case 5:
                                    // Liga
                                    break;
                                case 6:
                                    // Random Generators
                                    System.out.println("|------------------------------|");
                                    System.out.println("| MENU INSERIR RANDOM:         |");
                                    System.out.println("| 1. Inserir Jogador           |");
                                    System.out.println("| 2. Inserir Treinador         |");
                                    System.out.println("| 3. Inserir Arbitro           |");
                                    System.out.println("| 4. Inserir Equipa            |");
                                    System.out.println("| 5. Inserir Liga              |");
                                    System.out.println("| 6. Voltar atrás              |");
                                    System.out.println("|------------------------------|");
                                    System.out.print("Escolha uma opção: ");

                                    try {
                                        optionInsertRandom = scanner.nextInt();

                                        // Insert a data
                                        switch (optionInsertRandom) {
                                            case 1:
                                                // Jogador
                                                jogador.insertFaker();
                                                break;
                                            case 2:
                                                // Treinador
                                                break;
                                            case 3:
                                                // Arbitro
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
                                case 7:
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
                        jogador.print();
                        pressEnterToContinue();
                        break;
                    case 3:
                        // Print Treinadores
                        treinador.print();
                        pressEnterToContinue();
                        break;
                    case 4:
                        // Print Equipas
                        pressEnterToContinue();
                        break;
                    case 5:
                        // Print Árbitros
                        pressEnterToContinue();
                        break;
                    case 6:
                        // Associar uma Equipa a uma Liga
                        break;
                    case 7:
                        // Estatisticas de uma Equipa
                        break;
                    case 8:
                        // Ver Partidas
                        break;
                    case 9:
                        // Criar Partida
                        printArbitro();
                        pressEnterToContinue();
                        break;
                    case 10:
                        System.out.println("Saindo do Programa...");
                        System.out.println(randomChuckNoris()); // Prints a fun fact about ChuckNoris

                        // Closes the scanner
                        scanner.close();
                        // Closes the Program
                        System.exit(0); // Sai do programa, com sucesso
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

    public void printArbitro() {
        // Instantiate arbitro objects and add them to the list
        Arbitro arbitro1 = new Arbitro();
        Arbitro arbitro2 = new Arbitro("Ricardo Sousa", 43, "5 anos");

        arbitros.add(arbitro1);
        arbitros.add(arbitro2);

        // Print the table Headers
//        System.out.printf(arbitro1.tableHeaders());
        // Print details of all players using a loop
        for (Arbitro arbitro : arbitros) {
            arbitro.print();
        }
    }

    // Checks if the file exists; if not, creates it
    public static void checkIfFileExists(String filepath) {
        File file = new File(filepath);

        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Ficheiro criado com sucesso!");
                } else {
                    System.out.println("Falha ao criar o ficheiro!");
                }
            }
        } catch (IOException e) {
            System.out.println("Ficheiro não foi encontrado" + e.getMessage());
        }
    }


    private static void clearConsole() {
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
//            clearConsole();
        } catch (Exception e) {
            System.out.println("Input inválido, tente novamente" + e.getMessage());
        }
    }

    private void getData() {
        jogador.getJogadores();
        treinador.getTreinadores();
    }

    public static boolean hasPontoEVirgulaString(String text) {
        return text.contains(";");
    }

    public static String randomNameJogador() {
        Faker fakerNameJogador = new Faker();
        return fakerNameJogador.esports().player();
    }

    public static String randomFullName() {
        Faker fakerFullName = new Faker();
        return fakerFullName.name().fullName();
    }

    public static String randomFirstName() {
        Faker fakerFirstName = new Faker();
        return fakerFirstName.name().firstName();
    }

    public static String randomLastName() {
        Faker fakerLastName = new Faker();
        return fakerLastName.name().lastName();
    }

    public static String randomChuckNoris() {
        Faker fakerChuckNoris = new Faker();
        return fakerChuckNoris.chuckNorris().fact();
    }

    public static String randomLorem() {
        Faker fakerLorem = new Faker();
        return fakerLorem.lorem().word();
    }
}



