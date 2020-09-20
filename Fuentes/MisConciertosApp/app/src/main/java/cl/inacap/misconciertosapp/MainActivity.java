package cl.inacap.misconciertosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView calendarioTxt;
    private TextView spinnerTxt;
    private Spinner spinnerGenero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarioTxt = findViewById(R.id.calendarioTxt);
        spinnerTxt = findViewById(R.id.spinnerTxt);
        spinnerGenero=findViewById(R.id.spinnerGenero);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.generos_array
                , android.R.layout.simple_spinner_item);
        spinnerGenero.setAdapter(adapter);

    }

    public void abrirCalendario(View view) {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONDAY);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int dayOfMonth, int month, int year) {
                String fecha = dayOfMonth+"/"+month+"/"+year;
                calendarioTxt.setText(fecha);
            }
        }, dia, mes, anio);
        dpd.show();
    }
}