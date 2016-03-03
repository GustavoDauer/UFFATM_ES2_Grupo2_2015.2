/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ccz
 */
public class Transacao {

    enum tipoTransacao {

        PAGAMENTO("pagamento"), SAQUE("saque"), DEPOSITO("deposito"), MOVIMENTO_POUPANCA("movimento_poupanca"), TRANSFERENCIA("transferencia");
        String tipo;

        private tipoTransacao(String tipo) {
            this.tipo = tipo;
        }

    };

    String data, idCliente, idConta, valor, valor_centavos;
    tipoTransacao tipo;

    String idContaTransferencia; // Só usado caso seja uma transferência

    public Transacao() {
    }

    public Transacao(HttpServletRequest request) {
        data = request.getParameter("data");
        idCliente = request.getParameter("idCliente");
        idConta = request.getParameter("idConta");
        valor = request.getParameter("valor");
        String centavos = "0";
        try {
            int posV = request.getParameter("valor").indexOf(",");
            if (posV > 0) {
                centavos = request.getParameter("valor").substring(posV);
            }
        } catch (Exception e) {
            centavos = "0";
        }

        valor_centavos = centavos;

        idContaTransferencia = request.getParameter("idContaTransferencia");;
    }

    public String getData() {
        return data;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getIdConta() {
        return idConta;
    }

    public String getValor() {
        return valor;
    }

    public String getValor_centavos() {
        return valor_centavos;
    }

    public tipoTransacao getTipo() {
        return tipo;
    }

    public String getIdContaTransferencia() {
        return idContaTransferencia;
    }    

    public void setData(String data) {
        this.data = data;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdConta(String idConta) {
        this.idConta = idConta;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setValor_centavos(String valor_centavos) {
        this.valor_centavos = valor_centavos;
    }

    public void setTipo(tipoTransacao tipo) {
        this.tipo = tipo;
    }

    public void setIdContaTransferencia(String idContaTransferencia) {
        this.idContaTransferencia = idContaTransferencia;
    }    
}
