package com.detomi.calculadora.model;

import android.widget.Button;

public class Numero {
    Button referencia;
    int valor;

    public Numero() {
        this.referencia = null;
        this.valor = -1;
    }

    public Numero(Button btInteface, int valor) {
        this.referencia = btInteface;
        this.valor = valor;
    }

    public Button getReferencia() {
        return referencia;
    }

    public void setReferencia(Button referencia) {
        this.referencia = referencia;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Numero{" +
                "referencia=" + referencia +
                ", valor=" + valor +
                '}';
    }
}
