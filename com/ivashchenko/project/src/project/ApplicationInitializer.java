package project;

import org.apache.log4j.PropertyConfigurator;
import project.external_systems.PhoneStation;
import project.external_systems.PhoneSystem;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

public class ApplicationInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initLog4j(servletContextEvent);
        startExternalPhoneStation();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }


    private void startExternalPhoneStation() {
        PhoneSystem phoneSystem = new PhoneSystem();
        PhoneStation phoneStation = new PhoneStation();
        phoneStation.addPhoneSystemObserver(phoneSystem);

        Thread thread = new Thread(phoneStation);
        thread.setDaemon(true);
        thread.start();
    }

    private void initLog4j(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("/") + log4jConfigFile;
        PropertyConfigurator.configure(fullPath);
    }
}
