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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class AzureEntryServiceTest {

    @Mock
    private PersonRepositoryAdapter personRepositoryAdapter;

    @Mock
    private CreatePersonService createPersonService;

    private AzureEntryService aez;

    @BeforeEach
    void setUp() {
        aez = new AzureEntryService(personRepositoryAdapter, createPersonService);
    }

    @Test
    void WhenRegisterPersonAndHasUniqueNameResultShouldBeSucceeded() {
        String uniquePersonName = "Unique Name";

        when(personRepositoryAdapter.isUniqueName(uniquePersonName)).thenReturn(true);
        when(createPersonService.createPerson(uniquePersonName)).thenReturn("mocked-person-id");
        when(personRepositoryAdapter.create(any(),any())).thenReturn("succeed");

        RegisterResult result = aez.register(uniquePersonName);

        assertThat(result.isSucceed(), is(true));
        assertThat(result.getReason(), is("succeed"));
    }

    @Test
    void whenRegisterPersonAndHasNoUniqueNameResultShouldNotBeSucceeded() {
        String noUniqueName = "No Unique Name";

        when(personRepositoryAdapter.isUniqueName(noUniqueName)).thenReturn(false);

        RegisterResult result = aez.register(noUniqueName);

        assertThat(result.isSucceed(), is(false));
        assertThat(result.getReason(), is("No unique name"));
    }

    @Test
    void whenRegisterPersonAndGetErrorShouldThrowException() {
        when(personRepositoryAdapter.isUniqueName(any())).thenReturn(true);
        when(createPersonService.createPerson(any())).thenThrow(new RuntimeException(""));

        aez.register("Rico");
    }
}