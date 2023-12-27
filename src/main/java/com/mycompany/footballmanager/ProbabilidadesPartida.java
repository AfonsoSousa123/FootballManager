/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author afonso, milena, tânia
 */
public class ProbabilidadesPartida {
    private Random random = new Random();

    public static double calculaProbabilidade(Equipa equipa, ArrayList<Arbitro> arbitros, boolean JogaPrimeiro) {
        double desempenhoMedio = calculaDesempenhoMedio(equipa);
        int numJogadores = equipa.getPlantel().size();
        double taticasTreinador = Double.parseDouble(calculaTaticasTreinador(equipa));
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

    private static String calculaTaticasTreinador(Equipa equipa) {
        String probabilidade_ganhar = "";
        // Impacto da tática do treinador na probabilidade de ganhar
        for (Treinador treinador : equipa.getTreinadoresValues(equipa.getIdTreinador())) {
            String tatica_n = treinador.getTaticas_fav().replaceAll("-", ""); // String sem "-"
            String lastNumberStr = tatica_n.substring(tatica_n.length() - 1); // Ultimo numero da string corresponde aos atacantes
            int last_number_tatica = Integer.parseInt(lastNumberStr); // passar de string para inteiro para poder ser comparável
            if (last_number_tatica >= 3) {
                probabilidade_ganhar = "maior";
            } else {
                probabilidade_ganhar = "menor";
            }
        }
        return probabilidade_ganhar;
    }

    private static String calculaPosicaoEquipa(Equipa equipa) {
        // Calcula a posicao da equipa e ajusta a probabilidade baseada no numero de Atacantes
        // For example:
        double atacantes = 0.0;
        double defesas = 0.0;

        for (Jogador jogador : equipa.getJogadoresValues(equipa.getPlantel())) {
            if (jogador.getPosicao() == Posicao.AVANCADO || jogador.getPosicao() == Posicao.CENTRAL) {
                atacantes += 1.0;
            } else if (jogador.getPosicao() == Posicao.DEFESA || jogador.getPosicao() == Posicao.MEDIO) {
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

