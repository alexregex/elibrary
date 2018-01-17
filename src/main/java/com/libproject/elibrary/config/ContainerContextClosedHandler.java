package com.libproject.elibrary.config;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
/*Resolve WARNING: org.apache.catalina.loader.WebappClassLoaderBase.clearReferencesJdbc The web application [ROOT] registered the JDBC driver*/
@WebListener
public class ContainerContextClosedHandler implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();

        java.sql.Driver driver;

        // clear drivers
        while(drivers.hasMoreElements()) {
            try {
                driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        // MySQL driver leaves around a thread. This static method cleans it up.
        try {
            AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
