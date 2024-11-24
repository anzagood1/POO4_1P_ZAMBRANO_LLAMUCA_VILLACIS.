package com.example;

public class Administrador extends Usuario{
    Cargo cargoAdmin;

    public Administrador(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasenia, String correo, Cargo cargoAdmin){
        super(codigoUnico, cedula,nombres, apellidos, usuario, contrasenia, correo);
        this.cargoAdmin = cargoAdmin;
    }

    public void consultarReserva(){
        System.out.println("Números de reservas: "+Reserva.numeroReservas);
        System.out.println("Reservas de Estudiantes: ");
        System.out.println("CÓDIGO RESERVA - ESTADO - FECHA - NOMBRES Y APELLIDOS - MATRÍCULA");
        for (Reserva reserva: reservas){
            if(reserva.getUsuario() instanceof Estudiante){
                System.out.println(reserva.getCodigoReserva()+" - "+reserva.getEstadoDeLaReserva()+" - "+reserva.getFechaReserva+" - "+reserva.getUsuario().getNombres()+" "+reserva.getUsuario().getApellidos()+" - "+reserva.getUsuario().getnumMatricula());
            }
        }
        System.out.println("Reservas de Profesores: ");
        System.out.println("CÓDIGO RESERVA - ESTADO - FECHA - NOMBRES Y APELLIDOS - MATERIA");
        for (Reserva reserva: reservas){
            if(reserva.getUsuario() instanceof Profesor){
                System.out.println(reserva.getCodigoReserva()+" - "+reserva.getEstadoDeLaReserva()+" - "+reserva.getFechaReserva+" - "+reserva.getUsuario().getNombres()+" "+reserva.getUsuario().getApellidos()+" - "+reserva.getCodigoUnicoEspacio());
            }
        }
    }

    public void gestionarReserva(){}
}
