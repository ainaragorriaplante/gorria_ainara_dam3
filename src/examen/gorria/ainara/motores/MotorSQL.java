/*
=========================================
AUTOR: AINARA GORRIA
GRUPO: DAM3
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/
package examen.gorria.ainara.motores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class MotorSQL {
    protected Connection conn;
    
    public abstract void connect();
    public abstract void disconnect();
    public abstract PreparedStatement prepare(String sql);
    
    // Métodos genéricos para facilitar consultas
    public ResultSet executeQuery(PreparedStatement ps) throws Exception {
        return ps.executeQuery();
    }
    public int executeUpdate(PreparedStatement ps) throws Exception {
        return ps.executeUpdate();
    }
}