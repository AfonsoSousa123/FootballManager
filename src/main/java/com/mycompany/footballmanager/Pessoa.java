/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import static com.mycompany.footballmanager.Menu.checkIfFileExists;
import static com.mycompany.footballmanager.Menu.randomFullName;

/**
 * @author afonso, milena, tânia
 */
public abstract class Pessoa {

    // BEGIN Variables ----------------------------------------------------------------
    private String nome;
    private int idade;

    protected Random random = new Random();
    // END Variables ----------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Pessoa() {
        nome = randomFullName();
        idade = random.nextInt(20, 40);
    }

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    // END Constructors ----------------------------------------------------------------

    // BEGIN Getters ----------------------------------------------------------------
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    // END Setters ----------------------------------------------------------------

    public String getNome() {
        return nome;
    }

    // BEGIN Setters ----------------------------------------------------------------
    public void setNome(String nome) {
        this.nome = nome;
    }
    // END Getters ----------------------------------------------------------------

    public void removeFromTXT(int id, String file) throws IOException {
        checkIfFileExists(file);

        // Cria um scanner para ler o ficheiro txt e cria un ficheiro temporario temp no qual vai ser escrito os dados sem o joador a ser eliminado
        try (Scanner scanner = new Scanner(new File(file))) {
            File tempFile = File.createTempFile("temp", ".txt");
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
            System.err.println("Erro ao eliminar: " + e.getMessage());
        }
    }
}
