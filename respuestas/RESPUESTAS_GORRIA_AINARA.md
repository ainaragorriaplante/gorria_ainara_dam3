/*
=========================================
AUTOR: AINARA GORRIA
GRUPO: DAM3
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/

PREGUNTA 1
Explica cómo funciona la relación 1:N entre Soc e Incidente tanto en SQL como en Java.

En SQL: Se implementa mediante una clave foránea (fk_soc_id) en la tabla incidentes que apunta a la clave primaria (id) de la tabla socs. Esto permite que un SOC tenga múltiples incidentes, pero cada incidente pertenezca a un único SOC.

En Java: Se representa mediante agregación/composición. La clase Incidente contiene un atributo del tipo Soc (referencia al objeto padre). Si mapeáramos desde el SOC hacia los incidentes, la clase Soc tendría una colección, aunque el UML proporcionado indica que la navegación principal es desde Incidente hacia Soc.

PREGUNTA 2
Explica por qué en Java utilizamos:
private Soc soc;
y no:
private int socId;

Porque Java es un lenguaje orientado a objetos, no un lenguaje relacional. Utilizar int socId nos obligaría a hacer lógica de base de datos fuera del DAO para obtener los detalles del SOC, rompiendo la encapsulación y el paradigma de Orientación a Objetos. Al inyectar el objeto Soc completo, abstraemos a la capa superior (lógica de negocio o UI) de cómo están estructurados los datos en SQL.

PREGUNTA 3
Explica qué ventaja aporta PreparedStatement frente a concatenar SQL manualmente.

Seguridad: Previene ataques de Inyección SQL escapando automáticamente los caracteres especiales.
Rendimiento: El motor de base de datos precompila la consulta y cachea el plan de ejecución, lo que hace que ejecuciones múltiples de la misma consulta (con distintos parámetros) sean mucho más rápidas.
Mantenibilidad: Evita el "infierno de las comillas" ("SELECT * FROM tabla WHERE id = " + id + " AND nombre = '" + nombre + "'"), haciendo el código mucho más limpio y legible usando marcadores de posición (?).
