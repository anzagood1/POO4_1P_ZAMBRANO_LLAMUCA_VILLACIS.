public class Usuario{
  protected String codigoUnico;
  protected String cedula;
  protected String nombre;
  protected String apellido;
  protected String usuario;
  protected String contrasena:
  protected String correo;

  public Usuario(String codigoUnico, String cedula, String nombre, String apellido, String usuario, String contrasena, String correo){
    this.codigoUnico = codigoUnico;
    this.cedula = cedula;
    this.nombre = nombre;
    this.apellido = apellido;
    this.usuario = usuario;
    this.contrasena = contrasena;
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

  public void setNombre(String nombre){
    this.nombre = nombre;
  }

  public void setApellido(String apellido){
    this.apellido = apellido;
  }

  public void setUsuario(String usuario){
    this.usuario = usuario;
  }
  
  public void setContrasena(String contrasena){
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

  public String getNombre(){
    return nombre;
  }

  public String getApellido(){
    return apellido;
  }

  public String getUsuario(){
    return usuario;
  }
  
  public String getContrasena(){
    return contrasena;
  }

  public String getCorreo(){
    return correo;
  }




  
