/*
=========================================
AUTOR: AINARA GORRIA
GRUPO: DAM3
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/
public class Incidente {
    private int id;
    private String codigoIncidente;
    private String tipoIncidente;
    private java.sql.Date fechaDeteccion;
    private String estado;
    
    // RELACIONES: Objetos, no IDs sueltos
    private Soc soc; 
    private InformeIncidente informe;

    // Constructor, Getters y Setters...
}