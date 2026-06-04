/*
=========================================
AUTOR: AINARA_GORRIA_DAM3
FICHERO: inserts.sql
DESCRIPCIÓN: Datos iniciales de prueba
=========================================
*/
drop table socs cascade;
drop table incidentes cascade;
drop table informes_incidente cascade;
-- Tabla SOCS (Lado 1)
CREATE TABLE socs (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    pais VARCHAR(100) NOT NULL,
    nivel_seguridad INT NOT NULL,
    autor_examen VARCHAR(150) NOT NULL DEFAULT 'AINARA_GORRIA_DAM3'
);

-- Tabla INCIDENTES (Lado N de SOCS, Lado 1 de INFORMES)
CREATE TABLE incidentes (
    id SERIAL PRIMARY KEY,
    codigo_incidente VARCHAR(50) UNIQUE NOT NULL,
    tipo_incidente VARCHAR(100) NOT NULL,
    fecha_deteccion DATE NOT NULL,
    estado VARCHAR(50) NOT NULL,
    fk_soc_id INT NOT NULL,
    autor_examen VARCHAR(150) NOT NULL DEFAULT 'NOMBRE_APELLIDO_DAM3',
    CONSTRAINT fk_incidente_soc FOREIGN KEY (fk_soc_id) REFERENCES socs (id) ON DELETE CASCADE
);

-- Tabla INFORMES_INCIDENTE (Lado 1, relación 1:1 con INCIDENTES)
CREATE TABLE informes_incidente (
    id SERIAL PRIMARY KEY,
    malware_detectado BOOLEAN NOT NULL,
    nivel_severidad INT NOT NULL,
    conclusion TEXT NOT NULL,
    fk_incidente_id INT UNIQUE NOT NULL, -- UNIQUE garantiza la relación 1:1
    autor_examen VARCHAR(150) NOT NULL DEFAULT 'NOMBRE_APELLIDO_DAM3',
    CONSTRAINT fk_informe_incidente FOREIGN KEY (fk_incidente_id) REFERENCES incidentes(id) ON DELETE CASCADE
);