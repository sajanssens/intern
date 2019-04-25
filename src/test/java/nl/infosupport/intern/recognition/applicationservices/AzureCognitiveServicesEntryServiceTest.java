package nl.infosupport.intern.recognition.applicationservices;

import nl.infosupport.intern.recognition.domainservices.CloudRecognitionService;
import nl.infosupport.intern.recognition.repositories.PersonRepositoryAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class AzureCognitiveServicesEntryServiceTest {

    @Mock
    private PersonRepositoryAdapter personRepositoryAdapter;

    @Mock
    private CloudRecognitionService cloudRecognitionService;

    @Test
    void whenRegisterNewName() {
        when(cloudRecognitionService.createPerson(any())).thenReturn(CompletableFuture.completedFuture("test-id"));
        when(personRepositoryAdapter.isUniqueName(any())).thenReturn(true);
        when(personRepositoryAdapter.create(any(),any())).thenReturn("");

        var azureService = new AzureCognitiveServicesEntryService(cloudRecognitionService);

        azureService.register("Rico");
    }
}