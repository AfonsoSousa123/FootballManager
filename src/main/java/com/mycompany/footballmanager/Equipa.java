/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.mycompany.footballmanager.Interfaces.Dados;
import static com.mycompany.footballmanager.Menu.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author afonso, milena, tânia
 */
public class Equipa implements Dados {
    // BEGIN Variables ----------------------------------------------------------------
    private int id;
    private String nome;
    private ArrayList<Integer> plantel;
    private int idTreinador;
    private int idLiga;
    private String cidade;
    private String pais;
    private String historico;
    private int golos_marcados;
    private int golos_sofridos;

    private Random random = new Random();
    private final String txtFilePath = "./src/main/java/com/mycompany/footballmanager/DB/equipas.txt"; // File Path
    // END Variables ------------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Equipa() {
        nome = randomTeam();
        cidade = randomCity();
        plantel = new ArrayList<>();
        pais = randomCountry();
        historico = randomLorem();
        golos_marcados = random.nextInt(0, 200);
        golos_sofridos = random.nextInt(0, 200);
    }

    public Equipa(
            String nome,
            ArrayList<Integer> plantel,
            int idTreinador,
            int idLiga,
            String cidade,
            String pais,
            String historico,
            int golos_marcados,
            int golos_sofridos
    ) {
        this.nome = nome;
        this.cidade = cidade;
        this.pais = pais;
        this.historico = historico;
        this.golos_marcados = golos_marcados;
        this.golos_sofridos = golos_sofridos;
    }
    // END Constructors ----------------------------------------------------------------

    // BEGIN Interface Methods ----------------------------------------------------------------
    @Override
    public void insert() {
        Scanner scanner = new Scanner(System.in);
        boolean insertMore = true;

        while (insertMore) {
            getEquipas(); // Updates the Equipas ArrayList
            equipas.add(insereEquipa());

            try {
                System.out.println("Deseja inserir outra Equipa? (sim/nao)");
                String choice = scanner.nextLine().trim().toLowerCase();

                if (!choice.equals("sim")) {
                    insertMore = false;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    public Equipa insereEquipa() {
        Equipa equipa = new Equipa();
        Scanner scanner = new Scanner(System.in);
        int latest = 0;

        try {
            // if the equipas ArrayList is not empty
            if (!Menu.equipas.isEmpty()) {
                // Gets the ID of the latest equipa, using the size of the ArrayList and decrementing 1
                latest = Menu.equipas.get(Menu.equipas.size() - 1).getId();
            }

            // Automatically increments the ID
            int increment = 1;
            equipa.setId(latest + increment);

            System.out.println("Inserir Equipa: ");
            // Nome
            try {
                System.out.println("Insira o Nome: ");
                String nome = scanner.nextLine();
                if (Menu.hasPontoEVirgulaString(nome)) {
                    System.out.println("O Nome da Equipa não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return insereEquipa();
                } else {
                    equipa.setNome(nome);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereEquipa();
            }

            // Plantel
            try {
                boolean insertMoreJogadores = true;
                ArrayList<Integer> JogadoresIDs = new ArrayList<>(); // Cria um arrayList para os IDs dos Jogadores
                Menu.jogador.print();

                while (insertMoreJogadores) {
//                    jogador.getJogadores();
                    System.out.println("Escolha o ID do Jogador que pretende adicionar ao Plantel: ");
                    int idJogador = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    if (idJogador > 0 && idJogador <= Menu.jogadores.size()) {
                        JogadoresIDs.add(idJogador);
                    } else {
                        System.out.println("Tem que escolher um ID existente das Jogadores! Tente Novamente...");
                        return insereEquipa();
                    }

                    System.out.println("Inside the While" + JogadoresIDs);
                    System.out.println("Deseja adicionar mais Jogadores ao Plantel? (sim/nao)");
                    String choicePlantel = scanner.nextLine().trim().toLowerCase();

                    if (!choicePlantel.equals("sim")) {
                        insertMoreJogadores = false;
                    }
                }
                System.out.println("Outside the While" + JogadoresIDs);
                setPlantel(JogadoresIDs);
                System.out.println("Plantel" + getPlantel());

            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereEquipa();
            }

            // Treinador
            try {
                Menu.treinador.print();
                System.out.println("Escolha o ID do Treinador que pretende adicionar à Equipa: ");
                int treinadorID = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (treinadorID > 0 && treinadorID <= Menu.treinadores.size()) {
                    equipa.setIdTreinador(treinadorID);
                } else {
                    System.out.println("Tem que escolher um ID existente dos Treinadores! Tente Novamente...");
                    return insereEquipa();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereEquipa();
            }

            // Liga
            try {
                Menu.liga.print();
                System.out.println("Escolha o ID da Liga que pretende adicionar à Equipa: ");
                int ligaID = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (ligaID > 0 && ligaID <= Menu.ligas.size()) {
                    equipa.setIdLiga(ligaID);
                } else {
                    System.out.println("Tem que escolher um ID existente das Ligas! Tente Novamente...");
                    return insereEquipa();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereEquipa();
            }

            // Cidade
            try {
                System.out.println("Insira a Cidade: ");
                String cidade = scanner.nextLine();

                if (Menu.hasPontoEVirgulaString(cidade)) {
                    System.out.println("A Cidade não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return insereEquipa();
                } else {
                    equipa.setCidade(cidade);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereEquipa();
            }

            // Pais
            try {
                System.out.println("Insira o Pais: ");
                String pais = scanner.nextLine();

                if (Menu.hasPontoEVirgulaString(pais)) {
                    System.out.println("O Pais não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return insereEquipa();
                } else {
                    equipa.setPais(pais);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereEquipa();
            }

            // Historico da Equipa
            try {
                System.out.println("Insira o Historico: ");
                String historico = scanner.nextLine();

                if (Menu.hasPontoEVirgulaString(historico)) {
                    System.out.println("O Historico da Equipa não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return insereEquipa();
                } else {
                    equipa.setHistorico(historico);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereEquipa();
            }

            // Golos Marcados
            try {
                System.out.println("Insira a quantidade de Golos Marcados: ");
                int golosMarcados = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (golosMarcados >= 0 && golosMarcados < 5000) {
                    equipa.setGolos_marcados(golosMarcados);
                } else {
                    System.out.println("A quantidade de Golos Marcados tem que ser menor que 5000 e! Tente Novamente...");
                    return insereEquipa();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: Não pode inserir strings neste campo\n");
                return insereEquipa();
            }

            // Golos Sofridos
            try {
                System.out.println("Insira a quantidade de Golos Sofridos: ");
                int golosSofridos = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (golosSofridos >= 0 && golosSofridos < 5000) {
                    equipa.setGolos_sofridos(golosSofridos);
                } else {
                    System.out.println("A quantidade de Golos Sofridos tem que ser menor que 5000 e! Tente Novamente...");
                    return insereEquipa();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: Não pode inserir strings neste campo\n");
                return insereEquipa();
            }

        } catch (Exception e) {
            System.out.println("Input inválido: " + e.getMessage() + "\n");
            return insereEquipa();
        } finally {
            System.out.println(equipa);
            writeToTXT(equipa);
        }

        return equipa;
    }

    // Method to write Equipa data to a TXT file
    private void writeToTXT(Equipa equipa) {
        checkIfFileExists(txtFilePath);

        try (FileWriter writer = new FileWriter(txtFilePath, true)) {
            StringBuilder sb = new StringBuilder();

            // Construct the TXT line
            sb.append(equipa.getId()).append(";");
            sb.append(equipa.getNome()).append(";");
            // Append the plantel elements with comma separator
            for (Integer jogador : equipa.getPlantel()) { // Plantel
                sb.append(jogador).append(",");
            }
            sb.deleteCharAt(sb.length() - 1); // Remove a ultima virgula
            sb.append(";"); // Para poder ser colocada ponto e virgula
            sb.append(equipa.getIdTreinador()).append(";");
            sb.append(equipa.getIdLiga()).append(";");
            sb.append(equipa.getCidade()).append(";");
            sb.append(equipa.getPais()).append(";");
            sb.append(equipa.getHistorico()).append(";");
            sb.append(equipa.getGolos_marcados()).append(";");
            sb.append(equipa.getGolos_sofridos()).append("\n");

            // Write the line to the file
            writer.append(sb.toString());
            // closes the output stream
            writer.flush();

            System.out.println("Equipa inserida com Sucesso!!!");
        } catch (IOException e) {
            System.out.println("Erro ao inserir a Equipa no ficheiro " + e.getMessage());
        }
    }

    @Override
    public void print() {
        getEquipas();

        // Print details of all players using a loop
        if (!equipas.isEmpty()) {
            // Print the table Headers
            System.out.printf(tableHeaders());

            for (Equipa equipa : equipas) {
                System.out.printf(equipa.toString());
            }
        } else {
            System.out.println("\nNão existem Equipas!\n");
        }
    }

    public void getEquipas() {
        checkIfFileExists(txtFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) {

            boolean firstLine = true; // Flag to identify the first line
            ArrayList<Equipa> equipas = new ArrayList<>(); // Create a new list for equipas
            String row;

            while ((row = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Set the flag to false after encountering the first line
                    continue; // Skip processing the first line
                }

                String[] data = row.split(";");

                // TXT format: ID, Nome, Plantel, Treinador, Liga, Cidade, País, Histórico, Golos Marcados, Golos Sofridos
                Equipa equipa = new Equipa();
                equipa.setId(Integer.parseInt(data[0])); // ID
                equipa.setNome(data[1]); // Nome
                String[] jogadorIds = data[2].split(","); // gets the ids of the jogadores
                ArrayList<Integer> plantel = new ArrayList<>();
                for (String jogadorId : jogadorIds) {
                    plantel.add(Integer.parseInt(jogadorId));
                }
                equipa.setPlantel(plantel); // Plantel
                equipa.setIdTreinador(Integer.parseInt(data[3])); // Treinador
                equipa.setIdLiga(Integer.parseInt(data[4])); // Liga
                equipa.setCidade(data[5]); // Cidade
                equipa.setPais(data[6]); // Pais
                equipa.setHistorico(data[7]); // Historico
                equipa.setGolos_marcados(Integer.parseInt(data[8])); // Golos Marcados
                equipa.setGolos_sofridos(Integer.parseInt(data[9])); // Golos Sofridos

                // Adds the equipa to the ArrayList
                equipas.add(equipa);
            }

            // Replaces the ArrayList from Menu class with the new ArrayList
            Menu.equipas = equipas;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id) {
        //
    }

    @Override
    public void delete(int id) {
        //
    }

    // BEGIN Faker Methods ----------------------------------------------------------------
    @Override
    public void insertFaker() {
        //
    }

    // END Faker Methods ----------------------------------------------------------------
    // END Interface Methods ----------------------------------------------------------------

    // BEGIN Getters and Setters ----------------------------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Integer> getPlantel() {
        return plantel;
    }

    public void setPlantel(ArrayList<Integer> plantel) {
        this.plantel = plantel;
    }

    public void setIdJogador(Integer idJogador) {
        plantel.add(idJogador);
    }

    public int getIdTreinador() {
        return idTreinador;
    }

    public void setIdTreinador(int idTreinador) {
        this.idTreinador = idTreinador;
    }

    public int getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(int idLiga) {
        this.idLiga = idLiga;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public int getGolos_marcados() {
        return golos_marcados;
    }

    public void setGolos_marcados(int golos_marcados) {
        this.golos_marcados = golos_marcados;
    }

    public int getGolos_sofridos() {
        return golos_sofridos;
    }

    public void setGolos_sofridos(int golos_sofridos) {
        this.golos_sofridos = golos_sofridos;
    }
    // END Getters and Setters ----------------------------------------------------------------

    // BEGIN toString Methods ----------------------------------------------------------------
    // Print headers
    public static String tableHeaders() {
        return String.format("| %-3s | %-25s | %-40s | %-15s | %-15s | %-10s | %-10s | %-30s | %-14s | %-14s |%n",
                "ID", "Nome", "Plantel", "Treinador", "Liga", "Cidade", "Pais", "Histórico", "Golos Marcados", "Golos Sofridos");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-25s | %-40s | %-15s | %-15s | %-10s | %-10s | %-30s | %-14s | %-22s |%n",
                getId(), getNome(), getPlantel(), getIdTreinador(), getIdLiga(), getCidade(), getPais(), getHistorico(), getGolos_marcados(), getGolos_sofridos());
    }
    // END toString Methods ----------------------------------------------------------------
}
