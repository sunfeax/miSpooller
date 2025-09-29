package net.ausiasmarch.connection;

import javax.servlet.annotation.WebListener;

@WebListener
public class AppConextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(javax.servlet.ServletContextEvent sce) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(javax.servlet.ServletContextEvent sce) {
    }


    
}
