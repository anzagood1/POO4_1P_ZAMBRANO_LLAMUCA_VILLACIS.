public class Usuario{
  protected String codigoUnico;
  protected String cedula;
  protected String nombres;
  protected String apellidos;
  protected String usuario;
  protected String contrasenia:
  protected String correo;

  public Usuario(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasenia, String correo){
    this.codigoUnico = codigoUnico;
    this.cedula = cedula;
    this.nombres = nombres;
    this.apellidos = apellidos;
    this.usuario = usuario;
    this.contrasenia = contrasenia;
    this.correo = correo;
  }

  public void consultarReserva(){
  }

  public void gestionarReserva(){
  }

  public void enviarNotificacion(){
  }

  public void setCodigoUnico(String codigoUnico){
    this.codigoUnico = codigoUnico;
  }

  public void setCedula(String cedula){
    this.cedula = cedula;
  }

  public void setNombres(String nombres){
    this.nombres = nombres;
  }

  public void setApellidos(String apellidos){
    this.apellidos = apellidos;
  }

  public void setUsuario(String usuario){
    this.usuario = usuario;
  }
  
  public void setContrasenia(String contrasenia){
    this.contrasena = contrasena;
  }

  public void setCorreo(String correo){
    this.correo = correo;
  }

   public String getCodigoUnico(){
    return codigoUnico;
  }

  public String getCedula(){
    return cedula;
  }

  public String getNombres(){
    return nombres;
  }

  public String getApellidos(){
    return apellidos;
  }

  public String getUsuario(){
    return usuario;
  }
  
  public String getContrasenia(){
    return contrasena;
  }

  public String getCorreo(){
    return correo;
  }




  
