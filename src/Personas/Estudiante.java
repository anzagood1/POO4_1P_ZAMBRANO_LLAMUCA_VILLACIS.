package src.Personas;

import Funcionalidades;
import java.util.Scanner;
import java.time.LocalDate;

public class Estudiante extends Usuario{
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
    super(codigoUnico, cedula,nombres, apellidos, usuario, contrasenia, correo)
    this.numMatricula= numMatricula;
    this.carrera= carrera;
  }

  /*
   * Este método permite al Estudiante realizar una reserva de un espacio.
  */
  @Override
  public void gestionarReserva(){
    Scanner sc = new Scanner(System.in);
    System.out.println("En que fecha desea realizar su reserva? (AAAA-MM-DD)");
    String fString = sc.nextLine();
    System.out.println("Que tipo de espacio desea reservar? (AULA, CACNCHA)");
    String tipo = sc.nextLine();
    TipoEspacio tipoEspacio = TipoEspacio.valueOf(tipo.toUpperCase());
    LocalDate fecha = LocalDate.parse(fString);
    int i = 0;
    for (Espacio espacio: Sistema.espacios){
      if(espacio.getTipoDeEspacio() == tipoEspacio){
        if(espacio.getEstado() == EstadoEspacio.DISPONIBLE){
          i++;
          System.out.println(i + ".|" + espacio);
        }
      }
    }
    System.out.println("Elija el numero del espacio que desea reservar ");
    int eleccion = sc.nextInt();
    sc.nextLine();
    Espacio e = Sistema.espacios.get(eleccion-1);
    System.out.println("Porque desea realizar la reserva?");
    String motivo = sc.nextLine();
    System.out.println("Desea reservar el espacio codigo " + e.getCodigoUnico() + " en la fecha " + fecha + "?");
    System.out.println("1.- Si");
    System.out.println("2.- No");
    int desicion = sc.nextInt();
    sc.nextLine();
    if(desicion == 1){
      Reserva r = new Reserva(Reserva.generarCodigoReserva(), e.getCodigoUnico(), fecha, e.getTipoDeEspacio(), EstadoReserva.PENDIENTE, motivo, this.getCodigoUnico(), this.getCedula());
      Sistema.reservas.add(r);
      String rs = r.toString();
      ManejoArchivos.EscribirArchivo("reservas.txt", rs);
      enviarNotificacion(r, e);
    }
  }

  /*
   * Este método permite al estudiante consultar una reserva que haya realizado previamente.
   * @param fecha es la fecha de la reserva a consultar.
   * @return no retorna valores, imprime en consola.
   */
  public void consultarReserva(){
    for (Reserva reserva: reservas){
      if (reserva.getFechaReserva()==fecha && reserva.getUsuario().getCodigoUnico()==this.getCodigoUnico()){
        Espacio espacio;
        for (Espacio espa: espacios){
          if (espa.getCodigoUnico()==reserva.getCodigoUnicoEspacio()){
            espacio=espa;
          }
        }

        System.out.println("Código de Reserva: "+reserva.getCodigoReserva());
        System.out.println("Fecha de Reserva: "+reserva.getFechaReserva());
        System.out.println("Tipo de Espacio: "+reserva.getTipoDeEspacio());
        System.out.println("Nombre de Espacio"+espacio.getNombre());
        System.out.println("Capacidad de Espacio: "+espacio.getCapacidad());
        System.out.println("Nombres y Apellidos: "+reserva.getUsuario().getNombres()+reserva.getUsuario().getApellidos());
        System.out.println("Estado de reserva: "+reserva.getEstadoDeLaReserva());
      }
    }
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
  
