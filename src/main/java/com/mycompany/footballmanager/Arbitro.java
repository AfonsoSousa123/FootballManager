/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import com.mycompany.footballmanager.Interfaces.Dados;

import java.util.ArrayList;

import static com.mycompany.footballmanager.Menu.arbitros;
import static com.mycompany.footballmanager.Menu.randomName;

/**
 * @author afonso, milena, tânia
 */
public class Arbitro extends Pessoa implements Dados {
    // BEGIN Variables ----------------------------------------------------------------
    private int id;
    private int experiencia;
    private String funcao;
    // END Variables ----------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Arbitro() {
        super.setNome(randomName());
        super.setIdade(random.nextInt(20, 40));
        experiencia = random.nextInt(1, 20);
        funcao = "";
    }

    public Arbitro(
            int id,
            String nome,
            int idade,
            int experiencia
    ) {
        super(nome, idade);
        this.id = id;
        this.experiencia = experiencia;
    }
    // END Constructors ----------------------------------------------------------------

    // BEGIN Interface Methods ----------------------------------------------------------------
    public ArrayList<Arbitro> getArbitros() {
        Menu.arbitros_p = ArbitroPrincipal.getArbitrosPrincipais();
        Menu.arbitros_a = ArbitroAssistente.getArbitrosAssistentes();

        // Combine as listas de ArbitroPrincipal e ArbitroAssistente em uma única lista
        ArrayList<Arbitro> arbitros = new ArrayList<>(Menu.arbitros_p);
        arbitros.addAll(Menu.arbitros_a);

        // Atualiza a lista de arbitros na classe Menu
        Menu.arbitros = arbitros;

        return arbitros;
    }

    @Override
    public void print() {
        getArbitros();
        // Print the table Headers
        System.out.printf(tableHeaders());

        // Print details of all Arbitros
        if (!Menu.arbitros.isEmpty()) {

            for (Arbitro arbitro : arbitros) {
                System.out.printf(arbitro.toString());
            }
        } else {
            System.out.println("\nNão existem Arbitros!\n");
        }
    }

    /*public void getArbitros() {
        checkIfFileExists(txtFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) {
            String row;
            boolean firstLine = true; // Flag to identify the first line
            ArrayList<Arbitro> arbitros = new ArrayList<>(); // Create a new list for arbitros

            while ((row = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Set the flag to false after encountering the first line
                    continue; // Skip processing the first line
                }

                String[] data = row.split(";");

                // TXT format: ID, Nome, Idade, Experiencia
                ArbitroPrincipal arbitro_p = new ArbitroPrincipal();
                arbitro_p.setId(Integer.parseInt(data[0]));
                arbitro_p.setNome(data[1]);
                arbitro_p.setIdade(Integer.parseInt(data[2]));
                arbitro_p.setExperiencia(data[3]);
                arbitro_p.setFuncao((data[4]));

                // Adds the arbitro to the ArrayList
                arbitros.add(arbitro_p);
            }
            br.close();

            // Replaces the ArrayList from Menu class with the new ArrayList
            Menu.arbitros = arbitros;
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro arbitros.txt: " + e.getMessage());
        }

    }*/

    @Override
    public void insertFaker() {
        // FALTA FAZER!!!
    }

    // END Interface Methods ----------------------------------------------------------------
    // BEGIN Getters and Setters ----------------------------------------------------------------
    @Override
    public String getNome() {
        return super.getNome();
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int getIdade() {
        return super.getIdade();
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    // END Getters and Setters ----------------------------------------------------------------

    // BEGIN toString Methods ----------------------------------------------------------------
    public static String tableHeaders() {
        System.out.println("|---------------------------- ARBITROS ----------------------------|");
        return String.format("| %-3s | %-20s | %-7s | %-11s | %-11s |%n",
                "ID", "Nome", "Idade", "Experiencia", "Funcao");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-20s | %-7s | %-11s | %-11s |%n",
                getId(), getNome(), getIdade(), getExperiencia(), getFuncao());
    }
    // END toString Methods ----------------------------------------------------------------
}

