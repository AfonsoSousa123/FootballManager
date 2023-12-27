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
public class ArbitroAssistente extends Arbitro {
    // BEGIN Variables ----------------------------------------------------------------
    private int id;

    private static final String txtFilePath = "./src/main/java/com/mycompany/footballmanager/DB/arbitros_sec.txt";
    // END Variables ----------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public ArbitroAssistente() {
        super.setNome("Arbitro principal nome");
        super.setIdade(random.nextInt(30, 60));
        super.setExperiencia(random.nextInt(1, 15));
        super.setFuncao("Assistente");
    }
    // END Constructors ----------------------------------------------------------------

    // BEGIN Getters and Setters ----------------------------------------------------------------
    public static ArrayList<ArbitroAssistente> getArbitrosAssistentes() {
        checkIfFileExists(txtFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) {
            String row;
            boolean firstLine = true;
            ArrayList<ArbitroAssistente> arbitros = new ArrayList<>();

            while ((row = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] data = row.split(";");

                ArbitroAssistente arbitro_a = new ArbitroAssistente();
                arbitro_a.setId(Integer.parseInt(data[0]));
                arbitro_a.setNome(data[1]);
                arbitro_a.setIdade(Integer.parseInt(data[2]));
                arbitro_a.setExperiencia(Integer.parseInt(data[3]));
                arbitro_a.setFuncao(data[4]);

                arbitros.add(arbitro_a);
            }

            return arbitros;
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro arbitros.txt: " + e.getMessage());
            return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
        }
    }

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
