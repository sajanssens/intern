package nl.infosupport.intern.recognition.applicationservices;

import nl.infosupport.intern.recognition.domainservices.CloudRecognitionService;
import nl.infosupport.intern.recognition.repositories.PersonRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class AzureCognitiveServicesEntryService implements EntryService {

//    private PersonRepositoryAdapter personService;
    private CloudRecognitionService recognitionService;

    @Autowired
    PersonRepositoryAdapter personService;
    @Autowired
    public AzureCognitiveServicesEntryService(CloudRecognitionService entryService) {
//        this.personService = personService;
        this.recognitionService = entryService;
    }

    @Override
    public String register(String name) {

        //If the name already exists, handle that somehow.
        if (!personService.isUniqueName(name)) throw new EntityExistsException("Name already exists!");

        var azurePersonIdFuture = recognitionService.createPerson(name);

        return personService.create(name, azurePersonIdFuture);

    }
}
