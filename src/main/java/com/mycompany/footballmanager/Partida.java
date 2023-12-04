/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.mycompany.footballmanager.Interfaces.Dados;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static com.mycompany.footballmanager.Menu.*;

/**
 * @author afonso, milena, tânia
 */
public class Partida implements Dados {
    // BEGIN Variables ------------------------------------------------------------------
    private int id;
    private String nome; // O nome vai ser a junção dos nomes das partidas que estão jogando
    private int arbitro;
    private int equipa;
    private int adversario;
    private String data; // quando formos inserir usamos um objeto de data para formatar a String para a data pretendida
    private String resultado; // por exemplo: 4:3
    private String local;
    private int golos_marcados;
    private int golos_sofridos;
    private int soma_cartoes;

    private Random random = new Random();
    private final String txtFilePath = "./src/main/java/com/mycompany/footballmanager/DB/partidas.txt";
    // END Variables ----------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Partida() {
        nome = randomTeam() + " vs " + randomTeam();
        arbitro = 0;
        equipa = 0;
        adversario = 0;
        data = randomDate();
        resultado = random.nextInt(0, 10) + ":" + random.nextInt(0, 10);
        local = randomCity();
        golos_marcados = random.nextInt(0, 20);
        golos_sofridos = random.nextInt(0, 20);
        soma_cartoes = random.nextInt(0, 20);
    }

    public Partida(
            String nome,
            int arbitro,
            int equipa,
            int adversario,
            String data,
            String resultado,
            String local,
            int golos_marcados,
            int golos_sofridos,
            int soma_cartoes
    ) {
        this.nome = nome;
        this.arbitro = arbitro;
        this.equipa = equipa;
        this.adversario = adversario;
        this.data = data;
        this.resultado = resultado;
        this.local = local;
        this.golos_marcados = golos_marcados;
        this.golos_sofridos = golos_sofridos;
        this.soma_cartoes = soma_cartoes;
    }

    // END Constructors ----------------------------------------------------------------

    // BEGIN Interface Methods ----------------------------------------------------------------
    @Override
    public void insert() {
        Scanner scanner = new Scanner(System.in);
        boolean insertMore = true;

        while (insertMore) {
            getPartidas(); // Updates the Partidas ArrayList
            partidas.add(inserePartida());

            try {
                System.out.println("Deseja inserir outra Partida? (sim/nao)");
                String choice = scanner.nextLine().trim().toLowerCase();

                if (!choice.equals("sim")) {
                    insertMore = false;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    public Partida inserePartida() {
        Partida partida = new Partida();
        Scanner scanner = new Scanner(System.in);
        int latest = 0;

        try {
            // if the partidas ArrayList is not empty
            if (!Menu.partidas.isEmpty()) {
                // Gets the ID of the latest partida, using the size of the ArrayList and decrementing 1
                latest = Menu.partidas.get(Menu.partidas.size() - 1).getId();
            }

            // Automatically increments the ID
            int increment = 1;
            partida.setId(latest + increment);

            System.out.println("Inserir Partida: ");
            // Arbitro
            try {
                Menu.arbitro.print();
                System.out.println("Escolha o ID do Arbitro que pretende adicionar à Partida: ");
                int arbitroID = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (arbitroID > 0 && arbitroID <= Menu.equipas.size()) {
                    partida.setArbitro(arbitroID);
                } else {
                    System.out.println("Tem que escolher um ID existente dos Arbitros! Tente Novamente...");
                    return inserePartida();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return inserePartida();
            }

            // Equipa
            try {
                Menu.equipa.print();
                System.out.println("Escolha o ID da Equipa que pretende adicionar à Partida: ");
                int equipaID = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (equipaID > 0 && equipaID <= Menu.equipas.size()) {
                    partida.setEquipa(equipaID);
                } else {
                    System.out.println("Tem que escolher um ID existente das Equipas! Tente Novamente...");
                    return inserePartida();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return inserePartida();
            }

            // Adversário
            try {
                System.out.println("Escolha o Adversario que pretende adicionar à Partida: ");
                int adversario = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (adversario > 0 && adversario <= Menu.equipas.size()) {
                    partida.setAdversario(adversario);
                } else {
                    System.out.println("Tem que escolher um Adversario existente nas Equipas! Tente Novamente...");
                    return inserePartida();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return inserePartida();
            }

            // Nome
            try {
                String nomePartida = partida.getNomeEquipa(partida.getEquipa()) + " vs " + partida.getNomeAdversario(partida.getAdversario());
                partida.setNome(nomePartida);
            } catch (Exception e) {
                System.out.println("Erro ao gerar o Nome da Partida: " + e.getMessage() + "\n");
            }

            // Data
            try {
                System.out.println("Insira a Data: ");
                String data = scanner.nextLine();

                // FALTA FAZER A VALIDAÇÃO DA DATA!!!
                if (Menu.hasPontoEVirgulaString(data)) {
                    System.out.println("A Data não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return inserePartida();
                } else {
                    partida.setData(data);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return inserePartida();
            }

            // Resultado
            try {
                System.out.println("Insira o Resultado: ");
                String resultado = scanner.nextLine();

                if (Menu.hasPontoEVirgulaString(resultado)) {
                    System.out.println("O Resultado não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return inserePartida();
                } else {
                    partida.setResultado(resultado);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return inserePartida();
            }

            // Local
            try {
                System.out.println("Insira o Local: ");
                String local = scanner.nextLine();

                if (Menu.hasPontoEVirgulaString(local)) {
                    System.out.println("O Local da Partida não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return inserePartida();
                } else {
                    partida.setLocal(local);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return inserePartida();
            }

            // Golos Marcados
            try {
                System.out.println("Insira a quantidade de Golos Marcados: ");
                int golosMarcados = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (golosMarcados >= 0 && golosMarcados < 5000) {
                    partida.setGolos_marcados(golosMarcados);
                } else {
                    System.out.println("A quantidade de Golos Marcados tem que ser menor que 5000 e! Tente Novamente...");
                    return inserePartida();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: Não pode inserir strings neste campo\n");
                return inserePartida();
            }

            // Golos Sofridos
            try {
                System.out.println("Insira a quantidade de Golos Sofridos: ");
                int golosSofridos = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (golosSofridos >= 0 && golosSofridos < 5000) {
                    partida.setGolos_sofridos(golosSofridos);
                } else {
                    System.out.println("A quantidade de Golos Sofridos tem que ser menor que 5000 e! Tente Novamente...");
                    return inserePartida();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: Não pode inserir strings neste campo\n");
                return inserePartida();
            }

//            // Soma de Cartoes
//            try {
//                System.out.println("Insira soma de Golos Marcados: ");
//                int golosMarcados = scanner.nextInt();
//                scanner.nextLine(); // Consume newline character
//
//                if (golosMarcados >= 0 && golosMarcados < 5000) {
//                    partida.setGolos_marcados(golosMarcados);
//                } else {
//                    System.out.println("A quantidade de Golos Marcados tem que ser menor que 5000 e! Tente Novamente...");
//                    return inserePartida();
//                }
//            } catch (Exception e) {
//                System.out.println("Input inválido: Não pode inserir strings neste campo\n");
//                return inserePartida();
//            }
        } catch (Exception e) {
            System.out.println("Input inválido: " + e.getMessage() + "\n");
            return inserePartida();
        }

        writeToTXT(partida);
        System.out.println(partida);

        return partida;
    }

    // Method to write Partida data to a TXT file
    private void writeToTXT(Partida partida) {
        checkIfFileExists(txtFilePath);

        try (FileWriter writer = new FileWriter(txtFilePath, true)) {
            BufferedWriter bw = new BufferedWriter(writer);
            StringBuilder sb = new StringBuilder();

            // Construct the TXT line
            sb.append(partida.getId()).append(";"); // get ID
            sb.append(partida.getNome()).append(";"); // get Nome ID
            sb.append(partida.getArbitro()).append(";"); // get Arbitro ID
            sb.append(partida.getEquipa()).append(";"); // get Equipa
            sb.append(partida.getAdversario()).append(";"); // get Adversario
            sb.append(partida.getData()).append(";"); // get Data
            sb.append(partida.getResultado()).append(";"); // get Resultado
            sb.append(partida.getLocal()).append(";"); // get Local
            sb.append(partida.getGolos_marcados()).append(";"); // get Golos Marados
            sb.append(partida.getGolos_sofridos()).append(";"); // get Golos Sofridos
            sb.append(partida.getSoma_cartoes()).append("\n"); // get Soma Cartoes

            // Write the line to the file
            bw.append(sb.toString());
            // closes the output stream
            bw.close();

            System.out.println("Partida inserida com Sucesso!!!");
        } catch (IOException e) {
            System.out.println("Erro ao inserir Partida no ficheiro partidas.txt: " + e.getMessage());
        }
    }

    @Override
    public void print() {
        getPartidas();
        // Print the table Headers
        System.out.printf(tableHeaders());

        // Print details of all partidas using a loop
        if (!partidas.isEmpty()) {
            for (Partida partida : partidas) {
                System.out.printf(partida.toString());
            }
        } else {
            System.out.println("\nNão existem Partidas!\n");
        }
    }

    public void getPartidas() {
        checkIfFileExists(txtFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) {
            boolean firstLine = true; // Flag to identify the first line
            ArrayList<Partida> partidas = new ArrayList<>(); // Create a new list for partidas
            String row;

            while ((row = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Set the flag to false after encountering the first line
                    continue; // Skip processing the first line
                }
                String[] data = row.split(";");

                // TXT format: ID, Nome, Arbitro, Equipa, Adversario, Data, Resultado, Local, Golos Marcados, Golos Sofridos, Soma Cartoes
                Partida partida = new Partida();
                partida.setId(Integer.parseInt(data[0])); // ID
                partida.setNome(data[1]); // Nome
                partida.setArbitro(Integer.parseInt(data[2])); // Arbitro
                partida.setEquipa(Integer.parseInt(data[3])); // Equipa
                partida.setAdversario(Integer.parseInt(data[4])); // Adversario
                partida.setData(data[5]); // Data
                partida.setResultado(data[6]); // Resultado
                partida.setLocal(data[7]); // Local
                partida.setGolos_marcados(Integer.parseInt(data[8])); // Golos Marcados
                partida.setGolos_sofridos(Integer.parseInt(data[9])); // Golos Sofridos
                partida.setSoma_cartoes(Integer.parseInt(data[10])); // Soma Cartoes

                // Adds the partida to the ArrayList
                partidas.add(partida);
            }
            br.close();

            // Replaces the ArrayList from Menu class with the new ArrayList
            Menu.partidas = partidas;
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro partidas.txt: " + e.getMessage());
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

    public int getArbitro() {
        return arbitro;
    }

    public String getNomeArbitro(int id) {
        for (Arbitro arbitro : Menu.arbitros) {
            if (arbitro.getId() == id) {
                return arbitro.getNome();
            }
        }
        return "Sem Arbitro associado"; // Retorna um valor predefinido se o id não for encontrado
    }

    public void setArbitro(int arbitro) {
        this.arbitro = arbitro;
    }

    public int getEquipa() {
        return equipa;
    }

    public String getNomeEquipa(int id) {
        for (Equipa equipa : Menu.equipas) {
            if (equipa.getId() == id) {
                return equipa.getNome();
            }
        }
        return "Sem Equipa associada"; // Retorna um valor predefinido se o id não for encontrado
    }

    public void setEquipa(int equipa) {
        this.equipa = equipa;
    }

    public int getAdversario() {
        return adversario;
    }

    public String getNomeAdversario(int id) {
        for (Equipa adversario : Menu.equipas) {
            if (adversario.getId() == id) {
                return adversario.getNome();
            }
        }
        return "Sem Adversario associado"; // Retorna um valor predefinido se o id não for encontrado
    }

    public void setAdversario(int adversario) {
        this.adversario = adversario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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

    public int getSoma_cartoes() {
        return soma_cartoes;
    }

    public void setSoma_cartoes(int soma_cartoes) {
        this.soma_cartoes = soma_cartoes;
    }

    // END Getters and Setters ----------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partida partida)) return false;
        return
                getId() == partida.getId() &&
                        getResultado().equals(partida.getResultado()) &&
                        getGolos_marcados() == partida.getGolos_marcados() &&
                        getGolos_sofridos() == partida.getGolos_sofridos() &&
                        Objects.equals(getNome(), partida.getNome()) &&
                        Objects.equals(getArbitro(), partida.getArbitro()) &&
                        Objects.equals(getEquipa(), partida.getEquipa()) &&
                        Objects.equals(getAdversario(), partida.getAdversario()) &&
                        Objects.equals(getData(), partida.getData()) &&
                        Objects.equals(getLocal(), partida.getLocal());
    }

    // Print headers
    public static String tableHeaders() {
        System.out.println("|----------------------------------------------------------------------------------------------------- PARTIDAS --------------------------------------------------------------------------------------------------------------------------|");
        return String.format("| %-3s | %-25s | %-40s | %-20s | %-20s | %-10s | %-10s | %-30s | %-14s | %-14s | %-15s |%n",
                "ID", "Nome", "Arbitro", "Equipa", "Adversario", "Data", "Resultado", "Local", "Golos Marcados", "Golos Sofridos", "Soma de Cartoes");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-25s | %-40s | %-20s | %-20s | %-10s | %-10s | %-30s | %-14s | %-14s | %-15s |%n",
                getId(),
                getNome(),
                getNomeArbitro(getArbitro()),
                getNomeEquipa(getEquipa()),
                getNomeAdversario(getAdversario()),
                getData(),
                getResultado(),
                getLocal(),
                getGolos_marcados(),
                getGolos_sofridos(),
                getSoma_cartoes()
        );
    }
    // END toString Methods ----------------------------------------------------------------
}
