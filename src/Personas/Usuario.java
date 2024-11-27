package src.Personas;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import io.github.cdimascio.dotenv.Dotenv;
import javax.mail.*;
import java.util.Scanner;

public abstract class Usuario{
  protected String codigoUnico;
  protected String cedula;
  protected String nombres;
  protected String apellidos;
  protected String usuario;
  protected String contrasenia;
  protected String correo;

  /**
   * Este es el constructor de la clase usuario.
   * @param codigoUnico es el codigo unico del usuario.
   * @param cedula es la cedula del usuario.
   * @param nombres son los nombres del usuario.
   * @param apellidos son los apellidos del usuario.
   * @param usuario es el nombre de usuario.
   * @param contrasenia es la contraseña del usuario.
   * @param correo es el correo del usuario
   * @return Este método no retorna nada, es un constructor.
   **/
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

  /**
   * Este método envia la notificación de una reserva realizada por un estudiantes al correo del administrador.
   * @param rs la reserva realizada.
   * @param es el espacio reservado.
   * @return no retorna valor, impreme en consola.
   **/
  public void enviarNotificacion(Reserva rs, Espacio es){
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
        mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse("svillacish@gmail.com"));
        mes.setSubject("Solicitud de reserva Realizada");
        mes.setText("De: " + this.getCorreo() + "\n" + "El estudiante " + this.getNombres() + this.getApellidos() + 
        " ha realizado una reserva con codigo " + rs.getCodigoReserva() + " para la fecha " + rs.getFechaReserva() + " en el espacio " + 
        es.getNombre() + ". Su motivo es: " +
        rs.getMotivoReserva() + ". Ingrese en el sistema para aprobar o rechazar la reserva");
        Transport.send(mes);
    } catch (Exception e){
        System.out.println(e.getMessage());
    }


  }


  /**
   * Este método envia la notificación de una reserva realizada por un profesor al correo del administrador.
   * @param rs la reserva realizada.
   * @param es el espacio reservado.
   * @param materia la materia por la que se reserva el espacio.
   * @return no retorna valor, impreme en consola.
   **/
  public void enviarNotificacion(Reserva rs, Espacio es, String materia){
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
        mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse("svillacish@gmail.com"));
        mes.setSubject("Reserva Realizada");
        mes.setText("De: " + this.getCorreo() + "\n" + "Se le notifica que el profesor " + this.getNombres() + 
        this.getApellidos() + " ha realizado una reserva con codigo " + rs.getCodigoReserva() + " para la fecha " + 
        rs.getFechaReserva() + " en el espacio " + es.getNombre() + " para la materia " + materia);
        Transport.send(mes);
    } catch (Exception e){
        System.out.println(e.getMessage());
    }
  }

  
  /**
   * Este método envia la notificación del estado de la reserva dado por el administrador hacia el usuario que hizo la reserva.
   * @param rs la reserva realizada.
   * @param u el usuario que realizó la reserva.
   * @param EstadoReserva el estado de la reserva dado por el administrador.
   * @return no retorna valor, impreme en consola.
   **/
  public void enviarNotificacion(Reserva r, Usuario u, EstadoReserva er){
    String desicion;
    if(er == EstadoReserva.RECHAZADO){
      Scanner sc = new Scanner(System.in);
      System.out.println("Cual es el motivo del rechazo? ");
      String motivo = sc.nextLine();
      desicion = "rechazado";
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
        mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(u.getCorreo()));
        mes.setSubject("ESTADO DE RESERVA: " + er);
        mes.setText("De: " + this.getCorreo() + "\n" + "Se ha " + desicion + " su reserva con codigo " 
        + r.getCodigoReserva() + " por el siguiente motivo: " + motivo);
        Transport.send(mes);
    } catch (Exception e){
        System.out.println(e.getMessage());
    }
    } else if(er == EstadoReserva.APROBADO){
      desicion = "aprobado";
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
          mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(u.getCorreo()));
          mes.setSubject("ESTADO DE RESERVA: " + er);
          mes.setText("De: " + this.getCorreo() + "\n" + "Se ha " + desicion + " su reserva con codigo " 
          + r.getCodigoReserva());
          Transport.send(mes);
      } catch (Exception e){
        System.out.println(e.getMessage());
      }
    }
    
  }

//Setters
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

//Getters
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



  
