/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.mycompany.footballmanager.Interfaces.Dados;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static com.mycompany.footballmanager.Menu.*;

/**
 * @author afonso, milena, tânia
 */
public class Treinador extends Pessoa implements Dados {
    // BEGIN Variables ----------------------------------------------------------------
    private int id = 0;
    private String especializacoes;
    private String taticas_fav;

    private final String txtFilePath = "./src/main/java/com/mycompany/footballmanager/DB/treinadores.txt";
    // END Variables ----------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Treinador() {
        super.setNome(randomName());
        super.setIdade(random.nextInt(30, 60));
        especializacoes = randomLorem();
        taticas_fav = random.nextInt(1, 4) + "-" + random.nextInt(1, 3) + "-" + random.nextInt(1, 4);
    }

    public Treinador(int id) {
        super.setNome(randomName());
        super.setIdade(random.nextInt(30, 60));
        this.id = id;
        especializacoes = randomLorem();
        taticas_fav = random.nextInt(1, 4) + "-" + random.nextInt(1, 3) + "-" + random.nextInt(1, 4);
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

//        scanner.close(); // Close Scanner after use
    }

    public Treinador insereTreinador() {
        Treinador treinador = new Treinador();
        Scanner scanner = new Scanner(System.in);
        int latest = 0;

        try {
            if (!Menu.treinadores.isEmpty()) {
                // Gets the ID of the latest treinador, using the size of the ArrayList and decrementing 1
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
        }

        writeToTXT(treinador);
        System.out.println(treinador);

        return treinador;
    }

    // Method to write Treinador data to a TXT file
    private void writeToTXT(Treinador treinador) {
        checkIfFileExists(txtFilePath);

        try (FileWriter writer = new FileWriter(txtFilePath, true)) { // Inicia a escrita no ficheiro
            BufferedWriter bw = new BufferedWriter(writer); // Cria um BufferedWriter para escrever no ficheiro
            StringBuilder sb = new StringBuilder(); // Cria um StringBuilder para construir a linha do TXT

            // Constrói a linha do ficheiro TXT
            sb.append(treinador.getId()).append(";"); // Adiciona o ID do treinador
            sb.append(treinador.getNome()).append(";"); // Adiciona o nome do treinador
            sb.append(treinador.getIdade()).append(";"); // Adiciona a idade do treinador
            sb.append(treinador.getEspecializacoes()).append(";"); // Adiciona as especializações do treinador
            sb.append(treinador.getTaticas_fav()).append("\n"); // Adiciona as táticas favoritas do treinador e quebra de linha

            // Escreve a linha do TXT no ficheiro
            bw.append(sb.toString());
            // Fecha o BufferedWriter
            bw.close();

        } catch (IOException e) { // Captura qualquer exceção que possa ocorrer durante a escrita no ficheiro
            System.out.println("Erro ao inserir Treinador no ficheiro treinadores.txt: " + e.getMessage());
        }
    }

    @Override
    public void print() {
        getTreinadores();
        // Imprime os cabeçalhos
        System.out.printf(tableHeaders());

        // Imprime todos os Treinadores
        if (!Menu.treinadores.isEmpty()) {
            for (Treinador treinador : Menu.treinadores) {
                System.out.printf(treinador.toString());
            }
        } else {
            System.out.println("\nNão existem Treinadores!\n");
        }
    }

    public void getTreinadores() {
        checkIfFileExists(txtFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) { // Inicia a leitura do ficheiro
            String row;
            boolean firstLine = true; // Flag para identificar a primeira linha
            ArrayList<Treinador> treinadores = new ArrayList<>(); // Cria uma nova lista para os treinadores

            while ((row = br.readLine()) != null) { // Lê o ficheiro linha por linha
                if (firstLine) { // Verifica se é a primeira linha do ficheiro
                    firstLine = false; // Define a flag como false depois de encontrar a primeira linha
                    continue; // Salta o processamento da primeira linha
                }

                String[] data = row.split(";"); // Divide os dados da linha por ponto e vírgula

                // Formato do ficheiro TXT: ID, Nome, Idade, Especializações e Táticas Favoritas
                Treinador treinador = new Treinador(); // Cria um novo objeto Treinador
                treinador.setId(Integer.parseInt(data[0])); // Define o ID do treinador
                treinador.setNome(data[1]); // Define o nome do treinador
                treinador.setIdade(Integer.parseInt(data[2])); // Define a idade do treinador
                treinador.setEspecializacoes(data[3]); // Define as especializações do treinador
                treinador.setTaticas_fav(data[4]); // Define as táticas favoritas do treinador

                treinadores.add(treinador); // Adiciona o treinador à lista de treinadores
            }
            br.close(); // Fecha o BufferedReader

            // Substitui a ArrayList da classe Menu pela nova ArrayList de treinadores
            Menu.treinadores = treinadores;
        } catch (IOException e) { // Captura qualquer exceção que possa ocorrer durante a leitura do ficheiro
            System.out.println("Erro ao ler o ficheiro treinadores.txt: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        if (id > 0 && id < (Menu.treinadores.size() - 1)) {
            Menu.treinadores.remove(id);
            System.out.println("O Treinador de ID " + id + " foi removido com sucesso");
        } else {
            System.out.println("ID incorreto! Tente novamente...");
        }
    }

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
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Quantos Treinadores quer gerar? ");
            int numOfChoices = scanner.nextInt(); // Lê a quantidade de treinadores a serem gerados
            scanner.nextLine(); // Consome o character de nova linha

            if (numOfChoices < 0 || numOfChoices > 5) { // Verifica se a entrada é válida (de 0 a 5)
                System.out.println("Só pode inserir no máximo 5 de cada vez! Tente Novamente...");
                insertFaker(); // Se a entrada não for válida, chama o método novamente para nova entrada
            }

            for (int i = 0; i < numOfChoices; i++) { // Loop para criar o número desejado de treinadores
                int increment = 1; // Incremento do ID
                int latest = 0; // ID do último treinador gerado

                if (!Menu.treinadores.isEmpty()) { // Verifica se há treinadores na lista
                    latest = Menu.treinadores.get(Menu.treinadores.size() - 1).getId(); // Vai buscar o ID do último treinador existente no ficheiro
                }

                Treinador treinador = new Treinador(latest + increment); // Cria um novo treinador com ID incrementado
                Menu.treinadores.add(treinador); // Adiciona o novo treinador à lista de treinadores

                writeToTXT(treinador); // Escreve as informações do treinador no ficheiro TXT
            }

            System.out.println(numOfChoices + " Treinadores Gerados com sucesso!");
            System.out.println("--------------------------------");
        } catch (Exception e) {
            System.out.println("Erro ao inserir Treinador no ficheiro treinadores.txt: " + e.getMessage());
        }
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
        System.out.println("|----------------------------------------- TREINADORES --------------------------------------------------|");
        return String.format("| %-3s | %-30s | %-7s | %-20s | %-30s |%n",
            "ID", "Nome", "Idade", "Especializações", "Táticas Favoritas");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-30s | %-7s | %-20s | %-30s |%n",
            getId(), getNome(), getIdade(), getEspecializacoes(), getTaticas_fav());
    }
}
