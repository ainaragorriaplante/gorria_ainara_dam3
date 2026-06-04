/*
=========================================
AUTOR: AINARA GORRIA
GRUPO: DAM3
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/
package examen.gorria.ainara.dao;
import examen.gorria.ainara.motores.MotorFactory;
import examen.gorria.ainara.motores.MotorSQL;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractDAO<T> implements DAO<T> {
    protected MotorSQL motorSql;

    public AbstractDAO() {
        this.motorSql = MotorFactory.create(MotorFactory.POSTGRE);
    }

    // BLOQUE 7: DYNAMIC UPDATE ENGINE
    // Construye y ejecuta un UPDATE solo con los campos informados
    protected void executeDynamicUpdate(String tabla, int id, LinkedHashMap<String, Object> campos) {
        if (campos.isEmpty()) return;

        StringBuilder sql = new StringBuilder("UPDATE " + tabla + " SET ");
        for (String columna : campos.keySet()) {
            sql.append(columna).append(" = ?, ");
        }
        // Quitar la última coma
        sql.setLength(sql.length() - 2);
        sql.append(" WHERE id = ?");

        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql.toString())) {
            int index = 1;
            for (Object valor : campos.values()) {
                ps.setObject(index++, valor);
            }
            ps.setInt(index, id);
            motorSql.executeUpdate(ps);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
    }
}
