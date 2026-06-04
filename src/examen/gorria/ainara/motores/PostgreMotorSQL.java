/*
=========================================
AUTOR: AINARA GORRIA
GRUPO: DAM3
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/
package examen.gorria.ainara.motores;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PostgreMotorSQL extends MotorSQL {
    private static final String URL = "jdbc:postgresql://TU_ENDPOINT_AWS_RDS:5432/apellido_nombre_dam_REC_3";
    private static final String USER = "tu_usuario";
    private static final String PASS = "tu_password";

    @Override
    public void connect() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PreparedStatement prepare(String sql) {
        try {
            return conn.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void disconnect() {
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
}