package net.ausiasmarch.connection;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariPool {

    private static HikariDataSource webappPool;

    // Método para obtener el DataSource (acceso global)
    public static DataSource getPool() {
        if (webappPool == null) {
            HikariConfig config = new HikariConfig();

            // Parámetros básicos de conexión
            config.setJdbcUrl(DatabaseParameters.getDbUrl());
            config.setUsername(DatabaseParameters.getDbUser());
            config.setPassword(DatabaseParameters.getDbPassword());

            // Configuración básica de pool
            config.setMaximumPoolSize(10);  // Número máximo de conexiones en el pool
            config.setMinimumIdle(2);       // Conexiones mínimas en reposo
            config.setIdleTimeout(30000);   // Tiempo de espera para cerrar conexiones inactivas (ms)
            config.setConnectionTimeout(30000); // Tiempo máximo para obtener una conexión (ms)
            config.setPoolName("MiHikariPool");

            webappPool = new HikariDataSource(config);
        }
        return webappPool;
    }

    // Cierre manual del pool si es necesario
    public static void closePool() {
        if (webappPool != null && !webappPool.isClosed()) {
            webappPool.close();
        }
    }

}
