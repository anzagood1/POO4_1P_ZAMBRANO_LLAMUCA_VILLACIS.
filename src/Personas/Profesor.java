package com.example.Personas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import com.example.Funcionalidades.*;
import com.example.Principal.*;
import com.example.ENUMS.TipoEspacio;
import com.example.ENUMS.EstadoEspacio;
import com.example.ENUMS.EstadoReserva;

public class Profesor extends Usuario{
    public static Scanner sc = new Scanner(System.in);
    private String facultad;
    private ArrayList<String> materias;


    /**
     * Este método es el constructor de la subclase Porfesor.
     * @param codigoUnico codigo unico del usuario.
     * @param cedula cedula del usuario.
     * @param nombres nombres del usuario.
     * @param apellidos apellidos del usuario.
     * @param usuario nombre del usuario.
     * @param contrasenia contrasenia del usuario.
     * @param correo correo del usuario.
     * @param facultad facultad del profesor.
     * @param materias materias que dicta el profesor.
     * @return no returna valor, es un constructor.
     */
    public Profesor(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasenia, String correo, String facultad, ArrayList<String> materias){
        super(codigoUnico, cedula,nombres, apellidos, usuario, contrasenia, correo);
        this.facultad = facultad;
        this.materias= materias ;
    }


    /**
     * Este método permite consultar una reserva previamente hecha por el profesor.
     * @param fecha la fecha de la reserva a consultar.
     * @return no retorna valores, imprime en consola.
     */
    @Override
    public void consultarReserva(){
        System.out.println("Cual es la fecha de la reserva que desea consultar? (AAAA-MM-DD)");
        String fString = sc.nextLine();
        LocalDate fecha = LocalDate.parse(fString);
        for (Reserva reserva: Sistema.reservas){
          if (reserva.getFechaReserva()==fecha && reserva.getCodigoUsuario().equals(this.getCodigoUnico())){
            System.out.println("Código de Reserva: "+reserva.getCodigoReserva());
            System.out.println("Fecha de Reserva: "+reserva.getFechaReserva());
            System.out.println("Tipo de Espacio: "+reserva.getTipoDeEspacio());

            for (Espacio espacio: Sistema.espacios){
              if (espacio.getCodigoUnico().equals(reserva.getCodigoUnicoEspacio())){
                System.out.println("Nombre de Espacio"+espacio.getNombre());
                System.out.println("Capacidad de Espacio: "+espacio.getCapacidad());  
                System.out.println("Nombre de Espacio"+espacio.getNombre());
                System.out.println("Capacidad de Espacio: "+espacio.getCapacidad());
              }
            }
 
            for (Usuario usuario: Sistema.usuarios){
                if (usuario.getCodigoUnico().equals(this.getCodigoUnico())){
                    System.out.println("Nombres y Apellidos: "+usuario.getNombres()+usuario.getApellidos());
                }
            }
            System.out.println("Estado de reserva: "+reserva.getEstadoDeLaReserva());
          }
        }
    }

    /**
     * Este método permite al Profesor realizar una reserva de un espacio.
     */
    @Override
    public void gestionarReserva(){
        System.out.println("En que fecha desea realizar su reserva? (AAAA-MM-DD)");
        String fString = sc.nextLine();
        LocalDate fecha = LocalDate.parse(fString);
        System.out.println("Que tipo de espacio desea reservar? (AULA, AUDITORIO, LABORATORIO)");
        String tipo = (sc.nextLine()).toUpperCase();
        TipoEspacio tipoEspacio = TipoEspacio.valueOf(tipo.toUpperCase());
        for (Espacio espacio: Sistema.espacios){
            if((espacio.getTipoDeEspacio()) == tipoEspacio){
                if(espacio.getEstado() == EstadoEspacio.DISPONIBLE){
                    System.out.println(espacio);
                }
            }
        }
        System.out.println("Elija el codigo del espacio que desea reservar ");
        String eleccion = sc.nextLine();
        int i = 0;
        for(String materia:this.getMaterias()){
            i++;
            System.out.println(i + ".|" + materia);
        }  
        System.out.println("Para que numero de materia desea hacer la reserva?");
        int num = sc.nextInt();
        sc.nextLine();
        String materia = this.getMaterias().get(num-1);
        for(Espacio espacio: Sistema.espacios){
            if(eleccion.equals(espacio.getCodigoUnico())){
                System.out.println("Desea reservar el espacio codigo " + espacio.getCodigoUnico() + " en la fecha " + fecha + "?");
                System.out.println("1.- Si");
                System.out.println("2.- No");
                int desicion = sc.nextInt();
                sc.nextLine();
                if(desicion == 1){
                    Reserva r = new Reserva(Reserva.generarCodigoReserva(), espacio.getCodigoUnico(), fecha, espacio.getTipoDeEspacio(), EstadoReserva.APROBADO, materia, this.getCodigoUnico(), this.getCedula());
                    Sistema.reservas.add(r);
                    String rs = r.toString();
                    manejoArchivos.EscribirArchivo("reservas.txt", rs);
                    espacio.setEstado(EstadoEspacio.RESERVADO);
                    manejoArchivos.borrarArchivo("espacios.txt");
                    for(Espacio espa: Sistema.espacios){
                        String es = espa.toString();
                        manejoArchivos.EscribirArchivo("espacios.txt", es);
                    }
                    enviarNotificacion(r, espacio, materia);
                    System.out.println("Reserva creada, regresando al menu...");
                } else{
                    System.out.println("Regresando al menu...");
                }
            }
        }
    }

    //getters
    public String getFacultad(){
        return facultad;
    }

    public ArrayList<String> getMaterias(){
        return materias;
    }

    //setters
    public void setFacultad(String facultad){
        this.facultad = facultad;
    }

    public void setMaterias(ArrayList<String> materias){
        this.materias = materias;
    }
}
