package se.gewalli.polymorphic_swagger;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import se.gewalli.kyminon.Result;
import se.gewalli.polymorphic_swagger.commands.Command;
import se.gewalli.polymorphic_swagger.data.EntityNotFound;
import se.gewalli.polymorphic_swagger.data.Repository;

/**
 * Wraps append batch and repository in order to append command results to repository and append them once done.
 */
public class CommandsHandler {
    @Autowired
    private AppendBatch appendBatch;
    @Autowired
    private Repository repository;
    Logger logger = LoggerFactory.getLogger(CommandsHandler.class);

    public CompletableFuture<Result<Integer, FailureReason>> handle(Command c) {
        var l = new ArrayList<Command>();
        l.add(c);
        try {
            c.run(repository);
        } catch (EntityNotFound entityNotFound) {
            logger.error("entity not found", entityNotFound);
        }
        return appendBatch.batch(l);
    }
}
