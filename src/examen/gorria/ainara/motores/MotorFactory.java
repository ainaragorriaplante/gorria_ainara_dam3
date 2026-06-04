/*
=========================================
AUTOR: AINARA GORRIA
GRUPO: DAM3
EXAMEN JDBC AWS RDS
FECHA: 04/06/2026
=========================================
*/
package examen.gorria.ainara.motores;

public class MotorFactory {
    public static final int POSTGRE = 1;

    public static MotorSQL create(int tipoMotor) {
        if (tipoMotor == POSTGRE) {
            return new PostgreMotorSQL();
        }
        return null;
    }
}
