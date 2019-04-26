package nl.infosupport.intern.recognition.domainservices;

import nl.infosupport.intern.recognition.domainservices.azure.CreatePersonService;
import nl.infosupport.intern.recognition.domainservices.azure.CreatePersonServiceImpl;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.create.CreatePersonCommand;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.create.CreatePersonCommandHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class CreatePersonServiceImplTest {

    @Mock
    CreatePersonCommandHandler<CreatePersonCommand> handler;

    @Mock
    CreatePersonCommand command;

    @Test
    void createPerson() throws ExecutionException, InterruptedException {
        when(handler.getResult()).thenReturn("mocked-value");

        CreatePersonService service = new CreatePersonServiceImpl(handler, command);

        CompletableFuture<String> rico = CompletableFuture.supplyAsync(() -> service.createPerson("Rico"));

        System.out.println(rico.get());
    }
}