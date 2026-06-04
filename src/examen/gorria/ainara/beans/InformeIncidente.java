/*
=========================================
AUTOR: AINARA GORRIA
GRUPO: DAM3
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/
package examen.apellido.nombre.beans;

public class InformeIncidente {
    private int id;
    private boolean malwareDetectado;
    private int nivelSeveridad;
    private String conclusion;

    //constructores
    public InformeIncidente() {}
    public InformeIncidente(int id, boolean malwareDetectado, int nivelSeveridad, String conclusion) {
        this.id = id;
        this.malwareDetectado = malwareDetectado;
        this.nivelSeveridad = nivelSeveridad;
        this.conclusion = conclusion;
    }
    // Getters y Setters correspondientes...
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isMalwareDetectado() {
        return malwareDetectado;
    }
    public void setMalwareDetectado(boolean malwareDetectado) {
        this.malwareDetectado = malwareDetectado;
    }
    public int getNivelSeveridad() {
        return nivelSeveridad;
    }
    public void setNivelSeveridad(int nivelSeveridad) {
        this.nivelSeveridad = nivelSeveridad;
    }
    public String getConclusion() {
        return conclusion;
    }
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    //toString
    @Override
    public String toString() {
        return "InformeIncidente{id=" + id + ", malwareDetectado=" + malwareDetectado + ", nivelSeveridad=" + nivelSeveridad + ", conclusion='" + conclusion + "'}";
    }
}