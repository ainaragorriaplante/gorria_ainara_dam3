/*
=========================================
AUTOR: AINARA GORRIA
GRUPO: DAM3
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/
package examen.gorria.ainara;

import examen.gorria.ainara.dao.*;
import examen.gorria.ainara.beans.*;
import java.sql.Date;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        
        // Instancia de los DAOs
        SocDAOImpl socDao = new SocDAOImpl();
        IncidenteDAOImpl incidenteDao = new IncidenteDAOImpl();
        InformeIncidenteDAOImpl informeDao = new InformeIncidenteDAOImpl();

        // ---------------------------------------------------------
        // 1. ADD INCIDENTE
        // ---------------------------------------------------------
        System.out.println("--- TEST 1: ADD INCIDENTE ---");
        Incidente nuevoIncidente = new Incidente();
        nuevoIncidente.setCodigoIncidente("INC-TEST-001");
        nuevoIncidente.setTipoIncidente("Ataque de Fuerza Bruta");
        nuevoIncidente.setFechaDeteccion(Date.valueOf("2026-06-04"));
        nuevoIncidente.setEstado("NUEVO");
        
        // Obtenemos un SOC real de la BBDD (ej. ID 1) para asignarlo y respetar el objeto
        Soc socAsignado = socDao.find(1); 
        nuevoIncidente.setSoc(socAsignado);
        
        incidenteDao.add(nuevoIncidente);
        System.out.println("Incidente 'INC-TEST-001' insertado correctamente.\n");

        // ---------------------------------------------------------
        // 2. UPDATE INCIDENTE
        // ---------------------------------------------------------
        System.out.println("--- TEST 2: UPDATE INCIDENTE ---");
        // Buscamos el incidente con ID 2 (asumiendo que existe por tus inserts.sql)
        Incidente incidenteUpdate = incidenteDao.find(2);
        if (incidenteUpdate != null) {
            incidenteUpdate.setEstado("RESUELTO_TEST");
            incidenteDao.update(incidenteUpdate);
            System.out.println("Incidente ID 2 actualizado a estado: " + incidenteUpdate.getEstado() + "\n");
        }

        // ---------------------------------------------------------
        // 3. FIND INCIDENTE
        // ---------------------------------------------------------
        System.out.println("--- TEST 3: FIND INCIDENTE ---");
        Incidente incidenteEncontrado = incidenteDao.find(1);
        if (incidenteEncontrado != null) {
            System.out.println("Encontrado: " + incidenteEncontrado.getCodigoIncidente() + 
                               " | SOC: " + incidenteEncontrado.getSoc().getNombre() + "\n");
        }

        // ---------------------------------------------------------
        // 4. FIND ALL INCIDENTES
        // ---------------------------------------------------------
        System.out.println("--- TEST 4: FIND ALL INCIDENTES ---");
        ArrayList<Incidente> todosIncidentes = incidenteDao.findAll();
        System.out.println("Total de incidentes en BBDD: " + todosIncidentes.size() + "\n");

        // ---------------------------------------------------------
        // 5. FIND INCIDENTES BY SOC
        // ---------------------------------------------------------
        System.out.println("--- TEST 5: FIND INCIDENTES BY SOC ---");
        int idSocBusqueda = 1; // SOC Central Madrid
        ArrayList<Incidente> incidentesSoc = incidenteDao.findIncidentesBySoc(idSocBusqueda);
        System.out.println("Incidentes del SOC ID " + idSocBusqueda + ":");
        for (Incidente i : incidentesSoc) {
            System.out.println(" - " + i.getCodigoIncidente() + " (" + i.getEstado() + ")");
        }
        System.out.println();

        // ---------------------------------------------------------
        // 6. FIND INCIDENTE WITH INFORME
        // ---------------------------------------------------------
        System.out.println("--- TEST 6: FIND INCIDENTE WITH INFORME ---");
        int idIncidenteConInforme = 1;
        Incidente incidenteConInforme = incidenteDao.findIncidenteWithInforme(idIncidenteConInforme);
        if (incidenteConInforme != null && incidenteConInforme.getInforme() != null) {
            System.out.println("Incidente: " + incidenteConInforme.getCodigoIncidente() +
                               "\n   -> Severidad del Informe: " + incidenteConInforme.getInforme().getNivelSeveridad() +
                               "\n   -> Conclusión: " + incidenteConInforme.getInforme().getConclusion() + "\n");
        }

        // ---------------------------------------------------------
        // BLOQUE 6: INCIDENTES CRÍTICOS
        // ---------------------------------------------------------
        System.out.println("--- TEST BLOQUE 6: FIND INCIDENTES CRÍTICOS ---");
        ArrayList<Incidente> criticos = incidenteDao.findIncidentesCriticos();
        for (Incidente i : criticos) {
            System.out.println("CRÍTICO -> Código: " + i.getCodigoIncidente() + 
                               " | SOC: " + i.getSoc().getNombre() + 
                               " (" + i.getSoc().getPais() + ")" +
                               " | Severidad: " + i.getInforme().getNivelSeveridad());
        }
        System.out.println();

        // ---------------------------------------------------------
        // BLOQUE 7: DYNAMIC UPDATE ENGINE
        // ---------------------------------------------------------
        System.out.println("--- TEST BLOQUE 7: DYNAMIC UPDATE ENGINE ---");
        /* JUSTIFICACIÓN DE LA SOLUCIÓN (Requisito obligatorio):
           El Update dinámico implementado en AbstractDAO (y utilizado en InformeIncidenteDAOImpl)
           permite actualizar únicamente los campos que han sido seteados en el objeto Java.
           Si un campo no se rellena, no se añade al SET de la consulta SQL.
           Esto evita crear múltiples métodos como updateConclusion(), updateSeveridad(),
           y concentra la responsabilidad de construcción SQL dinámica de forma segura usando
           PreparedStatement para evitar inyección SQL. Mantenemos el Principio Abierto/Cerrado.
        */
        InformeIncidente infModificado = new InformeIncidente();
        infModificado.setId(3); // Modificamos el informe con ID 3
        infModificado.setConclusion("Actualizado por DYNAMIC UPDATE ENGINE el " + new java.util.Date());
        // NO seteamos nivelSeveridad ni malwareDetectado, por lo que el DAO no los actualizará.
        
        informeDao.update(infModificado);
        System.out.println("Informe ID 3 actualizado dinámicamente. Revisar base de datos.");
    }
}