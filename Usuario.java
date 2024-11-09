public class Usuario{
  protected String codigoUnico;
  protected String cedula;
  protected String nombres;
  protected String apellidos; 
  protected String usuario;
  protected String contrasenia;
  protected String correo;

  public Usuario(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasenia, String correo){
    this.codigoUnico= codigoUnico;
    this.cedula= cedula;
    this.nombres= nombres;
    this.apellidos= apellidos;
    this.usuario= usuario;
    this.contrasenia= contrasenia;
    this.correo= correo;
  }

  protected void consultarReserva(){
  }

  protected void gestionarReserva(){
  }

  protected void enviarNotificacion(){
  }

}
