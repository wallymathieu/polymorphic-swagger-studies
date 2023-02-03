package se.gewalli.polymorphic_swagger.configuration;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import se.gewalli.polymorphic_swagger.AppendBatch;
import se.gewalli.polymorphic_swagger.CommandsHandler;
import se.gewalli.polymorphic_swagger.data.InMemoryRepository;
import se.gewalli.polymorphic_swagger.data.Repository;
import se.gewalli.polymorphic_swagger.json.AppendToFile;

@Configuration
public class AppConfig {
    @Autowired
    private Environment env;

    @Bean
    public Repository repository() {
        return new InMemoryRepository();
    }

    @Bean
    public AppendBatch appendBatch() throws IOException {
        var dbLocation = env.getProperty("FILE_DB_LOCATION");
        var logger = LoggerFactory.getLogger(AppConfig.class);
        if (dbLocation == null || dbLocation.isEmpty()) {
            logger.info("No database location found, using tmp");
            dbLocation = "/tmp/test.db";
        }
        var db = new File(dbLocation);
        if (!db.exists()) {
            db.createNewFile();
        }
        return new AppendToFile(dbLocation,
                Executors.newFixedThreadPool(1),
                err -> logger.error("Error while appending ", err));
    }

    @Bean
    public CommandsHandler persistCommandsHandler() {
        return new CommandsHandler();
    }

}