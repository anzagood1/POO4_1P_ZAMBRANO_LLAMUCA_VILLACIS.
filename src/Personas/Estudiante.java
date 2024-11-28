package com.example.Personas;

import com.example.Funcionalidades.*;
import com.example.Principal.*;
import com.example.ENUMS.*;
import java.util.Scanner;
import java.time.LocalDate;

public class Estudiante extends Usuario{
  public static Scanner sc = new Scanner(System.in);
  private int numMatricula;
  private String carrera;

  /**
   * Este método es el constructor de la subclase Estudiantes.
   * @param codigoUnico codigo unico del usuario.
   * @param cedula cedula del usuario.
   * @param nombres nombres del usuario.
   * @param apellidos apellidos del usuario.
   * @param usuario nombre del usuario.
   * @param contrasenia contrasenia del usuario.
   * @param correo correo del usuario.
   * @param numMatricula número de matricula del estudiante. 
   * @param carrera carrera que estudia el estudiante.
   * @return no retorna valor, es un constructor.
   */
  public Estudiante(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasenia, String correo, int numMatricula, String carrera){
    super(codigoUnico, cedula,nombres, apellidos, usuario, contrasenia, correo);
    this.numMatricula= numMatricula;
    this.carrera= carrera;
  }

  /*
   * Este método permite al Estudiante realizar una reserva de un espacio.
  */
    @Override
  public void gestionarReserva(){
    System.out.println("En que fecha desea realizar su reserva? (AAAA-MM-DD)");
    String fString = sc.nextLine();
    System.out.println("Que tipo de espacio desea reservar? (AULA, CANCHA)");
    String tipo = sc.nextLine();
    TipoEspacio tipoEspacio = TipoEspacio.valueOf(tipo.toUpperCase());
    LocalDate fecha = LocalDate.parse(fString);
    for (Espacio espacio: Sistema.espacios){
      if(espacio.getTipoDeEspacio() == tipoEspacio){
        if(espacio.getEstado() == EstadoEspacio.DISPONIBLE){
          System.out.println(espacio);
        }
      }
    }
    System.out.println("Elija el codigo del espacio que desea reservar ");
    String eleccion = sc.nextLine();
    for (Espacio espacio: Sistema.espacios){
      if(eleccion.equals(espacio.getCodigoUnico())){
        System.out.println("Porque desea realizar la reserva?");
        String motivo = sc.nextLine();
        System.out.println("Desea reservar el espacio codigo " + espacio.getCodigoUnico() + " en la fecha " + fecha + "?");
        System.out.println("1.- Si");
        System.out.println("2.- No");
        int desicion = sc.nextInt();
        sc.nextLine();
        if(desicion == 1){
          Reserva r = new Reserva(Reserva.generarCodigoReserva(), espacio.getCodigoUnico(), fecha, espacio.getTipoDeEspacio(), EstadoReserva.PENDIENTE, motivo, this.getCodigoUnico(), this.getCedula());
          Sistema.reservas.add(r);
          String rs = r.toString();
          manejoArchivos.EscribirArchivo("reservas.txt", rs);
          enviarNotificacion(r, espacio);
          System.out.println("Reserva creada, regresando al menu...");
        }else{
          System.out.println("Regresando al menu...");
        }
      } 
    }
  }
     // sc.close();
  /*
   * Este método permite al estudiante consultar una reserva que haya realizado previamente.
   * @param fecha es la fecha de la reserva a consultar.
   * @return no retorna valores, imprime en consola.
   */
  public void consultarReserva(){
   // Scanner sc = new Scanner(System.in);
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
        }else{
          System.out.println("Regresando al menu");
        }}
   //sc.close();
}

  //setters
  public void setnumMatricula(int numMatricula){
    this.numMatricula= numMatricula;
  }

  public void setCarrera(String carrera){
    this.carrera= carrera;
  }

  //getters
  public int getnumMatricula(){
    return numMatricula;
  }

  public String getCarrera(){
    return carrera;
  }

}
  

  
