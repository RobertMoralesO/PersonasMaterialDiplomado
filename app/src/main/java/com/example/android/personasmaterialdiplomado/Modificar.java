package com.example.android.personasmaterialdiplomado;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class Modificar extends AppCompatActivity {
    private EditText txtnombre;
    private  EditText txtapellido,txtcedula;
    private TextInputLayout icajaNombre;
    private TextInputLayout icajaApellido;
    private TextInputLayout icajacedula;
    ArrayList<Integer> foto;
    private Resources res;
    private Spinner genero_spiner;
    private String cedula,apellido,nombre,cedulaexis, id;
    private int sexo_d,fot;
    private String genero_vector[];
    private Bundle bundle;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        txtnombre = (EditText)findViewById(R.id.txtnombre_modi);
        txtcedula = (EditText)findViewById(R.id.txtcedula_modi);
        txtapellido = (EditText)findViewById(R.id.txtapellido_modi);
        genero_spiner=(Spinner) findViewById(R.id.cbxsexo_modi);
        res = this.getResources();
        genero_vector=res.getStringArray(R.array.sexo);
        i = getIntent();
        bundle = i.getBundleExtra("datos");
        id = bundle.getString("id");
        cedula = bundle.getString("cedula");
        nombre = bundle.getString("nombre");
        apellido = bundle.getString("apellido");
        txtapellido.setText(apellido);
        txtcedula.setText(cedula);

        txtnombre.setText(nombre);
        sexo_d = bundle.getInt("sexo");
        fot = bundle.getInt("foto");
        ArrayAdapter<String> adapter_genero = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,genero_vector);
        genero_spiner.setAdapter(adapter_genero);
        genero_spiner.setSelection(sexo_d);
        icajaNombre = (TextInputLayout)findViewById(R.id.cajanombre__modi);
        icajaApellido = (TextInputLayout)findViewById(R.id.cajaapellido_modi);
        icajacedula = (TextInputLayout)findViewById(R.id.cajacedula_modi);
    }

    public void Modificar(View v){
        String nom = txtnombre.getText().toString();
        String ape = txtapellido.getText().toString();
        String ced = txtcedula.getText().toString();
        Persona p = new Persona(id,fot,ced,nom,ape,genero_spiner.getSelectedItemPosition());

        if(cedula.equals(ced)){
            p.modificar();
            Snackbar.make(v, res.getString(R.string.mensaje_exito_modificar), Snackbar.LENGTH_LONG).setAction("action", null).show();
            Cancelar();
        }else{
            if(Metodos.exitencia_persona(Datos.obtenerPersonas(),ced)){
                txtcedula.setError(res.getString(R.string.persona_existente_error));
                txtcedula.requestFocus();
              }else{
                p.modificar();
                Snackbar.make(v, res.getString(R.string.mensaje_exito_modificar), Snackbar.LENGTH_LONG).setAction("action", null).show();
                Cancelar();
            }
        }


    }
    public void Cancelar(View v){
        Cancelar();
    }
    public void Cancelar(){
        String nom = txtnombre.getText().toString();
        String ape = txtapellido.getText().toString();
        String ced = txtcedula.getText().toString();
        Intent i = new Intent(Modificar.this,DetallePersona.class);
        Bundle b = new Bundle();
        b.putString("cedula",ced);
        b.putString("nombre",nom);
        b.putString("apellido",ape);

        b.putInt("sexo",genero_spiner.getSelectedItemPosition());
        b.putInt("foto",fot);
        i.putExtra("datos",b);
        startActivity(i);
    }
}
