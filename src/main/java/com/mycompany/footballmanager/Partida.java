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
            getPartidas(); // Atualiza o ArrayList das Partidas
            Menu.partidas.add(inserePartida());

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
            // Verifica se o ArrayList das partidas está vazio
            if (!Menu.partidas.isEmpty()) {
                // Obtém o ID da última partida, usando o tamanho do ArrayList e subtraindo 1
                latest = Menu.partidas.get(Menu.partidas.size() - 1).getId();
            }

            // Incrementa o ID automaticamente
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
                        ArbitrosIDs.add(idArbitro); // Adiciona o Id do arbitro ao ArrayList ArbitrosIDs
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
                    partida.setEquipa(equipaID); // Define a equipa da Partida
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
                    partida.setAdversario(adversario); // Define o adversario da Partida
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
                    partida.setData(data); // Define a data da Partida
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
                    partida.setResultado(resultado); // Define o resultado da Partida
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

        } catch (Exception e) {
            System.out.println("Input inválido: " + e.getMessage() + "\n");
            return inserePartida();
        }

        writeToTXT(partida);
        System.out.println(partida);

        return partida;
    }

    // Metodo para inserir Partida no ficheiro TXT
    private void writeToTXT(Partida partida) {
        checkIfFileExists(txtFilePath);

        try (FileWriter writer = new FileWriter(txtFilePath, true)) { // Abre o ficheiro para escrita
            BufferedWriter bw = new BufferedWriter(writer);
            StringBuilder sb = new StringBuilder();

            // Constrói a linha para ser escrita no ficheiro
            sb.append(partida.getId()).append(";"); // Obtém o ID
            sb.append(partida.getNome()).append(";"); // Obtém o Nome
            // Adiciona os IDs dos árbitros separados por vírgula
            for (Integer arbitroID : partida.getArbitrosIDs()) {
                sb.append(arbitroID).append(",");
            }
            sb.deleteCharAt(sb.length() - 1); // Remove a última vírgula
            sb.append(";"); // Adiciona ponto e vírgula
            sb.append(partida.getEquipaID()).append(";"); // Obtém a Equipa
            sb.append(partida.getAdversarioID()).append(";"); // Obtém o Adversário
            sb.append(partida.getData()).append(";"); // Obtém a Data
            sb.append(partida.getResultado()).append(";"); // Obtém o Resultado
            sb.append(partida.getLocal()).append(";"); // Obtém o Local
            sb.append(partida.getGolos_marcados()).append(";"); // Obtém os Golos Marcados
            sb.append(partida.getGolos_sofridos()).append(";"); // Obtém os Golos Sofridos
            sb.append(partida.getSoma_cartoes()).append("\n"); // Obtém a Soma de Cartões

            // Escreve a linha no ficheiro
            bw.append(sb.toString());
            // Fecha o buffer
            bw.close();

            System.out.println("Partida inserida com Sucesso!!!");
        } catch (IOException e) {
            System.out.println("Erro ao inserir Partida no ficheiro partidas.txt: " + e.getMessage());
        }
    }


    @Override
    public void print() {
        getPartidas();
        // Imprime o cabeçalho da tabela
        System.out.printf(tableHeaders());

        // Imprime todas as Partidas
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
            boolean firstLine = true; // Flag para identificar a primeira linha
            ArrayList<Partida> partidas = new ArrayList<>(); // Cria uma nova lista para armazenar as partidas
            String row;

            while ((row = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Define a flag como false após encontrar a primeira linha
                    continue; // Ignora o processamento da primeira linha (O cabeçalho)
                }
                String[] data = row.split(";"); // Divide a linha em partes usando o separador ";"

                // Cria um novo objeto Partida e preenche os seus atributos com os dados lidos do ficheiro
                Partida partida = new Partida();
                partida.setId(Integer.parseInt(data[0])); // Define o ID da partida
                partida.setNome(data[1]); // Define o Nome da partida

                // Processamento dos IDs dos árbitros
                String[] arbitrosIds = data[2].split(","); // Separa os IDs dos árbitros por vírgula
                ArrayList<Integer> arbitros = new ArrayList<>();
                for (String arbitroId : arbitrosIds) {
                    arbitros.add(Integer.parseInt(arbitroId)); // Adiciona os IDs dos árbitros à lista
                }
                partida.setArbitrosIDs(arbitros); // Define os IDs dos árbitros da partida

                // Define os outros atributos da partida com base nos dados lidos do ficheiro
                partida.setEquipa(Integer.parseInt(data[3])); // Define a Equipa da partida
                partida.setAdversario(Integer.parseInt(data[4])); // Define o Adversário da partida
                partida.setData(data[5]); // Define a Data da partida
                partida.setResultado(data[6]); // Define o Resultado da partida
                partida.setLocal(data[7]); // Define o Local da partida
                partida.setGolos_marcados(Integer.parseInt(data[8])); // Define os Golos Marcados na partida
                partida.setGolos_sofridos(Integer.parseInt(data[9])); // Define os Golos Sofridos na partida
                partida.setSoma_cartoes(Integer.parseInt(data[10])); // Define a Soma dos Cartões na partida

                // Adiciona o objeto Partida à lista de partidas
                partidas.add(partida);
            }
            br.close(); // Fecha o ficheiro

            // Substitui a ArrayList na classe Menu pela nova ArrayList criada com os dados lidos do ficheiro
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
        ArrayList<String> nomesArbitros = new ArrayList<>(); // Cria uma lista para armazenar os nomes dos árbitros

        for (Arbitro arbitro : Menu.arbitros) { // Percorre a lista de árbitros no Menu
            for (Integer arbitroID : arbitros) { // Percorre os IDs dos árbitros fornecidos
                if (!arbitros.isEmpty()) { // Verifica se a lista de IDs não está vazia
                    if (arbitro.getId() == arbitroID) { // Compara os IDs para encontrar correspondências
                        nomesArbitros.add(arbitro.getNome()); // Adiciona o nome do árbitro à lista se houver correspondência
                    }
                } else { // Se a lista de IDs estiver vazia
                    nomesArbitros.add("Sem Arbitros associados");
                }
            }
        }
        return nomesArbitros; // Retorna a lista de nomes dos árbitros correspondentes aos IDs fornecidos
    }

    public void setArbitrosIDs(ArrayList<Integer> arbitros) {
        this.arbitros = arbitros;
    }

    public int getEquipaID() {
        return equipa;
    }

    public String getNomeEquipa(int id) {
        for (Equipa equipa : Menu.equipas) { // Percorre a lista de equipas no Menu
            if (equipa.getId() == id) { // Verifica se o ID da equipas corresponde ao ID fornecido
                return equipa.getNome(); // Retorna o nome da equipas se houver correspondência
            }
        }
        return "Sem Equipa associada";
    }

    public Equipa getEquipaValues(int id) {
        for (Equipa equipa : Menu.equipas) {
            if (equipa.getId() == id) { // Verifica se o ID da equipas corresponde ao ID fornecido
                return equipa; // Retorna o objeto completo da equipas se houver correspondência
            }
        }
        return null; // Retorna null se não houver correspondência do ID da equipas
    }


    public void setEquipa(int equipa) {
        this.equipa = equipa;
    }

    public int getAdversarioID() {
        return adversario;
    }

    public String getNomeAdversario(int id) {
        for (Equipa adversario : Menu.equipas) {
            if (adversario.getId() == id) { // Verifica se o ID do adversario corresponde ao ID fornecido
                return adversario.getNome(); // Retorna o nome do adversario se houver correspondência
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
