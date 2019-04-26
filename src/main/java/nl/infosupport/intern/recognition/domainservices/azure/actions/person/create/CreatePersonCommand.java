package nl.infosupport.intern.recognition.domainservices.azure.actions.person.create;

import lombok.Data;
import nl.infosupport.intern.recognition.domainservices.azure.actions.Command;


@Data

public class CreatePersonCommand implements Command {

    private final String groupId;
    private final String name;

    public CreatePersonCommand(String groupId, String name) {
        this.groupId = groupId;
        this.name = name;

    }
}
