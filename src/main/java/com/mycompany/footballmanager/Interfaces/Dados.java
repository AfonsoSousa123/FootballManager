/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.footballmanager.Interfaces;

/**
 * @author afonso, milena, tânia
 */
public interface Dados {
    default void insert() {
        //
    }

    void print();

    default void update(int id) {
        //
    }

    default void delete(int id) {
        //
    }

    default void insertFaker() {
        //
    }
}
