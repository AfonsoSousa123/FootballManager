/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.mycompany.footballmanager.Interfaces.Dados;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.mycompany.footballmanager.Menu.*;

/**
 * @author afonso, milena, tânia
 */
public class Jogador extends Pessoa implements Dados {
    // BEGIN Variables ----------------------------------------------------------------
    private int id = 0;
    private String posicao;
    private String hist_lesoes;
    private int ataque; // de 0 a 100
    private int defesa; // de 0 a 100
    private int n_agressividade; // de 0 a 5
    private final String txtFilePath = "./src/main/java/com/mycompany/footballmanager/DB/jogadores.txt"; // File Path
    // END Variables ----------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Jogador() {
        super.setNome("Zézinho");
        super.setIdade(random.nextInt(20, 40));
        posicao = "central";
        hist_lesoes = "perna partida";
        ataque = random.nextInt(1, 100);
        defesa = random.nextInt(1, 100);
        n_agressividade = random.nextInt(1, 100);
    }

    public Jogador(
            int id,
            String nome,
            int idade,
            String posicao,
            String hist_lesoes,
            int ataque,
            int defesa,
            int n_agressividade
    ) {
        super(nome, idade);
        this.id = id;
        this.posicao = posicao;
        this.hist_lesoes = hist_lesoes;
        this.ataque = ataque;
        this.defesa = defesa;
        this.n_agressividade = n_agressividade;
    }
    // END Constructors ----------------------------------------------------------------

    // BEGIN Interface Methods ----------------------------------------------------------------
    @Override
    public void insert() {
        Scanner scanner = new Scanner(System.in);

        boolean insertMore = true;

        while (insertMore) {
            getJogadores();
            jogadores.add(insereJogador());

            try {
                System.out.println("Deseja inserir outro Jogador? (sim/nao)");
                String choice = scanner.nextLine().trim().toLowerCase();

                if (!choice.equals("sim")) {
                    insertMore = false;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    public Jogador insereJogador() {
        Jogador jogador = new Jogador();
        Scanner scanner = new Scanner(System.in);
        int latest = 0;

        try {
            // if the jogadores ArrayList is not empty
            if (!Menu.jogadores.isEmpty()) {
                // Gets the ID of the latest jogador, using the size of the ArrayList and decrementing 1
                latest = Menu.jogadores.get(Menu.jogadores.size() - 1).getId();
            }

            // Automatically increments the ID
            int increment = 1;
            jogador.setId(latest + increment);

            System.out.println("Inserir Jogador: ");
            // Nome
            try {
                System.out.println("Insira o Nome: ");
                String nome = scanner.nextLine();
                if (Menu.hasPontoEVirgulaString(nome)) {
                    System.out.println("O Nome do Jogador não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return insereJogador();
                } else {
                    jogador.setNome(nome);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereJogador();
            }

            // Idade
            try {
                System.out.println("Insira a Idade: ");
                int idade = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (idade >= 18 && idade <= 45) {
                    jogador.setIdade(idade);
                } else {
                    System.out.println("A Idade do Jogador tem que ter entre 18 e 45 anos, inclusive! Tente Novamente...");
                    return insereJogador();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereJogador();
            }

            // Posição
            try {
                System.out.println("Insira a Posição: ");
                String posicao = scanner.nextLine().trim();

                if (Menu.hasPontoEVirgulaString(posicao)) {
                    System.out.println("A Posição do Jogador não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return insereJogador();
                } else {
                    jogador.setPosicao(posicao);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereJogador();
            }

            // Historico de Lesões
            try {
                System.out.println("Insira o Historico de Lesões: ");
                String historico = scanner.nextLine();

                if (Menu.hasPontoEVirgulaString(historico)) {
                    System.out.println("O Historico de Lesões do Jogador não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return insereJogador();
                } else {
                    jogador.setHist_lesoes(historico);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereJogador();
            }

            // Ataque
            try {
                System.out.println("Insira o Ataque: ");
                int ataque = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (ataque > 0 && ataque <= 100) {
                    jogador.setAtaque(ataque);
                } else {
                    System.out.println("O Jogador só pode ter entre 1 e 100 valores de Ataque! Tente Novamente...");
                    return insereJogador();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereJogador();
            }

            // Defesa
            try {
                System.out.println("Insira o Defesa: ");
                int defesa = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (defesa > 0 && defesa <= 100) {
                    jogador.setDefesa(defesa);
                } else {
                    System.out.println("O Jogador só pode ter entre 1 e 100 valores de Defesa! Tente Novamente...");
                    return insereJogador();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereJogador();
            }

            // Nivel de Agressividade
            try {
                System.out.println("Insira o Nivel de Agressividade: ");
                int n_agressividade = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (n_agressividade > 0 && n_agressividade <= 100) {
                    jogador.setN_agressividade(n_agressividade);
                } else {
                    System.out.println("O Jogador só pode ter entre 1 e 100 valores de Nivel de Agressividade! Tente Novamente...");
                    return insereJogador();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereJogador();
            }

        } catch (Exception e) {
            System.out.println("Input inválido: " + e.getMessage() + "\n");
            return insereJogador();
        } finally {
//            scanner.close();
        }

        writeToTXT(jogador);

        return jogador;
    }

    // Method to write Jogador data to a TXT file
    private void writeToTXT(Jogador jogador) {
        checkIfFileExists(txtFilePath);

        try (FileWriter writer = new FileWriter(txtFilePath, true)) {
            StringBuilder sb = new StringBuilder();

            // Construct the CSV line
            sb.append(jogador.getId()).append(";");
            sb.append(jogador.getNome()).append(";");
            sb.append(jogador.getIdade()).append(";");
            sb.append(jogador.getPosicao()).append(";");
            sb.append(jogador.getHist_lesoes()).append(";");
            sb.append(jogador.getAtaque()).append(";");
            sb.append(jogador.getDefesa()).append(";");
            sb.append(jogador.getN_agressividade()).append("\n");

            // Write the CSV line to the file
            writer.append(sb.toString());
            // closes the output stream
            writer.flush();
        } catch (IOException e) {
            System.out.println("Erro ao inserir o Jogador no ficheiro " + e.getMessage());
        }
    }

    @Override
    public void print() {
        getJogadores();

        // Print details of all players using a loop
        if (!jogadores.isEmpty()) {
            // Print the table Headers
            System.out.printf(tableHeaders());

            for (Jogador jogador : jogadores) {
                System.out.printf(jogador.toString());
            }
        } else {
            System.out.println("\nNão existem Jogadores!\n");
        }
    }

    public void getJogadores() {
        checkIfFileExists(txtFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) {

            boolean firstLine = true; // Flag to identify the first line
            ArrayList<Jogador> jogadores = new ArrayList<>(); // Create a new list for jogadores
            String row;

            while ((row = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Set the flag to false after encountering the first line
                    continue; // Skip processing the first line
                }

                String[] data = row.split(";");

                // TXT format: ID, Nome, Idade, Posição, Histórico de Lesões, Ataque, Defesa, Nível de Agressividade
                Jogador jogador = new Jogador();
                jogador.setId(Integer.parseInt(data[0]));
                jogador.setNome(data[1]);
                jogador.setIdade(Integer.parseInt(data[2]));
                jogador.setPosicao(data[3]);
                jogador.setHist_lesoes(data[4]);
                jogador.setAtaque(Integer.parseInt(data[5]));
                jogador.setDefesa(Integer.parseInt(data[6]));
                jogador.setN_agressividade(Integer.parseInt(data[7]));

                // Adds the jogador to the ArrayList
                jogadores.add(jogador);
            }

            // Replaces the ArrayList from Menu class with the new ArrayList
            Menu.jogadores = jogadores;
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
    // END Interface Methods ----------------------------------------------------------------

    // BEGIN Faker Methods ----------------------------------------------------------------

    @Override
    public void insertFaker() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Quantos Jogadores quer gerar? ");
            int numOfChoices = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (numOfChoices <= 0 || numOfChoices > 11) {
                System.out.println("Só pode inserir no maximo 11 de cada vez! Tente Novamente...");
                insertFaker();
            }

            for (int i = 0; i < numOfChoices; i++) {
                // Automatically increments the ID
                int increment = 1;
                int latest = 0;
                // if the jogadores ArrayList is not empty
                if (!Menu.jogadores.isEmpty()) {
                    // Gets the ID of the latest jogador, using the size of the ArrayList and decrementing 1
                    latest = Menu.jogadores.get(Menu.jogadores.size() - 1).getId();
                }

                Jogador jogador = new Jogador(
                        latest + increment, // ID automatically increments
                        randomFullName(), // Random Nome
                        random.nextInt(20, 40), // Random Idade
                        randomLorem(), // Random Posição
                        randomLorem(), // Random Historico de Lesões
                        random.nextInt(1, 100), // Random Ataque
                        random.nextInt(1, 100), // Random Defesa
                        random.nextInt(1, 100) // Random Nivel de Agressividade
                );
                jogadores.add(jogador); // Adds the new Jogador to the Jogadores ArrayList
                writeToTXT(jogador); // Writes the Jogador to the TXT File
            }

            System.out.print("Jogadores Gerados com sucesso!");
            System.out.println("--------------------------------");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // END Faker Methods ----------------------------------------------------------------

    // BEGIN Setters ----------------------------------------------------------------

    private void setId(int id) {
        this.id = id;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public void setHist_lesoes(String hist_lesoes) {
        this.hist_lesoes = hist_lesoes;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void setN_agressividade(int n_agressividade) {
        this.n_agressividade = n_agressividade;
    }
    // END Setters ----------------------------------------------------------------

    // BEGIN Getters ----------------------------------------------------------------
    public int getId() {
        return id;
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public int getIdade() {
        return super.getIdade();
    }

    public String getPosicao() {
        return posicao;
    }

    public String getHist_lesoes() {
        return hist_lesoes;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getN_agressividade() {
        return n_agressividade;
    }
    // END Getters ----------------------------------------------------------------

    // Print headers
    public static String tableHeaders() {
        return String.format("| %-3s | %-25s | %-7s | %-20s | %-30s | %-7s | %-7s | %-14s |%n",
                "ID", "Nome", "Idade", "Posição", "Histórico de Lesões", "Ataque", "Defesa", "Nível de Agressividade");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-25s | %-7s | %-20s | %-30s | %-7s | %-7s | %-22s |%n",
                getId(), getNome(), getIdade(), getPosicao(), getHist_lesoes(), getAtaque(), getDefesa(), getN_agressividade());
    }
}
