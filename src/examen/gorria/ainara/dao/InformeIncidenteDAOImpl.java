/*
=========================================
AUTOR: AINARA GORRIA
GRUPO: DAM3
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/
package examen.gorria.ainara.dao;

import examen.gorria.ainara.beans.InformeIncidente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class InformeIncidenteDAOImpl extends AbstractDAO<InformeIncidente> {

    @Override
    public void add(InformeIncidente bean) {
        String sql = "INSERT INTO informes_incidente (malware_detectado, nivel_severidad, conclusion, fk_incidente_id, autor_examen) VALUES (?, ?, ?, ?, 'NOMBRE_APELLIDO_DAM3')";
        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql)) {
            ps.setBoolean(1, bean.isMalwareDetectado());
            ps.setInt(2, bean.getNivelSeveridad());
            ps.setString(3, bean.getConclusion());
            
            // Requerido por la BD para establecer la relación 1:1
            ps.setInt(4, bean.getFkIncidenteId()); 
            
            motorSql.executeUpdate(ps);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
    }

    @Override
    public void update(InformeIncidente bean) {
        // USO DEL MOTOR DINÁMICO (Bloque 7) implementado en el AbstractDAO
        LinkedHashMap<String, Object> campos = new LinkedHashMap<>();
        
        if (bean.getConclusion() != null && !bean.getConclusion().isEmpty()) {
            campos.put("conclusion", bean.getConclusion());
        }
        if (bean.getNivelSeveridad() > 0) {
            campos.put("nivel_severidad", bean.getNivelSeveridad());
        }
        campos.put("malware_detectado", bean.isMalwareDetectado());
        
        executeDynamicUpdate("informes_incidente", bean.getId(), campos);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM informes_incidente WHERE id = ?";
        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql)) {
            ps.setInt(1, id);
            motorSql.executeUpdate(ps);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
    }

    @Override
    public InformeIncidente find(int id) {
        InformeIncidente informe = null;
        String sql = "SELECT * FROM informes_incidente WHERE id = ?";
        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = motorSql.executeQuery(ps)) {
                if (rs.next()) {
                    informe = new InformeIncidente();
                    informe.setId(rs.getInt("id"));
                    informe.setMalwareDetectado(rs.getBoolean("malware_detectado"));
                    informe.setNivelSeveridad(rs.getInt("nivel_severidad"));
                    informe.setConclusion(rs.getString("conclusion"));
                    informe.setFkIncidenteId(rs.getInt("fk_incidente_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
        return informe;
    }

    @Override
    public ArrayList<InformeIncidente> findAll() {
        ArrayList<InformeIncidente> lista = new ArrayList<>();
        String sql = "SELECT * FROM informes_incidente";
        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql);
             ResultSet rs = motorSql.executeQuery(ps)) {
            while (rs.next()) {
                InformeIncidente informe = new InformeIncidente();
                informe.setId(rs.getInt("id"));
                informe.setMalwareDetectado(rs.getBoolean("malware_detectado"));
                informe.setNivelSeveridad(rs.getInt("nivel_severidad"));
                informe.setConclusion(rs.getString("conclusion"));
                informe.setFkIncidenteId(rs.getInt("fk_incidente_id"));
                lista.add(informe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
        return lista;
    }
}
