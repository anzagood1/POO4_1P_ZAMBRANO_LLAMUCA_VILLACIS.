package src.Funcionalidades;

import ENUMS.TipoEspacio;
import ENUMS.EstadoEspacio;

public class Espacio {
    private String codigoUnico;
    private TipoEspacio tipoDeEspacio;
    private String nombre;
    private int capacidad;
    private EstadoEspacio estado;
    
    public Espacio(String codigoUnico,TipoEspacio tipoDeEspacio, String nombre, int capacidad,  EstadoEspacio estado ){
        this.codigoUnico = codigoUnico;
        this.tipoDeEspacio = tipoDeEspacio;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.estado = estado;
    }
    
    public void setCodigoUnico(String codigoUnico){
        this.codigoUnico = codigoUnico;
    }

    public void setTipoEspacio(TipoEspacio tipoDeEspacio){
        this.tipoDeEspacio = tipoDeEspacio;
    }

    public void setNombre(String nombre){
        this.nombre = nombre; 
    }

    public void setCapacidad(int capacidad){
        this.capacidad = capacidad;
    }

    public void setEstado(EstadoEspacio estado){
        this.estado = estado;
    }

    public String getCodigoUnico(){
        return codigoUnico;
    }
    
    public TipoEspacio getTipoDeEspacio(){
        return tipoDeEspacio;
    }

    public String getNombre(){
        return nombre;
    }

    public int getCapacidad(){
        return capacidad;
    }

    public EstadoEspacio getEstado(){
        return estado;
    }

    public String toString(){
        return this.codigoUnico + " | " + this.tipoDeEspacio
        + " | " + this.nombre + " | " + this.capacidad + " | " + 
        this.estado;
    }
}
