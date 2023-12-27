/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.github.javafaker.Faker;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author afonso, milena, tânia
 */
public class Menu {
    // ArrayLists
    public static ArrayList<Jogador> jogadores = new ArrayList<>();
    public static ArrayList<Treinador> treinadores = new ArrayList<>();
    public static ArrayList<Arbitro> arbitros = new ArrayList<>();
    public static ArrayList<Equipa> equipas = new ArrayList<>();
    public static ArrayList<Partida> partidas = new ArrayList<>();
    public static ArrayList<Liga> ligas = new ArrayList<>();
    public static ArrayList<ArbitroPrincipal> arbitros_p;
    public static ArrayList<ArbitroAssistente> arbitros_a;
    public static ArrayList<EstatisticasEquipa> statsEquipas = new ArrayList<>();

    // Instances of the Objects
    public static Jogador jogador = new Jogador();
    public static Treinador treinador = new Treinador();
    public static Equipa equipa = new Equipa();
    public static Partida partida = new Partida();
    public static Liga liga = new Liga();
    public static Arbitro arbitro = new Arbitro();
    public static EstatisticasEquipa statsEquipa = new EstatisticasEquipa();

    // Scanner instances
    public Scanner scanner = new Scanner(System.in);

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

    public static boolean validarData(String dataStr) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate data = LocalDate.parse(dataStr, formato);
            return true; // Se a conversão foi bem-sucedida, a data é válida
        } catch (Exception e) {
            return false; // Se ocorreu uma exceção, a data é inválida
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

    public static boolean hasPontoEVirgulaString(String text) {
        return text.contains(";");
    }

    public static String randomName() {
        Faker fakerName = new Faker();
        return fakerName.name().name();
    }

    public static String randomYoda() {
        Faker fakerYoda = new Faker();
        return fakerYoda.yoda().quote();
    }

    public static String randomLorem() {
        Faker fakerLorem = new Faker();
        return fakerLorem.lorem().word();
    }

    public static String randomCity() {
        Faker fakerCity = new Faker();
        return fakerCity.address().cityName();
    }

    public static String randomCountry() {
        Faker fakerCountry = new Faker();
        return fakerCountry.address().country();
    }

    public static String randomTeam() {
        Faker fakerTeam = new Faker();
        return fakerTeam.team().sport();
    }

    public static String randomDate() {
        Faker fakerDate = new Faker();
        return fakerDate.date().toString();
    }

    private void printInvalidOptionError() {
        System.out.println("Opção inválida! Insira um valor válido.");
    }

    private void pressEnterToContinue() {
        System.out.println("Pressione ENTER para continuar...");
        try {
            System.in.read();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Input inválido, tente novamente" + e.getMessage());
        }
    }

    private void getData() {
        jogador.getJogadores();
        treinador.getTreinadores();
        arbitro.getArbitros();
        equipa.getEquipas();
        partida.getPartidas();
        liga.getLigas();
    }

    public void menu() {
        int option = 0;
        int optionInsert;
        int optionRemove;
        int optionInsertRandom;

        getData(); // gets all the data from the TXT Files

        do {
            System.out.println("|----------------------------------------|");
            System.out.println("| FOOTBALL MANAGER 23/24                 |");
            System.out.println("|----------------------------------------|");
            System.out.println("| MENU PRINCIPAL:                        |");
            System.out.println("| 1. Inserir Dados                       |");
            System.out.println("| 2. Ler Dados de Jogadores              |");
            System.out.println("| 3. Ler Dados de Treinadores            |");
            System.out.println("| 4. Ler Dados das Equipas               |");
            System.out.println("| 5. Ler Dados dos Arbitros              |");
            System.out.println("| 6. Ler Dados das Ligas                 |");
            System.out.println("| 7. Remover Dados                       |");
            System.out.println("| 8. Associar Equipa a uma Liga          |");
            System.out.println("| 9. Estatisticas das Equipas            |");
            System.out.println("| 10. Ver Partidas                       |");
            System.out.println("| 11. Criar e Simular Partida            |");
            System.out.println("| 12. Sair                               |");
            System.out.println("|----------------------------------------|");
            System.out.print("Escolha uma opção: ");

            try {
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("|------------------------------|");
                        System.out.println("| MENU INSERIR DADOS:          |");
                        System.out.println("| 1. Inserir Jogador           |");
                        System.out.println("| 2. Inserir Treinador         |");
                        System.out.println("| 3. Inserir Equipa            |");
                        System.out.println("| 4. Inserir Liga              |");
                        System.out.println("| 5. Inserir Random            |");
                        System.out.println("| 6. Voltar atrás              |");
                        System.out.println("|------------------------------|");
                        System.out.print("Escolha uma opção: ");

                        try {
                            optionInsert = scanner.nextInt();

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
                                    // Equipa
                                    equipa.insert();
                                    break;
                                case 4:
                                    // Liga
                                    liga.insert();
                                    break;
                                case 5:
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
                                                pressEnterToContinue();
                                                break;
                                            case 2:
                                                // Treinador
                                                treinador.insertFaker();
                                                pressEnterToContinue();
                                                break;
                                            case 3:
                                                // Arbitro
                                                arbitro.insertFaker();
                                                pressEnterToContinue();
                                                break;
                                            case 4:
                                                // Equipa
                                                equipa.insertFaker();
                                                pressEnterToContinue();
                                                break;
                                            case 5:
                                                // Liga
                                                liga.insertFaker();
                                                pressEnterToContinue();
                                                break;
                                            case 6:
                                                System.out.println("Voltando Atrás...");
                                                break;
                                            default:
                                                printInvalidOptionError();
                                                menu();
                                                break;
                                        }
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                        printInvalidOptionError();
                                        menu();
                                    }
                                    break;
                                case 6:
                                    System.out.println("Voltando Atrás...");
                                    break;
                                default:
                                    printInvalidOptionError();
                                    menu();
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            printInvalidOptionError();
                            menu();
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
                        equipa.print();
                        pressEnterToContinue();
                        break;
                    case 5:
                        // Print Árbitros
                        arbitro.print();
                        pressEnterToContinue();
                        break;
                    case 6:
                        // Print Árbitros
                        liga.print();
                        pressEnterToContinue();
                        break;
                    case 7:
                        // Remover Dados
                        System.out.println("|------------------------------|");
                        System.out.println("| MENU REMOVER:                |");
                        System.out.println("| 1. Remover Jogador           |");
                        System.out.println("| 2. Remover Treinador         |");
                        System.out.println("| 3. Remover Arbitro           |");
                        System.out.println("| 4. Remover Equipa            |");
                        System.out.println("| 5. Remover Liga              |");
                        System.out.println("| 6. Voltar atrás              |");
                        System.out.println("|------------------------------|");
                        System.out.print("Escolha uma opção: ");

                        try {
                            optionRemove = scanner.nextInt();

                            switch (optionRemove) {
                                case 1:
                                    // Jogador
                                    jogador.removeJogador();
                                    break;
                                case 2:
                                    // Treinador
                                    treinador.removeTreinador();
                                    break;
                                case 3:
                                    // Arbitro
                                    System.out.println("Nao implementado!");
                                    break;
                                case 4:
                                    // Equipa
                                    System.out.println("Nao implementado!");
                                    break;
                                case 5:
                                    // Liga
                                    System.out.println("Nao implementado!");
                                    break;
                                case 6:
                                    System.out.println("Voltando Atrás...");
                                    break;
                                default:
                                    printInvalidOptionError();
                                    menu();
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            printInvalidOptionError();
                        }
                        break;
                    case 8:
                        // Associar uma Equipa a uma Liga
                        liga.associarEquipa();
                        pressEnterToContinue();
                        break;
                    case 9:
                        // Estatisticas das Equipas
                        statsEquipa.print();
                        pressEnterToContinue();
                        break;
                    case 10:
                        // Ver Partida
                        partida.print();
                        pressEnterToContinue();
                        break;
                    case 11:
                        // Criar e Simular Partida
                        partida.insert();
                        break;
                    case 12:
                        System.out.println("Saindo do Programa...");
                        System.out.println("Yoda Quote: " + randomYoda()); // Prints quote fom Yoda

                        // Closes the scanner
                        scanner.close();
                        // Closes the Program
                        System.exit(0); // Sai do programa, com sucesso
                        break;
                    default:
                        printInvalidOptionError();
                        menu();
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                printInvalidOptionError();
//                scanner.nextLine(); // Consume invalid input
                menu();
            }
        } while (option > 0 && option < 12);
    }
}



