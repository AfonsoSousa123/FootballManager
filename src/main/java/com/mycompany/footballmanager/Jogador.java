/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.mycompany.footballmanager.Interfaces.Dados;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static com.mycompany.footballmanager.Menu.jogadores;

/**
 * @author afonso, milena, tânia
 */
public class Jogador extends Pessoa implements Dados {
    // BEGIN Variables ----------------------------------------------------------------
    private static int AI = 1; // Auto Increment
    private int id = 0;
    private String posicao;
    private String hist_lesoes;
    private int ataque; // de 0 a 100
    private int defesa; // de 0 a 100
    private int n_agressividade; // de 0 a 100
    // END Variables ----------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Jogador() {
        super.setNome("Zézinho");
        super.setIdade(random.nextInt(20, 40));
        posicao = "central";
        hist_lesoes = "perna partida";
        ataque = 10;
        defesa = 15;
        n_agressividade = 5;
    }

    public Jogador(
            String nome,
            int idade,
            String posicao,
            String hist_lesoes,
            int ataque,
            int defesa,
            int n_agressividade
    ) {
        super(nome, idade);
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
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public Jogador insereJogador() {
        Jogador jogador = new Jogador();
        Scanner scanner = new Scanner(System.in);

        try {
            // Gets the ID of the latest jogador, using the size of the ArrayList and decrementing 1
            int latest = Menu.jogadores.get(Menu.jogadores.size() - 1).getId();
            int increment = 1;
            jogador.setId(latest + increment); // Automatically increments the ID

            System.out.println("Inserir Jogador: ");
            try {
                System.out.println("Insira o Nome: ");
                String nome = scanner.nextLine();
                if (Menu.hasVirgulaString(nome)) {
                    System.out.println("O Nome do Jogador não pode conter virgulas! Tente Novamente...");
                    return insereJogador();
                } else {
                    jogador.setNome(nome);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage());
                return insereJogador();
            }

            try {
                System.out.println("Insira a Idade: ");
                int idade = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (idade > 0 && idade <= 40) {
                    jogador.setIdade(idade);
                } else {
                    System.out.println("A Idade do Jogador tem que ter entre 1 e 40 anos, inclusive! Tente Novamente...");
                    return insereJogador();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage());
                return insereJogador();
            }

            try {
                System.out.println("Insira a Posição: ");
                String posicao = scanner.nextLine().trim();

                if (Menu.hasVirgulaString(posicao)) {
                    System.out.println("A Posição do Jogador não pode conter virgulas! Tente Novamente...");
                    return insereJogador();
                } else {
                    jogador.setPosicao(posicao);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage());
                return insereJogador();
            }

            try {
                System.out.println("Insira o Historico de Lesões: ");
                String historico = scanner.nextLine();

                if (Menu.hasVirgulaString(historico)) {
                    System.out.println("O Historico de Lesões do Jogador não pode conter virgulas! Tente Novamente...");
                    return insereJogador();
                } else {
                    jogador.setHist_lesoes(historico);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage());
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
                System.out.println("Input inválido: " + e.getMessage());
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
                System.out.println("Input inválido: " + e.getMessage());
                return insereJogador();
            }

            // Nivel de Agressividade
            try {
                System.out.println("Insira o Nivel de Agressividade: ");
                int n_agressividade = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (n_agressividade > 0 && n_agressividade <= 5) {
                    jogador.setN_agressividade(n_agressividade);
                } else {
                    System.out.println("O Jogador só pode ter entre 1 e 5 valores de Nivel de Agressividade! Tente Novamente...");
                    return insereJogador();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage());
                return insereJogador();
            }

        } catch (Exception e) {
            System.out.println("Input inválido: " + e.getMessage());
            return insereJogador();
        } finally {
//            scanner.close();
        }

        writeToCSV(jogador);

        return jogador;
    }

    // Method to write Jogador data to a CSV file
    private void writeToCSV(Jogador jogador) {
        String csvFile = "./src/main/java/com/mycompany/footballmanager/DB/jogadores.csv";

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
            sb.append(jogador.getId()).append(",");
            sb.append(jogador.getNome()).append(",");
            sb.append(jogador.getIdade()).append(",");
            sb.append(jogador.getPosicao()).append(",");
            sb.append(jogador.getHist_lesoes()).append(",");
            sb.append(jogador.getAtaque()).append(",");
            sb.append(jogador.getDefesa()).append(",");
            sb.append(jogador.getN_agressividade()).append("\n");

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
        getJogadores();
        // Print the table Headers
        System.out.printf(Jogador.tableHeaders());

        // Print details of all players using a loop
        if (!jogadores.isEmpty()) {
            for (Jogador jogador : jogadores) {
                System.out.printf(jogador.toString());
            }
        } else {
            System.out.println("Não existem Jogadores!");
        }
    }

    public void getJogadores() {
        // Path to file
        String path = "src/main/java/com/mycompany/footballmanager/DB/jogadores.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String row;
            boolean firstLine = true; // Flag to identify the first line
            ArrayList<Jogador> jogadores = new ArrayList<>(); // Create a new list for jogadores
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

                // CSV format: ID, Nome, Idade, Posição, Histórico de Lesões, Ataque, Defesa, Nível de Agressividade
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
        return String.format("| %-3s | %-20s | %-7s | %-20s | %-30s | %-7s | %-7s | %-14s |%n",
                "ID", "Nome", "Idade", "Posição", "Histórico de Lesões", "Ataque", "Defesa", "Nível de Agressividade");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-20s | %-7s | %-20s | %-30s | %-7s | %-7s | %-14d |%n",
                getId(), getNome(), getIdade(), getPosicao(), getHist_lesoes(), getAtaque(), getDefesa(), getN_agressividade());
    }
}
