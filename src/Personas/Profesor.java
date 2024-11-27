package src.Personas;

import java.util.ArrayList;
import Funcionalidades;

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
    public Profesor(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasenia, String correo, facultad facultad, ArrayList<String> materias){
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
    @Override
    public void gestionarReserva(){
        Scanner sc = new Scanner(System.in);
        System.out.println("En que fecha desea realizar su reserva? (AAAA-MM-DD)");
        String fString = sc.nextLine();
        LocalDate fecha = LocalDate.parse(fString);
        System.out.println("Que tipo de espacio desea reservar? (AULA, AUDITORIO, LABORATORIO)");
        String tipo = (sc.nextLine()).toUpperCase();
        int i = 0;
        TipoEspacio tipoEspacio = TipoEspacio.valueOf(tipo.toUpperCase());
        for (Espacio espacio: Sistema.espacios){
            if((espacio.getTipoDeEspacio()) == tipoEspacio){
                if(espacio.getEstado() == EstadoEspacio.DISPONIBLE){
                    i++;
                    System.out.println(i + ".|" + espacio);
                }
            }
        }
        System.out.println("Elija el numero del espacio que desea reservar ");
        int eleccion = sc.nextInt();
        sc.nextLine();
        i = 0;
        for(String materia:this.getMaterias()){
        i++;
        System.out.println(i + ".|" + materia);
        }  
        System.out.println("Para que numero de materia desea hacer la reserva?");
        int num = sc.nextInt();
        sc.nextLine();
        String materia = this.getMaterias().get(num-1);
        Espacio e = Sistema.espacios.get(eleccion-1);
        System.out.println("Desea reservar el espacio codigo " + e.getCodigoUnico() + " en la fecha " + fecha + "?");
        System.out.println("1.- Si");
        System.out.println("2.- No");
        int desicion = sc.nextInt();
        sc.nextLine();
        if(desicion == 1){
            Reserva r = new Reserva(Reserva.generarCodigoReserva(), e.getCodigoUnico(), fecha, e.getTipoDeEspacio(), EstadoReserva.APROBADO, materia, this.getCodigoUnico(), this.getCedula());
            Sistema.reservas.add(r);
            String rs = r.toString();
            ManejoArchivos.EscribirArchivo("reservas.txt", rs);
            e.setEstado(EstadoEspacio.RESERVADO);
            ManejoArchivos.borrarArchivo("espacios.txt");
            for(Espacio espacio: Sistema.espacios){
                String es = espacio.toString();
                ManejoArchivos.EscribirArchivo("espacios.txt", es);
            }
            enviarNotificacion(r, e, materia);
            System.out.println("Regresando al menu...");
        }else{
            System.out.println("Regresando al menu...");
        }
    }

    //getters
    public facultad getFacultad(){
        return facultad;
    }

    public ArrayList<String> getMaterias(){
        return materias;
    }

    //setters
    public void setFacultad(facultad facultad){
        this.facultad = facultad;
    }

    public void setMaterias(ArrayList<String> materias){
        this.materias = materias;
    }
}
