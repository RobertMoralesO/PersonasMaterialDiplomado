package com.example.android.personasmaterialdiplomado;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Modificar extends AppCompatActivity {
    private EditText txtnombre;
    private  EditText txtapellido,txtcedula;
    private TextInputLayout icajaNombre;
    private TextInputLayout icajaApellido;
    private TextInputLayout icajacedula;

    private Resources res;
    private Spinner genero_spiner;
    private String cedula,apellido,nombre,cedulaexis, id,fot;
    private int sexo_d;
    private String genero_vector[];
    private Bundle bundle;
    private Intent i;
    private ImageView foto;
    private Uri filePath;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        storageReference = FirebaseStorage.getInstance().getReference();
        foto = (ImageView)findViewById(R.id.fotoActualizar);
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
        fot = bundle.getString("foto");
        ArrayAdapter<String> adapter_genero = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,genero_vector);
        genero_spiner.setAdapter(adapter_genero);
        genero_spiner.setSelection(sexo_d);
        icajaNombre = (TextInputLayout)findViewById(R.id.cajanombre__modi);
        icajaApellido = (TextInputLayout)findViewById(R.id.cajaapellido_modi);
        icajacedula = (TextInputLayout)findViewById(R.id.cajacedula_modi);

        storageReference.child(fot).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(Modificar.this).load(uri).into(foto);
            }
        });
    }

    public void Modificar(View v){
        String nom = txtnombre.getText().toString();
        String ape = txtapellido.getText().toString();
        String ced = txtcedula.getText().toString();
        Persona p = new Persona(id,fot,ced,nom,ape,genero_spiner.getSelectedItemPosition());

        if(cedula.equals(ced)){

            p.modificar();
            if (filePath !=null)subir_foto(fot);
            Snackbar.make(v, res.getString(R.string.mensaje_exito_modificar), Snackbar.LENGTH_LONG).setAction("action", null).show();
           // Cancelar();
        }else{
            if(Metodos.exitencia_persona(Datos.obtenerPersonas(),ced)){
                txtcedula.setError(res.getString(R.string.persona_existente_error));
                txtcedula.requestFocus();
              }else{
                p.modificar();
                if (filePath !=null)subir_foto(fot);
                Snackbar.make(v, res.getString(R.string.mensaje_exito_modificar), Snackbar.LENGTH_LONG).setAction("action", null).show();
               // Cancelar();
            }
        }


    }
    public void Cancelar(View v){
        Cancelar();
    }
    public void Cancelar(){
       /* String nom = txtnombre.getText().toString();
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
        startActivity(i);*/
        finish();
        Intent i = new Intent(Modificar.this,Principal.class);
        startActivity(i);

    }
    public void onBackPressed(){
        Cancelar();
    }


    public void seleccionar_foto(View v){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,res.getString(R.string.mensaje_seleccion)),1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1){
            filePath = data.getData();
            if(filePath != null){
                foto.setImageURI(filePath);
            }
        }
    }

    public void subir_foto(String foto){
        StorageReference childRef = storageReference.child(foto);
        UploadTask uploadTask = childRef.putFile(filePath);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Cancelar();
            }
        });

    }
}
