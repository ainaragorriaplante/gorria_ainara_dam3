/*
=========================================
AUTOR: AINARA GORRIA
GRUPO: DAM3
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/
package examen.gorria.ainara.beans;

public class Soc {
    private int id;
    private String nombre;
    private String pais;
    private int nivelSeguridad;

    public Soc() {}
    
    // Constructor
    public Soc(int id, String nombre, String pais, int nivelSeguridad) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.nivelSeguridad = nivelSeguridad;
    }

    // getters y setters omitidos para brevedad
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public int getNivelSeguridad() {
        return nivelSeguridad;
    }
    public void setNivelSeguridad(int nivelSeguridad) {
        this.nivelSeguridad = nivelSeguridad;
    }
    
    // toString
    @Override
    public String toString() {
        return "Soc{id=" + id + ", nombre='" + nombre + "', pais='" + pais + "', nivelSeguridad=" + nivelSeguridad + "}";
    }
}
