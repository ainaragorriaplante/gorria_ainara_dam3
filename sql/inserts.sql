/*
=========================================
AUTOR: AINARA_GORRIA_DAM3
FICHERO: inserts.sql
DESCRIPCIÓN: Datos iniciales de prueba
=========================================
*/

-- 1. INSERTS TABLA SOCS
INSERT INTO socs (nombre, pais, nivel_seguridad, autor_examen) VALUES
('SOC Central Madrid', 'ESPAÑA', 5, 'AINARA_GORRIA_DAM3'),
('SOC Barcelona Norte', 'ESPAÑA', 4, 'AINARA_GORRIA_DAM3'),
('CyberDefense Paris', 'FRANCIA', 5, 'AINARA_GORRIA_DAM3'),
('SecOps Berlin', 'ALEMANIA', 3, 'AINARA_GORRIA_DAM3'),
('London Intel', 'REINO UNIDO', 4, 'AINARA_GORRIA_DAM3');

-- 2. INSERTS TABLA INCIDENTES (Relación N:1 con SOCS)
INSERT INTO incidentes (codigo_incidente, tipo_incidente, fecha_deteccion, estado, fk_soc_id, autor_examen) VALUES
('INC-2026-001', 'Ransomware', '2026-06-01', 'ABIERTO', 1, 'AINARA_GORRIA_DAM3'), -- SOC España
('INC-2026-002', 'Phishing', '2026-06-02', 'CERRADO', 2, 'AINARA_GORRIA_DAM3'), -- SOC España
('INC-2026-003', 'DDoS', '2026-06-03', 'EN_ANALISIS', 3, 'AINARA_GORRIA_DAM3'), -- SOC Francia
('INC-2026-004', 'Exfiltración', '2026-06-04', 'ESCALADO', 1, 'AINARA_GORRIA_DAM3'), -- SOC España
('INC-2026-005', 'Malware Infección', '2026-06-05', 'ABIERTO', 4, 'AINARA_GORRIA_DAM3'); -- SOC Alemania

-- 3. INSERTS TABLA INFORMES_INCIDENTE (Relación 1:1 con INCIDENTES)
-- NOTA: El primer y cuarto registro cumplen las condiciones de INCIDENTES_CRITICOS 
-- (Malware = TRUE, Severidad > 90, y su SOC asociado está en ESPAÑA).
INSERT INTO informes_incidente (malware_detectado, nivel_severidad, conclusion, fk_incidente_id, autor_examen) VALUES
(TRUE, 95, 'Infección crítica por ransomware LockBit. Sistemas aislados.', 1, 'AINARA_GORRIA_DAM3'),
(FALSE, 40, 'Intento de phishing detectado y bloqueado por el filtro.', 2, 'AINARA_GORRIA_DAM3'),
(FALSE, 75, 'Ataque volumétrico mitigado por el proveedor de red.', 3, 'AINARA_GORRIA_DAM3'),
(TRUE, 92, 'Fuga de datos detectada en el servidor de base de datos.', 4, 'AINARA_GORRIA_DAM3'),
(TRUE, 60, 'Troyano aislado en estación de trabajo de RRHH.', 5, 'AINARA_GORRIA_DAM3');