/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.mycompany.footballmanager.Interfaces.Dados;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import static com.mycompany.footballmanager.Menu.*;

import static com.mycompany.footballmanager.Menu.*;

/**
 * @author afonso, milena, tânia
 */
public class Jogador extends Pessoa implements Dados {
    // BEGIN Variables ----------------------------------------------------------------
    private int id = 0;
    private Posicao posicao;

    // Para mapear as os enums para umas Strings mais user-friendly
    private static final HashMap<String, Posicao> posicaoMap = new HashMap<>();
    static {
        posicaoMap.put("Avancado", Posicao.AVANCADO);
        posicaoMap.put("Medio", Posicao.MEDIO);
        posicaoMap.put("Central", Posicao.CENTRAL);
        posicaoMap.put("Defesa", Posicao.DEFESA);
        posicaoMap.put("Guarda Redes", Posicao.GUARDA_REDES);
    }

    // O mapa invertido ao apresentado em cima
    private static final HashMap<Posicao, String> posicaoMapInvertida = new HashMap<>();
    static {
        posicaoMapInvertida.put(Posicao.AVANCADO, "Avancado");
        posicaoMapInvertida.put(Posicao.MEDIO, "Medio");
        posicaoMapInvertida.put(Posicao.CENTRAL, "Central");
        posicaoMapInvertida.put(Posicao.DEFESA, "Defesa");
        posicaoMapInvertida.put(Posicao.GUARDA_REDES, "Guarda Redes");
    }
    private String hist_lesoes;
    private int ataque; // de 0 a 100
    private int defesa; // de 0 a 100
    private int n_agressividade; // de 0 a 100

