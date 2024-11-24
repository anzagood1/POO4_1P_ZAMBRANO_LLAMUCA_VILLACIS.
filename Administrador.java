package com.example;

public class Administrador extends Usuario{
    Cargo cargoAdmin;

    public Administrador(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasenia, String correo, Cargo cargoAdmin){
        super(codigoUnico, cedula,nombres, apellidos, usuario, contrasenia, correo);
        this.cargoAdmin = cargoAdmin;
    }

    public void consultarReserva(){}

    public void gestionarReserva(){}
}
