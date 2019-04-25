package nl.infosupport.intern.recognition.repositories;

import java.util.concurrent.CompletableFuture;

public interface PersonRepositoryAdapter {

    boolean isUniqueName(String name);
    String create(String name, CompletableFuture<String> personId);

}
