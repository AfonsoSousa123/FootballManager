/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static com.mycompany.footballmanager.Menu.checkIfFileExists;

/**
 * @author afonso, milena, tânia
 */
public class ArbitroPrincipal extends Arbitro {
    private int id;

    private static final String txtFilePath = "./src/main/java/com/mycompany/footballmanager/DB/arbitros.txt";

    // BEGIN Constructors ----------------------------------------------------------------
    public ArbitroPrincipal() {
        super.setNome("Arbitro principal nome");
        super.setIdade(random.nextInt(30, 60));
        super.setExperiencia(random.nextInt(1, 15));
        super.setFuncao("Principal");
    }
    // END Constructors ----------------------------------------------------------------

    public static ArrayList<ArbitroPrincipal> getArbitrosPrincipais() {
        checkIfFileExists(txtFilePath); // Verifica se o ficheiro existe

        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) {
            String row;
            boolean firstLine = true; // Flag para identificar a primeira linha
            ArrayList<ArbitroPrincipal> arbitros = new ArrayList<>(); // Cria uma nova lista para os árbitros

            while ((row = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Ignora o processamento da primeira linha (cabeçalho)
                }

                String[] data = row.split(";"); // Divide a linha em partes usando o separador ";"

                // Cria um novo objeto ArbitroPrincipal e preenche os seus atributos com os dados lidos do ficheiro
                Arbitro arbitro_p = new ArbitroPrincipal();
                ((ArbitroPrincipal) arbitro_p).setId(Integer.parseInt(data[0])); // Define o ID do árbitro
                arbitro_p.setNome(data[1]); // Define o Nome do árbitro
                arbitro_p.setIdade(Integer.parseInt(data[2])); // Define a Idade do árbitro
                arbitro_p.setExperiencia(Integer.parseInt(data[3])); // Define a Experiência do árbitro
                arbitro_p.setFuncao(data[4]); // Define a Função do árbitro

                arbitros.add((ArbitroPrincipal) arbitro_p); // Adiciona o objeto ArbitroPrincipal à lista de árbitros
            }

            return arbitros; // Retorna a lista de árbitros preenchida
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro arbitros.txt: " + e.getMessage());
            return new ArrayList<>(); // Retorna uma lista vazia em caso de erro na leitura do ficheiro
        }
    }

    // BEGIN Getters and Setters ----------------------------------------------------------------
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    // END Getters and Setters ----------------------------------------------------------------

    // BEGIN toString Methods ----------------------------------------------------------------
    @Override
    public String toString() {
        return String.format("| %-3s | %-20s | %-7s | %-11s | %-11s |%n",
            getId(), getNome(), getIdade(), getExperiencia(), getFuncao());
    }
    // END toString Methods ----------------------------------------------------------------
}
