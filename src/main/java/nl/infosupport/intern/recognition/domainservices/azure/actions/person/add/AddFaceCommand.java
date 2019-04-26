package nl.infosupport.intern.recognition.domainservices.azure.actions.person.add;

import lombok.Data;
import nl.infosupport.intern.recognition.domainservices.azure.actions.Command;

import java.awt.image.BufferedImage;

@Data
public class AddFaceCommand implements Command {

    private final String groupId;
    private final String personId;
    private final BufferedImage image;
}
