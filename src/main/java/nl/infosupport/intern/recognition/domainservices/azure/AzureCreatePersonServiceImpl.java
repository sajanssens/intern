package nl.infosupport.intern.recognition.domainservices.azure;

import nl.infosupport.intern.recognition.domainservices.azure.actions.person.create.AzureCreatePersonCommand;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.create.AzureCreatePersonCommandHandler;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.create.CreatePersonCommand;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.create.CreatePersonCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AzureCreatePersonServiceImpl implements CreatePersonService{

    private AzureCreatePersonCommandHandler<AzureCreatePersonCommand> handler;
    private AzureCreatePersonCommand command;

    @Autowired
    public AzureCreatePersonServiceImpl(AzureCreatePersonCommandHandler<AzureCreatePersonCommand> handler, AzureCreatePersonCommand command) {
        this.handler = handler;
        this.command = command;
    }

    @Override
    public String createPerson(String name) {
        handler.handle(command.setData(name));
        return handler.getResult();
    }
}
