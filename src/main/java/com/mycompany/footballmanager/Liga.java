/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import java.util.ArrayList;
import java.util.Random;

import static com.mycompany.footballmanager.Menu.randomCountry;
import static com.mycompany.footballmanager.Menu.randomTeam;

/**
 * @author afonso, milena, tânia
 */
public class Liga {
    // BEGIN Variables ----------------------------------------------------------------
    private int id;
    private String nome;
    private String pais;
    private ArrayList<Equipa> equipas;
    private ArrayList<Partida> partidas;
    private int ranking_equipas;

    private Random random = new Random();
    // END Variables ----------------------------------------------------------------

    // BEGIN Constructors ----------------------------------------------------------------
    public Liga() {
        nome = randomTeam();
        pais = randomCountry();
        ranking_equipas = random.nextInt(1, 50);
    }

    public Liga(
            String nome,
            String pais,
            ArrayList<Equipa> equipas,
            ArrayList<Partida> partidas,
            int ranking_equipas
    ) {
        this.nome = nome;
        this.pais = pais;
        this.ranking_equipas = ranking_equipas;
    }
    // END Constructors ----------------------------------------------------------------

    // BEGIN Getters and Setters ----------------------------------------------------------------
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public ArrayList<Equipa> getEquipas() {
        return equipas;
    }

    public void setEquipas(Equipa equipa) {
        equipas.add(equipa);
    }

    public ArrayList<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(Partida partida) {
        partidas.add(partida);
    }

    public int getRankingEquipas() {
        return ranking_equipas;
    }

    public void setRankingEquipas(int ranking_equipas) {
        this.ranking_equipas = ranking_equipas;
    }
    // END Getters and Setters ----------------------------------------------------------------

    // Print headers
    public static String tableHeaders() {
        return String.format("| %-3s | %-25s | %-7s | %-20s | %-30s | %-14s |%n",
                "ID", "Nome", "País", "Equipas", "Partidas", "Ranking de Equipas");
    }

    @Override
    public String toString() {
        return String.format("| %-3s | %-25s | %-7s | %-20s | %-30s | %-7s | %-7s | %-22s |%n",
                getId(), getNome(), getPais(), getEquipas(), getPartidas(), getRankingEquipas());
    }

}
