/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import java.util.Objects;
import java.util.Random;

import static com.mycompany.footballmanager.Menu.*;

/**
 * @author afonso, milena, tânia
 */
public class Partida {
    // BEGIN Variables ------------------------------------------------------------------
    private int id;
    private String nome; // O nome vai ser a junção dos nomes das equipas que estão jogando
    private int arbitro;
    private int equipa;
    private int adversario;
    private String data; // quando formos inserir usamos um objeto de data para formatar a String para a data pretendida
    private String resultado; // por exemplo: 4:3
    private String local;
    private int golos_marcados;
    private int golos_sofridos;
    private int soma_cartoes;

    private Random random = new Random();
    private final String txtFilePath = "./src/main/java/com/mycompany/footballmanager/DB/ligas.txt";
    // END Variables ----------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Partida() {
        nome = randomTeam() + " vs " + randomTeam();
        arbitro = 0;
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
            int arbitro,
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
        this.arbitro = arbitro;
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

    public int getArbitro() {
        return arbitro;
    }

    public void setArbitro(int arbitro) {
        this.arbitro = arbitro;
    }

    public int getEquipa() {
        return equipa;
    }

    public void setEquipa(int equipa) {
        this.equipa = equipa;
    }

    public int getAdversario() {
        return adversario;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partida partida)) return false;
        return
                getId() == partida.getId() &&
                        getResultado() == partida.getResultado() &&
                        getGolos_marcados() == partida.getGolos_marcados() &&
                        getGolos_sofridos() == partida.getGolos_sofridos() &&
                        Objects.equals(getNome(), partida.getNome()) &&
                        Objects.equals(getArbitro(), partida.getArbitro()) &&
                        Objects.equals(getEquipa(), partida.getEquipa()) &&
                        Objects.equals(getAdversario(), partida.getAdversario()) &&
                        Objects.equals(getData(), partida.getData()) &&
                        Objects.equals(getLocal(), partida.getLocal());
    }

    // Print headers
    public static String tableHeaders() {
        return String.format("| %-3s | %-25s | %-40s | %-20s | %-15s | %-10s | %-10s | %-30s | %-14s | %-14s |%n",
                "ID", "Nome", "Arbitro", "Treinador", "Equipa", "Adversario", "Pais", "Histórico", "Golos Marcados", "Golos Sofridos");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-25s | %-40s | %-20s | %-15s | %-10s | %-10s | %-30s | %-14s | %-22s |%n",
                getId(), getNome(), getArbitro(), getEquipa(), getAdversario(), getData(), getResultado(), getLocal(), getGolos_marcados(), getGolos_sofridos());
    }
    // END toString Methods ----------------------------------------------------------------
}
