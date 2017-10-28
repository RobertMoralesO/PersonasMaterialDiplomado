package com.example.android.personasmaterialdiplomado;

/**
 * Created by android on 07/10/2017.
 */

public class Persona {
    private String id;
    private String foto;
    private String cedula;
    private String nombre;
    private String apellido;
    private int sexo;

    public Persona(){

    }

    public Persona(String foto,String cedula, String nombre, String apellido, int sexo){
        this.foto=foto;
        this.cedula=cedula;
        this.nombre=nombre;
        this.apellido=apellido;
        this.sexo=sexo;
    }

    public Persona(String id,String foto,String cedula, String nombre, String apellido, int sexo){
        this.foto=foto;
        this.cedula=cedula;
        this.nombre=nombre;
        this.apellido=apellido;
        this.sexo=sexo;
        this.id=id;
    }
    public Persona(String id){

        this.id=id;
    }

    public Persona(String foto, String nombre, String apellido){
        this.foto=foto;
        this.cedula="";
        this.nombre=nombre;
        this.apellido=apellido;
        this.sexo=0;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void guardar(){
        Datos.guardarPersona(this);
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void eliminar(){
        Datos.eliminarPersona(this);
    }

    public void modificar(){
         Datos.Modificar(this);
    }
}
