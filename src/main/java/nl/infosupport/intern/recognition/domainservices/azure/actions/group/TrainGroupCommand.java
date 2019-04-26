package nl.infosupport.intern.recognition.domainservices.azure.actions.group;

import lombok.Data;
import nl.infosupport.intern.recognition.domainservices.azure.actions.Command;

@Data
public class TrainGroupCommand implements Command {

    private final String groupId;

}
