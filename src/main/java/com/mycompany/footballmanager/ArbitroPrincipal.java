/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballmanager;

/**
 * @author afons
 */
public class ArbitroPrincipal extends Arbitro {
    private static int AI = 1; // Auto Increment
    private int id;

    public ArbitroPrincipal() {
        super.setNome("Arbitro principal nome");
        super.setIdade(random.nextInt(30, 60));
        super.setExperiencia(random.nextInt(1, 15) + " Anos");
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
