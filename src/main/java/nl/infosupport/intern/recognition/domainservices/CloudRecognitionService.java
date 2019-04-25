package nl.infosupport.intern.recognition.domainservices;

import java.awt.image.BufferedImage;

public interface CloudRecognitionService {

    String createPerson(String name);

    String addFaceToPerson(String personId, BufferedImage image);
}
