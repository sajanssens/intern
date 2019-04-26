package nl.infosupport.intern.recognition.domainservices.azure.actions.group;

import lombok.Data;
import nl.infosupport.intern.recognition.domainservices.azure.actions.Command;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Data
@Component
public class TrainGroupCommand implements Command {

    private final String groupId;

    public TrainGroupCommand(@Qualifier("getAzureGroupId") String groupId) {
        this.groupId = groupId;
    }
}
