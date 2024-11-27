package src.Personas;

import Funcionalidades;

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
  public void gestionarReserva(){
    super.gestionarReserva();
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
  
