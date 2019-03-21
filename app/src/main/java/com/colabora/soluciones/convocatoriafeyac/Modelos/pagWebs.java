package com.colabora.soluciones.convocatoriafeyac.Modelos;

import java.util.Map;

public class pagWebs {

    private String icon;
    private Map<String, Object> secciones;
    private int tipo;


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Map<String, Object> getSecciones() {
        return secciones;
    }

    public void setSecciones(Map<String, Object> secciones) {
        this.secciones = secciones;
    }

    public pagWebs() {
    }

    public pagWebs(String icon, Map<String, Object> secciones, int tipo) {
        this.icon = icon;
        this.secciones = secciones;
        this.tipo = tipo;
    }
}
