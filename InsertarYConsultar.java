package proy3ET6;

import java.sql.*;

public class InsertarYConsultar {

    /**
     *
     * @param actualiza
     * @return boolean
     */

    public boolean insertar(String actualiza) {

        // Documentacion: cambiado control por seRealizaronInserciones

        boolean seRealizaronInserciones = false;

        try {

            /**
             * Cargamos el driver de mariaDB junto a la URL de la conexion y user/password. Luego creamos la
             * encapsulacion de las consultas y estamos listos para pasar datos a la BBDD.
             * Devolvemos un boolean para saber si se han realizado o no cambios
             *
             *
             */

            Class.forName("org.mariadb.jdbc.Driver");
            String urlCon = "jdbc:mariadb://localhost:3306/Proy3TE6";
            Connection conexBd = DriverManager.getConnection(urlCon, "root", "root");
            Statement encapsulaCons = conexBd.createStatement();

            // Documentacion: cambio filActualizadas por numeroFilasActualizadas

            int numeroFilasActualizadas = encapsulaCons.executeUpdate(actualiza);
            seRealizaronInserciones = numeroFilasActualizadas > 0;


            encapsulaCons.close();
            conexBd.close();
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return seRealizaronInserciones;
    }

    /**
     *
     * @param consulta
     * @return ResultSet con el resultado de las consultas
     */

    public ResultSet consulta(String consulta) {

        /**
         * Igual que antes, construimos el objeto para interactuar con la BBDD, guardamos el resultado
         * de ejecutar la consulta en resulCons y es lo que devolvemos al realizar una consulta
         */

        ResultSet resultadoConsultas = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String urlCon = "jdbc:mariadb://localhost:3306/Proy3TE6";
            Connection conexBd = DriverManager.getConnection(urlCon, "root", "root");
            Statement encapsulaCons = conexBd.createStatement();

            //Documentacion resulCons > resultadoConsultas

            resultadoConsultas = encapsulaCons.executeQuery(consulta);


            encapsulaCons.close();
            conexBd.close();
        } catch (ClassNotFoundException | SQLException cnfe) {
            System.out.println(cnfe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultadoConsultas;
    }
}

