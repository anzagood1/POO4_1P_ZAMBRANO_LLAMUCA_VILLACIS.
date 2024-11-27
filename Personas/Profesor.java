import java.util.ArrayList;
public class Profesor extends Usuario{
    
    private facultad facultad;
    private ArrayList<String> materias;

    Profesor(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasenia, String correo, facultad facultad, ArrayList<String> materias){
        super(codigoUnico, cedula,nombres, apellidos, usuario, contrasenia, correo);
        this.facultad = facultad;
        this.materias= materias ;
    }

    
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
