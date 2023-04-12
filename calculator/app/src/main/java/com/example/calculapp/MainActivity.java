package com.example.calculapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int val1=0;
    private int val2=0;
    private String operation="";
    private boolean isOp1=true;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }

    private void afficher(){
        if(!isOp1)
            textView.setText(String.valueOf(val1) + " " + operation + " " + String.valueOf(val2) );
        else
            textView.setText(String.valueOf(val1));
    }

    public void setOperator(View v){
        switch (v.getId()){
            case R.id.bu_plus: operation="+"; break;
            case R.id.bu_moins: operation="-"; break;
            case R.id.bu_multi: operation="*"; break;
            case R.id.bu_div: operation="/"; break;
            default:
                return; //do nothing if no operator
        }
        isOp1=false;
        afficher();
    }

    public void addNumber(View v){
        int val = Integer.parseInt(((Button)v).getText().toString());
        if(isOp1){
            val1 = val1 * 10 + val;
            afficher();
        }else{
            val2 = val2 * 10 + val;
            afficher();
        }
    }

    public void calculer(View v) {
        if (!isOp1) {
            switch (operation) {
                case "+" :  val1 = val1 + val2; break;
                case "-" : val1 = val1 - val2;break;
                case "*" : val1 = val1 * val2;break;
                case "/" : val1 = val1 / val2;break;
                default:
                    return; //do nothing if no operator
            }
            val2 = 0;
            isOp1 = true;
            afficher();
        }
    }

    public void reset(View view){
        val1=0;val2=0;isOp1=true;operation="";
        afficher();
    }
}