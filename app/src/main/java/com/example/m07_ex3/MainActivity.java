package com.example.m07_ex3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int contadorA=0;
    int contadorB=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button_point_3_teamA = findViewById(R.id.button_point_3);
        Button button_point_2_teamA = findViewById(R.id.button_point_2);
        Button button_free_teamA = findViewById(R.id.button_free);

        Button button_point_3_teamB = findViewById(R.id.button_point_3_teamB);
        Button button_point_2_teamB = findViewById(R.id.button_point_2_teamB);
        Button button_free_teamB = findViewById(R.id.button_free_teamB);

        Button button_reset = findViewById(R.id.button_reset);

        button_point_3_teamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntuar(v );
                actualizaEstadoPelota();
            }
        });

        button_point_2_teamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntuar(v);
                actualizaEstadoPelota();
            }
        });

        button_free_teamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntuar(v);
                actualizaEstadoPelota();
            }
        });

        button_point_3_teamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntuar(v);
                actualizaEstadoPelota();
            }
        });

        button_point_2_teamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntuar(v);
                actualizaEstadoPelota();
            }
        });

        button_free_teamB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntuar(v);
                actualizaEstadoPelota();
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset(v);
                actualizaEstadoPelota();
            }
        });


    }

    public void puntuar(View view){

        TextView textView_point_teamA = findViewById(R.id.textView_point_teamA);
        TextView textView_point_teamB = findViewById(R.id.textView_point_teamB);

        if(view.getId() == R.id.button_point_3){
            contadorA +=3;
        } else if(view.getId() == R.id.button_point_2){
            contadorA +=2;
        } else if (view.getId() == R.id.button_free){
            contadorA +=1;
        }

        if(view.getId() == R.id.button_point_3_teamB){
            contadorB +=3;
        } else if(view.getId() == R.id.button_point_2_teamB){
            contadorB +=2;
        } else if (view.getId() == R.id.button_free_teamB){
            contadorB +=1;
        }

        textView_point_teamA.setText(String.valueOf(contadorA));
        textView_point_teamB.setText(String.valueOf(contadorB));

    }

    public void reset(final View view){

        final TextView textView_point_teamA = findViewById(R.id.textView_point_teamA);
        final TextView textView_point_teamB = findViewById(R.id.textView_point_teamB);

        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Resetear puntos?");
        dialogo.setMessage("Perderás la puntuación registrada hasta el momento.");
        dialogo.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                contadorA = contadorB = 0;
                textView_point_teamA.setText("0");
                textView_point_teamB.setText("0");
                actualizaEstadoPelota();

            }
        });
        dialogo.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                //No hacer nada, se mantienen los contadores
            }
        });
        dialogo.show();

    }


    /* Actualiza la pelota según el estado de los contadores
   - Si es empate, se muestra la pelota imparcial
   - Si gana uno o el otro, la pelota ha de mirar hacia un marcador o el
     otro.
*/
    public void actualizaEstadoPelota(){
        ImageView imageView = findViewById(R.id.imageView);
        if(contadorA == contadorB){
            imageView.setImageResource(R.drawable.basketball2);
        } else if(contadorA > contadorB){
            imageView.setRotationY(-180f);
            imageView.setImageResource(R.drawable.basketball1);
        } else if(contadorB > contadorA){
            imageView.setRotationY(0f);
            imageView.setImageResource(R.drawable.basketball1);
        }
    }

    public void onSaveInstanceState(Bundle estado ) {
        super.onSaveInstanceState(estado);
        estado.putInt("contadorA", contadorA);
        estado.putInt("contadorB", contadorB);
    }

    public void onRestoreInstanceState(Bundle estado){
        super.onRestoreInstanceState(estado);

        TextView textView_point_teamA = findViewById(R.id.textView_point_teamA);
        TextView textView_point_teamB = findViewById(R.id.textView_point_teamB);

        contadorA = estado.getInt("contadorA");
        contadorB = estado.getInt("contadorB");

        textView_point_teamA.setText(String.valueOf(contadorA));
        textView_point_teamB.setText(String.valueOf(contadorB));

        actualizaEstadoPelota();
    }


}
