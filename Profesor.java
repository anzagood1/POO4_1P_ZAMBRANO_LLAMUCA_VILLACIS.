import java.util.ArrayList;
public class Profesor extends Usuario{
    
    private facultad facultad;
    private ArrayList<String> materias;

    Profesor(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contraseña, String correo, facultad facultad, ArrayList<String> materias){
        super(codigoUnico, cedula,nombres, apellidos, usuario, contrasenia, correo);
        facultad = this.facultad;
        materias = this.materias;
    }

    
    public void consultarReserva(Date fecha){
        System.out.println("Codigo reserva...");
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
        facultad = this.facultad;
    }

    public void setMaterias(ArrayList<String> materias){
        materias = this.materias;
    }
}
