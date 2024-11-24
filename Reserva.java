package com.example;

import java.sql.Date;

public class Reserva {
    private String codigoUnicoEspacio;
    private Date fechaReserva;
    private TipoEspacio tipoDeEspacio;
    private EstadoReserva estadoDeLaReserva;
    private String motivoDeLaReserva;
    public Usuario usuario;
    public static int numeroReservas;

    public Reserva(String codigoUnicoEspacio, Date fechaReserva, TipoEspacio tipoDeEspacio, EstadoReserva estadoDeLaReserva, String motivoDeLaReserva, Usuario usuario){
        this.codigoUnicoEspacio = codigoUnicoEspacio;
        this.fechaReserva = fechaReserva;
        this.tipoDeEspacio = tipoDeEspacio;
        this.estadoDeLaReserva = estadoDeLaReserva;
        this.motivoDeLaReserva = motivoDeLaReserva;
        this.usuario = usuario;
        numeroReservas++;
    }

    //setters
    public void setCodigoUnicoEspacio(String codigoUnicoEspacio){
        this.codigoUnicoEspacio = codigoUnicoEspacio;
    }

    public void setFechaReserva(Date fechaReserva){
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

   /*  public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    */

    //getters
    public String getCodigoUnicoEspacio(){
        return this.codigoUnicoEspacio;
    }

    public Date getFechaReserva(){
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
    /* 
    public Usuario getUsuario(){
        return this.usuario;
    }
    */
}
