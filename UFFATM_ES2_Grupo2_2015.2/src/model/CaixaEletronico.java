/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ccz
 */
public class CaixaEletronico {
    
    private int papel;
    private int cheque;
    
    private int nota10;
    private int nota20;
    private int nota50;
    private int nota100;

    public CaixaEletronico(int papel, int cheque, int nota10, int nota20, int nota50, int nota100) {
        this.papel = papel;
        this.cheque = cheque;
        this.nota10 = nota10;
        this.nota20 = nota20;
        this.nota50 = nota50;
        this.nota100 = nota100;
    }
    
}
