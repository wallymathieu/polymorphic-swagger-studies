package se.gewalli.polymorphic_swagger;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import se.gewalli.kyminon.Result;
import se.gewalli.polymorphic_swagger.commands.Command;

public interface AppendBatch {
    CompletableFuture<Result<Integer, FailureReason>> batch(Collection<Command> commands);

    CompletableFuture<Result<Collection<Command>, FailureReason>> readAll();
}