    private final String txtFilePath = "./src/main/java/com/mycompany/footballmanager/DB/jogadores.txt"; // File Path
    // END Variables ----------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Jogador() {
        super.setNome(randomName());
        super.setIdade(random.nextInt(20, 40));
        posicao = getRandomPosicao();
        hist_lesoes = randomLorem();
        ataque = random.nextInt(1, 100);
        defesa = random.nextInt(1, 100);
        n_agressividade = random.nextInt(1, 100);
    }

    public Jogador(
        int id,
        String nome,
        int idade,
        Posicao posicao,
        String hist_lesoes,
        int ataque,
        int defesa,
        int n_agressividade
    ) {
        super(nome, idade);
        this.id = id;
        this.posicao = posicao;
        this.hist_lesoes = hist_lesoes;
        this.ataque = ataque;
        this.defesa = defesa;
        this.n_agressividade = n_agressividade;
    }
    // END Constructors ----------------------------------------------------------------

    // BEGIN Interface Methods ----------------------------------------------------------------
    @Override
    public void insert() {
        Scanner scanner = new Scanner(System.in);

        boolean insertMore = true;

        while (insertMore) {
            getJogadores(); // Atualiza os Jogadores
            Menu.jogadores.add(insereJogador());

            try {
                System.out.println("Deseja inserir outro Jogador? (sim/nao)");
                String choice = scanner.nextLine().trim().toLowerCase();

                if (!choice.equals("sim")) {
                    insertMore = false;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    public Jogador insereJogador() {
        Jogador jogador = new Jogador(); // Cria uma nova instância do Jogador
        Scanner scanner = new Scanner(System.in);
        int latest = 0;

        try {
            if (!Menu.jogadores.isEmpty()) { // Verifica se a lista de jogadores não está vazia
                // Obtém o ID do ultimo Jogador usando o tamanho da lista e decrementando 1
                latest = Menu.jogadores.get(Menu.jogadores.size() - 1).getId();
            }

            int increment = 1; // Incrementa 1 para gerar um novo ID para o novo jogador
            jogador.setId(latest + increment); // Define o ID do novo jogador automaticamente

            System.out.println("Inserir Jogador: ");
            // Nome
            try {
                System.out.println("Insira o Nome: ");
                String nome = scanner.nextLine();
                if (Menu.hasPontoEVirgulaString(nome)) {
                    System.out.println("O Nome do Jogador não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return insereJogador();
                } else {
                    jogador.setNome(nome); // Define o nome do jogador
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereJogador();
            }

            // Idade
            try {
                System.out.println("Insira a Idade: ");
                int idade = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (idade >= 18 && idade <= 45) {
                    jogador.setIdade(idade); // Define a idade do jogador
                } else {
                    System.out.println("A Idade do Jogador tem que ter entre 18 e 45 anos, inclusive! Tente Novamente...");
                    return insereJogador();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: Não pode inserir strings neste campo\n");
                return insereJogador();
            }

            // Posição
            try {
                System.out.println("Escolha entre estas Posicoes: Avancado, Medio, Central, Defesa ou Guarda Redes");
                System.out.println("Insira a Posicao: ");
                String posicao = scanner.nextLine().trim();

                if (Menu.hasPontoEVirgulaString(posicao)) {
                    System.out.println("A Posição do Jogador não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return insereJogador();
                } else if (!posicaoMap.containsKey(posicao)) {
                    System.out.println("Inseriu uma Posicao incorreta! Tente Novamente!");
                    return insereJogador();
                } else {
                    jogador.setPosicaoInsert(posicao); // Define a idade do jogador
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereJogador();
            }

            // Historico de Lesões
            try {
                System.out.println("Insira o Historico de Lesões: ");
                String historico = scanner.nextLine();

                if (Menu.hasPontoEVirgulaString(historico)) {
                    System.out.println("O Historico de Lesões do Jogador não pode conter ponto e virgulas ';' ! Tente Novamente...");
                    return insereJogador();
                } else {
                    jogador.setHist_lesoes(historico);
                }
            } catch (Exception e) {
                System.out.println("Input inválido: " + e.getMessage() + "\n");
                return insereJogador();
            }

            // Ataque
            try {
                System.out.println("Insira o Ataque: ");
                int ataque = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (ataque > 0 && ataque <= 100) {
                    jogador.setAtaque(ataque);
                } else {
                    System.out.println("O Jogador só pode ter entre 1 e 100 valores de Ataque! Tente Novamente...");
                    return insereJogador();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: Não pode inserir strings neste campo\n");
                return insereJogador();
            }

            // Defesa
            try {
                System.out.println("Insira o Defesa: ");
                int defesa = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (defesa > 0 && defesa <= 100) {
                    jogador.setDefesa(defesa);
                } else {
                    System.out.println("O Jogador só pode ter entre 1 e 100 valores de Defesa! Tente Novamente...");
                    return insereJogador();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: Não pode inserir strings neste campo\n");
                return insereJogador();
            }

            // Nivel de Agressividade
            try {
                System.out.println("Insira o Nivel de Agressividade: ");
                int n_agressividade = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (n_agressividade > 0 && n_agressividade <= 100) {
                    jogador.setN_agressividade(n_agressividade);
                } else {
                    System.out.println("O Jogador só pode ter entre 1 e 100 valores de Nivel de Agressividade! Tente Novamente...");
                    return insereJogador();
                }
            } catch (Exception e) {
                System.out.println("Input inválido: Não pode inserir strings neste campo\n");
                return insereJogador();
            }

        } catch (Exception e) {
            System.out.println("Input inválido: " + e.getMessage() + "\n");
            return insereJogador();
        }

        writeToTXT(jogador); // Chama o método writeToTXT para escrever o novo jogador no ficheiro

        return jogador; // Retorna o novo jogador criado
    }

    public void writeToTXT(Jogador jogador) {
        checkIfFileExists(txtFilePath); // Verifica se o ficheiro TXT existe

        try (FileWriter writer = new FileWriter(txtFilePath, true)) { // Abre um writer de escrita para o ficheiro TXT
            BufferedWriter bw = new BufferedWriter(writer); // Inicializa um buffer de escrita
            StringBuilder sb = new StringBuilder(); // Cria um StringBuilder para construir a linha do ficheiro TXT

            // Constrói a linha do ficheiro TXT com os dados do jogador separados por ponto e vírgula
            sb.append(jogador.getId()).append(";"); // ID do jogador
            sb.append(jogador.getNome()).append(";"); // Nome do jogador
            sb.append(jogador.getIdade()).append(";"); // Idade do jogador
            sb.append(jogador.getPosicao()).append(";"); // Posição do jogador
            sb.append(jogador.getHist_lesoes()).append(";"); // Histórico de lesões do jogador
            sb.append(jogador.getAtaque()).append(";"); // Valor de ataque do jogador
            sb.append(jogador.getDefesa()).append(";"); // Valor de defesa do jogador
            sb.append(jogador.getN_agressividade()).append("\n"); // Nível de agressividade do jogador, seguido por uma nova linha

            // Escreve a linha construída no ficheiro TXT usando o buffer de escrita
            bw.append(sb.toString());
            bw.flush(); // Descarrega o buffer

        } catch (IOException e) { // Captura exceções de entrada e saída (I/O)
            System.out.println("Erro ao inserir o Jogador no ficheiro " + e.getMessage());
        }
    }

    @Override
    public void print() {
        getJogadores(); // Obtém os jogadores do ficheiro

        if (!jogadores.isEmpty()) { // Verifica se há jogadores
            // Imprime os cabeçalhos
            System.out.printf(tableHeaders());

            // Imprime todos os Jogadores usando um foreach loop
            for (Jogador jogador : jogadores) {
                System.out.printf(jogador.toString());
            }
        } else {
            System.out.println("\nNão existem Jogadores!\n");
        }
    }

    public void getJogadores() {
        checkIfFileExists(txtFilePath); // Verifica se o ficheiro TXT existe

        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) { // Abre um writer para o ficheiro TXT
            boolean firstLine = true; // Flag para identificar a primeira linha
            ArrayList<Jogador> jogadores = new ArrayList<>(); // Cria uma nova lista para jogadores
            String row;

            while ((row = br.readLine()) != null) { // Lê cada linha do ficheiro TXT
                if (firstLine) {
                    firstLine = false; // Define a flag como false após encontrar a primeira linha
                    continue; // Pula o processamento da primeira linha (cabeçalho)
                }

                String[] data = row.split(";"); // Divide os dados da linha com base no ponto e vírgula

                // Formato do TXT: ID, Nome, Idade, Posição, Histórico de Lesões, Ataque, Defesa, Nível de Agressividade
                Jogador jogador = new Jogador(); // Cria um novo objeto Jogador
                jogador.setId(Integer.parseInt(data[0])); // Define o ID do jogador
                jogador.setNome(data[1]); // Define o Nome do jogador
                jogador.setIdade(Integer.parseInt(data[2])); // Define a Idade do jogador
                jogador.setPosicao(data[3]); // Define a Posição do jogador
                jogador.setHist_lesoes(data[4]); // Define o Histórico de Lesões do jogador
                jogador.setAtaque(Integer.parseInt(data[5])); // Define o valor de Ataque do jogador
                jogador.setDefesa(Integer.parseInt(data[6])); // Define o valor de Defesa do jogador
                jogador.setN_agressividade(Integer.parseInt(data[7])); // Define o Nível de Agressividade do jogador

                // Adiciona o jogador à ArrayList de jogadores
                jogadores.add(jogador);
            }
            br.close(); // Fecha o writer do ficheiro TXT

            // Substitui a ArrayList da classe Menu pela nova ArrayList de jogadores
            Menu.jogadores = jogadores;
        } catch (IOException e) { // Captura exceções de entrada e saída (I/O)
            System.out.println("Erro ao ler o ficheiro jogadores.txt: " + e.getMessage()); // Exibe uma mensagem de erro, caso ocorra alguma exceção
        }
    }

    @Override
    public void delete(int id) {
        if (id > 0 && id < (jogadores.size() - 1)) {
            jogadores.remove(id);
            System.out.println("O Jogador de ID " + id + " foi removido com sucesso");
        } else {
            System.out.println("ID incorreto! Tente novamente...");
        }
    }
    // END Interface Methods ----------------------------------------------------------------

    public void removeJogador() throws IOException {
        Scanner scanner = new Scanner(System.in);
        getJogadores(); // Gets an updated list of jogadores
        print(); // prints the updated list

        System.out.println("Indique o ID do jogador que pretende remover: ");
        int playerID = scanner.nextInt();
        delete(playerID);
        removeFromTXT(playerID, txtFilePath);
    }

    // BEGIN Faker Methods ----------------------------------------------------------------
    @Override
    public void insertFaker() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Quantos Jogadores quer gerar? ");
            int numOfChoices = scanner.nextInt(); // Lê o número inserido
            scanner.nextLine(); // Consumir o caracter de nova linha

            if (numOfChoices < 0 || numOfChoices > 11) { // Verifica se o número está no intervalo permitido
                // Imprime uma mensagem de erro se o número estiver fora do intervalo permitido
                System.out.println("Só pode inserir no máximo 11 de cada vez! Tente Novamente...");
                insertFaker(); // Chama recursivamente o método para pedir outro número válido
            }

            for (int i = 0; i < numOfChoices; i++) { // Loop para gerar o número especificado de jogadores
                int increment = 1; // Incrementa 1 para o ID
                int latest = 0; // Variável para armazenar o último ID de jogador
                if (!Menu.jogadores.isEmpty()) { // Verifica se a lista de jogadores não está vazia
                    latest = Menu.jogadores.get(Menu.jogadores.size() - 1).getId(); // Obtém o ID do último jogador na lista
                }

                Jogador jogador = new Jogador( // Cria um novo objeto jogador com dados aleatórios
                    latest + increment, // ID automaticamente incrementado
                    randomName(), // Nome aleatório
                    random.nextInt(20, 40), // Idade aleatória entre 20 e 40 anos
                    getRandomPosicao(), // Posição aleatória
                    randomLorem() + ", " + randomLorem(), // Histórico de Lesões aleatório
                    random.nextInt(1, 100), // Valor aleatório de Ataque
                    random.nextInt(1, 100), // Valor aleatório de Defesa
                    random.nextInt(1, 100) // Nível aleatório de Agressividade
                );
                jogadores.add(jogador); // Adiciona o novo jogador à lista de jogadores

                writeToTXT(jogador); // Escreve os dados do jogador no ficheiro TXT
            }

            System.out.println(numOfChoices + " Jogadores Gerados com sucesso!");
            System.out.println("--------------------------------");
        } catch (Exception e) {
            System.out.println("Erro ao inserir Jogador no ficheiro jogadores.txt: " + e.getMessage());
        }
    }

    public Posicao getRandomPosicao() {
        Set<String> posicaoKeys = posicaoMap.keySet(); // Guarda as posicoes num Set

        // Converte as para um array
        String[] posicaoArray = posicaoKeys.toArray(new String[0]);

        // Escolhe um indice aleatorio, dentro do alcance das posicoes
        int randomIndex = random.nextInt(posicaoArray.length);

        // Retira aleatoriamente uma posicao
        String randomPosicaoKey = posicaoArray[randomIndex];
        return posicaoMap.get(randomPosicaoKey);
    }
    // END Faker Methods ----------------------------------------------------------------

    // BEGIN Getters and Setters ----------------------------------------------------------------
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public int getIdade() {
        return super.getIdade();
    }

    public void setPosicao(String posicao) {
        this.posicao = Posicao.valueOf(posicao);
    }

    public void setPosicaoInsert(String posicao) {
        if (posicaoMap.containsKey(posicao)) {
            this.posicao = posicaoMap.get(posicao);
        }
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public String getPosicaoFormatted(Posicao posicao) {
        return posicaoMapInvertida.getOrDefault(posicao, "Sem Posicao!");
    }

    public String getHist_lesoes() {
        return hist_lesoes;
    }

    public void setHist_lesoes(String hist_lesoes) {
        this.hist_lesoes = hist_lesoes;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getN_agressividade() {
        return n_agressividade;
    }

    public void setN_agressividade(int n_agressividade) {
        this.n_agressividade = n_agressividade;
    }

    // END Getters and Setters ----------------------------------------------------------------
    // Print headers
    public static String tableHeaders() {
        System.out.println("|------------------------------------------------------------------ JOGADORES ------------------------------------------------------------------------|");
        return String.format("| %-3s | %-25s | %-7s | %-20s | %-35s | %-7s | %-7s | %-14s |%n",
            "ID", "Nome", "Idade", "Posição", "Histórico de Lesões", "Ataque", "Defesa", "Nível de Agressividade");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-25s | %-7s | %-20s | %-35s | %-7s | %-7s | %-22s |%n",
            getId(), getNome(), getIdade(), getPosicaoFormatted(getPosicao()), getHist_lesoes(), getAtaque(), getDefesa(), getN_agressividade());
    }
}
