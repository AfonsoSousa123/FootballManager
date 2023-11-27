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
public class Treinador extends Pessoa implements Dados {
    private final String txtFilePath = "./src/main/java/com/mycompany/footballmanager/DB/treinadores.txt";

    // BEGIN Variables ----------------------------------------------------------------
    private int id = 0;
    private String especializacoes;
    private String taticas_fav;
    // END Variables ----------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Treinador() {
        super.setNome(randomFullName());
        super.setIdade(random.nextInt(30, 60));
        especializacoes = randomLorem();
        taticas_fav = randomLorem();
    }

    public Treinador(
            int id,
            String nome,
            int idade,
            String especializacoes,
            String taticas_fav
    ) {
        super(nome, idade);
        this.id = id;
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
        int latest = 0;

        try {
            if (!Menu.treinadores.isEmpty()) {
                // Gets the ID of the latest jogador, using the size of the ArrayList and decrementing 1
                latest = Menu.treinadores.get(Menu.treinadores.size() - 1).getId();
            }

            // Automatically increments the ID
            int increment = 1;
            treinador.setId(latest + increment);

            System.out.println("Inserir Treinador: ");
            try {
                System.out.println("Insira o Nome: ");
                String nome = scanner.nextLine();
                if (Menu.hasPontoEVirgulaString(nome)) {
                    System.out.println("O Nome do Treinador não pode conter ponto e virgulas ';' ! Tente Novamente...");
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

                if (idade >= 30 && idade <= 70) {
                    treinador.setIdade(idade);
                } else {
                    System.out.println("A Idade do Treinador tem que ter entre 30 e 70 anos, inclusive! Tente Novamente...");
                    return insereTreinador();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereTreinador();
            }

            try {
                System.out.println("Insira as Especializações: ");
                String especializacoes = scanner.nextLine().trim();

                if (Menu.hasPontoEVirgulaString(especializacoes)) {
                    System.out.println("As Especializações do Treinador não podem conter ponto e virgulas ';' ! Tente Novamente...");
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

                if (Menu.hasPontoEVirgulaString(taticas_fav)) {
                    System.out.println("As Táticas Favoritas do Treinador não podem conter ponto e virgulas ';' ! Tente Novamente...");
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

        writeToTXT(treinador);

        return treinador;
    }

    // Method to write Treinador data to a CSV file
    private void writeToTXT(Treinador treinador) {
        checkIfFileExists(txtFilePath);

        try (FileWriter writer = new FileWriter(txtFilePath, true)) {
            StringBuilder sb = new StringBuilder();

            // Construct the CSV line
            sb.append(treinador.getId()).append(";");
            sb.append(treinador.getNome()).append(";");
            sb.append(treinador.getIdade()).append(";");
            sb.append(treinador.getEspecializacoes()).append(";");
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

        // Print details of all Treinadores
        if (!treinadores.isEmpty()) {
            // Print the table Headers
            System.out.printf(tableHeaders());

            for (Treinador treinador : treinadores) {
                System.out.printf(treinador.toString());
            }
        } else {
            System.out.println("\nNão existem Treinadores!\n");
        }
    }

    public void getTreinadores() {
        checkIfFileExists(txtFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) {
            String row;
            boolean firstLine = true; // Flag to identify the first line
            ArrayList<Treinador> treinadores = new ArrayList<>(); // Create a new list for treinadores


            while ((row = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Set the flag to false after encountering the first line
                    continue; // Skip processing the first line
                }

                String[] data = row.split(";");

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
    public void update(int id) {
        //
    }

    @Override
    public void delete(int id) {
        if (id > 0 && id < (treinadores.size() - 1)) {
            treinadores.remove(id);
            System.out.println("O Jogador de ID " + id + " foi removido com sucesso");
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

    public void removeTreinador() throws IOException {
        Scanner scanner = new Scanner(System.in);
        getTreinadores(); // Gets an updated list of treinadores
        print(); // prints the updated list

        System.out.println("Indique o ID do treinador que pretende remover: ");
        int treinadorID = scanner.nextInt();
        delete(treinadorID);
        removeFromTXT(treinadorID, txtFilePath);
    }

    // END Interface Methods --------------------------------------------------------


    @Override
    public void insertFaker() {

    }

    // BEGIN Getters and Setters ----------------------------------------------------------------
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setEspecializacoes(String especializacoes) {
        this.especializacoes = especializacoes;
    }

    public String getTaticas_fav() {
        return taticas_fav;
    }

    public void setTaticas_fav(String taticas_fav) {
        this.taticas_fav = taticas_fav;
    }

    // END Getters and Setters ----------------------------------------------------------------
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
