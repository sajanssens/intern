package nl.infosupport.intern.recognition.domainservices.repositories;

import nl.infosupport.intern.recognition.domain.Person;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface PersonRepositoryAdapter {

    boolean isUniqueName(String name);
    Optional<Person> findById(String name);
    String create(String name, CompletableFuture<String> personId);

}
