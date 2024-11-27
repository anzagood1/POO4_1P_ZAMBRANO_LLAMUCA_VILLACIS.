package src.Personas;

import Funcionalidades;
import ENUMS.Cargo;

public class Administrador extends Usuario{
    Cargo cargoAdmin;

    /**
     * Este método es el constructor de la subclase Administrador.
     * @param codigoUnico codigo unico del usuario.
     * @param cedula cedula del usuario.
     * @param nombres nombres del usuario.
     * @param apellidos apellidos del usuario.
     * @param usuario nombre del usuario.
     * @param contrasenia contrasenia del usuario.
     * @param correo correo del usuario.
     * @param cargoAdmin cargo del administrador
     * @return no retorna valores, es un constructor.
     */
    public Administrador(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasenia, String correo, Cargo cargoAdmin){
        super(codigoUnico, cedula,nombres, apellidos, usuario, contrasenia, correo);
        this.cargoAdmin = cargoAdmin;
    }

    /**
     * Este método permite al administrador visualizar todas las reservas con los datos de estas.
     */
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

    /**
     * Método que permite al admnistrador aprobar o rechazar las reservas.
     */
    public void gestionarReserva(){}
}
