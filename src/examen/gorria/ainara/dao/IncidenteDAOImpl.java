
/*
=========================================
AUTOR: AINARA GORRIA
GRUPO: DAM3
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/
package examen.gorria.ainara.dao;

import examen.gorria.ainara.beans.Incidente;
import examen.gorria.ainara.beans.Soc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class IncidenteDAOImpl extends AbstractDAO<Incidente> {

    @Override
    public void add(Incidente bean) {
        String sql = "INSERT INTO incidentes (codigo_incidente, tipo_incidente, fecha_deteccion, estado, fk_soc_id, autor_examen) VALUES (?, ?, ?, ?, ?, 'NOMBRE_APELLIDO_DAM3')";
        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql)) {
            ps.setString(1, bean.getCodigoIncidente());
            ps.setString(2, bean.getTipoIncidente());
            ps.setDate(3, bean.getFechaDeteccion());
            ps.setString(4, bean.getEstado());
            // Extraemos el ID relacional desde el objeto mapeado
            ps.setInt(5, bean.getSoc().getId()); 
            motorSql.executeUpdate(ps);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
    }

    @Override
    public void update(Incidente bean) {
        String sql = "UPDATE incidentes SET codigo_incidente = ?, tipo_incidente = ?, fecha_deteccion = ?, estado = ?, fk_soc_id = ? WHERE id = ?";
        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql)) {
            ps.setString(1, bean.getCodigoIncidente());
            ps.setString(2, bean.getTipoIncidente());
            ps.setDate(3, bean.getFechaDeteccion());
            ps.setString(4, bean.getEstado());
            ps.setInt(5, bean.getSoc().getId());
            ps.setInt(6, bean.getId());
            motorSql.executeUpdate(ps);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM incidentes WHERE id = ?";
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
    public Incidente find(int id) {
        Incidente inc = null;
        // INNER JOIN OBLIGATORIO PARA MAPEAR EL SOC
        String sql = "SELECT i.*, s.nombre AS soc_nombre, s.pais, s.nivel_seguridad " +
                     "FROM incidentes i " +
                     "INNER JOIN socs s ON i.fk_soc_id = s.id " +
                     "WHERE i.id = ?";
                     
        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = motorSql.executeQuery(ps)) {
                if (rs.next()) {
                    inc = new Incidente();
                    inc.setId(rs.getInt("id"));
                    inc.setCodigoIncidente(rs.getString("codigo_incidente"));
                    inc.setTipoIncidente(rs.getString("tipo_incidente"));
                    inc.setFechaDeteccion(rs.getDate("fecha_deteccion"));
                    inc.setEstado(rs.getString("estado"));

                    // Mapeo del objeto SOC
                    Soc soc = new Soc();
                    soc.setId(rs.getInt("fk_soc_id"));
                    soc.setNombre(rs.getString("soc_nombre"));
                    soc.setPais(rs.getString("pais"));
                    soc.setNivelSeguridad(rs.getInt("nivel_seguridad"));
                    inc.setSoc(soc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
        return inc;
    }

    @Override
    public ArrayList<Incidente> findAll() {
        ArrayList<Incidente> lista = new ArrayList<>();
        String sql = "SELECT i.*, s.nombre AS soc_nombre, s.pais, s.nivel_seguridad " +
                     "FROM incidentes i " +
                     "INNER JOIN socs s ON i.fk_soc_id = s.id";
                     
        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql);
             ResultSet rs = motorSql.executeQuery(ps)) {
            while (rs.next()) {
                Incidente inc = new Incidente();
                inc.setId(rs.getInt("id"));
                inc.setCodigoIncidente(rs.getString("codigo_incidente"));
                inc.setTipoIncidente(rs.getString("tipo_incidente"));
                inc.setFechaDeteccion(rs.getDate("fecha_deteccion"));
                inc.setEstado(rs.getString("estado"));

                Soc soc = new Soc();
                soc.setId(rs.getInt("fk_soc_id"));
                soc.setNombre(rs.getString("soc_nombre"));
                soc.setPais(rs.getString("pais"));
                soc.setNivelSeguridad(rs.getInt("nivel_seguridad"));
                inc.setSoc(soc);

                lista.add(inc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
        return lista;
    }
    // PARA EL TEST 5:
    public ArrayList<Incidente> findIncidentesBySoc(int socId) {
        ArrayList<Incidente> lista = new ArrayList<>();
        String sql = "SELECT i.*, s.nombre AS soc_nombre, s.pais, s.nivel_seguridad " +
                     "FROM incidentes i " +
                     "INNER JOIN socs s ON i.fk_soc_id = s.id " +
                     "WHERE i.fk_soc_id = ?";
                     
        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql)) {
            ps.setInt(1, socId);
            try (ResultSet rs = motorSql.executeQuery(ps)) {
                while (rs.next()) {
                    Incidente inc = new Incidente();
                    inc.setId(rs.getInt("id"));
                    inc.setCodigoIncidente(rs.getString("codigo_incidente"));
                    inc.setEstado(rs.getString("estado"));
                    // Mapeo
                    Soc soc = new Soc();
                    soc.setId(rs.getInt("fk_soc_id"));
                    soc.setNombre(rs.getString("soc_nombre"));
                    soc.setPais(rs.getString("pais"));
                    inc.setSoc(soc);
                    
                    lista.add(inc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
        return lista;
    }

    // PARA EL TEST 6:
    public Incidente findIncidenteWithInforme(int incidenteId) {
        Incidente inc = null;
        String sql = "SELECT i.*, inf.id AS inf_id, inf.malware_detectado, inf.nivel_severidad, inf.conclusion " +
                     "FROM incidentes i " +
                     "INNER JOIN informes_incidente inf ON i.id = inf.fk_incidente_id " +
                     "WHERE i.id = ?";
                     
        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql)) {
            ps.setInt(1, incidenteId);
            try (ResultSet rs = motorSql.executeQuery(ps)) {
                if (rs.next()) {
                    inc = new Incidente();
                    inc.setId(rs.getInt("id"));
                    inc.setCodigoIncidente(rs.getString("codigo_incidente"));
                    
                    // Mapeo del informe 1:1
                    InformeIncidente inf = new InformeIncidente();
                    inf.setId(rs.getInt("inf_id"));
                    inf.setMalwareDetectado(rs.getBoolean("malware_detectado"));
                    inf.setNivelSeveridad(rs.getInt("nivel_severidad"));
                    inf.setConclusion(rs.getString("conclusion"));
                    
                    inc.setInforme(inf);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
        return inc;
    }
    // Bloque 6: BONUS_QUERY_ADVANCED - INCIDENTES_CRITICOS
    // Justificación: Esta consulta vive aquí y no en AbstractDAO porque es 
    // específica del dominio de Incidente. Utilizamos INNER JOIN para hidratar 
    // los objetos dependientes de un solo viaje a base de datos.
    public ArrayList<Incidente> findIncidentesCriticos() {
        ArrayList<Incidente> lista = new ArrayList<>();
        String sql = "SELECT i.id, i.codigo_incidente, i.tipo_incidente, i.fecha_deteccion, i.estado, " +
                     "s.id AS soc_id, s.nombre AS soc_nombre, s.pais, s.nivel_seguridad, " +
                     "inf.id AS info_id, inf.malware_detectado, inf.nivel_severidad, inf.conclusion " +
                     "FROM incidentes i " +
                     "INNER JOIN socs s ON i.fk_soc_id = s.id " +
                     "INNER JOIN informes_incidente inf ON i.id = inf.fk_incidente_id " +
                     "WHERE inf.malware_detectado = TRUE " +
                     "AND inf.nivel_severidad > 90 " +
                     "AND UPPER(s.pais) = 'ESPAÑA'";

        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql);
             ResultSet rs = motorSql.executeQuery(ps)) {
             
            while (rs.next()) {
                Incidente inc = new Incidente();
                inc.setId(rs.getInt("id"));
                inc.setCodigoIncidente(rs.getString("codigo_incidente"));
                inc.setTipoIncidente(rs.getString("tipo_incidente"));
                inc.setFechaDeteccion(rs.getDate("fecha_deteccion"));
                inc.setEstado(rs.getString("estado"));

                // MAPPING MANUAL DE OBJETOS RELACIONADOS (CRITERIO FUNDAMENTAL)
                Soc soc = new Soc();
                soc.setId(rs.getInt("soc_id"));
                soc.setNombre(rs.getString("soc_nombre"));
                soc.setPais(rs.getString("pais"));
                soc.setNivelSeguridad(rs.getInt("nivel_seguridad"));
                inc.setSoc(soc);

                InformeIncidente inf = new InformeIncidente();
                inf.setId(rs.getInt("info_id"));
                inf.setMalwareDetectado(rs.getBoolean("malware_detectado"));
                inf.setNivelSeveridad(rs.getInt("nivel_severidad"));
                inf.setConclusion(rs.getString("conclusion"));
                inc.setInforme(inf);

                lista.add(inc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
        return lista;
    }
}
