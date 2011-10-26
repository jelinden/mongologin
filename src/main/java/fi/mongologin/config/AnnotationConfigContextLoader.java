package fi.mongologin.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextLoader;

public class AnnotationConfigContextLoader implements ContextLoader {

    public ApplicationContext loadContext(String... locations) throws Exception {
        Class<?>[] configClasses = new Class<?>[locations.length];
        for (int i = 0; i < locations.length; i++) {
            configClasses[i] = Class.forName(locations[i]);
        }        
        return new AnnotationConfigApplicationContext(configClasses);
    }

    public String[] processLocations(Class<?> c, String... locations) {
        return locations;
    }
}
