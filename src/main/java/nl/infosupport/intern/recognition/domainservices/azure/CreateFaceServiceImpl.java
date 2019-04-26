package nl.infosupport.intern.recognition.domainservices.azure;

import org.springframework.beans.factory.annotation.Value;

import java.awt.image.BufferedImage;

public class CreateFaceServiceImpl implements CreateFaceService {

    @Value("${azure.groupid}")
    private String groupId;

    @Override
    public String addFaceToPerson(String personId, BufferedImage image) {
//        var handler = commands.get(ADDFACE);
//        handler.handle(new AzureAddFaceCommand(groupId, personId, image));
//
//        return handler.getResult();
        return "";
    }
}
