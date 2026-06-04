/*
=========================================
AUTOR: AINARA GORRIA
GRUPO: DAM3
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/
package examen.gorria.ainara.dao;
import examen.gorria.ainara.beans.Soc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SocDAOImpl extends AbstractDAO<Soc> {

    @Override
    public void add(Soc bean) {
        String sql = "INSERT INTO socs (nombre, pais, nivel_seguridad, autor_examen) VALUES (?, ?, ?, 'NOMBRE_APELLIDO_DAM3')";
        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql)) {
            ps.setString(1, bean.getNombre());
            ps.setString(2, bean.getPais());
            ps.setInt(3, bean.getNivelSeguridad());
            motorSql.executeUpdate(ps);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
    }

    @Override
    public void update(Soc bean) {
        String sql = "UPDATE socs SET nombre = ?, pais = ?, nivel_seguridad = ? WHERE id = ?";
        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql)) {
            ps.setString(1, bean.getNombre());
            ps.setString(2, bean.getPais());
            ps.setInt(3, bean.getNivelSeguridad());
            ps.setInt(4, bean.getId());
            motorSql.executeUpdate(ps);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM socs WHERE id = ?";
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
    public Soc find(int id) {
        Soc soc = null;
        String sql = "SELECT * FROM socs WHERE id = ?";
        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = motorSql.executeQuery(ps)) {
                if (rs.next()) {
                    soc = new Soc();
                    soc.setId(rs.getInt("id"));
                    soc.setNombre(rs.getString("nombre"));
                    soc.setPais(rs.getString("pais"));
                    soc.setNivelSeguridad(rs.getInt("nivel_seguridad"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
        return soc;
    }

    @Override
    public ArrayList<Soc> findAll() {
        ArrayList<Soc> lista = new ArrayList<>();
        String sql = "SELECT * FROM socs";
        motorSql.connect();
        try (PreparedStatement ps = motorSql.prepare(sql);
             ResultSet rs = motorSql.executeQuery(ps)) {
            while (rs.next()) {
                Soc soc = new Soc();
                soc.setId(rs.getInt("id"));
                soc.setNombre(rs.getString("nombre"));
                soc.setPais(rs.getString("pais"));
                soc.setNivelSeguridad(rs.getInt("nivel_seguridad"));
                lista.add(soc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            motorSql.disconnect();
        }
        return lista;
    }
}