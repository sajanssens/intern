package nl.infosupport.intern.recognition.domainservices.azure;

import java.awt.image.BufferedImage;

public interface CreateFaceService {

    String addFaceToPerson(String personId, BufferedImage image);
}
