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
            ligas.add(insereLiga());

            System.out.println("Deseja inserir outra Liga? (sim/nao)");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (!choice.equals("sim")) {
                insertMore = false;
            }
        }

//        scanner.nextLine(); // Consume newline character
//        scanner.close(); // Close Scanner after use
    }

    public Liga insereLiga() {
        Liga liga = new Liga();
        Scanner scanner = new Scanner(System.in);
        int latest = 0;

        try {
            if (!Menu.ligas.isEmpty()) {
                // Gets the ID of the latest liga, using the size of the ArrayList and decrementing 1
                latest = Menu.ligas.get(Menu.ligas.size() - 1).getId();
            }

            // Automatically increments the ID
            int increment = 1;
            liga.setId(latest + increment);

            System.out.println("Inserir Liga: ");
            try {
                System.out.println("Insira o País: ");
                String pais = scanner.nextLine();
                if (Menu.hasPontoEVirgulaString(pais)) {
                    System.out.println("O País da Liga não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return insereLiga();
                } else {
                    liga.setPais(pais);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereLiga();
            }

            // Equipas
            try {
                boolean insertMoreEquipas = true;
                ArrayList<Integer> EquipasIDs = new ArrayList<Integer>(); // Cria um arrayList para os ids das Equipas
                Menu.equipa.print();

                while (insertMoreEquipas) {
//                    getEquipas();
                    System.out.println("Escolha um id de uma Equipa: ");
                    int idEquipa = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    if (idEquipa > 0 && idEquipa <= Menu.equipas.size()) {
                        for (int i = 0; i < Menu.equipas.size(); i++) {
                            EquipasIDs.add(idEquipa);
                        }
                    } else {
                        System.out.println("Tem que escolher um ID existente das Equipas! Tente Novamente...");
                        return insereLiga();
                    }

                    System.out.println("Deseja adicionar mais Equipas à Liga? (sim/nao)");
                    String choice = scanner.nextLine().trim().toLowerCase();

                    if (!choice.equals("sim")) {
                        insertMoreEquipas = false;
                    }
                }
                setEquipas(EquipasIDs);

            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereLiga();
            }

            // Partidas
            try {
                boolean insertMoreEquipas = true;
                ArrayList<Integer> PartidasIDs = new ArrayList<>(); // Cria um arrayList para os ids das Equipas
//                Menu.partida.print();

                while (insertMoreEquipas) {
//                    getPartidas();
                    System.out.println("Escolha um id de uma Equipa: ");
                    int idPartida = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    if (idPartida > 0 && idPartida <= Menu.partidas.size()) {
                        for (int i = 0; i < Menu.partidas.size(); i++) {
                            PartidasIDs.add(idPartida);
                        }
                    } else {
                        System.out.println("Tem que escolher um ID existente das Partidas! Tente Novamente...");
                        return insereLiga();
                    }

                    System.out.println("Deseja adicionar mais Partidas à Liga? (sim/nao)");
                    String choice = scanner.nextLine().trim().toLowerCase();

                    if (!choice.equals("sim")) {
                        insertMoreEquipas = false;
                    }
                }
                setEquipas(PartidasIDs);

            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereLiga();
            }

            try {
                System.out.println("Insira o Ranking das Equipas: ");
                int ranking_equipas = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (ranking_equipas >= 0 && ranking_equipas <= 100) {
                    liga.setRankingEquipas(ranking_equipas);
                } else {
                    System.out.println("O Ranking das Equipas tem que ter entre 30 e 70 anos, inclusive! Tente Novamente...");
                    return insereLiga();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereLiga();
            }

        } catch (Exception e) {
            System.out.println("Input inválido: " + e.getMessage() + "\n");
            return insereLiga();
        } finally {
//            scanner.close();
        }

        writeToTXT(liga);

        return liga;
    }

    // Method to write Liga data to a TXT file
    private void writeToTXT(Liga liga) {
        checkIfFileExists(txtFilePath);

        try (FileWriter writer = new FileWriter(txtFilePath, true)) {
            StringBuilder sb = new StringBuilder();

            // Construct the TXT line
            sb.append(liga.getId()).append(";");
            sb.append(liga.getNome()).append(";");
//            sb.append(liga.getIdade()).append(";");
//            sb.append(liga.getEspecializacoes()).append(";");
//            sb.append(liga.getTaticas_fav()).append("\n");

            // Write the TXT line to the file
            writer.append(sb.toString());
            // closes the output stream
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void print() {
        getLigas();

        // Print details of all Ligaes
        if (!ligas.isEmpty()) {
            // Print the table Headers
            System.out.printf(tableHeaders());

            for (Liga liga : ligas) {
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
                liga.setNome(data[2]); // Pais

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

            // Replaces the ArrayList from Menu class with the new ArrayList
            Menu.ligas = ligas;
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
        if (id > 0 && id < (ligas.size() - 1)) {
            ligas.remove(id);
            System.out.println("A Liga com o ID " + id + " foi removida com sucesso");
        } else {
            System.out.println("ID incorreto! Tente novamente...");
        }
    }

    /*public void removeFromTXT(int id, String file) throws IOException {
        checkIfFileExists(file);

        // Cria um scanner para ler o ficheiro txt e cria un ficheiro temporario player_data no qual vai ser escrito os dados sem o joador a ser eliminado
        try (Scanner scanner = new Scanner(new File(file))) {
            File tempFile = File.createTempFile("player_data", ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (!line.startsWith(String.valueOf(id))) {
                        writer.write(line + "\n");
                    }
                }
            }

            //Garante que o ficheiro é apagado mesmo que o programa feche derrepente
            tempFile.deleteOnExit();

            //Cria um novo ficheiro com o mesmo nome e caminho do original e apaga o original
            File originalFile = new File(file);
            originalFile.delete();

            //Renomeia o ficheiro temporario para o ficheiro original fazendo com que os dados guardados no temporário sejam agora do original
            tempFile.renameTo(originalFile);
        } catch (IOException e) {
            System.err.println("Error deleting player: " + e.getMessage());
        }
    }*/

    public void removeLiga() throws IOException {
        Scanner scanner = new Scanner(System.in);
        getLigas(); // Gets an updated list of ligas
        print(); // prints the updated list

        System.out.println("Indique o ID da liga que pretende remover: ");
        int ligaID = scanner.nextInt();
        delete(ligaID);
//        removeFromTXT(ligaID, txtFilePath);
    }

    @Override
    public void insertFaker() {

    }

    // END Interface Methods --------------------------------------------------------

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

    public void setEquipas(ArrayList<Integer> equipas) {
        this.equipas = equipas;
    }

    public ArrayList<Integer> getPartidas() {
        return partidas;
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
        return String.format("| %-3s | %-25s | %-7s | %-20s | %-30s | %-14s |%n",
                "ID", "Nome", "País", "Equipas", "Partidas", "Ranking de Equipas");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-25s | %-7s | %-20s | %-30s | %-22s |%n",
                getId(), getNome(), getPais(), getEquipas(), getPartidas(), getRankingEquipas());
    }

}
