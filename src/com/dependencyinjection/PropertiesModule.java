package com.dependencyinjection;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.IOException;
import java.util.Properties;

public class PropertiesModule extends AbstractModule {

    protected void configure() {
        try {
            Properties properties = new Properties();
            assert getClass().getClassLoader() != null;
            properties.load(getClass().getClassLoader().getResourceAsStream("resource.properties"));
            Names.bindProperties(binder(), properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
