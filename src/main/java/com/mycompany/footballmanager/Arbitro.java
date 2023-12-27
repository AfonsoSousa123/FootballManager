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

        // Combina as listas de ArbitroPrincipal e ArbitroAssistente numa única lista
        ArrayList<Arbitro> arbitros = new ArrayList<>(Menu.arbitros_p);
        arbitros.addAll(Menu.arbitros_a);

        // Atualiza a lista de arbitros na classe Menu
        Menu.arbitros = arbitros;

        return arbitros;
    }

    @Override
    public void print() {
        getArbitros();
        // Imprime o Cabeçalho
        System.out.printf(tableHeaders());

        // Imprime todos os Arbitros
        if (!Menu.arbitros.isEmpty()) {

            for (Arbitro arbitro : Menu.arbitros) {
                System.out.printf(arbitro.toString());
            }
        } else {
            System.out.println("\nNão existem Arbitros!\n");
        }
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

