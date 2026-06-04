/*
=========================================
AUTOR: AINARA GORRIA
GRUPO: DAM3
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/
package examen.gorria.ainara.beans;
public class Incidente {
    private int id;
    private String codigoIncidente;
    private String tipoIncidente;
    private java.sql.Date fechaDeteccion;
    private String estado;
    
    // RELACIONES: Objetos, no IDs sueltos
    private Soc soc; 
    private InformeIncidente informe;

    // Constructor 
    
    public Incidente() {}
    
    public Incidente(int id, String codigoIncidente, String tipoIncidente, java.sql.Date fechaDeteccion, String estado, Soc soc, InformeIncidente informe) {
        this.id = id;
        this.codigoIncidente = codigoIncidente;
        this.tipoIncidente = tipoIncidente;
        this.fechaDeteccion = fechaDeteccion;
        this.estado = estado;
        this.soc = soc;
        this.informe = informe;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCodigoIncidente() {
        return codigoIncidente;
    }
    public void setCodigoIncidente(String codigoIncidente) {
        this.codigoIncidente = codigoIncidente;
    }
    public String getTipoIncidente() {
        return tipoIncidente;
    }
    public void setTipoIncidente(String tipoIncidente) {
        this.tipoIncidente = tipoIncidente;
    }
    public java.sql.Date getFechaDeteccion() {
        return fechaDeteccion;
    }
    public void setFechaDeteccion(java.sql.Date fechaDeteccion) {
        this.fechaDeteccion = fechaDeteccion;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Soc getSoc() {
        return soc;
    }
    public void setSoc(Soc soc) {
        this.soc = soc;
    }
    public InformeIncidente getInforme() {
        return informe;
    }
    public void setInforme(InformeIncidente informe) {
        this.informe = informe;
    }

    // toString
    @Override
    public String toString() {
        return "Incidente{id=" + id + ", codigoIncidente='" + codigoIncidente + "', tipoIncidente='" + tipoIncidente + "', fechaDeteccion=" + fechaDeteccion + ", estado='" + estado + "', soc=" + soc + ", informe=" + informe + "}";
    }
}