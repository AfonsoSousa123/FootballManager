/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import java.util.Random;

/**
 *
 * @author afonso, milena, tânia
 */
public class ProbabilidadesPartida {
    private Random random = new Random();

    public static double calculateProbability(Equipa equipa, Arbitro arbitro, boolean JogaPrimeiro) {
        double desempenhoMedio = calculaDesempenhoMedio(equipa);
        int numJogadores = equipa.getPlantel().size();
        double taticasTreinador = calculaTaticasTreinador(equipa);
//        double experienciaArbitro = arbitro.getExperiencia();
        String teamPosition = calculateTeamPosition(equipa);
        double factorJogaPrimeiro = JogaPrimeiro ? 1.1 : 1.0;

        // Return the calculated probability
        return desempenhoMedio *
                numJogadores *
                taticasTreinador *
//                experienciaArbitro *
                factorJogaPrimeiro;
    }

    private static double calculaDesempenhoMedio(Equipa equipa) {
        // Calculate the average performance of each player based on their statistics and injury history
        // For example:
        double totalPerformance = 0.0;
        // for (Jogador jogador : equipa.getPlantel()) {
        //     double performance = (jogador.getGoals() + jogador.getAssists()) / (jogador.getGamesPlayed() + 1);
        //     double injuryImpact = jogador.getInjuryHistory().size() * 0.1;
        //     totalPerformance += performance - injuryImpact;
        // }
        double avgPerformance = totalPerformance / equipa.getPlantel().size();

        // Return the calculated average performance
        return avgPerformance;
    }

    private static double calculaTaticasTreinador(Equipa equipa) {
        // Calculate the impact of the coach's tactics on the team's performance
        // For example:
        // double attackingPlayers = 0.0;
        // double defendingPlayers = 0.0;
        // for (Jogador jogador : equipa.getPlantel()) {
        //     if (jogador.getPosition().equals("Atacante")) {
        //         attackingPlayers += 1.0;
        //     } else if (jogador.getPosition().equals("Defesa")) {
        //         defendingPlayers += 1.0;
        //     }
        // }
        // double coachTactics = attackingPlayers / (attackingPlayers + defendingPlayers);

        // Return the calculated coach tactics
        return coachTactics;
    }

    private static String calculateTeamPosition(Equipa equipa) {
        // Calculate the team's position and adjust the probability based on the number of attacking players
        // For example:
         double attackingPlayers = 0.0;
         double defendingPlayers = 0.0;
//         for (Jogador jogador : equipa.getJogadores()) {
//             if (jogador.getPosicao().equals("Atacante")) {
//                 attackingPlayers += 1.0;
//             } else if (jogador.getPosicao().equals("Defesa")) {
//                 defendingPlayers += 1.0;
//             }
//         }
         if (attackingPlayers > defendingPlayers) {
             return "Atacante";
         } else if (defendingPlayers > attackingPlayers) {
             return "Defesa";
         } else {
             return "Equilibrada";
         }

        // Return the calculated team position
        return posicaoEquipa;
    }
}

