package nl.infosupport.intern.recognition.domainservices.repositories;

import java.util.concurrent.CompletableFuture;

public interface PersonRepositoryAdapter {

    boolean isUniqueName(String name);
    String create(String name, CompletableFuture<String> personId);

}
