public class Estudiante extends Usuario{
  private int numMatricula;
  private String carrera;

  public Estudiante(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String costrasenia, String correo, int numMatricula, String carrera){
    super(codigoUnico, cedula,nombres, apellidos, usuario, contrasenia, correo)
    this.setnumMatricula(numMatricula);
    this.setCarrera(carrera);
  }

  public void gestionarReserva(){
    super.gestionarReserva();
  }

  public void consultarReserva(){
    super.consultarReserva();
  }

  public void setnumMatricula(int numMatricula){
    this.numMatricula= numMatricula;
  }

  public void setCarrera(String carrera){
    this.carrera= carrera;
  }

  public int getnumMatricula(){
    return numMatricula;
  }

  public String getCarrera(){
    return carrera;
  }

}
  
