package nl.infosupport.intern.recognition.domainservices;

import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;

public interface CloudRecognitionService {

    CompletableFuture<String> createPerson(String name);

    CompletableFuture<String> addFaceToPerson(String personId, BufferedImage image);
}
