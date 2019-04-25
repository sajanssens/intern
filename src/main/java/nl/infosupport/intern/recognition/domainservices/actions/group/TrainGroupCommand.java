package nl.infosupport.intern.recognition.domainservices.actions.group;

import lombok.Data;
import nl.infosupport.intern.recognition.domainservices.actions.person.Command;

@Data
public class TrainGroupCommand implements Command {

    private final String groupId;

}
