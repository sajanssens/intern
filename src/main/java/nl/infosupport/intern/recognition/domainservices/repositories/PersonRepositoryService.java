package nl.infosupport.intern.recognition.domainservices.repositories;

import nl.infosupport.intern.recognition.domain.Person;
import nl.infosupport.intern.recognition.domainservices.azure.actions.group.TrainGroupCommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

@Service
public class PersonRepositoryService implements PersonRepositoryAdapter {

    private static Logger logger = LoggerFactory.getLogger(TrainGroupCommandHandler.class);

    private PersonRepository repo;

    @Autowired
    public PersonRepositoryService(PersonRepository personRepository) {
        this.repo = personRepository;
    }

    @Override
    public boolean isUniqueName(String name) {
        return !repo.existsById(name);

    }

    @Override
    public String create(String name, CompletableFuture<String> personId) {

        Person person = new Person();
        person.setName(name);

        personId.completeOnTimeout("time-out", 5, SECONDS)
                .thenAcceptAsync(person::setPersonId)
                .thenRun(() -> repo.save(person))
                .thenRun(() -> logger.debug("entity saved in database"));

        return "succeed";
    }


}
