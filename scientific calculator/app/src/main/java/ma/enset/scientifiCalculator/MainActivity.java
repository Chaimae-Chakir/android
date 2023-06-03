package ma.enset.scientifiCalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;



public class MainActivity extends AppCompatActivity {

    public enum Ops {
        ADD("+"),
        SOUSTRACTIONS("-"),
        MULTIPLE("*"),
        DIVISER("/");
        private String nom = "";

        Ops(String nom) {
            this.nom = nom;
        }

        public String toString() {
            return name();
        }
    }

    private TextView Affichage;
    private int valeur1 = 0, valeur2 = 0;
    private Ops operateur = null;
    private boolean isOp1 = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Affichage = (TextView) findViewById(R.id.Affichage);


        Button btnClear = (Button) findViewById(R.id.btn_AC);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
    }


    public void Equals(View view) {
        Button btn_equal = (Button) view;
        btn_equal.findViewById(R.id.btn_equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });
    }

    private void afficher() {
        if (!isOp1) {
            // v = valeur2;
            Affichage.setText(String.valueOf(valeur1)+" "+operateur.nom +" "+String.valueOf(valeur2));
        }
        else {
            Affichage.setText(String.valueOf(valeur1));
        }
    }

    public void calculate() {
        if (!isOp1) {
            switch (operateur) {
                case ADD:
                    valeur1 = valeur1 + valeur2;
                    break;
                case MULTIPLE:
                    valeur1 = valeur1 * valeur2;
                    break;
                case SOUSTRACTIONS:
                    valeur1 = valeur1 - valeur2;
                    break;
                case DIVISER:
                    if( valeur2==0) Toast.makeText(this, "Impossible de diviser par 0!", Toast.LENGTH_LONG).show();
                    else valeur1 = valeur1 / valeur2;
                    break;
                default:
                    return; // si ya pas d'operateur
            }
            valeur2 = 0;
            isOp1 = true;
            afficher();
        }
    }


    private void clear() {
        valeur1 = 0;
        valeur2 = 0;
        operateur = null;
        isOp1 = true;
        Affichage.setText(null);
    }


    public void setOperator(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                operateur = Ops.ADD;
                break;
            case R.id.btn_multipe:
                operateur = Ops.MULTIPLE;
                break;
            case R.id.btn_dive:
                operateur = Ops.DIVISER;
                break;
            case R.id.btn_st:
                operateur = Ops.SOUSTRACTIONS;
                break;
            default:
                Toast.makeText(this, "opérateur non reconnu", Toast.LENGTH_LONG).show();
                return;
        }
        isOp1 = false;
        afficher();
    }


    public void ajouterNbr(View view) {
        try {
            int val = Integer.parseInt(((Button) view).getText().toString());
            if (isOp1) {
                valeur1 = valeur1 * 10 + val;
                afficher();
            } else {
                valeur2 = valeur2 * 10 + val;
                afficher();
            }

        } catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Valeur erronée", Toast.LENGTH_LONG);
        }
    }



    public void click(View view) {
        Button clicked = (Button) view;
        if (clicked.getText().toString().equals("AC")) {
            Affichage.clearComposingText();
        } else if (clicked.getText().toString().equals("=")) {
            if (Affichage.getText().toString().trim().isEmpty()) {
                Toast.makeText(MainActivity.this, "SVP entrer une expression!", Toast.LENGTH_LONG).show();
            } else scientifiCalculator(Affichage.getText().toString());
        } else {
            if (clicked.getId() == R.id.btn_add)
                Affichage.setText(Affichage.getText() + "+");
            else if (clicked.getId() == R.id.btn_st)
                Affichage.setText(Affichage.getText() + "-");
            else if (clicked.getId() == R.id.btn_multipe)
                Affichage.setText(Affichage.getText() + "*");
            else if (clicked.getId() == R.id.btn_dive)
                Affichage.setText(Affichage.getText() + "/");
            else Affichage.setText(Affichage.getText() + clicked.getText().toString());
        }
    }

    public void scientifiCalculator(String expression) {
        Expression exp = new Expression(expression);
        double result = exp.calculate();
        if (Double.isNaN(result) && (Affichage.getText().toString().contains("/0"))) {
            Toast.makeText(MainActivity.this, "Impossible de diviser par 0!", Toast.LENGTH_LONG).show();
            clear();
        } else if (Double.isNaN(result)) {
            Toast.makeText(MainActivity.this, "Erreur de syntax !", Toast.LENGTH_LONG).show();
            clear();
        } else {
            Affichage.setText(null);
            Affichage.setText(String.valueOf(result));
        }
    }


}