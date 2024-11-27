import java.util.ArrayList;
public class Profesor extends Usuario{
    
    private facultad facultad;
    private ArrayList<String> materias;


    /**
     * Este método es el constructor de la subclase Porfesor.
     * @param codigoUnico codigo unico del usuario.
     * @param cedula cedula del usuario.
     * @param nombres nombres del usuario.
     * @param apellidos apellidos del usuario.
     * @param usuario nombre del usuario.
     * @param contrasenia contrasenia del usuario.
     * @param correo correo del usuario.
     * @param facultad facultad del profesor.
     * @param materias materias que dicta el profesor.
     * @return no returna valor, es un constructor.
     */
    Profesor(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasenia, String correo, facultad facultad, ArrayList<String> materias){
        super(codigoUnico, cedula,nombres, apellidos, usuario, contrasenia, correo);
        this.facultad = facultad;
        this.materias= materias ;
    }


    /**
     * Este método permite consultar una reserva previamente hecha por el profesor.
     * @param fecha la fecha de la reserva a consultar.
     * @return no retorna valores, imprime en consola.
     */
    public void consultarReserva(Date fecha){
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

    /**
     * Este método permite al Profesor realizar una reserva de un espacio.
     */
    public void gestionarReserva(){
        super.gestionarReserva();
    }

    public facultad getFacultad(){
        return facultad;
    }

    public ArrayList<String> getMaterias(){
        return materias;
    }

    public void setFacultad(facultad facultad){
        this.facultad = facultad;
    }

    public void setMaterias(ArrayList<String> materias){
        this.materias = materias;
    }
}
