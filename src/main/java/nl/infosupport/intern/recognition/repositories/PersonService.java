package nl.infosupport.intern.recognition.repositories;

import nl.infosupport.intern.recognition.domain.Person;
import nl.infosupport.intern.recognition.domainservices.actions.group.TrainGroupCommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

@Service
public class PersonService implements PersonRepositoryAdapter {

    private static Logger logger = LoggerFactory.getLogger(TrainGroupCommandHandler.class);

    private PersonRepository repo;

    @Autowired
    public PersonService(PersonRepository personRepository) {
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
                .thenAcceptAsync(person::setFaceId)
                .thenRun(() -> repo.save(person))
                .thenRun(() -> logger.debug("entity saved in database"));

        return name;
    }


}
