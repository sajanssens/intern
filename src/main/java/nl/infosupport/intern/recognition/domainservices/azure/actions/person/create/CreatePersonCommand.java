package nl.infosupport.intern.recognition.domainservices.azure.actions.person.create;

import nl.infosupport.intern.recognition.domainservices.azure.actions.Command;

public interface CreatePersonCommand extends Command {

    CreatePersonCommand setData(String name);
}
