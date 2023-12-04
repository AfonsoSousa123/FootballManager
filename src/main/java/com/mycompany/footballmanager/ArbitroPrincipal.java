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
    private static int AI = 1; // Auto Increment
    private int id;

    private static final String txtFilePath = "./src/main/java/com/mycompany/footballmanager/DB/arbitros.txt";

    public ArbitroPrincipal() {
        super.setNome("Arbitro principal nome");
        super.setIdade(random.nextInt(30, 60));
        super.setExperiencia(random.nextInt(1, 15) + " Anos");
    }

    public static ArrayList<ArbitroPrincipal> getArbitrosPrincipais() {
        checkIfFileExists(txtFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(txtFilePath))) {
            String row;
            boolean firstLine = true;
            ArrayList<ArbitroPrincipal> arbitros = new ArrayList<>();

            while ((row = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] data = row.split(";");
                
                ArbitroPrincipal arbitro_p = new ArbitroPrincipal();
                arbitro_p.setId(Integer.parseInt(data[0]));
                arbitro_p.setNome(data[1]);
                arbitro_p.setIdade(Integer.parseInt(data[2]));
                arbitro_p.setExperiencia(data[3]);

                arbitros.add(arbitro_p);
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

    // Print headers
    public static String tableHeaders() {
        return String.format("| %-3s | %-20s | %-7s | %-20s |%n",
                "ID", "Nome", "Idade", "Especializações");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-20s | %-7s | %-20s |%n",
                getId(), getNome(), getIdade(), getExperiencia());
    }
}
