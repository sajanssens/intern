package nl.infosupport.intern.recognition.domainservices.azure.actions.person.add;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;

import java.awt.image.BufferedImage;

@Data
public class AzureAddFaceCommand implements AddFaceCommand {

    private final String groupId;
    private String personId;
    private BufferedImage image;

    public AzureAddFaceCommand(@Qualifier("getAzureGroupId") String groupId) {
        this.groupId = groupId;
    }

    @Override
    public AzureAddFaceCommand setData(String personId, BufferedImage image) {
        this.personId = personId;
        this.image = image;

        return this;
    }
}
