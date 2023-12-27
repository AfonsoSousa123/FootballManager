/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author afonso, milena, tânia
 */
public class ProbabilidadesPartida {
    private Random random = new Random();

    public static double calculaProbabilidade(Equipa equipa, ArrayList<Arbitro> arbitros, boolean JogaPrimeiro) {
        double desempenhoMedio = calculaDesempenhoMedio(equipa);
        int numJogadores = equipa.getPlantel().size();
        double taticasTreinador = calculaTaticasTreinador(equipa);
        double experienciaArbitros = 0;
        String teamPosition = calculaPosicaoEquipa(equipa);
        double factorJogaPrimeiro = JogaPrimeiro ? 1.1 : 1.0;
        
        for (Arbitro arbitro : arbitros) {
            experienciaArbitros += arbitro.getExperiencia();
        }

        // Retorna a probabilidade calculada
        return desempenhoMedio *
            numJogadores *
            taticasTreinador *
            experienciaArbitros *
            factorJogaPrimeiro;
    }

    private static double calculaDesempenhoMedio(Equipa equipa) {
        // Calcula o desemsenho medio de cada jogador baseado no seu historico de lesoes e no seu nivel de agressividade
        double desempenhoTotal = 0.0;
         for (Jogador jogador : equipa.getJogadoresValues(equipa.getPlantel())) {
             double desempenho = (jogador.getHist_lesoes().length() * 0.1 / (jogador.getN_agressividade() + jogador.getAtaque() + jogador.getDefesa()));
             desempenhoTotal += desempenho;
         }

        // Retorna o desempenho medio
        return desempenhoTotal / equipa.getPlantel().size();
    }

    private static double calculaTaticasTreinador(Equipa equipa) {
        // Calculate the impact of the coach's tactics on the team's performance
        // For example:
         double attackingPlayers = 0.0;
         double defendingPlayers = 0.0;
         for (Jogador jogador : equipa.getJogadoresValues(equipa.getPlantel())) {
             if (jogador.getPosicao().equals("Atacante")) {
                 attackingPlayers += 1.0;
             } else if (jogador.getPosicao().equals("Defesa")) {
                 defendingPlayers += 1.0;
             }
         }

//         Retorna as Taticas do Treinador
        return attackingPlayers / (attackingPlayers + defendingPlayers);
    }

    private static String calculaPosicaoEquipa(Equipa equipa) {
        // Calcula a posicao da equipa e ajusta a probabilidade baseada no numero de Atacantes
        // For example:
         double atacantes = 0.0;
         double defesas = 0.0;

         for (Jogador jogador : equipa.getJogadoresValues(equipa.getPlantel())) {
             if (jogador.getPosicao().equals("AVANCADO") || jogador.getPosicao().equals("CENTRAL")) {
                 atacantes += 1.0;
             } else if (jogador.getPosicao().equals("DEFESA") || jogador.getPosicao().equals("MEDIO")) {
                 defesas += 1.0;
             }
         }

         if (atacantes > defesas) {
             return "Atacante";
         } else if (defesas > atacantes) {
             return "Defesa";
         } else {
             return "Equilibrada";
         }
    }
}

