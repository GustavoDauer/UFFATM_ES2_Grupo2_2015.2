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
public abstract class Conta {

    public String numero;
    private int saldo;
    private String cartao; //número de identificação do cartão

    abstract void geraNumero();

    abstract void geraCartao();

}
