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
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        IncidenteDAOImpl incidenteDao = new IncidenteDAOImpl();
        InformeIncidenteDAOImpl informeDao = new InformeIncidenteDAOImpl();

        System.out.println("--- TESTS OBLIGATORIOS EXAMEN ---");

        // Implementa aquí las llamadas a los TEST 1 al 6 solicitados en el enunciado
        // TEST 1: ADD INCIDENTE

        // TEST 2: UPDATE INCIDENTE
        // TEST 3: DELETE INCIDENTE
        // TEST 4: FIND INCIDENTE POR ID
        // TEST 5: FIND TODOS LOS INCIDENTES

        // TEST 6: FIND INCIDENTES CRÍTICOS
        System.out.println("\nEJECUTANDO: CONSULTA AVANZADA (INCIDENTES CRÍTICOS)");
        ArrayList<Incidente> criticos = incidenteDao.findIncidentesCriticos();
        for (Incidente i : criticos) {
            System.out.println("Incidente: " + i.getCodigoIncidente() + 
                               " | SOC: " + i.getSoc().getNombre() + 
                               " | Nivel Severidad: " + i.getInforme().getNivelSeveridad());
        }
         // TEST BLOQUE 7: DYNAMIC UPDATE ENGINE
        /* Justificación DYNAMIC UPDATE:
           Generamos dinámicamente el SET en el AbstractDAO utilizando un LinkedHashMap.
           Esto evita crear métodos como updateSeveridad(), updateConclusion(), etc.,
           permitiendo actualizar 1, 2 o todos los campos simultáneamente 
           manteniendo la seguridad del PreparedStatement. */
        System.out.println("\nEJECUTANDO: DYNAMIC UPDATE EN INFORME ID = 2");
        InformeIncidente infModificado = new InformeIncidente();
        infModificado.setId(2);
        infModificado.setConclusion("Actualizado dinámicamente desde el test " + System.currentTimeMillis());
        infModificado.setNivelSeveridad(45); // Era 40
        infModificado.setMalwareDetectado(false);
        
        informeDao.update(infModificado);
        System.out.println("Update dinámico finalizado. Revisa tu BD AWS RDS.");
    }
}