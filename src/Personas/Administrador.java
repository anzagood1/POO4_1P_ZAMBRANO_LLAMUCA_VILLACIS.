package src.Personas;

import src.Funcionalidades.*;
import src.ENUMS.Cargo;
import src.Principal.*;
import java.util.Scanner;

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
        for (Reserva reserva: Sistema.reservas){
            for (Usuario usuario: Sistema.usuarios){
                if(reserva.getCodigoUnico.equals(usuario.getCodigoUnico)){
                    if(usuario instanceof Estudiante){
                    System.out.println(reserva.getCodigoReserva()+" - "+reserva.getEstadoDeLaReserva()+" - "+reserva.getFechaReserva()+" - "+usuario.getNombres()+" "+usuario.getApellidos()+" - "+usuario.getnumMatricula());
                    }
                }
            }
        }
        System.out.println("Reservas de Profesores: ");
        System.out.println("CÓDIGO RESERVA - ESTADO - FECHA - NOMBRES Y APELLIDOS - MATERIA");
        for (Reserva reserva: Sistema.reservas){
            for (Usuario usuario: Sistema.usuarios){
                if(reserva.getCodigoUnico.equals(usuario.getCodigoUnico)){
                    if(usuario instanceof Profesor){
                    System.out.println(reserva.getCodigoReserva()+" - "+reserva.getEstadoDeLaReserva()+" - "+reserva.getFechaReserva()+" - "+usuario.getNombres()+" "+usuario.getApellidos()+" - "+reserva.getMotivoReserva());
                    }
                }
            }
        }
    }

    /**
    * Método que permite al admnistrador aprobar o rechazar las reservas.
    */
    @Override
    public void gestionarReserva(){
        for(Reserva reserva: Sistema.reservas){
            if(reserva.getEstadoDeLaReserva() == EstadoReserva.PENDIENTE){
                System.out.println(reserva);
            }
        }
        System.out.println("Elija el codigo de reserva que desea gestionar");
        int eleccion = sc.nextInt();
        sc.nextLine();
        for (Reserva reserva: Sistema.reservas){
            if(reserva.getCodigoReserva() == eleccion){
                System.out.println(reserva.getCodigoReserva());
                System.out.println(reserva.getFechaReserva());
                for(Espacio espacio: Sistema.espacios){
                    if(reserva.getCodigoUnicoEspacio().equals(espacio.getCodigoUnico())){
                        System.out.println(espacio.getTipoDeEspacio());
                        System.out.println(espacio.getNombre());
                        System.out.println(espacio.getCapacidad());  
                    }   
                }
                for (Usuario usuario: Sistema.usuarios){
                    if(reserva.getCodigoUsuario().equals(usuario.getCodigoUnico())){
                        System.out.println(usuario.getNombres());
                        System.out.println(usuario.getApellidos());
                    }
                }
            }
        }
        System.out.println("Desea aprobar o rechazar la reserva? (APROBADO/RECHAZADO)");
        String estado = sc.nextLine();
        EstadoReserva tipoEstado = EstadoReserva.valueOf(estado.toUpperCase());
        if (tipoEstado == EstadoReserva.APROBADO){
            for(Reserva reserv: Sistema.reservas){
                if(reserv.getCodigoReserva() == eleccion)
                    reserv.setEstadoDeLaReserva(EstadoReserva.APROBADO);
                    for(Espacio espacio: Sistema.espacios){
                        if(reserv.getCodigoUnicoEspacio().equals(espacio.getCodigoUnico())){
                            espacio.setEstado(EstadoEspacio.RESERVADO);
                }
            }
            manejoArchivos.borrarArchivo("espacios.txt");
            for(Espacio espacio: Sistema.espacios){
                String es = espacio.toString();
                manejoArchivos.EscribirArchivo("espacios.txt", es);
            }
            for(Usuario usuario: Sistema.usuarios){
                if(reserv.getCodigoUsuario().equals(usuario.getCodigoUnico())){
                    enviarNotificacion(reserv,usuario, reserv.getEstadoDeLaReserva());
                }
            }
        } 
    }else if(tipoEstado == EstadoReserva.RECHAZADO){
            for(Reserva reserv: Sistema.reservas){
                reserv.setEstadoDeLaReserva(EstadoReserva.RECHAZADO);
                for(Espacio espacio: Sistema.espacios){
                    if(reserv.getCodigoUnicoEspacio() == espacio.getCodigoUnico()){
                        espacio.setEstado(EstadoEspacio.DISPONIBLE);
                    }
                }
                manejoArchivos.borrarArchivo("espacios.txt");
                for(Espacio espacio: Sistema.espacios){
                    String es = espacio.toString();
                    manejoArchivos.EscribirArchivo("espacios.txt", es);
                }
                for(Usuario usuario: Sistema.usuarios){
                    if(reserv.getCodigoUsuario() == usuario.getCodigoUnico()){
                        enviarNotificacion(reserv,usuario, reserv.getEstadoDeLaReserva());
                    }
                }
            }
        }
    }
}
