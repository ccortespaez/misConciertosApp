package cl.inacap.misconciertosapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cl.inacap.misconciertosapp.dao.ConciertosDAO;
import cl.inacap.misconciertosapp.dto.Concierto;

public class MainActivity extends AppCompatActivity {
    private List<Concierto> conciertos = new ArrayList<>();
    private ArrayAdapter<Concierto> adapter;
    private TextView calendarioTxt;
    private TextView generoTxt;
    private Spinner spinnerGenero;
    private TextView calificacionTxt;
    private Spinner spinnerCalificacion;
    private EditText artistaTxt;
    private EditText valorEntradaTxt;
    private Button agregarBtn;
    private ListView conciertosLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.calendarioTxt = findViewById(R.id.calendarioTxt);
        this.generoTxt = findViewById(R.id.generoTxt);
        this.spinnerGenero=findViewById(R.id.spinnerGenero);
        this.calificacionTxt = findViewById(R.id.calificacionTxt);
        this.spinnerCalificacion = findViewById(R.id.spinnerCalificacion);
        this.artistaTxt = findViewById(R.id.artistaTxt);
        this.valorEntradaTxt = findViewById((R.id.valorEntradaTxt));
        this.agregarBtn = findViewById(R.id.agregarBtn);
        this.conciertosLv = findViewById(R.id.conciertosLv);
        this.adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,conciertos);
        this.conciertosLv.setAdapter(adapter);

        String [] generosMusicales= {"Seleccione","Rock", "Jazz", "Pop", "Reggaeton", "Salsa", "Metal"};
        ArrayAdapter <String> adapterGenero = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, generosMusicales);
        spinnerGenero.setAdapter(adapterGenero);

        String [] calificacion = {"Seleccione","1","2","3","4","5","6","7"};
        ArrayAdapter <String> adapterCalificacion = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, calificacion);
        spinnerCalificacion.setAdapter(adapterCalificacion);



        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                String artista = artistaTxt.getText().toString().trim();
                String fecha = calendarioTxt.getText().toString();
                String generos  = spinnerGenero.getSelectedItem().toString();
                String calificacion = spinnerCalificacion.getSelectedItem().toString();
                String valorStr = valorEntradaTxt.getText().toString().trim();
                int valor =0;
                if (artista.isEmpty()){
                    errores.add("Debe ingresar el nombre del artista");
                }
                if (fecha.isEmpty()){
                    errores.add("Debe ingresar una fecha valida");
                }
                try {
                    valor = Integer.parseInt(valorStr);
                    if (valor<0){
                        throw new NumberFormatException();
                    }
                }catch (NumberFormatException ex){
                    errores.add("El valor debe ser mayor a 0");
                }
                if (generos.equals("Seleccione")){
                    errores.add("Debe seleccionar un genero musical");
                }

                if (calificacion.equals("Seleccione")){
                    errores.add("Debe seleccionar una calificacion");
                }

                if (errores.isEmpty()){
                    Concierto c = new Concierto();
                    c.setNombreArtista(artista);
                    c.setFecha(fecha);
                    c.setCalificacion(calificacion);
                    c.setGenerosMusicales(generos);
                    c.setValorEntrada(valor);
                    conciertos.add(c);
                    new ConciertosDAO().add(c);
                    adapter.notifyDataSetChanged();

                }else{
                    mostrarErrores(errores);
                }
            }
        });

    }

    private void mostrarErrores(List<String> errores){
        String mensaje = "";
        for (String e: errores) {
            mensaje += "-" + e + "\n";
        }
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Error de validacion")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", null)
                .show();

    }

    public void abrirCalendario(View view) {
        Calendar cal = Calendar.getInstance();
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONDAY);
        int anio = cal.get(Calendar.YEAR);
        DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth +"/"+ (month+1) + "/" +year;
                calendarioTxt.setText(fecha);
            }
        }, dia, mes, anio);
        dpd.show();
    }
}