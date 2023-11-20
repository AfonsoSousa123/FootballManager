/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.mycompany.footballmanager.Interfaces.Dados;

import java.io.*;
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
//        this.id = getId() + AI++;
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
//        this.id = AI++;
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
            jogadores.add(insereJogador());

            System.out.println("Deseja inserir outro Jogador? (sim/nao)");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (!choice.equals("sim")) {
                insertMore = false;
            }
        }

//        scanner.nextLine(); // Consume newline character
//        scanner.close(); // Close Scanner after use
    }

    public Jogador insereJogador() {
        Jogador jogador = new Jogador();
        Scanner scanner = new Scanner(System.in);

        try {
            jogador.setId(getId() + AI++);

            System.out.println("Nome: ");
            String nome = scanner.nextLine().trim();
            jogador.setNome(nome);

            System.out.println("Idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            jogador.setIdade(idade);

            System.out.println("Posição: ");
            String posicao = scanner.nextLine().trim();
            jogador.setPosicao(posicao);

            System.out.println("Historico: ");
            String historico = scanner.nextLine();
            jogador.setHist_lesoes(historico);

            System.out.println("Ataque: ");
            int ataque = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            jogador.setAtaque(ataque);

            System.out.println("Defesa: ");
            int defesa = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            jogador.setDefesa(defesa);

            System.out.println("Agressividade: ");
            int n_agressividade = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            jogador.setN_agressividade(n_agressividade);
//            scanner.nextLine(); // Consume newline character
//
//            scanner.close(); // Closing Scanner
        } catch (Exception e) {
            System.out.println("Input inválido: " + e.getMessage());
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
        // Path to file
        String path = "src/main/java/com/mycompany/footballmanager/DB/jogadores.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String row = br.readLine(); // Read the header line (skipping processing)

            if (row != null) {
                while ((row = br.readLine()) != null) {
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

                    jogadores.add(jogador);
                }
            } else {
                System.out.println("O ficheiro está vazio!");
            }

            // Print the table Headers
            System.out.printf(Jogador.tableHeaders());

            // Print details of all players using a loop
            for (Jogador jogador : jogadores) {
                System.out.printf(jogador.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the table Headers
        System.out.printf(Jogador.tableHeaders());

        // Print details of all players using a loop
        for (Jogador jogador : jogadores) {
            System.out.printf(jogador.toString());
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
