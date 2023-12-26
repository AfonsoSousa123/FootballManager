/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;


import com.mycompany.footballmanager.Interfaces.Dados;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static com.mycompany.footballmanager.Menu.*;

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

    private Random random = new Random();
    private final String txtFilePath = "./src/main/java/com/mycompany/footballmanager/DB/equipas.txt"; // File Path
    // END Variables ------------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Equipa() {
        nome = randomTeam();
        idLiga = 0;
        idTreinador = 0;
        cidade = randomCity();
        plantel = new ArrayList<>();
        pais = randomCountry();
    }

    public Equipa(
            int id,
            String nome,
            ArrayList<Integer> plantel,
            int idTreinador,
            int idLiga,
            String cidade,
            String pais
    ) {
        this.id = id;
        this.plantel = plantel;
        this.idTreinador = idTreinador;
        this.idLiga = idLiga;
        this.nome = nome;
        this.cidade = cidade;
        this.pais = pais;
    }
    // END Constructors ----------------------------------------------------------------

    // BEGIN Interface Methods ----------------------------------------------------------------
    @Override
    public void insert() {
        Scanner scanner = new Scanner(System.in);
        boolean insertMore = true;

        while (insertMore) {
            getEquipas(); // Updates the Equipas ArrayList
            Menu.equipas.add(insereEquipa());

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
        int treinadoresSize = Menu.treinadores.get(Menu.treinadores.size() - 1).getId();
        int jogadoresSize = Menu.jogadores.get(Menu.jogadores.size() - 1).getId();

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
                Menu.jogador.print();
                boolean insertMoreJogadores = true;
                ArrayList<Integer> JogadoresIDs = new ArrayList<>(); // Cria um arrayList para os IDs dos Jogadores

                while (insertMoreJogadores) {
                    System.out.println("Escolha o ID do Jogador que pretende adicionar ao Plantel: ");
                    int idJogador = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    if (checkJogadorInEquipas(idJogador) || (JogadoresIDs.contains(idJogador))) {
                        System.out.println("Este Jogador já tem numa equipa! Tente Novamente...");
                        continue;
                    } else if ((idJogador > 0) && (idJogador <= jogadoresSize)) {
                        JogadoresIDs.add(idJogador);
                    } else {
                        System.out.println("Tem que escolher um ID existente das Jogadores! Tente Novamente...");
                        continue;
                    }

                    System.out.println("Deseja adicionar mais Jogadores ao Plantel? (sim/nao)");
                    String choicePlantel = scanner.nextLine().trim().toLowerCase();

                    if (!choicePlantel.equals("sim")) {
                        insertMoreJogadores = false;
                    }
                }
                equipa.setPlantel(JogadoresIDs);
                System.out.println("Plantel: " + String.join(", ", equipa.getNomesJogadores(equipa.getPlantel())));

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

                if (checkTreinadorInEquipas(treinadorID)) {
                    System.out.println("Este Treinador já tem numa equipa! Tente Novamente...");
                    return insereEquipa();
                } else if ((treinadorID > 0) && (treinadorID <= treinadoresSize)) {
                    equipa.setIdTreinador(treinadorID);
                } else {
                    System.out.println("Tem que escolher um ID existente dos Treinadores! Tente Novamente...");
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

        } catch (Exception e) {
            System.out.println("Input inválido: " + e.getMessage() + "\n");
            return insereEquipa();
        }

        writeToTXT(equipa);
        System.out.println(equipa);

        return equipa;
    }

    // Method to write Equipa data to a TXT file
    public void writeToTXT(Equipa equipa) {
        checkIfFileExists(txtFilePath);

        try (FileWriter writer = new FileWriter(txtFilePath, true)) {
            BufferedWriter bw = new BufferedWriter(writer);
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
            sb.append(equipa.getIdTreinador()).append(";"); // get Treinador ID
            sb.append(equipa.getIdLiga()).append(";"); // get Liga ID
            sb.append(equipa.getCidade()).append(";"); // get Cidade
            sb.append(equipa.getPais()).append("\n"); // get Pais

            // Write the line to the file
            bw.append(sb.toString());
            // closes the output stream
            bw.close();

        } catch (IOException e) {
            System.out.println("Erro ao inserir Equipa no ficheiro equipas.txt: " + e.getMessage());
        }
    }

    public void updateToTXT(Equipa equipaToUpdate) {
        checkIfFileExists(txtFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) {
            ArrayList<String> linhas = new ArrayList<>();
            String row;
            boolean isFirstLine = true; // Flag to skip the first line

            while ((row = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    linhas.add(row); // Add the header to the list without changes
                    continue;
                }
                linhas.add(row); // Read existing linhas from the file into a list
            }

            for (int i = 1; i < linhas.size(); i++) { // Start loop from index 1 to skip the first line
                String[] data = linhas.get(i).split(";");
                int equipaIdFromFile = Integer.parseInt(data[0]); // Equipa ID

                if (equipaIdFromFile == equipaToUpdate.getId()) {
                    // Update the line related to the Equipa being updated
                    StringBuilder sb = new StringBuilder();
                    sb.append(equipaToUpdate.getId()).append(";"); // Update ID
                    sb.append(equipaToUpdate.getNome()).append(";"); // Update Nome
                    // Append the plantel elements with comma separator
                    for (Integer jogador : equipaToUpdate.getPlantel()) { // Plantel
                        sb.append(jogador).append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1); // Remove the last comma
                    sb.append(";"); // Append semicolon
                    sb.append(equipaToUpdate.getIdTreinador()).append(";"); // Update Treinador ID
                    sb.append(equipaToUpdate.getIdLiga()).append(";"); // Update Liga ID
                    sb.append(equipaToUpdate.getCidade()).append(";"); // Update Cidade
                    sb.append(equipaToUpdate.getPais()); // Update Pais

                    linhas.set(i, sb.toString()); // Set the updated line in the list
                    break; // Exit loop as the update is done
                }
            }

            // Write the modified lines back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(txtFilePath, false))) {
                for (String linha : linhas) {
                    bw.write(linha);
                    bw.newLine(); // Add a new line after each line
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao escrever no ficheiro equipas.txt: " + e.getMessage());
        }
    }

    public boolean checkJogadorInEquipas(int id) {
        for (Equipa eq : Menu.equipas) {
            for (int jogadorID : eq.getPlantel()) {
                if (id == jogadorID) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkTreinadorInEquipas(int id) {
        for (Equipa eq : Menu.equipas) {
            if (id == eq.getIdTreinador()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void print() {
        getEquipas();
        // Print the table Headers
        System.out.printf(tableHeaders());

        // Print details of all players using a loop
        if (!equipas.isEmpty()) {
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

                // TXT format: ID, Nome, Plantel, Treinador, Liga, Cidade, País
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

                // Adds the equipa to the ArrayList
                equipas.add(equipa);
            }
            br.close();

            // Replaces the ArrayList from Menu class with the new ArrayList
            Menu.equipas = equipas;
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro equipas.txt: " + e.getMessage());
        }
    }

    // BEGIN Faker Methods ----------------------------------------------------------------
    @Override
    public void insertFaker() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Quantas Equipas quer gerar? ");
            int numOfChoices = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (numOfChoices < 0 || numOfChoices > 2) {
                System.out.println("Só pode inserir no maximo 2 de cada vez! Tente Novamente...");
                insertFaker();
            }

            for (int i = 0; i < numOfChoices; i++) {
                // Automatically increments the ID
                int increment = 1;
                int latest = 0;
                // if the equipas ArrayList is not empty
                if (!Menu.equipas.isEmpty()) {
                    // Gets the ID of the latest equipa, using the size of the ArrayList and decrementing 1
                    latest = Menu.equipas.get(Menu.equipas.size() - 1).getId();
                }

                Equipa equipa = new Equipa(
                    latest + increment, // ID automatically increments
                    randomTeam(), // Random Nome
                    generateJogadores(11), // Plantel
                    random.nextInt(0, Menu.treinadores.size()), // Treinador ID
                    random.nextInt(0, Menu.ligas.size()), // Liga ID
                    randomCity(), // Cidade
                    randomCountry() // Pais
                );
                equipas.add(equipa); // Adds the new Equipa to the Equipaes ArrayList

                writeToTXT(equipa); // Writes the Equipa to the TXT File
            }

            System.out.println(numOfChoices + " Equipas Geradas com sucesso!");
            System.out.println("--------------------------------");
        } catch (Exception e) {
            System.out.println("Erro ao inserir Equipa no ficheiro equipas.txt: " + e.getMessage());
        }
    }

    private ArrayList<Integer> generateJogadores(int numJogadores) {
        ArrayList<Integer> randomPlantel = new ArrayList<>();

        try {
            for (int i = 0; i < numJogadores; i++) {
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
                    randomName(), // Random Nome
                    random.nextInt(20, 40), // Random Idade
                    Menu.jogador.getRandomPosicao(), // Random Posição
                    randomLorem(), // Random Historico de Lesões
                    random.nextInt(1, 100), // Random Ataque
                    random.nextInt(1, 100), // Random Defesa
                    random.nextInt(1, 100) // Random Nivel de Agressividade
                );
                randomPlantel.add(jogador.getId()); // Adds the jogadores Ids to the randomPlantel
                jogadores.add(jogador); // Adds the new Jogador to the Jogadores ArrayList

                jogador.writeToTXT(jogador); // Writes the Jogador to the TXT File
            }
            System.out.println("Plantel inserido e associado com sucesso");
        } catch (Exception e) {
            System.out.println("Erro ao inserir Jogador no ficheiro jogadores.txt: " + e.getMessage());
        }

        return randomPlantel;
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

    public ArrayList<String> getNomesJogadores(ArrayList<Integer> plantel) {
        ArrayList<String> nomesJogadores = new ArrayList<>();

        for (Jogador jogador : Menu.jogadores) {
            for (Integer jogadorID : plantel) {
                if (!plantel.isEmpty()) {
                    if (jogador.getId() == jogadorID) {
                        nomesJogadores.add(jogador.getNome());
                    }
                } else {
                    nomesJogadores.add("Sem Jogadores associados");
                }
            }
        }
        return nomesJogadores;
    }

    public ArrayList<Jogador> getJogadoresValues(ArrayList<Integer> plantel) {
        ArrayList<Jogador> jogadoresSelecionados = new ArrayList<>();

        for (Jogador jogador : Menu.jogadores) {
            for (Integer jogadorID : plantel) {
                if (!plantel.isEmpty()) {
                    if (jogador.getId() == jogadorID) {
                        jogadoresSelecionados.add(jogador);
                    }
                }
            }
        }
        return jogadoresSelecionados;
    }

    public ArrayList<Integer> getPlantel() {
        return plantel;
    }

    public void setPlantel(ArrayList<Integer> plantel) {
        this.plantel = plantel;
    }

    public int getIdTreinador() {
        return idTreinador;
    }

    public String getNomeTreinador(int id) {
        for (Treinador treinador : Menu.treinadores) {
            if (treinador.getId() == id) {
                return treinador.getNome();
            }
        }
        return "Sem Treinador associado"; // Retorna um valor predefinido se o id não for encontrado
    }

    public void setIdTreinador(int idTreinador) {
        this.idTreinador = idTreinador;
    }

    public int getIdLiga() {
        return idLiga;
    }

    public String getNomeLiga(int id) {
        for (Liga liga : Menu.ligas) {
            if (liga.getId() == id) {
                return liga.getNome();
            }
        }
        return "Sem Liga associada"; // Retorna um valor predefinido se o id não for encontrado
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
    // END Getters and Setters ----------------------------------------------------------------

    // BEGIN toString Methods ----------------------------------------------------------------
    // Print headers
    public static String tableHeaders() {
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------- EQUIPAS --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
        return String.format("| %-3s | %-25s | %-200s | %-25s | %-20s | %-25s | %-25s |%n",
                "ID", "Nome", "Plantel", "Treinador", "Liga", "Cidade", "Pais");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-25s | %-200s | %-25s | %-20s | %-25s | %-25s |%n",
                getId(),
                getNome(),
                String.join(", ", getNomesJogadores(getPlantel())),
                getNomeTreinador(getIdTreinador()),
                getNomeLiga(getIdLiga()),
                getCidade(),
                getPais()
        );
    }
    // END toString Methods ----------------------------------------------------------------
}
