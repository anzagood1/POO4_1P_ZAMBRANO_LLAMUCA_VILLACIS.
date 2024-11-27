package src.Personas;

import Funcionalidades;
import ENUMS.Cargo;

public class Administrador extends Usuario{
    Cargo cargoAdmin;

    /**
     * Este método es el constructor de la subclase Administrador.
     * @param codigoUnico codigo unico del usuario.
     * @param cedula cedula del usuario.
     * @param nombres nombres del usuario.
     * @param apellidos apellidos del usuario.
     * @param usuario nombre del usuario.
     * @param contrasenia contrasenia del usuario.
     * @param correo correo del usuario.
     * @param cargoAdmin cargo del administrador
     * @return no retorna valores, es un constructor.
     */
    public Administrador(String codigoUnico, String cedula, String nombres, String apellidos, String usuario, String contrasenia, String correo, Cargo cargoAdmin){
        super(codigoUnico, cedula,nombres, apellidos, usuario, contrasenia, correo);
        this.cargoAdmin = cargoAdmin;
    }

    /**
     * Este método permite al administrador visualizar todas las reservas con los datos de estas.
     */
    public void consultarReserva(){
        System.out.println("Números de reservas: "+Reserva.numeroReservas);
        System.out.println("Reservas de Estudiantes: ");
        System.out.println("CÓDIGO RESERVA - ESTADO - FECHA - NOMBRES Y APELLIDOS - MATRÍCULA");
        for (Reserva reserva: reservas){
            if(reserva.getUsuario() instanceof Estudiante){
                System.out.println(reserva.getCodigoReserva()+" - "+reserva.getEstadoDeLaReserva()+" - "+reserva.getFechaReserva+" - "+reserva.getUsuario().getNombres()+" "+reserva.getUsuario().getApellidos()+" - "+reserva.getUsuario().getnumMatricula());
            }
        }
        System.out.println("Reservas de Profesores: ");
        System.out.println("CÓDIGO RESERVA - ESTADO - FECHA - NOMBRES Y APELLIDOS - MATERIA");
        for (Reserva reserva: reservas){
            if(reserva.getUsuario() instanceof Profesor){
                System.out.println(reserva.getCodigoReserva()+" - "+reserva.getEstadoDeLaReserva()+" - "+reserva.getFechaReserva+" - "+reserva.getUsuario().getNombres()+" "+reserva.getUsuario().getApellidos()+" - "+reserva.getCodigoUnicoEspacio());
            }
        }
    }

    /**
     * Método que permite al admnistrador aprobar o rechazar las reservas.
     */
    @Override
    public void gestionarReserva(){
        Scanner sc = new Scanner(System.in);
        int i = 0;
        for(Reserva reserva: Sistema.reservas){
            if(reserva.getEstadoDeLaReserva() == EstadoReserva.PENDIENTE){
                i++;
                System.out.println(i + ".| " + reserva);
            }
        }
        System.out.println("Elija el numero de reserva que desea gestionar");
        int eleccion = sc.nextInt();
        sc.nextLine();
        Reserva r = Sistema.reservas.get(eleccion-1);
        System.out.println(r.getCodigoReserva());
        System.out.println(r.getFechaReserva());
        for(Espacio espacio: Sistema.espacios){
            if(r.getCodigoUnicoEspacio() == espacio.getCodigoUnico()){
                System.out.println(espacio.getTipoDeEspacio());
                System.out.println(espacio.getNombre());
                System.out.println(espacio.getCapacidad());  
            }
        }
        for (Usuario usuario: Sistema.usuarios){
            if(r.getCodigoUsuario() == usuario.getCodigoUnico()){
                System.out.println(usuario.getNombres());
                System.out.println(usuario.getApellidos());
            }
        }
        System.out.println("Desea aprobar o rechazar la reserva? (APROBADO/RECHAZADO)");
        String estado = sc.nextLine();
        EstadoReserva tipoEstado = EstadoReserva.valueOf(estado.toUpperCase());
        if (tipoEstado == EstadoReserva.APROBADO){
            r.setEstadoDeLaReserva(EstadoReserva.APROBADO);
            for(Espacio espacio: Sistema.espacios){
                if(r.getCodigoUnicoEspacio() == espacio.getCodigoUnico()){
                    espacio.setEstado(EstadoEspacio.RESERVADO);
                }
            }
            ManejoArchivos.borrarArchivo("espacios.txt");
            for(Espacio espacio: Sistema.espacios){
                String es = espacio.toString();
                ManejoArchivos.EscribirArchivo("espacios.txt", es);
            }
            for(Usuario usuario: Sistema.usuarios){
                if(r.getCodigoUsuario() == usuario.getCodigoUnico()){
                    enviarNotificacion(r,usuario, r.getEstadoDeLaReserva());
                }
            }
        }else if(tipoEstado == EstadoReserva.RECHAZADO){
            r.setEstadoDeLaReserva(EstadoReserva.RECHAZADO);
            for(Espacio espacio: Sistema.espacios){
                if(r.getCodigoUnicoEspacio() == espacio.getCodigoUnico()){
                    espacio.setEstado(EstadoEspacio.DISPONIBLE);
                }
            }
            ManejoArchivos.borrarArchivo("espacios.txt");
            for(Espacio espacio: Sistema.espacios){
                String es = espacio.toString();
                ManejoArchivos.EscribirArchivo("espacios.txt", es);
            }
            for(Usuario usuario: Sistema.usuarios){
                if(r.getCodigoUsuario() == usuario.getCodigoUnico()){
                    enviarNotificacion(r,usuario, r.getEstadoDeLaReserva());
                }
            }
        }

        
    } 
}
