package nl.infosupport.intern.recognition.domainservices.azure.actions.person.create;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Data
@Component
public class AzureCreatePersonCommand implements CreatePersonCommand {

    private final String groupId;

    private String name;

    public AzureCreatePersonCommand(@Qualifier("getAzureGroupId") String groupId) {
        this.groupId = groupId;
    }

    @Override
    public CreatePersonCommand setData(String name) {
        this.name = name;

        return this;
    }
}
