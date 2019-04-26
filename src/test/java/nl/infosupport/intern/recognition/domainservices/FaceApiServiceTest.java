package nl.infosupport.intern.recognition.domainservices;

import nl.infosupport.intern.recognition.domainservices.azure.CloudService;
import nl.infosupport.intern.recognition.domainservices.azure.FaceApiService;
import nl.infosupport.intern.recognition.domainservices.azure.actions.CommandType;
import nl.infosupport.intern.recognition.domainservices.azure.actions.CommandHandler;
import nl.infosupport.intern.recognition.domainservices.azure.actions.Command;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class FaceApiServiceTest {

    @Mock
    Map<CommandType, CommandHandler<Command>> commands;
    @Mock
    CommandHandler cm;


    @Test
    void createPerson() throws ExecutionException, InterruptedException {
        when(commands.get(any())).thenReturn(cm);
        when(cm.getResult()).thenReturn("mocked-value");

        CloudService service = new FaceApiService(commands);

        CompletableFuture<String> rico = CompletableFuture.supplyAsync(() -> service.createPerson("Rico"));

        System.out.println(rico.get());
    }
}