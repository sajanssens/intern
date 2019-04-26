package nl.infosupport.intern.recognition.domainservices.azure;

import java.awt.image.BufferedImage;

public interface CloudService {

    String createPerson(String name);

    String addFaceToPerson(String personId, BufferedImage image);
}
