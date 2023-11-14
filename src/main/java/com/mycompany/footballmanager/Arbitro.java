/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

/**
 * @author afonso, milena, tânia
 */
public class Arbitro extends Pessoa {
    private String experiencia;

    // BEGIN Constructors ----------------------------------------------------------------
    public Arbitro() {
        super();
        experiencia = "3 Anos";
    }

    public Arbitro(
            String nome,
            int idade,
            String experiencia
    ) {
        super(nome, idade);
        this.experiencia = experiencia;
    }
    // END Constructors ----------------------------------------------------------------

    // BEGIN Setters ----------------------------------------------------------------
    @Override
    public void setIdade(int idade) {
        super.setIdade(idade);
    }

    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
    // END Setters ----------------------------------------------------------------

    // BEGIN Getters ----------------------------------------------------------------
    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public int getIdade() {
        return super.getIdade();
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    public String getExperiencia() {
        return experiencia;
    }
    // END Getters ----------------------------------------------------------------

    // Print headers
    public String tableHeaders() {
        return String.format("| %-3s | %-20s | %-7s | %-20s |%n",
                "ID", "Nome", "Idade", "Especializações");
    }

    @Override
    public String toString() {
        String dataRow = String.format("| %-3s | %-20s | %-7s | %-20s |%n",
                getId(), getNome(), getIdade(), getExperiencia());
        return dataRow;
    }
}
