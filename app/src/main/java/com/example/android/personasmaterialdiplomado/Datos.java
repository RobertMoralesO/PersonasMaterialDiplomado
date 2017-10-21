package com.example.android.personasmaterialdiplomado;

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
        p.setId(databaseReference.push().getKey());
        databaseReference.child(db).child(p.getId()).setValue(p);

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
}

