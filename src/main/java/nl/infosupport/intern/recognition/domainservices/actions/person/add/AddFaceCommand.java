package nl.infosupport.intern.recognition.domainservices.actions.person.add;

import lombok.Data;
import nl.infosupport.intern.recognition.domainservices.actions.person.Command;

import java.awt.*;
import java.awt.image.BufferedImage;

@Data
public class AddFaceCommand implements Command {

    private final String groupId;
    private final String personId;
    private final BufferedImage image;
}
