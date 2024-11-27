package src.Funcionalidades;

import java.time.LocalDate;
import ENUMS.TipoEspacio;
import ENUMS.EstadoReserva;

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
