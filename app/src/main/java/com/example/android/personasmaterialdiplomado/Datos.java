package com.example.android.personasmaterialdiplomado;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by android on 07/10/2017.
 */

public class Datos {
    private static String db ="Personas";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private static ArrayList<Persona> personas = new ArrayList();

    public static void guardarPersona(Persona p) {
        //p.setId(databaseReference.push().getKey());
        databaseReference.child(db).child(p.getId()).setValue(p);

    }

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static ArrayList<Persona> obtenerPersonas() {
        return personas;
    }

    public static void setPersonas(ArrayList<Persona> per){
        personas=per;
    }

    public static void eliminarPersona(Persona p){
        databaseReference.child(db).child(p.getId()).removeValue();

    }
    public static int ExistePersonaIndex(String cedula){
        Log.i("TEST",""+personas.size());
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getCedula().equals(cedula)){
                return i;
            }
        }
        return -1;

    }
    public static void Modificar(Persona p) {
       databaseReference.child(db).child(p.getId()).setValue(p);
    }



}

