package nl.infosupport.intern.recognition.domainservices.azure.actions.person.add;

import nl.infosupport.intern.recognition.domainservices.azure.actions.Command;

import java.awt.image.BufferedImage;

public interface AddFaceCommand extends Command {

    AzureAddFaceCommand setData(String personId, BufferedImage image);

}