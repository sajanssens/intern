package nl.infosupport.intern.recognition.web.controllers;

import nl.infosupport.intern.recognition.applicationservices.EntryService;
import nl.infosupport.intern.recognition.domainservices.actions.group.TrainGroupCommandHandler;
import nl.infosupport.intern.recognition.web.models.NewPersonModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/person")
public class PersonController {

    private static Logger logger = LoggerFactory.getLogger(TrainGroupCommandHandler.class);


    private final EntryService entryService;

    @Autowired
    public PersonController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping(path = "/register")
    public String registerPerson(@RequestBody NewPersonModel person) {

        logger.debug("{}", person);

        String name = entryService.register(person.getName());

        return "creating new person with name " + name;

    }

}
