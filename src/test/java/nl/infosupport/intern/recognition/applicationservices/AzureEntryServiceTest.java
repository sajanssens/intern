package nl.infosupport.intern.recognition.applicationservices;

import nl.infosupport.intern.recognition.domainservices.azure.CreatePersonService;
import nl.infosupport.intern.recognition.domainservices.repositories.PersonRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class AzureEntryServiceTest {

    @Mock
    private PersonRepositoryAdapter personRepositoryAdapter;

    @Mock
    private CreatePersonService createPersonService;

    @BeforeEach
    void setUp() {
        when(personRepositoryAdapter.isUniqueName(any())).thenReturn(true);
        when(personRepositoryAdapter.create(any(),any())).thenReturn("");
    }

    @Test
    void whenRegisterNewNameWithCompletedFuture() {
        when(createPersonService.createPerson(any())).thenReturn("test-id");

        var azureService = new AzureEntryService(personRepositoryAdapter, createPersonService);

        azureService.register("Rico");
    }
}