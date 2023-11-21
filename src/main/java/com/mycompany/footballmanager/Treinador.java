/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.mycompany.footballmanager.Interfaces.Dados;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static com.mycompany.footballmanager.Menu.treinadores;

/**
 * @author afonso, milena, tânia
 */
public class Treinador extends Pessoa implements Dados {
    private static int AI = 1; // Auto Increment
    private int id = 0;
    private String especializacoes;
    private String taticas_fav;

    // BEGIN Constructors ----------------------------------------------------------------
    public Treinador() {
        super.setNome("Treinador nome");
        super.setIdade(random.nextInt(30, 60));
        especializacoes = "Estilo ofensivo";
        taticas_fav = "3-4-3, 5-6-4";
    }

    public Treinador(
            String nome,
            int idade,
            String especializacoes,
            String taticas_fav
    ) {
        super(nome, idade);
        this.especializacoes = especializacoes;
        this.taticas_fav = taticas_fav;
    }
    // END Constructors ----------------------------------------------------------------

    // BEGIN Interface Methods ---------------------------------------------------------
    @Override
    public void insert() {
        Scanner scanner = new Scanner(System.in);

        boolean insertMore = true;

        while (insertMore) {
            getTreinadores();
            treinadores.add(insereTreinador());

            System.out.println("Deseja inserir outro Treinador? (sim/nao)");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (!choice.equals("sim")) {
                insertMore = false;
            }
        }

//        scanner.nextLine(); // Consume newline character
//        scanner.close(); // Close Scanner after use
    }

    public Treinador insereTreinador() {
        Treinador treinador = new Treinador();
        Scanner scanner = new Scanner(System.in);

        try {
            // Gets the ID of the latest treinador, using the size of the ArrayList and decrementing 1
            int latest = treinadores.get(treinadores.size() - 1).getId();
            int increment = 1;
            treinador.setId(latest + increment); // Automatically increments the ID

            System.out.println("Inserir Jogador: ");
            try {
                System.out.println("Insira o Nome: ");
                String nome = scanner.nextLine();
                if (Menu.hasVirgulaString(nome)) {
                    System.out.println("O Nome do Jogador não pode conter virgulas! Tente Novamente...");
                    return insereTreinador();
                } else {
                    treinador.setNome(nome);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereTreinador();
            }

            try {
                System.out.println("Insira a Idade: ");
                int idade = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (idade > 0 && idade <= 40) {
                    treinador.setIdade(idade);
                } else {
                    System.out.println("A Idade do Jogador tem que ter entre 1 e 40 anos, inclusive! Tente Novamente...");
                    return insereTreinador();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereTreinador();
            }

            try {
                System.out.println("Insira as Especializações: ");
                String especializacoes = scanner.nextLine().trim();

                if (Menu.hasVirgulaString(especializacoes)) {
                    System.out.println("As Especializações do Treinador não podem conter virgulas! Tente Novamente...");
                    return insereTreinador();
                } else {
                    treinador.setEspecializacoes(especializacoes);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereTreinador();
            }

            try {
                System.out.println("Insira as Táticas Favoritas do Treinador: ");
                String taticas_fav = scanner.nextLine();

                if (Menu.hasVirgulaString(taticas_fav)) {
                    System.out.println("As Táticas Favoritas do Treinador não podem conter virgulas! Tente Novamente...");
                    return insereTreinador();
                } else {
                    treinador.setTaticas_fav(taticas_fav);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereTreinador();
            }

        } catch (Exception e) {
            System.out.println("Input inválido: " + e.getMessage() + "\n");
            return insereTreinador();
        } finally {
//            scanner.close();
        }

        writeToCSV(treinador);

        return treinador;
    }

    // Method to write Treinador data to a CSV file
    private void writeToCSV(Treinador treinador) {
        String csvFile = "./src/main/java/com/mycompany/footballmanager/DB/treinadores.csv";

        try (FileWriter writer = new FileWriter(csvFile, true)) {
            File file = new File(csvFile);

            // Check if the file exists; if not, creates it
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Ficheiro criado com sucesso!");
                } else {
                    System.out.println("Falha ao criar o ficheiro!");
                    return; // Exit the method if file creation fails
                }
            }

            StringBuilder sb = new StringBuilder();

            // Construct the CSV line
            sb.append(treinador.getId()).append(",");
            sb.append(treinador.getNome()).append(",");
            sb.append(treinador.getIdade()).append(",");
            sb.append(treinador.getEspecializacoes()).append(",");
            sb.append(treinador.getTaticas_fav()).append("\n");

            // Write the CSV line to the file
            writer.append(sb.toString());
            // closes the output stream
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void print() {
        getTreinadores();
        // Print the table Headers
        System.out.printf(Treinador.tableHeaders());

        // Print details of all Treinadores using a loop
        if (!treinadores.isEmpty()) {
            for (Treinador treinador : treinadores) {
                System.out.printf(treinador.toString());
            }
        } else {
            System.out.println("Não existem Treinadores!");
        }
    }

    public void getTreinadores() {
        // Path to file
        String path = "src/main/java/com/mycompany/footballmanager/DB/treinadores.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String row;
            boolean firstLine = true; // Flag to identify the first line
            ArrayList<Treinador> treinadores = new ArrayList<>(); // Create a new list for treinadores
            File file = new File(path);

            // Check if the file exists; if not, creates it
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("Ficheiro criado com sucesso!");
                } else {
                    System.out.println("Falha ao criar o ficheiro!");
                    return; // Exit the method if file creation fails
                }
            }

            while ((row = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Set the flag to false after encountering the first line
                    continue; // Skip processing the first line
                }

                String[] data = row.split(",");

                // CSV format: ID, Nome, Idade, Especializações e Taticas Favoritas
                Treinador treinador = new Treinador();
                treinador.setId(Integer.parseInt(data[0]));
                treinador.setNome(data[1]);
                treinador.setIdade(Integer.parseInt(data[2]));
                treinador.setEspecializacoes(data[3]);
                treinador.setTaticas_fav(data[4]);

                // Adds the treinador to the ArrayList
                treinadores.add(treinador);
            }

            // Replaces the ArrayList from Menu class with the new ArrayList
            Menu.treinadores = treinadores;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        //
    }

    @Override
    public void delete() {
        //
    }
    // END Interface Methods --------------------------------------------------------


    // BEGIN Setters ----------------------------------------------------------------

    public void setId(int id) {
        this.id = id;
    }

    public void setEspecializacoes(String especializacoes) {
        this.especializacoes = especializacoes;
    }

    public void setTaticas_fav(String taticas_fav) {
        this.taticas_fav = taticas_fav;
    }

    // END Setters ------------------------------------------------------------------

    // BEGIN Getters ----------------------------------------------------------------

    public int getId() {
        return id;
    }

    @Override
    public int getIdade() {
        return super.getIdade();
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    public String getEspecializacoes() {
        return especializacoes;
    }

    public String getTaticas_fav() {
        return taticas_fav;
    }
    // END Getters ----------------------------------------------------------------

    // Print headers
    public static String tableHeaders() {
        return String.format("| %-3s | %-20s | %-7s | %-20s | %-30s |%n",
                "ID", "Nome", "Idade", "Especializações", "Táticas Favoritas");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-20s | %-7s | %-20s | %-30s |%n",
                getId(), getNome(), getIdade(), getEspecializacoes(), getTaticas_fav());
    }
}
