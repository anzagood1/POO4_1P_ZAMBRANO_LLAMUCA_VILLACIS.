public abstract class Usuario{
  protected String codigoUnico;
  protected String cedula;
  protected String nombres;
  protected String apellidos;
  protected String usuario;
  protected String contrasenia;
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

  public abstract void consultarReserva();

  public abstract void gestionarReserva();

  public void enviarNotificacion(Reserva rs, Espacio es){
    ArrayList<Administrador> admins = new ArrayList<Administrador>();
    for (Usuario u: Sistema.usuarios){
      if ( u instanceof Administrador){
        Administrador a = (Administrador)u;
        admins.add(a);
      }
    }
    Random r = new Random();
    int i = r.nextInt(0, admins.size()-1);
    String mail = admins.get(i).getCorreo();
    Dotenv dot = Dotenv.load();

    String host = dot.get("MAIL_HOST");
    String port = dot.get("MAIL_PORT");
    String user = dot.get("MAIL_USER");
    String pass = dot.get("MAIL_PASS");

    Properties prop = new Properties();
    prop.put("mail.smtp.host", host);
    prop.put("mail.smtp.port", port);
    prop.put("mail.smtp.auth", true);
    prop.put("mail.smtp.starttls.enable", true);

    Session sesion = Session.getInstance(prop, new Authenticator(){
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(user, pass);
        }
    });

    try {
        Message mes = new MimeMessage(sesion);
        mes.setFrom(new InternetAddress(user, "Reservas de Espacios"));
        mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
        mes.setSubject("Reserva Realizada");
        mes.setText("De: " + this.getCorreo() + "\n" + "El estudiante " + this.getNombres() + this.getApellidos() + 
        " ha realizado una reserva con codigo " + rs.getCodigoReserva() + " para la fecha " + rs.getFechaReserva() + " en el espacio " + 
        es.getNombre() + ". Su motivo es: " +
        rs.getMotivoReserva() + ". Ingrese en el sistema para aprobar o rechazar la reserva");
        Transport.send(mes);
    } catch (Exception e){
        System.out.println(e.getMessage());
    }


    }

  public void enviarNotificacion(Reserva rs, Espacio es, String materia){
    ArrayList<Administrador> admins = new ArrayList<Administrador>();
    for (Usuario u: Sistema.usuarios){
      if ( u instanceof Administrador){
        Administrador a = (Administrador)u;
        admins.add(a);
      }
    }
    Random r = new Random();
    int i = r.nextInt(0, admins.size()-1);
    String mail = admins.get(i).getCorreo();
    Dotenv dot = Dotenv.load();

    String host = dot.get("MAIL_HOST");
    String port = dot.get("MAIL_PORT");
    String user = dot.get("MAIL_USER");
    String pass = dot.get("MAIL_PASS");

    Properties prop = new Properties();
    prop.put("mail.smtp.host", host);
    prop.put("mail.smtp.port", port);
    prop.put("mail.smtp.auth", true);
    prop.put("mail.smtp.starttls.enable", true);

    Session sesion = Session.getInstance(prop, new Authenticator(){
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(user, pass);
        }
    });

    try {
        Message mes = new MimeMessage(sesion);
        mes.setFrom(new InternetAddress(user, "Reservas de Espacios"));
        mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
        mes.setSubject("Reserva Realizada");
        mes.setText("De: " + this.getCorreo() + "\n" + "Se le notifica que el profesor " + this.getNombres() + 
        this.getApellidos() + " ha realizado una reserva con codigo " + rs.getCodigoReserva() + " para la fecha " + 
        rs.getFechaReserva() + " en el espacio " + es.getNombre() + " para la materia " + materia);
        Transport.send(mes);
    } catch (Exception e){
        System.out.println(e.getMessage());
    }
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
}



  
