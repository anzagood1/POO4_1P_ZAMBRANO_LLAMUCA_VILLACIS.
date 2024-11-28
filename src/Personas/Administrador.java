package com.example.Personas;

import com.example.Funcionalidades.*;
import com.example.ENUMS.*;
import com.example.Principal.*;
import java.util.Scanner;

public class Administrador extends Usuario{
    public static Scanner sc = new Scanner(System.in);
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
    @Override
    public void consultarReserva(){
        System.out.println("Números de reservas: "+Reserva.numeroReservas);
        System.out.println("Reservas de Estudiantes: ");
        System.out.println("CÓDIGO RESERVA - ESTADO - FECHA - NOMBRES Y APELLIDOS - MATRÍCULA");
        for (Reserva reserva: Sistema.reservas){
            for (Usuario usuario: Sistema.usuarios){
                if(reserva.getCodigoUsuario().equals(usuario.getCodigoUnico())){
                    if(usuario instanceof Estudiante){
                    Estudiante u = (Estudiante) usuario;
                    System.out.println(reserva.getCodigoReserva()+" - "+reserva.getEstadoDeLaReserva()+" - "+reserva.getFechaReserva()+" - "+u.getNombres()+" "+u.getApellidos()+" - "+u.getnumMatricula());
                    };
                }
            }
        }
        System.out.println("Reservas de Profesores: ");
        System.out.println("CÓDIGO RESERVA - ESTADO - FECHA - NOMBRES Y APELLIDOS - MATERIA");
        for (Reserva reserva: Sistema.reservas){
            for (Usuario usuario: Sistema.usuarios){
                if(reserva.getCodigoUsuario().equals(usuario.getCodigoUnico())){
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
        int i = 0;
        for(Reserva reserva: Sistema.reservas){
            if(reserva.getEstadoDeLaReserva() == EstadoReserva.PENDIENTE){
                System.out.println(reserva);
                i = 1;
            }
        }
        if(i == 1){
            System.out.println("Elija el codigo de reserva que desea gestionar");
            int eleccion = sc.nextInt();
            sc.nextLine();
            int x = 0;
            for (Reserva reserva: Sistema.reservas){
                if(reserva.getCodigoReserva() == eleccion){
                    x++;
                    System.out.println("Codigo de Reserva: " + reserva.getCodigoReserva());
                    System.out.println("Fecha de Reserva: " + reserva.getFechaReserva());
                    for(Espacio espacio: Sistema.espacios){
                        if(reserva.getCodigoUnicoEspacio().equals(espacio.getCodigoUnico())){
                            System.out.println("Tipo de Espacio: " + espacio.getTipoDeEspacio());
                            System.out.println("Nombre de Espacio: " + espacio.getNombre());
                            System.out.println("Capacidad de Espacio: " + espacio.getCapacidad());  
                        }   
                    }
                    for (Usuario usuario: Sistema.usuarios){
                        if(reserva.getCodigoUsuario().equals(usuario.getCodigoUnico())){
                            System.out.println("Nombres de Usuario: " + usuario.getNombres());
                            System.out.println("Apellidos de Usuario: " + usuario.getApellidos());
                        }
                    }
                }
            }
            if(x != 0){
            System.out.println("Desea aprobar o rechazar la reserva? (APROBADO/RECHAZADO)");
            String estado = sc.nextLine();
            EstadoReserva tipoEstado = EstadoReserva.valueOf(estado.toUpperCase());
            if (tipoEstado == EstadoReserva.APROBADO){
                for(Reserva reserv: Sistema.reservas){
                    if(reserv.getCodigoReserva() == eleccion){
                        reserv.setEstadoDeLaReserva(EstadoReserva.APROBADO);
                        for(Espacio espacio: Sistema.espacios){
                            if(reserv.getCodigoUnicoEspacio().equals(espacio.getCodigoUnico())){
                                espacio.setEstado(EstadoEspacio.RESERVADO);
                            }
                        }
                        for(Usuario usuario: Sistema.usuarios){
                            if(reserv.getCodigoUsuario().equals(usuario.getCodigoUnico())){
                                enviarNotificacion(reserv, usuario, reserv.getEstadoDeLaReserva());
                                System.out.println("Reserva aprobada");
                                System.out.println("Regresando al menu...");
                            }
                        }
                    }
                }  
            }else if(tipoEstado == EstadoReserva.RECHAZADO){
                for(Reserva reserv: Sistema.reservas){
                    if(reserv.getCodigoReserva() == eleccion){
                        reserv.setEstadoDeLaReserva(EstadoReserva.RECHAZADO);
                        for(Espacio espacio: Sistema.espacios){
                            if(reserv.getCodigoUnicoEspacio().equals(espacio.getCodigoUnico())){
                                espacio.setEstado(EstadoEspacio.DISPONIBLE);
                            }
                        }
                        for(Usuario usuario: Sistema.usuarios){
                            if(reserv.getCodigoUsuario().equals(usuario.getCodigoUnico())){
                                enviarNotificacion(reserv,usuario, reserv.getEstadoDeLaReserva());
                                System.out.println("Reserva rechazada");
                                System.out.println("Regresando al menu...");
                            }
                        }
                    }
                }  
            }
        }
        if(x == 0){
            System.out.println("No hay reservas con el codigo ingresado");
            System.out.println("Regresando al menu");
        }
            manejoArchivos.borrarArchivo("reservas.txt");
            for(Reserva reservs: Sistema.reservas){
                String rs = reservs.toString();
                manejoArchivos.EscribirArchivo("reservas.txt", rs);
            }
            manejoArchivos.borrarArchivo("espacios.txt");
            for(Espacio espacio: Sistema.espacios){
                String es = espacio.toString();
                manejoArchivos.EscribirArchivo("espacios.txt", es);
            }  
        }else{
            System.out.println("No hay reservas pendientes");
            System.out.println("Regresando al menu...");
        }
    }
    //setter
    public void setCargo (Cargo cargoAdmin){
        this.cargoAdmin= cargoAdmin;
    }

    //getter
    public Cargo getCargo (){
    return cargoAdmin;
    }
}
