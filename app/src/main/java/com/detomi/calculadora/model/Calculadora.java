package com.detomi.calculadora.model;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;

public class Calculadora {
    ArrayList<Numero> pilhaNumeros;
    String primeiroNumero;
    String segundoNumero;
    boolean primeiroNumeroCompleto;
    boolean sengundoNumeroCompleto;
    String operador;
    boolean operadorEscolhido;
    boolean primeiroNumeroPonto;
    boolean segundoNumeroPonto;
    String resultado;
    Context context;

    public Calculadora(Context context) {
        this.context = context;
        pilhaNumeros = new ArrayList<>();
        primeiroNumero = "";
        segundoNumero = "";
        primeiroNumeroCompleto = false;
        sengundoNumeroCompleto = false;
        operadorEscolhido = false;
        primeiroNumeroPonto = false;
        segundoNumeroPonto = false;
        operador = "";
        resultado = "";
    }

    public String restarCalculadora(){
        pilhaNumeros = new ArrayList<>();
        primeiroNumero = "";
        segundoNumero = "";
        primeiroNumeroCompleto = false;
        sengundoNumeroCompleto = false;
        operadorEscolhido = false;
        primeiroNumeroPonto = false;
        segundoNumeroPonto = false;
        operador = "";
        resultado = "";
        return 0+"";
    }

    public void Alerta(String msg){
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public String addNumero(int numero){
        if(primeiroNumeroCompleto == false) {
            if(primeiroNumero.length() > 8){
                Alerta("Limite máximo de algarismos!");
                return primeiroNumero + operador + segundoNumero;
            }
            primeiroNumero = primeiroNumero.concat(String.valueOf(numero));
        } else if(sengundoNumeroCompleto == false && operadorEscolhido == true){
            if(segundoNumero.length() > 8){
                Alerta("Limite máximo de algarismos!");
                return primeiroNumero + operador + segundoNumero;
            }
            segundoNumero = segundoNumero.concat(String.valueOf(numero));
        }
        return primeiroNumero + operador + segundoNumero;
    }

    public String addPonto(){
        if(primeiroNumeroPonto == false && primeiroNumero.length() > 0){
            primeiroNumeroPonto = true;
            primeiroNumero = primeiroNumero.concat(".");
        } else if(segundoNumeroPonto == false && segundoNumero.length() > 0){
            segundoNumeroPonto = true;
            segundoNumero = segundoNumero.concat(".");
        } else{
            Alerta("Não é possível adicionar a pontuação!");
        }
        return primeiroNumero + operador + segundoNumero;
    }

    public String addOperador(String operador){
        if(operadorEscolhido == false && primeiroNumero.length() > 0) {
            this.operador = operador;
            primeiroNumeroCompleto = true;
            operadorEscolhido = true;
        } else {
            Alerta("Não é possível adicionar o operador!");
            if(primeiroNumero.length() == 0)
                return "0";
        }
        return primeiroNumero + this.operador + segundoNumero;
    }

    public String back(){
        if(primeiroNumeroCompleto == true && operadorEscolhido == true && segundoNumero.length() > 0){
            String aux = segundoNumero.substring(0, segundoNumero.length()-1);
            segundoNumero = aux;
            if(!segundoNumero.contains(".")){
                segundoNumeroPonto = false;
            }
        } else if(segundoNumero.length() == 0 && operadorEscolhido == true){
            operadorEscolhido = false;
            operador = "";
        } else if(primeiroNumero.length() > 0){
            primeiroNumeroCompleto = false;
            String aux = primeiroNumero.substring(0, primeiroNumero.length()-1);
            primeiroNumero = aux;
            if(!primeiroNumero.contains(".")){
                primeiroNumeroPonto = false;
            }
        }
        if(primeiroNumero.length() == 0) {
            return "0";
        }
        return primeiroNumero + this.operador + segundoNumero;
    }

    // Retorna verdadeiro se a "String" é um número(só contem números)
    public static boolean eNumeroValido(String StringNumero) {
        int i;
        String NUM = "0123456789.-";
        for(i=0;i<StringNumero.length();i++){
            if(!NUM.contains(StringNumero.charAt(i)+"")){
                return false;
            }
        }
        return true;
    }

    public String calcula(){
        if(primeiroNumeroCompleto == true && segundoNumero.length() > 0 && operadorEscolhido == true) {
            double n1 = Double.parseDouble(primeiroNumero);
            double n2 = Double.parseDouble(segundoNumero);
            double resultado;
            switch (operador) {
                case "/":
                    if(n2 == 0){
                        Alerta("Não é possível realizar divisão por zero!");
                        return primeiroNumero + this.operador + segundoNumero;
                    }
                    resultado = n1 / n2;
                    break;
                case "*":
                    resultado = n1 * n2;
                    break;
                case "-":
                    resultado = n1 - n2;
                    break;
                case "+":
                    resultado = n1 + n2;
                    break;
                default:
                    throw new IllegalStateException("operador inválido: " + operador);
            }
            // Verifica o resultado
            if(eNumeroValido(String.valueOf(resultado)) == false){
                Alerta("Não foi possível realizar o cálculo!");
                restarCalculadora();
                return "0";
            }
            restarCalculadora();
            this.primeiroNumero = String.valueOf(resultado);
            this.primeiroNumeroCompleto = true;
            this.primeiroNumeroPonto = true;
            this.resultado = String.valueOf(resultado);
            return String.valueOf(resultado);
        }
        Alerta("Expressão inválida!");
        return primeiroNumero + this.operador + segundoNumero;
    }
}
