/*
=========================================
AUTOR: AINARA GORRIA
GRUPO: DAM3
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/
package examen.gorria.ainara.dao;
import java.util.ArrayList;

public interface DAO<T> {
    void add(T bean);
    void update(T bean);
    void delete(int id);
    T find(int id);
    ArrayList<T> findAll();
}