package com.detomi.calculadora.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.detomi.calculadora.R;
import com.detomi.calculadora.model.Calculadora;
import com.detomi.calculadora.model.Numero;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Numero> numeros = new ArrayList<>();
    Button sum,div,mult,sub,ponto;
    Button calc, reset, back;
    TextView entrada;
    Calculadora calculadora;

    private void iniciarBtsNum(){
        for(int i=0; i < 10; i++){
            String btID = "n" + i ;
            int resID = getResources().getIdentifier(btID, "id", getPackageName());
            Button auxBt = findViewById(resID);
            Numero auxNum = new Numero(auxBt, i);
            numeros.add(auxNum);
        }
    }

    private void iniciarBtsOp(){
        sum = findViewById(R.id.sum);
        div = findViewById(R.id.div);
        mult = findViewById(R.id.mult);
        sub = findViewById(R.id.sub);
        ponto = findViewById(R.id.ponto);
    }

    private void iniciarEntrada(Context context){
        entrada = findViewById(R.id.entrada);
        calculadora = new Calculadora(context);
    }

    private void acoesBtNum(){
        for (Numero n: numeros) {
            n.getReferencia().setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    for(Numero n: numeros){
                        if(n.getReferencia().getId() == view.getId() ){
                            btNumClick(n);
                        }
                    }
                }
            });
        }
    }

    private void iniciarResultReset(){
        reset = findViewById(R.id.c);
        calc = findViewById(R.id.resultado);
        back = findViewById(R.id.back);
    }

    private void acoesResultReset(){
        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String resultado = calculadora.restarCalculadora();
                entrada.setText(resultado);
            }
        });
        calc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String resultado = calculadora.calcula();
                entrada.setText(resultado);
            }
        });
    }

    private void acoesBtsOpe(){
        sum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String resultado = calculadora.addOperador("+");
                entrada.setText(resultado);
            }
        });
        div.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String resultado = calculadora.addOperador("/");
                entrada.setText(resultado);
            }
        });
        mult.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String resultado = calculadora.addOperador("*");
                entrada.setText(resultado);
            }
        });
        sub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String resultado = calculadora.addOperador("-");
                entrada.setText(resultado);
            }
        });
        ponto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String resultado = calculadora.addPonto();
                entrada.setText(resultado);
            }
        });
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String resultado = calculadora.back();
                entrada.setText(resultado);
            }
        });
    }

    private void btNumClick(Numero n){
        String resultado =  calculadora.addNumero(n.getValor());
        entrada.setText(resultado);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instancia dos botões referentes aos números
        iniciarBtsNum();

        // Iniciar operadores
        iniciarBtsOp();

        // Iniciar entrada
        iniciarEntrada(getApplicationContext());

        // Iniciar resultado e reset
        iniciarResultReset();

        // Ações números
        acoesBtNum();

        // Ações operadores
        acoesBtsOpe();

        // Ações iniciar e reset
        acoesResultReset();
    }
}