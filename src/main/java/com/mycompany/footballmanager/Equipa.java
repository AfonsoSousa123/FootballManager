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
public class Equipa implements Dados {
    private final String txtFilePath = "./src/main/java/com/mycompany/footballmanager/DB/equipas.txt"; // File Path

    // BEGIN Variables ----------------------------------------------------------------
    private int id;
    private String nome;
    private ArrayList<Jogador> plantel;
    private Treinador treinador;
    private Liga liga;
    private String cidade;
    private String pais;
    private String historico;
    private int golos_marcados;
    private int golos_sofridos;

//    private Random random = new Random();
    // END Variables ------------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Equipa() {
        nome = randomTeam();
        cidade = randomCity();
        pais = randomCountry();
        historico = randomLorem();
        golos_marcados = random.nextInt(0, 200);
        golos_sofridos = random.nextInt(0, 200);
    }

    public Equipa(
            String nome,
            ArrayList<Jogador> plantel,
            Treinador treinador,
            Liga liga,
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
                System.out.println("Escolha o Plantel: ");
                String plantel = scanner.nextLine();
                if (Menu.hasPontoEVirgulaString(plantel)) {
                    System.out.println("O Nome da Equipa não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return insereEquipa();
                } else {
                    equipa.setNome(plantel);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereEquipa();
            }

            // Treinador
            try {
                System.out.println("Escolha o Treinador: ");
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

                if (golosMarcados > 0 && golosMarcados <= 200) {
                    equipa.setGolos_marcados(golosMarcados);
                } else {
                    System.out.println("O Jogador só pode ter entre 1 e 100 valores de Nivel de Agressividade! Tente Novamente...");
                    return insereEquipa();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereEquipa();
            }

            // Golos Sofridos
            try {
                System.out.println("Insira a quantidade de Golos Sofridos: ");
                int golosSofridos = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (golosSofridos > 0 && golosSofridos <= 200) {
                    equipa.setGolos_sofridos(golosSofridos);
                } else {
                    System.out.println("O Jogador só pode ter entre 1 e 100 valores de Nivel de Agressividade! Tente Novamente...");
                    return insereEquipa();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereEquipa();
            }

        } catch (Exception e) {
            System.out.println("Input inválido: " + e.getMessage() + "\n");
            return insereEquipa();
        } finally {
//            scanner.close();
        }

        writeToTXT(equipa);

        return equipa;
    }

    // Method to write Jogador data to a TXT file
    private void writeToTXT(Equipa equipa) {
        checkIfFileExists(txtFilePath);

        try (FileWriter writer = new FileWriter(txtFilePath, true)) {
            StringBuilder sb = new StringBuilder();

            // Construct the CSV line
            sb.append(equipa.getId()).append(";");
            sb.append(equipa.getNome()).append(";");
            sb.append(equipa.getPlantel()).append(";");
            sb.append(equipa.getTreinador()).append(";");
            sb.append(equipa.getLiga()).append(";");
            sb.append(equipa.getCidade()).append(";");
            sb.append(equipa.getPais()).append(";");
            sb.append(equipa.getHistorico()).append(";");
            sb.append(equipa.getGolos_marcados()).append(";");
            sb.append(equipa.getGolos_sofridos()).append("\n");

            // Write the line to the file
            writer.append(sb.toString());
            // closes the output stream
            writer.flush();
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
                equipa.setId(Integer.parseInt(data[0]));
                equipa.setNome(data[1]);

                // Plantel format:
                String[] plantelData = data[2].split(",");
                ArrayList<Jogador> plantel = new ArrayList<>();
                for (String jogadorData : plantelData) {
                    Jogador jogador = new Jogador();
                    // Set the properties of the jogador instance using the jogadorData string
                    plantel.add(jogador);
                }
                equipa.setPlantel(plantel);

                // Treinador
                String[] treinadorData = data[3].split(",");
                Treinador treinador = new Treinador(
                        Integer.parseInt(treinadorData[0]),
                        treinadorData[1],
                        Integer.parseInt(treinadorData[2]),
                        treinadorData[3],
                        treinadorData[4]
                );
                equipa.setTreinador(treinador);

//                // Liga
//                String[] ligaData = data[4].split(",");
//                Liga liga = new Liga(
//                        Integer.parseInt(ligaData[0]),
//                        ligaData[1],
//                        Integer.parseInt(ligaData[2]),
//                        ligaData[3],
//                        ligaData[4]
//                );
//                equipa.setLiga(liga);

                equipa.setCidade(data[5]);
                equipa.setPais(data[6]);
                equipa.setHistorico(data[7]);
                equipa.setGolos_marcados(Integer.parseInt(data[8]));
                equipa.setGolos_sofridos(Integer.parseInt(data[9]));

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
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Quantas Equipas quer gerar? ");
            int numOfChoices = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (numOfChoices <= 0 || numOfChoices > 3) {
                System.out.println("Só pode inserir no maximo 3 de cada vez! Tente Novamente...");
                insertFaker();
            }

            for (int i = 0; i < numOfChoices; i++) {
                // Automatically increments the ID
                int increment = 1;
                int latest = 0;
//                // if the equipaes ArrayList is not empty
//                if (!Menu.equipas.isEmpty()) {
//                    // Gets the ID of the latest equipa, using the size of the ArrayList and decrementing 1
//                    latest = Menu.equipas.get(Menu.equipas.size() - 1).getId();
//                }

//                Equipa equipa = new Equipa(
//                        latest + increment, // ID automatically increments
//                        randomFullName(), // Random Nome
//                        random.nextInt(20, 40), // Random Idade
//                        randomLorem(), // Random Posição
//                        randomLorem(), // Random Historico de Lesões
//                        random.nextInt(1, 100), // Random Ataque
//                        random.nextInt(1, 100), // Random Defesa
//                        random.nextInt(1, 100) // Random Nivel de Agressividade
//                );
//                equipas.add(equipa); // Adds the new Equipa to the Equipas ArrayList
//                writeToTXT(equipa); // Writes the Equipa to the TXT File
            }

            System.out.print("Equipas Geradas com sucesso!");
            System.out.println("--------------------------------");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // END Faker Methods ----------------------------------------------------------------
    // END Interface Methods ----------------------------------------------------------------

    // BEGIN Setters ----------------------------------------------------------------
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPlantel(ArrayList<Jogador> plantel) {
        this.plantel = plantel;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public void setGolos_marcados(int golos_marcados) {
        this.golos_marcados = golos_marcados;
    }

    public void setGolos_sofridos(int golos_sofridos) {
        this.golos_sofridos = golos_sofridos;
    }

    // END Setters ----------------------------------------------------------------

    // BEGIN Getters ----------------------------------------------------------------
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Jogador> getPlantel() {
        return plantel;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public Liga getLiga() {
        return liga;
    }

    public String getCidade() {
        return cidade;
    }

    public String getPais() {
        return pais;
    }

    public String getHistorico() {
        return historico;
    }

    public int getGolos_marcados() {
        return golos_marcados;
    }

    public int getGolos_sofridos() {
        return golos_sofridos;
    }

    // END Getters ----------------------------------------------------------------

    // BEGIN toString Methods ----------------------------------------------------------------
    // Print headers
    public static String tableHeaders() {
        return String.format("| %-3s | %-25s | %-7s | %-20s | %-15s | %-10s | %-10s | %-14s | %-14s | %-14s |%n",
                "ID", "Nome", "Plantel", "Treinador", "Liga", "Cidade", "Pais", "Histórico", "Golos Marcados", "Golos Sofridos");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-25s | %-7s | %-20s | %-15s | %-10s | %-10s | %-22s | %-20s | %-22s |%n",
                getId(), getNome(), getPlantel(), getTreinador(), getLiga(), getCidade(), getPais(), getHistorico(), getGolos_marcados(), getGolos_sofridos());
    }
    // END toString Methods ----------------------------------------------------------------
}
