package src.Funcionalidades;

import java.time.LocalDate;
import src.ENUMS.TipoEspacio;
import src.ENUMS.EstadoReserva;
import src.Principal.Sistema;


public class Reserva {
    private int codigoReserva;
    private String codigoUnicoEspacio;
    private LocalDate fechaReserva;
    private TipoEspacio tipoDeEspacio;
    private EstadoReserva estadoDeLaReserva;
    private String motivoDeLaReserva;
    public String codigoUsuario;
    public String cedulaUsuario;
    public static int numeroReservas;

    /**
     * Método constructor de la clase Reserva.
     * @param codigoReserva codigo de la reserva.
     * @param codigoUnicoEspacio codigo unico del espacio de la reserva.
     * @param fechaReserva fecha de la reserva.
     * @param tipoDeEspacio tipo de espacio del espacio de la reserva.
     * @param estadoDeLaReserva estado de la reserva.
     * @param motivoDeLaReserva motivo de la reserva.
     * @param codigoUsuario codigo del usuario que realiza la reserva.
     * @param cedulaUsuario cedula del usuario que realiza la reserva.
     */
    public Reserva(int codigoReserva, String codigoUnicoEspacio, LocalDate fechaReserva, TipoEspacio tipoDeEspacio, EstadoReserva estadoDeLaReserva, String motivoDeLaReserva, String codigoUsuario, String cedulaUsuario){
        this.codigoReserva = codigoReserva;
        this.codigoUnicoEspacio = codigoUnicoEspacio;
        this.fechaReserva = fechaReserva;
        this.tipoDeEspacio = tipoDeEspacio;
        this.estadoDeLaReserva = estadoDeLaReserva;
        this.motivoDeLaReserva = motivoDeLaReserva;
        this.codigoUsuario = codigoUsuario;
        this.cedulaUsuario = cedulaUsuario;
        numeroReservas++;
    }

    //setters
    public void setCodigoReserva(int codigoReserva){
        this.codigoReserva = codigoReserva;
    }

    public void setCodigoUnicoEspacio(String codigoUnicoEspacio){
        this.codigoUnicoEspacio = codigoUnicoEspacio;
    }

    public void setFechaReserva(LocalDate fechaReserva){
        this.fechaReserva = fechaReserva;
    }

    public void setTipoEspacio(TipoEspacio tipoDeEspacio){
        this.tipoDeEspacio = tipoDeEspacio;
    }

    public void setEstadoDeLaReserva(EstadoReserva estadoDeLaReserva){
        this.estadoDeLaReserva = estadoDeLaReserva;
    }

    public void setMotivoDeLaReserva(String motivoDeLaReserva){
        this.motivoDeLaReserva = motivoDeLaReserva;
    }

    public void setCodigoUsuario(String codigoUsuario){
        this.codigoUsuario = codigoUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario){
        this.cedulaUsuario = cedulaUsuario;
    }

    //getters
    public int getCodigoReserva(){
        return this.codigoReserva;
    }

    public String getCodigoUnicoEspacio(){
        return this.codigoUnicoEspacio;
    }

    public LocalDate getFechaReserva(){
        return this.fechaReserva;
    }

    public TipoEspacio getTipoDeEspacio(){
        return this.tipoDeEspacio;
    }

    public EstadoReserva getEstadoDeLaReserva(){
        return this.estadoDeLaReserva;
    }

    public String getMotivoReserva(){
        return this.motivoDeLaReserva;
    }
    
    public String getCodigoUsuario(){
        return this.codigoUsuario;
    }

    public String getCedulaUsuario(){
        return this.cedulaUsuario;
    }

    /**
     * Método que genera el código de las nuevas reservas registradas.
     * @return retorna el un int, el código único de la nueva reserva.
     */
    public static int generarCodigoReserva(){
        int i = (Sistema.reservas.size()) - 1;
        if(i<0){
            return 1;
        }
        Reserva ultimo = Sistema.reservas.get(i);
        int codUnico = (ultimo.getCodigoReserva()) + 1;
        return codUnico;
    }

    public String toString(){
        return this.codigoReserva + " | " + this.codigoUsuario 
        + " | " + this.cedulaUsuario + " | " + this.fechaReserva
        + this.codigoUnicoEspacio + " | " + this.tipoDeEspacio + " | " 
        + this.estadoDeLaReserva + " | " + this.motivoDeLaReserva; 
    }

}
