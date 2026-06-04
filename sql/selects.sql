/*
=========================================
AUTOR: AINARA_GORRIA_DAM3
FICHERO: inserts.sql
DESCRIPCIÓN: Datos iniciales de prueba
=========================================
*/

-- =========================================
-- CONSULTAS BÁSICAS (CRUD DAO)
-- =========================================

-- FIND ALL (SOCS)
SELECT id, nombre, pais, nivel_seguridad, autor_examen FROM socs;

-- FIND BY ID (SOCS)
SELECT id, nombre, pais, nivel_seguridad, autor_examen FROM socs WHERE id = ?;

-- FIND ALL (INCIDENTES - Sin joins para mapeo básico si se requiere)
SELECT id, codigo_incidente, tipo_incidente, fecha_deteccion, estado, fk_soc_id, autor_examen FROM incidentes;

-- =========================================
-- CONSULTAS PARA TESTS OBLIGATORIOS (INNER JOIN)
-- =========================================

-- 5. FIND INCIDENTES BY SOC
SELECT i.*, s.nombre AS soc_nombre, s.pais, s.nivel_seguridad 
FROM incidentes i
INNER JOIN socs s ON i.fk_soc_id = s.id
WHERE i.fk_soc_id = ?;

-- 6. FIND INCIDENTE WITH INFORME (Relación 1:1)
SELECT i.*, inf.id AS informe_id, inf.malware_detectado, inf.nivel_severidad, inf.conclusion
FROM incidentes i
INNER JOIN informes_incidente inf ON i.id = inf.fk_incidente_id
WHERE i.id = ?;

-- =========================================
-- BLOQUE 6: BONUS_QUERY_ADVANCED
-- IDENTIFICADOR: INCIDENTES_CRITICOS
-- =========================================
-- Devuelve incidentes que cumplan simultáneamente:
-- MALWARE_DETECTADO = TRUE, NIVEL_SEVERIDAD > 90, SOC en ESPAÑA
-- Realiza INNER JOIN de las 3 tablas para cargar los objetos completos en Java
SELECT 
    i.id AS incidente_id, i.codigo_incidente, i.tipo_incidente, i.fecha_deteccion, i.estado,
    s.id AS soc_id, s.nombre AS soc_nombre, s.pais, s.nivel_seguridad,
    inf.id AS informe_id, inf.malware_detectado, inf.nivel_severidad, inf.conclusion
FROM incidentes i
INNER JOIN socs s ON i.fk_soc_id = s.id
INNER JOIN informes_incidente inf ON i.id = inf.fk_incidente_id
WHERE inf.malware_detectado = TRUE 
  AND inf.nivel_severidad > 90 
  AND UPPER(s.pais) = 'ESPAÑA';

-- =========================================
-- BLOQUE 7: DYNAMIC_UPDATE_ENGINE (EJEMPLOS)
-- =========================================
-- La lógica en AbstractDAO/InformeIncidenteDAOImpl construirá consultas similares 
-- a estas dependiendo de los campos que vengan informados (!= null) en el objeto Java.

-- Ejemplo 1: Actualizar solo la severidad
UPDATE informes_incidente 
SET nivel_severidad = ? 
WHERE id = ?;

-- Ejemplo 2: Actualizar malware y conclusión
UPDATE informes_incidente 
SET malware_detectado = ?, conclusion = ? 
WHERE id = ?;

-- Ejemplo 3: Actualizar los 3 campos dinámicos simultáneamente
UPDATE informes_incidente 
SET malware_detectado = ?, nivel_severidad = ?, conclusion = ? 
WHERE id = ?;