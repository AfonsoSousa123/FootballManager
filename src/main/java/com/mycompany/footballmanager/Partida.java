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
public class Partida implements Dados {
    // BEGIN Variables ------------------------------------------------------------------
    private int id;
    private String nome; // O nome vai ser a junção dos nomes das partidas que estão jogando
    private ArrayList<Integer> arbitros;
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
        arbitros = new ArrayList<>();
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
            ArrayList<Integer> arbitros,
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
        this.arbitros = arbitros;
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
    public void simulaPartida(Partida partida) {
        if (partida.getEquipaID() > partida.getAdversarioID()) {
            System.out.println(getNomeEquipa(partida.getEquipaID()) + " Ganhou a partida por" + partida.getResultado() +" !");
        } else if (partida.getEquipaID() < partida.getAdversarioID()) {
            System.out.println(getNomeAdversario(partida.getAdversarioID()) + " Ganhou a partida por" + partida.getResultado() +" !");
        } else {
            System.out.println("Houve Empate entre as Equipas");
        }
    }
    public String caraCoroa() {
        if (random.nextInt(2) == 1) {
            return "COROA";
        } else {
            return "CARA";
        }
    }

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
        scanner.close();
    }

    public Partida inserePartida() {
        Partida partida = new Partida();
        Scanner scanner = new Scanner(System.in);
        int latest = 0;
        int equipasSize = Menu.equipas.get(Menu.equipas.size() - 1).getId();

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
            // Arbitros
            try {
                boolean insertMoreArbitros = true;
                ArrayList<Integer> ArbitrosIDs = new ArrayList<>(); // Cria um arrayList para os ids dos Arbitros
                Menu.arbitro.print(); // imprime os arbitros existentes
                int arbitrosSize = Menu.arbitros.get(Menu.arbitros.size() - 1).getId();

                while (insertMoreArbitros) {
                    System.out.println("Escolha um ID de um Arbitro: ");
                    int idArbitro = scanner.nextInt(); // recebe o id do Arbitro
                    scanner.nextLine(); // Consume newline character

                    if (idArbitro > 0 && idArbitro <= arbitrosSize) {
                        ArbitrosIDs.add(idArbitro);
                    } else if (ArbitrosIDs.size() > 4) {
                        System.out.println("Chegou ao limite de Arbitros por Partida!");
                        break;
                    } else {
                        System.out.println("Tem que escolher um ID existente das Arbitros! Tente Novamente...");
                        continue;
                    }

                    System.out.println("Deseja adicionar mais Arbitros à Partida? (sim/nao)");
                    String choice = scanner.nextLine().trim().toLowerCase();

                    if (!choice.equals("sim")) {
                        insertMoreArbitros = false;
                    }
                }
                partida.setArbitrosIDs(ArbitrosIDs); // guarda os ids das Arbitros na Partida

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

                if (equipaID > 0 && equipaID <= equipasSize) {
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
                System.out.println(
                    "Escolha o Adversario que pretende jogar contra a Equipa: " +
                    Menu.equipas.get(partida.equipa - 1).getNome()
                );
                int adversario = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (partida.getNomeAdversario(adversario).equals(partida.getNomeEquipa(partida.equipa))) {
                    System.out.println("Uma equipa não pode jogar contra si propria! Tente Novamente...");
                    return inserePartida();
                } else if (partida.getEquipaValues(adversario).getIdLiga() != partida.getEquipaValues(partida.equipa).getIdLiga()) {
                    System.out.println("As equipas têm que ser da mesma Liga! Tente Novamente...");
                    return inserePartida();
                }  else if (adversario > 0 && adversario <= equipasSize) {
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
                String nomePartida = partida.getNomeEquipa(partida.getEquipaID()) + " vs " + partida.getNomeAdversario(partida.getAdversarioID());
                partida.setNome(nomePartida);
            } catch (Exception e) {
                System.out.println("Erro ao gerar o Nome da Partida: " + e.getMessage() + "\n");
            }

            // Data
            try {
                System.out.println("Insira a Data: ");
                String data = scanner.nextLine();

                if (!Menu.validarData(data)) {
                    System.out.println("A data não está de acordo com o formato: DD-MM-AAAA, tente novamente ");
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

//            // Golos Marcados
//            try {
//                System.out.println("Insira a quantidade de Golos Marcados: ");
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
//
//            // Golos Sofridos
//            try {
//                System.out.println("Insira a quantidade de Golos Sofridos: ");
//                int golosSofridos = scanner.nextInt();
//                scanner.nextLine(); // Consume newline character
//
//                if (golosSofridos >= 0 && golosSofridos < 5000) {
//                    partida.setGolos_sofridos(golosSofridos);
//                } else {
//                    System.out.println("A quantidade de Golos Sofridos tem que ser menor que 5000 e! Tente Novamente...");
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
            // Append the equipa elements with comma separator
            for (Integer arbitroID : partida.getArbitrosIDs()) {
                sb.append(arbitroID).append(",");
            }
            sb.deleteCharAt(sb.length() - 1); // Remove a ultima virgula
            sb.append(";"); // Para poder ser colocada ponto e virgula
            sb.append(partida.getEquipaID()).append(";"); // get Equipa
            sb.append(partida.getAdversarioID()).append(";"); // get Adversario
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

                String[] arbitrosIds = data[2].split(","); // gets the ids of the equipas
                ArrayList<Integer> arbitros = new ArrayList<>();

                for (String arbitroId : arbitrosIds) {
                    arbitros.add(Integer.parseInt(arbitroId));
                }
                partida.setArbitrosIDs(arbitros); // Arbitros
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

    public ArrayList<Integer> getArbitrosIDs() {
        return arbitros;
    }

    public ArrayList<String> getNomesArbitros(ArrayList<Integer> arbitros) {
        ArrayList<String> nomesArbitros = new ArrayList<>();

        for (Arbitro arbitro : Menu.arbitros) {
            for (Integer arbitroID : arbitros) {
                if (!arbitros.isEmpty()) {
                    if (arbitro.getId() == arbitroID) {
                        nomesArbitros.add(arbitro.getNome());
                    }
                } else {
                    nomesArbitros.add("Sem Arbitros associados");
                }
            }
        }
        return nomesArbitros;
    }

    public void setArbitrosIDs(ArrayList<Integer> arbitros) {
        this.arbitros = arbitros;
    }

    public int getEquipaID() {
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

    public Equipa getEquipaValues(int id) {
        for (Equipa equipa : Menu.equipas) {
            if (equipa.getId() == id) {
                return equipa;
            }
        }
        return Menu.equipa;
    }

    public void setEquipa(int equipa) {
        this.equipa = equipa;
    }

    public int getAdversarioID() {
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

    // BEGIN Other Methods ----------------------------------------------------------------
    // Verifica se o nome da Equipa é igual ao do Adversario
    @Override
    public boolean equals(Object o) {
        // verifica se o objeto é ele proprio
        if (this == o) return true;
        // verifica se o objeto é nullo
        if (o == null) return false;
        // verifica se o objeto é instancia de Partida
        if (!(o instanceof Partida partida)) return false;
        // Compara a equipa com o adversario
        return (equipa == partida.equipa && adversario == partida.adversario);
    }

    // Print headers
    public static String tableHeaders() {
        System.out.println("|----------------------------------------------------------------------------------------------------- PARTIDAS --------------------------------------------------------------------------------------------------------------------------|");
        return String.format("| %-3s | %-25s | %-60s | %-10s | %-10s | %-30s | %-14s | %-14s | %-15s |%n",
                "ID", "Nome", "Arbitros", "Data", "Resultado", "Local", "Golos Marcados", "Golos Sofridos", "Soma de Cartoes");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-25s | %-60s | %-10s | %-10s | %-30s | %-14s | %-14s | %-15s |%n",
                getId(),
                getNome(),
                String.join(", ", getNomesArbitros(getArbitrosIDs())),
                getData(),
                getResultado(),
                getLocal(),
                getGolos_marcados(),
                getGolos_sofridos(),
                getSoma_cartoes()
        );
    }
    // END Other Methods ----------------------------------------------------------------
}
