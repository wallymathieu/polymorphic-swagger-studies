package se.gewalli.polymorphic_swagger;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.gewalli.polymorphic_swagger.commands.Command;
import se.gewalli.polymorphic_swagger.data.EntityNotFound;
import se.gewalli.polymorphic_swagger.data.Repository;

@Component
public class PostInitializationBean {
    @Autowired
    private AppendBatch appendBatch;
    @Autowired
    private Repository repository;
    Logger logger = LoggerFactory.getLogger(PostInitializationBean.class);

    @PostConstruct
    public void init() {
        appendBatch.readAll().thenApply(res -> res.fold(collection -> {
                    logger.info("booting up repository information based on stored information");
                    for (Command command : collection) {
                        try {
                            command.run(repository);
                        } catch (EntityNotFound entityNotFound) {
                            logger.error("EntityNotFound", entityNotFound);
                        }
                    }
                    return 0;
                },
                err -> {
                    logger.error("Failed to read all", err);
                    return 1;
                })).join();
    }
}