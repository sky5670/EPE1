package com.example.rlcr.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Double.parseDouble;


public class MainActivity extends AppCompatActivity {

    EditText et_epr1, et_epr2, et_epe1, et_epe2, et_total, et_eva1, et_eva2, et_eva3, et_eva4, eximido, et_examen, prom_final;
    Button btn_aceptar, button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Solemnes
        et_epr1 = (EditText) findViewById(R.id.et_epr1);
        et_epr2 = (EditText) findViewById(R.id.et_epr2);
        et_epe1 = (EditText) findViewById(R.id.et_epe1);
        et_epe2 = (EditText) findViewById(R.id.et_epe2);

        //Evas
        et_eva1 = (EditText) findViewById(R.id.et_eva1);
        et_eva2 = (EditText) findViewById(R.id.et_eva2);
        et_eva3 = (EditText) findViewById(R.id.et_eva3);
        et_eva4 = (EditText) findViewById(R.id.et_eva4);

        //Total
        et_total = (EditText) findViewById(R.id.et_total);
        eximido = (EditText) findViewById(R.id.eximido);

        et_examen = (EditText) findViewById(R.id.et_examen);
        prom_final = (EditText) findViewById(R.id.prom_final);

        et_examen.setEnabled(false);

        prom_final.setKeyListener(null);
        prom_final.setFocusable(false);


        et_total.setKeyListener(null);
        et_total.setFocusable(false);

        eximido.setKeyListener(null);
        eximido.setFocusable(false);

        btn_aceptar = (Button) findViewById(R.id.btn_aceptar);
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context contexto = getApplicationContext();

                    double epr1 = parseDouble(et_epr1.getText().toString())/10;
                    double epr2 = parseDouble(et_epr2.getText().toString())/10;
                    double epe1 = parseDouble(et_epe1.getText().toString())/10;
                    double epe2 = parseDouble(et_epe2.getText().toString())/10;

                    double eva1 = parseDouble(et_eva1.getText().toString())/10;
                    double eva2 = parseDouble(et_eva2.getText().toString())/10;
                    double eva3 = parseDouble(et_eva3.getText().toString())/10;
                    double eva4 = parseDouble(et_eva4.getText().toString())/10;

                    Log.d("EPR1: ",""+epr1);
                    Log.d("EPE1: ",""+epe1);
                    Log.d("EPR2: ",""+epr2);
                    Log.d("EPE2: ",""+epe2);


                    //Calculo
                    double resusol = (epr1 * 0.10) +  (epe1 * 0.15) + (epr2 * 0.20) + (epe2 * 0.25);
                    double resueva = ((eva1+eva2+eva3+eva4)/4)*0.3;

                    double resultado = (resusol + resueva);

                long mult=(long)Math.pow(10,1);
                double resu=(Math.round(resultado*mult))/(double)mult;

                    et_total.setText(Double.toString(resu));
                    Toast.makeText(contexto, "No necesita dar examen, tiene un :" + resu, Toast.LENGTH_SHORT).show();

                if (resultado >= 5.5)
                    eximido.setText("Eximido");
                else{
                    eximido.setText("No eximido");
                    Toast.makeText(contexto, "Debe examen" + resu, Toast.LENGTH_SHORT).show();
                    et_examen.setEnabled(true);
                    et_examen.setFocusable(true);


                }
            }


        });

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent button1 = new Intent(MainActivity.this, Datos.class);
                startActivity(button1);
            }
        });


        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

        double exa = Double.parseDouble(et_examen.getText().toString())/10;
        double pepe = Double.parseDouble(et_total.getText().toString());


        double resufinal = (exa * 0.3) + (pepe * 0.7);
                Double promFinal = aproximar(resufinal);
        prom_final.setText(Double.toString(promFinal));
            }
        });

    }

    public Double aproximar(Double notaFinal){
        long mult=(long)Math.pow(10,1);
        double resu=(Math.round(notaFinal*mult))/(double)mult;
        return resu;
    }

}// cierra la clase


