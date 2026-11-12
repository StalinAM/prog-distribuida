package com.programacion.distribuida.authors.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

@Path("/config")
public class TestConfigRest {
    @Inject //en quarkus se puede omitir
    @ConfigProperty(name = "quarkus.http.port", defaultValue = "8080")
    Integer port;

    @ConfigProperty(name = "app.name")
    String diAppName;

    @GET
    public String test(){

        //punto de acceso al sistema de configuracion
        Config config= ConfigProvider.getConfig();
        //listar las fuentes de configuracion
        config.getConfigSources().forEach(it->{
            System.out.printf("[%5d] %s%n", it.getOrdinal(), it.getName());
        });

        String appName = config.getValue("app.name", String.class);
        Optional<String> appTitle = config.getOptionalValue("app.title", String.class);


        System.out.println("----------------------");
        System.out.println("Port="+ port);


        if (appTitle.isPresent()){
            System.out.println("app.title= "+appTitle.get());

        }else{
            System.out.println("app.name= "+appName.getClass());
        }
        System.out.println("app.name= "+appName);
        return "GREAT!";
    }
}