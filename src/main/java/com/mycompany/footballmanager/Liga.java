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
public class Liga implements Dados {
    // BEGIN Variables ----------------------------------------------------------------
    private int id;
    private String nome;
    private String pais;
    private ArrayList<Integer> equipas;
    private ArrayList<Integer> partidas;
    private int ranking_equipas;

    private Random random = new Random();
    private final String txtFilePath = "./src/main/java/com/mycompany/footballmanager/DB/ligas.txt";
    // END Variables ----------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Liga() {
        nome = randomTeam();
        pais = randomCountry();
        equipas = new ArrayList<>();
        partidas = new ArrayList<>();
        ranking_equipas = random.nextInt(1, 50);
    }

    public Liga(
            int id,
            String nome,
            String pais,
            ArrayList<Integer> equipas,
            ArrayList<Integer> partidas,
            int ranking_equipas
    ) {
        this.id = id;
        this.nome = nome;
        this.equipas = equipas;
        this.partidas = partidas;
        this.pais = pais;
        this.ranking_equipas = ranking_equipas;
    }
    // END Constructors ----------------------------------------------------------------

    // BEGIN Interface Methods ---------------------------------------------------------
    @Override
    public void insert() {
        Scanner scanner = new Scanner(System.in);
        boolean insertMore = true;

        while (insertMore) {
            getLigas();
            Menu.ligas.add(insereLiga());

            System.out.println("Deseja inserir outra Liga? (sim/nao)");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (!choice.equals("sim")) {
                insertMore = false;
            }
        }
    }

    public Liga insereLiga() {
        Liga liga = new Liga();
        Scanner scanner = new Scanner(System.in);
        int latest = 0;

        try {
            if (!Menu.ligas.isEmpty()) {
                // Obtém o ID da última liga, usando o tamanho do ArrayList e subtraindo 1
                latest = Menu.ligas.get(Menu.ligas.size() - 1).getId();
            }

            // Incrementa automaticamente o ID
            int increment = 1;
            liga.setId(latest + increment);

            System.out.println("Inserir Liga: ");
            // Nome
            try {
                System.out.println("Insira o Nome: ");
                String nome = scanner.nextLine();

                if (Menu.hasPontoEVirgulaString(nome)) { // Verifica se o nome contém ponto e vírgula
                    System.out.println("O Nome da Liga não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return insereLiga();
                } else {
                    liga.setNome(nome); // Define o nome da Liga
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereLiga();
            }

            // Pais
            try {
                System.out.println("Insira o País: ");
                String pais = scanner.nextLine();

                if (Menu.hasPontoEVirgulaString(pais)) {
                    System.out.println("O País da Liga não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return insereLiga();
                } else {
                    liga.setPais(pais); // Define o país da Liga
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereLiga();
            }

            // Equipas
            try {
                boolean insertMoreEquipas = true;
                ArrayList<Integer> EquipasIDs = new ArrayList<>(); // Cria um arrayList para os ids das Equipas
                Menu.equipa.print(); // imprime as equipas existentes
                int equipasSize = Menu.equipas.get(Menu.equipas.size() - 1).getId();

                while (insertMoreEquipas) {
                    System.out.println("Escolha um ID de uma Equipa: ");
                    int idEquipa = scanner.nextInt(); // recebe o id da Equipa
                    scanner.nextLine(); // Consume newline character

                    if (checkEquipaInLigas(idEquipa) || (EquipasIDs.contains(idEquipa))) {
                        System.out.println("Esta Equipa já tem numa Liga! Tente Novamente...");
                        continue;
                    } else if ((idEquipa > 0) &&
                        (idEquipa <= equipasSize) &&
                        (Menu.equipas.get(idEquipa - 1).getPais().equals(liga.getPais())))
                    {
                        EquipasIDs.add(idEquipa);
                    } else if (!(Menu.equipas.get(idEquipa - 1).getPais().equals(liga.getPais()))) {
                        System.out.println("A Equipa tem que ser do mesmo Pais que a Liga! Tente Novamente...");
                        continue;
                    } else {
                        System.out.println("Tem que escolher um ID existente das Equipas! Tente Novamente...");
                        continue;
                    }

                    System.out.println("Deseja adicionar mais Equipas à Liga? (sim/nao)");
                    String choice = scanner.nextLine().trim().toLowerCase();

                    if (!choice.equals("sim")) {
                        insertMoreEquipas = false;
                    }
                }
                liga.setEquipas(EquipasIDs); // guarda os ids das Equipas na Liga

            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereLiga();
            }

            // Partidas
            try {
                boolean insertMorePartidas = true;
                ArrayList<Integer> PartidasIDs = new ArrayList<>(); // Cria um arrayList para os ids das Partidas
                Menu.partida.print(); // imprime as partidas existentes
                int partidaSize = Menu.partidas.get(Menu.partidas.size() - 1).getId();

                while (insertMorePartidas) {
                    System.out.println("Escolha um ID de uma Partida: ");
                    int idPartida = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    Partida partidaValues = Menu.partidas.get(idPartida - 1);

                    if (checkPartidaInLigas(idPartida) || (PartidasIDs.contains(idPartida))) {
                        System.out.println("Esta Partida já tem numa Liga! Tente Novamente...");
                        continue;
                    } else if (idPartida > 0 && idPartida <= partidaSize) {
                        PartidasIDs.add(idPartida);
                    } else if (
                        !(liga.getPais().equals(partidaValues.getEquipaValues(partidaValues.getEquipaID()).getPais()))
                    ) {
                        System.out.println("A Partida tem que ter Equipas da mesma Liga! Tente Novamente...");
                        continue;
                    } else {
                        System.out.println("Tem que escolher um ID existente das Partidas! Tente Novamente...");
                        continue;
                    }

                    System.out.println("Deseja adicionar mais Partidas à Liga? (sim/nao)");
                    String choice = scanner.nextLine().trim().toLowerCase();

                    if (!choice.equals("sim")) {
                        insertMorePartidas = false;
                    }
                }
                liga.setPartidas(PartidasIDs); // guarda os ids das Partidas na Liga

            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereLiga();
            }

            try {
                System.out.println("Insira o Ranking das Equipas: ");
                int ranking_equipas = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (ranking_equipas >= 0 && ranking_equipas <= 100) {
                    liga.setRankingEquipas(ranking_equipas); // guarda o ranking das equipas
                } else {
                    System.out.println("O Ranking das Equipas tem que ter entre 0 e 100 anos, inclusive! Tente Novamente...");
                    return insereLiga();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: Não pode inserir strings neste campo\n");
                return insereLiga();
            }

        } catch (Exception e) {
            System.out.println("Input inválido: " + e.getMessage() + "\n");
            return insereLiga();
        }

        writeToTXT(liga); // Escreve as informações da Liga no ficheiro
        System.out.println(liga);
        System.out.println("Liga inserida com Sucesso!!!");

        return liga; // Retorna a Liga criada
    }

    // Metodo para inserir Liga no ficheiro TXT
    private void writeToTXT(Liga liga) {
        checkIfFileExists(txtFilePath);

        try (FileWriter writer = new FileWriter(txtFilePath, true)) { // Abre o ficheiro para escrita
            BufferedWriter bw = new BufferedWriter(writer); // Cria um BufferedWriter para escrever no ficheiro
            StringBuilder sb = new StringBuilder(); // Cria um StringBuilder para construir a linha de texto

            // Constrói a linha de texto com o seguinte formato para a Liga
            sb.append(liga.getId()).append(";"); // ID da Liga
            sb.append(liga.getNome()).append(";"); // Nome da Liga
            sb.append(liga.getPais()).append(";"); // País da Liga

            // Adiciona os IDs das equipas separados por vírgula
            for (Integer equipaID : liga.getEquipas()) {
                sb.append(equipaID).append(",");
            }
            sb.deleteCharAt(sb.length() - 1); // Remove a última vírgula
            sb.append(";"); // Adiciona um ponto e vírgula para separação

            // Adiciona os IDs das partidas com separador de vírgula
            for (Integer partidaID : liga.getPartidas()) {
                sb.append(partidaID).append(",");
            }
            sb.deleteCharAt(sb.length() - 1); // Remove a última vírgula
            sb.append(";"); // Adiciona um ponto e vírgula para separação

            sb.append(liga.getRankingEquipas()).append("\n"); // Adiciona o ranking das equipas e vai linha abaixo

            // Escreve a linha de texto no ficheiro
            bw.append(sb.toString());
            // Fecha o BufferedWriter
            bw.close();

        } catch (IOException e) { // Captura qualquer exceção de I/O que possa ocorrer
            System.out.println("Erro ao inserir Liga no ficheiro ligas.txt: " + e.getMessage());
        }
    }

    public void updateToTXT(Liga ligaToUpdate) {
        checkIfFileExists(txtFilePath);

        // Abre o ficheiro para leitura e cria um BufferedWriter para escrita no ficheiro
        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) {
            ArrayList<String> linhas = new ArrayList<>(); // Cria um ArrayList para armazenar as linhas de texto
            String row;
            boolean isFirstLine = true; // Flag para pular a primeira linha

            // Lê o ficheiro e armazena as linhas no ArrayList
            while ((row = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    linhas.add(row); // Adiciona o cabeçalho à lista sem alterações
                    continue;
                }
                linhas.add(row); // Lê as linhas existentes do ficheiro e adiciona à lista
            }

            // Percorre as linhas do ficheiro (excluindo o cabeçalho)
            for (int i = 1; i < linhas.size(); i++) {
                String[] data = linhas.get(i).split(";");
                int ligaIdFromFile = Integer.parseInt(data[0]); // Obtém o ID

                if (ligaIdFromFile == ligaToUpdate.getId()) {
                    // Atualiza a linha relacionada à Liga que está sendo atualizada
                    StringBuilder sb = new StringBuilder();
                    sb.append(ligaToUpdate.getId()).append(";"); // Atualiza o ID
                    sb.append(ligaToUpdate.getNome()).append(";"); // Atualiza o Nome
                    sb.append(ligaToUpdate.getPais()).append(";"); // Atualiza o País
                    // Adiciona os IDs das equipas separadas por vírgula
                    for (Integer equipaID : ligaToUpdate.getEquipas()) {
                        sb.append(equipaID).append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1); // Remove a última vírgula
                    sb.append(";"); // Adiciona ponto e vírgula
                    // Adiciona os IDs das partidas separadas por vírgula
                    for (Integer partidaID : ligaToUpdate.getPartidas()) {
                        sb.append(partidaID).append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1); // Remove a última vírgula
                    sb.append(";"); // Adiciona ponto e vírgula
                    sb.append(ligaToUpdate.getRankingEquipas()); // Atualiza o Ranking das Equipas

                    linhas.set(i, sb.toString()); // Define a linha atualizada no ArrayList
                    break; // Sai do loop
                }
            }

            // Escreve as linhas modificadas de volta no ficheiro
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(txtFilePath, false))) {
                for (String linha : linhas) {
                    bw.write(linha);
                    bw.newLine(); // Adiciona uma nova linha após cada linha, exceto a última
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao escrever no ficheiro ligas.txt: " + e.getMessage());
        }
    }

    @Override
    public void print() {
        getLigas();
        // Print the table Headers
        System.out.printf(tableHeaders());

        // Print details of all Ligas
        if (!Menu.ligas.isEmpty()) {
            for (Liga liga : Menu.ligas) {
                System.out.printf(liga.toString());
            }
        } else {
            System.out.println("\nNão existem Ligas!\n");
        }
    }

    public void getLigas() {
        checkIfFileExists(txtFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) {
            String row;
            boolean firstLine = true; // Flag to identify the first line
            ArrayList<Liga> ligas = new ArrayList<>(); // Create a new list for ligas

            while ((row = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Set the flag to false after encountering the first line
                    continue; // Skip processing the first line
                }

                String[] data = row.split(";");

                // TXT format: ID, Nome, Idade, Especializações e Taticas Favoritas
                Liga liga = new Liga();
                liga.setId(Integer.parseInt(data[0])); // ID
                liga.setNome(data[1]); // Nome
                liga.setPais(data[2]); // Pais

                String[] equipasIds = data[3].split(","); // gets the ids of the equipas
                ArrayList<Integer> equipas = new ArrayList<>();

                for (String equipaId : equipasIds) {
                    equipas.add(Integer.parseInt(equipaId));
                }
                liga.setEquipas(equipas); // Equipas

                String[] partidasIds = data[4].split(","); // gets the ids of the partidas
                ArrayList<Integer> partidas = new ArrayList<>();

                for (String equipaId : partidasIds) {
                    partidas.add(Integer.parseInt(equipaId));
                }
                liga.setPartidas(partidas); // Partidas
                liga.setRankingEquipas(Integer.parseInt(data[5])); // Ranking Equipas

                // Adds the liga to the ArrayList
                ligas.add(liga);
            }
            br.close();

            // Replaces the ArrayList from Menu class with the new ArrayList
            Menu.ligas = ligas;
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro ligas.txt: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        if (id > 0 && id < (Menu.ligas.size() - 1)) {
            Menu.ligas.remove(id);
            System.out.println("A Liga com o ID " + id + " foi removida com sucesso");
        } else {
            System.out.println("ID incorreto! Tente novamente...");
        }
    }

    public void removeLiga() throws IOException {
        Scanner scanner = new Scanner(System.in);
        getLigas(); // Gets an updated list of ligas
        print(); // prints the updated list

        System.out.println("Indique o ID da liga que pretende remover: ");
        int ligaID = scanner.nextInt();
        delete(ligaID);
//        removeFromTXT(ligaID, txtFilePath);
    }
    // END Interface Methods --------------------------------------------------------

    public void associarEquipa() {
        Scanner scanner = new Scanner(System.in);

        try {
            getLigas();
            Menu.liga.print(); // imprime as ligas existentes
            System.out.println("Selecione a Liga que pretende associar: ");
            int idLiga = scanner.nextInt();
            scanner.nextLine();
            int ligaSize = Menu.ligas.get(Menu.ligas.size() - 1).getId();
            int idLigaAjustado = (idLiga - 1);

            if ((idLiga > ligaSize) || idLiga >= 0) {
                System.out.println("Tem que escolher um ID existente das Ligas! Tente Novamente...");
                associarEquipa();
            }

            boolean insertMoreEquipas = true;
            ArrayList<Integer> EquipasIDs = new ArrayList<>(); // Cria um arrayList para os ids das Equipas
            // Propagar as equipas existentes na liga selecionada para poder adicionar mais equipas
            Menu.equipa.print(); // imprime as equipas existentes
            int equipasSize = Menu.equipas.get(Menu.equipas.size() - 1).getId();

            while (insertMoreEquipas) {
                System.out.println("Escolha um ID de uma Equipa: ");
                int idEquipa = scanner.nextInt(); // recebe o id da Equipa
                scanner.nextLine(); // Consume newline character
                int idEquipaAjustado = (idEquipa - 1);

                if (checkEquipaInLigas(idEquipa) || (EquipasIDs.contains(idEquipa))) {
                    System.out.println("Esta Equipa já tem numa Liga! Tente Novamente...");
                    continue;
                } else if ((idEquipa > 0) &&
                    (idEquipa <= equipasSize) &&
                    (Menu.equipas.get(idEquipaAjustado).getPais().equals(Menu.ligas.get(idLigaAjustado).getPais())))
                {
                    EquipasIDs.add(idEquipa); // Adiciona a Equipa selecionada ao ArrayList de Integers
                    Menu.equipas.get(idEquipaAjustado).setIdLiga(idLiga); // Associa a Liga selecionada à equipa
                    Menu.equipa.updateToTXT(Menu.equipas.get(idEquipaAjustado)); // Atualiza no ficheiro
                    System.out.println("Equipa da Liga: " + Menu.equipas.get(idEquipaAjustado).getNomeLiga(Menu.equipas.get(idEquipaAjustado).getIdLiga()));
                } else if (!(Menu.equipas.get(idEquipaAjustado).getPais().equals(Menu.ligas.get(idLigaAjustado).getPais()))) {
                    System.out.println("A Equipa tem que ser do mesmo Pais que a Liga! Tente Novamente...");
                    continue;
                } else {
                    System.out.println("Tem que escolher um ID existente das Equipas! Tente Novamente...");
                    continue;
                }

                System.out.println("Deseja adicionar mais Equipas à Liga? (sim/nao)");
                String choice = scanner.nextLine().trim().toLowerCase();

                if (!choice.equals("sim")) {
                    insertMoreEquipas = false;
                }
            }
            Menu.ligas.get(idLigaAjustado).setEquipas(EquipasIDs); // guarda os ids das Equipas na Liga
            updateToTXT(Menu.ligas.get(idLigaAjustado)); // Atualiza o Ficheiro

            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("Equipas associadas a Liga " + Menu.ligas.get(idLigaAjustado).getNome() +
                ": " + String.join(", ",
                    Menu.ligas.get(idLigaAjustado).getNomesEquipas(
                            Menu.ligas.get(idLigaAjustado).getEquipas()
                    )
                )
            );

        } catch (Exception e) {
            System.out.println("Input inválido: " + e.getMessage() + "\n");
            associarEquipa();
        }
    }

    public boolean checkEquipaInLigas(int id) {
        for (Liga liga : Menu.ligas) {
            for (int equipaID : liga.getEquipas()) {
                if (id == equipaID) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkPartidaInLigas(int id) {
        for (Liga liga : Menu.ligas) {
            for (int equipaID : liga.getPartidas()) {
                if (id == equipaID) {
                    return true;
                }
            }
        }
        return false;
    }

    // BEGIN Getters and Setters ----------------------------------------------------------------
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public ArrayList<Integer> getEquipas() {
        return equipas;
    }

    public ArrayList<String> getNomesEquipas(ArrayList<Integer> equipas) {
        ArrayList<String> nomesEquipas = new ArrayList<>();

        for (Equipa equipa : Menu.equipas) {
            for (Integer equipaID : equipas) {
                if (!equipas.isEmpty()) {
                    if (equipa.getId() == equipaID) {
                        nomesEquipas.add(equipa.getNome());
                    }
                } else {
                    nomesEquipas.add("Sem Equipas associados");
                }
            }
        }
        return nomesEquipas;
    }

    public void setEquipas(ArrayList<Integer> equipas) {
        this.equipas = equipas;
    }

    public ArrayList<Integer> getPartidas() {
        return partidas;
    }

    public ArrayList<String> getNomesPartidas(ArrayList<Integer> partidas) {
        ArrayList<String> nomesPartidas = new ArrayList<>();

        for (Partida partida : Menu.partidas) {
            for (Integer partidaID : partidas) {
                if (!partidas.isEmpty()) {
                    if (partida.getId() == partidaID) {
                        nomesPartidas.add(partida.getNome());
                    }
                } else {
                    nomesPartidas.add("Sem Partidas associados");
                }
            }
        }
        return nomesPartidas;
    }

    public void setPartidas(ArrayList<Integer> partidas) {
        this.partidas = partidas;
    }

    public int getRankingEquipas() {
        return ranking_equipas;
    }

    public void setRankingEquipas(int ranking_equipas) {
        this.ranking_equipas = ranking_equipas;
    }
    // END Getters and Setters ----------------------------------------------------------------

    // Print headers
    public static String tableHeaders() {
        System.out.println("|---------------------------------------------------------------------------------------------- LIGAS --------------------------------------------------------------------------------------------------------|");
        return String.format("| %-3s | %-15s | %-10s | %-70s | %-70s | %-20s |%n",
                "ID", "Nome", "País", "Equipas", "Partidas", "Ranking de Equipas");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-15s | %-10s | %-70s | %-70s | %-20s |%n",
                getId(),
                getNome(),
                getPais(),
                String.join(", ", getNomesEquipas(getEquipas())),
                String.join(", ", getNomesPartidas(getPartidas())),
                getRankingEquipas()
        );
    }
}
