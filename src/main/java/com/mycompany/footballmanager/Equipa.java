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
        Equipa equipa = new Equipa(); // Cria uma nova instância da Equipa
        Scanner scanner = new Scanner(System.in);
        int latest = 0;
        int treinadoresSize = Menu.treinadores.get(Menu.treinadores.size() - 1).getId(); // Obtém o tamanho da lista de treinadores
        int jogadoresSize = Menu.jogadores.get(Menu.jogadores.size() - 1).getId(); // Obtém o tamanho da lista de jogadores

        try {
            if (!Menu.equipas.isEmpty()) { // Verifica se a lista de equipas não está vazia
                // Obtém o ID da última equipa usando o tamanho da lista e decrementando 1
                latest = Menu.equipas.get(Menu.equipas.size() - 1).getId();
            }

            int increment = 1; // Incremento para gerar um novo ID para a nova equipa
            equipa.setId(latest + increment); // Define o ID da nova equipa automaticamente

            System.out.println("Inserir Equipa: ");
            // Nome
            try {
                System.out.println("Insira o Nome: ");
                String nome = scanner.nextLine();

                if (Menu.hasPontoEVirgulaString(nome)) {
                    System.out.println("O Nome da Equipa não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return insereEquipa();
                } else {
                    equipa.setNome(nome); // Define o nome da equipa
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
                        System.out.println("Este Jogador já tem numa Equipa! Tente Novamente...");
                        continue;
                    } else if ((idJogador > 0) && (idJogador <= jogadoresSize)) {
                        JogadoresIDs.add(idJogador); // Adiciona os IDs dos jogadores na Equipa
                    } else {
                        System.out.println("Tem que escolher um ID existente dos Jogadores! Tente Novamente...");
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

        writeToTXT(equipa); // Chama o método writeToTXT para escrever a nova equipa no ficheiro
        System.out.println(equipa); // Imprime a equipa

        return equipa; // Retorna a nova equipa criada
    }

    // Method to write Equipa data to a TXT file
    public void writeToTXT(Equipa equipa) {
        checkIfFileExists(txtFilePath); // Verifica se o ficheiro existe

        try (FileWriter writer = new FileWriter(txtFilePath, true)) {
            BufferedWriter bw = new BufferedWriter(writer); // Cria um buffer de escrita para escrever no ficheiro
            StringBuilder sb = new StringBuilder(); // Cria um StringBuilder para construir a linha a ser escrita

            // Constrói a linha do ficheiro TXT
            sb.append(equipa.getId()).append(";"); // Adiciona o ID da Equipa
            sb.append(equipa.getNome()).append(";"); // Adiciona o Nome da Equipa
            // Adiciona os elementos do plantel com separador de vírgula
            for (Integer jogador : equipa.getPlantel()) { // Itera sobre o plantel da Equipa
                sb.append(jogador).append(","); // Adiciona cada ID de jogador com uma vírgula como separador
            }
            sb.deleteCharAt(sb.length() - 1); // Remove a última vírgula (se houver)
            sb.append(";");
            sb.append(equipa.getIdTreinador()).append(";"); // Adiciona o ID do Treinador da Equipa
            sb.append(equipa.getIdLiga()).append(";"); // Adiciona o ID da Liga à Equipa pertencente
            sb.append(equipa.getCidade()).append(";"); // Adiciona a Cidade da Equipa
            sb.append(equipa.getPais()).append("\n"); // Adiciona o País da Equipa e uma nova linha

            // Escreve a linha no ficheiro usando o BufferedWriter
            bw.append(sb.toString());
            bw.close(); // Fecha o fluxo de saída

        } catch (IOException e) {
            System.out.println("Erro ao inserir Equipa no ficheiro equipas.txt: " + e.getMessage());
        }
    }

    public void updateToTXT(Equipa equipaToUpdate) {
        checkIfFileExists(txtFilePath); // Verifica se o ficheiro existe

        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) {
            ArrayList<String> linhas = new ArrayList<>();
            String row;
            boolean isFirstLine = true; // Flag para pular a primeira linha

            while ((row = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    linhas.add(row); // Adiciona o cabeçalho à lista sem alterações
                    continue;
                }
                linhas.add(row); // Lê as linhas existentes do ficheiro e adiciona à lista
            }

            for (int i = 1; i < linhas.size(); i++) { // Inicia o loop a partir do índice 1 para salta a primeira linha
                String[] data = linhas.get(i).split(";");
                int equipaIdFromFile = Integer.parseInt(data[0]); // ID da Equipa

                if (equipaIdFromFile == equipaToUpdate.getId()) {
                    // Atualiza a linha relacionada à Equipa que está sendo atualizada
                    StringBuilder sb = new StringBuilder();
                    sb.append(equipaToUpdate.getId()).append(";"); // Atualiza o ID
                    sb.append(equipaToUpdate.getNome()).append(";"); // Atualiza o Nome
                    // Adiciona os elementos do plantel com separador de vírgula
                    for (Integer jogador : equipaToUpdate.getPlantel()) { // Plantel
                        sb.append(jogador).append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1); // Remove a última vírgula
                    sb.append(";"); // Adiciona ponto e vírgula
                    sb.append(equipaToUpdate.getIdTreinador()).append(";"); // Atualiza o ID do Treinador
                    sb.append(equipaToUpdate.getIdLiga()).append(";"); // Atualiza o ID da Liga
                    sb.append(equipaToUpdate.getCidade()).append(";"); // Atualiza a Cidade
                    sb.append(equipaToUpdate.getPais()); // Atualiza o País

                    linhas.set(i, sb.toString()); // Define a linha atualizada na lista
                    break; // Sai do loop, pois a atualização está concluída
                }
            }

            // Escreve as linhas modificadas de volta para o ficheiro
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(txtFilePath, false))) {
                for (String linha : linhas) {
                    bw.write(linha);
                    bw.newLine(); // Adiciona uma nova linha após cada linha
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao escrever no ficheiro equipas.txt: " + e.getMessage());
        }
    }

    public boolean checkJogadorInEquipas(int id) {
        for (Equipa eq : Menu.equipas) { // Itera sobre todas as equipas
            for (int jogadorID : eq.getPlantel()) { // Itera sobre os IDs dos jogadores no plantel de cada equipa
                if (id == jogadorID) { // Verifica se o ID fornecido corresponde a algum jogador na equipa
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkTreinadorInEquipas(int id) {
        for (Equipa eq : Menu.equipas) { // Itera sobre todas as equipas no Menu
            if (id == eq.getIdTreinador()) { // Verifica se o ID fornecido corresponde ao ID do treinador da equipa
                return true;
            }
        }
        return false;
    }

    @Override
    public void print() {
        getEquipas(); // Obtém as equipas dos ficheiros

        // Imprime os cabeçalhos da tabela
        System.out.printf(tableHeaders());

        // Imprime os detalhes de todas as equipas usando um loop
        if (!Menu.equipas.isEmpty()) { // Verifica se a lista de equipas não está vazia
            for (Equipa equipa : Menu.equipas) { // Itera sobre cada equipa na lista
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
            scanner.nextLine(); // Consome a proxima linha

            if (numOfChoices < 0 || numOfChoices > 2) { // Verifica se o número de escolhas está dentro do intervalo especificado
                System.out.println("Só pode inserir no maximo 2 de cada vez! Tente Novamente...");
                insertFaker(); // Chama recursivamente o método para inserção novamente em caso de valor inválido
            }

            for (int i = 0; i < numOfChoices; i++) { // Loop para criar o número especificado de equipas
                int increment = 1;
                int latest = 0;

                if (!Menu.equipas.isEmpty()) { // Verifica se a lista de equipas não está vazia
                    latest = Menu.equipas.get(Menu.equipas.size() - 1).getId(); // Obtém o último ID de equipa na lista
                }

                // Cria uma nova equipa com dados aleatórios e IDs autoincrementados
                Equipa equipa = new Equipa(
                        latest + increment, // ID automaticamente incrementado
                        randomTeam(), // Nome aleatório
                        generateJogadores(11), // Cria um plantel de 11 jogadores aleatórios
                        random.nextInt(0, Menu.treinadores.size()), // ID do treinador aleatório
                        random.nextInt(0, Menu.ligas.size()), // ID da liga aleatória
                        randomCity(), // Cidade aleatória
                        randomCountry() // País aleatório
                );

                equipas.add(equipa); // Adiciona a nova equipa à lista de equipas
                writeToTXT(equipa); // Escreve os dados da equipa no arquivo TXT
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
            for (int i = 0; i < numJogadores; i++) { // Loop para criar o número especificado de jogadores
                int increment = 1;
                int latest = 0;

                if (!Menu.jogadores.isEmpty()) { // Verifica se a lista de jogadores não está vazia
                    latest = Menu.jogadores.get(Menu.jogadores.size() - 1).getId(); // Obtém o último ID de jogador na lista
                }

                // Cria um novo jogador com dados aleatórios e IDs autoincrementados
                Jogador jogador = new Jogador(
                    latest + increment, // ID automaticamente incrementado
                    randomName(), // Nome aleatório
                    random.nextInt(20, 40), // Idade aleatória entre 20 e 40 anos
                    Menu.jogador.getRandomPosicao(), // Posição aleatória do jogador
                    randomLorem(), // Histórico de lesões aleatório
                    random.nextInt(1, 100), // Valor de ataque aleatório
                    random.nextInt(1, 100), // Valor de defesa aleatório
                    random.nextInt(1, 100) // Nível de agressividade aleatório
                );

                randomPlantel.add(jogador.getId()); // Adiciona os IDs dos jogadores ao plantel aleatório
                Menu.jogadores.add(jogador); // Adiciona o novo jogador à lista de jogadores
                jogador.writeToTXT(jogador); // Escreve os dados do jogador no arquivo TXT
            }

            System.out.println("Plantel inserido e associado com sucesso");
        } catch (Exception e) {
            System.out.println("Erro ao inserir Jogador no ficheiro jogadores.txt: " + e.getMessage());
        }

        return randomPlantel; // Retorna a lista de IDs dos jogadores criados
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
        ArrayList<String> nomesJogadores = new ArrayList<>(); // Cria uma lista para armazenar os nomes dos jogadores

        for (Jogador jogador : Menu.jogadores) { // Percorre a lista de jogadores no Menu
            for (Integer jogadorID : plantel) { // Percorre os IDs dos jogadores fornecidos
                if (!plantel.isEmpty()) { // Verifica se a lista de IDs não está vazia
                    if (jogador.getId() == jogadorID) { // Compara os IDs para encontrar correspondências
                        nomesJogadores.add(jogador.getNome()); // Adiciona o nome do jogador à lista se houver correspondência
                    }
                } else { // Se a lista de IDs estiver vazia
                    nomesJogadores.add("Sem Jogadores associados");
                }
            }
        }
        return nomesJogadores; // Retorna a lista de nomes dos jogadores correspondentes aos IDs fornecidos
    }

    public ArrayList<Jogador> getJogadoresValues(ArrayList<Integer> plantel) {
        ArrayList<Jogador> jogadoresSelecionados = new ArrayList<>(); // Cria uma lista para armazenar os jogadores selecionados

        for (Jogador jogador : Menu.jogadores) { // Percorre a lista de jogadores no Menu
            for (Integer jogadorID : plantel) { // Percorre os IDs dos jogadores fornecidos
                if (!plantel.isEmpty()) { // Verifica se a lista de IDs não está vazia
                    if (jogador.getId() == jogadorID) { // Compara os IDs para encontrar correspondências
                        jogadoresSelecionados.add(jogador); // Adiciona o jogador à lista se houver correspondência
                    }
                }
            }
        }
        return jogadoresSelecionados; // Retorna a lista de jogadores correspondentes aos IDs fornecidos
    }

    public ArrayList<Treinador> getTreinadoresValues(int idTreinador) {
        ArrayList<Treinador> treinadoresSelecionados = new ArrayList<>(); // Cria uma lista para armazenar os jogadores selecionados

        for (Treinador treinador : treinadores) { // Percorre a lista de jogadores no Menu
            if (treinador.getId() == idTreinador) { // Compara os IDs para encontrar correspondências
                treinadoresSelecionados.add(treinador); // Adiciona o jogador à lista se houver correspondência
            }

        }
        return treinadoresSelecionados; // Retorna a lista de jogadores correspondentes aos IDs fornecidos
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
                String.join(", ", getNomesJogadores(getPlantel())), // Separa os nomes dos Jogadores por virgulas, retirando "[]"
                getNomeTreinador(getIdTreinador()),
                getNomeLiga(getIdLiga()),
                getCidade(),
                getPais()
        );
    }
    // END toString Methods ----------------------------------------------------------------
}
