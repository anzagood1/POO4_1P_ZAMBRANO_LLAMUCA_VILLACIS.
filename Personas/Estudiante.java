public class Estudiante extends Usuario{
  private int numMatricula;
  private String carrera;

  public Estudiante(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasenia, String correo, int numMatricula, String carrera){
    super(codigoUnico, cedula,nombres, apellidos, usuario, contrasenia, correo)
    this.numMatricula= numMatricula;
    this.carrera= carrera;
  }

  public void gestionarReserva(){
    super.gestionarReserva();
  }

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
  
