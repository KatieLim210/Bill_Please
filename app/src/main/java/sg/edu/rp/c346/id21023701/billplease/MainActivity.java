package sg.edu.rp.c346.id21023701.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    Button btnsplit;
    Button btnreset;
    TextView txtamt;
    TextView txtpax;
    TextView txtdiscount;
    TextView txtbill;
    TextView txtpays;


    EditText etamt;
    EditText etpax;
    EditText etdiscount;


    ToggleButton tbNoSvs;
    ToggleButton tbGST;

    RadioGroup rgPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etamt = findViewById(R.id.et1);
        etpax = findViewById(R.id.et2);
        etdiscount = findViewById(R.id.et3);

        txtamt = findViewById(R.id.txt1);
        txtpax = findViewById(R.id.txt2);
        txtdiscount = findViewById(R.id.txt3);
        txtbill = findViewById(R.id.txt4);
        txtpays = findViewById(R.id.txt5);

        tbNoSvs = findViewById(R.id.toggleButton1);
        tbGST = findViewById(R.id.toggleButton2);

        btnsplit = findViewById(R.id.btn2);
        btnreset = findViewById(R.id.btn1);

        rgPayment = findViewById(R.id.radioGroup);

        btnsplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double newamt = 0.0;
                if(etamt.getText().toString().length()!=0 && etpax.getText().toString().length()!=0){
                }   else if (tbNoSvs.isChecked()&& !tbGST.isChecked()){
                    newamt= Double.parseDouble((etamt.getText().toString()))*1.07;
                }   else if(!tbNoSvs.isChecked()&& tbGST.isChecked()){
                    newamt= Double.parseDouble((etamt.getText().toString()))*1.1;
                }   else if(!tbNoSvs.isChecked()&& !tbGST.isChecked()){
                    newamt= Double.parseDouble((etamt.getText().toString()));

                }   else{
                    newamt= Double.parseDouble((etamt.getText().toString()))*1.17;
                }

                if(etdiscount.getText().toString().length()!=0){
                    newamt= newamt * (1-Double.parseDouble(etdiscount.getText().toString())/100);
                }
                double finalNewamt = newamt;

                 txtbill.setText("Total BIll:$ "+ String.format("%.2f", finalNewamt));
                 int numofpax = Integer.parseInt(etpax.getText().toString());
                int checkedRadioId = rgPayment.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.paynow && numofpax !=1){
                        txtpays.setText("Each Pays: $" + String.format("%.2f", finalNewamt / numofpax) + " via paynow");
                    } else if(checkedRadioId == R.id.cash && numofpax !=1){
                    txtpays.setText("Each Pays: $" + String.format("%.2f", finalNewamt / numofpax) + " via cash");
                }else if(checkedRadioId == R.id.paynow){
                    txtpays.setText("Each Pays: $" + finalNewamt+" via paynow");
                }else{
                    txtpays.setText("Each Pays: $" + finalNewamt+" via cash");
                }


                    }


        });
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etamt.setText("");
                etpax.setText("");
                tbNoSvs.setChecked(false);
                tbGST.setChecked(false);
                etdiscount.setText("");
            }
        });

    ;}

};







