package nl.infosupport.intern.recognition.domainservices;

import nl.infosupport.intern.recognition.domainservices.actions.CommandEnum;
import nl.infosupport.intern.recognition.domainservices.actions.CommandHandler;
import nl.infosupport.intern.recognition.domainservices.actions.person.Command;
import nl.infosupport.intern.recognition.domainservices.actions.person.add.AddFaceCommand;
import nl.infosupport.intern.recognition.domainservices.actions.person.create.CreatePersonCommand;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import static nl.infosupport.intern.recognition.domainservices.actions.CommandEnum.CREATE;

@Service
public class FaceApiService implements CloudRecognitionService {

    private Map<CommandEnum, CommandHandler<Command>> commands;

    public FaceApiService(Map<CommandEnum, CommandHandler<Command>> commands) {
        this.commands = commands;
    }

    @Override
    public CompletableFuture<String> createPerson(String name) {
        System.out.println(Thread.currentThread().getName());

        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> new CreatePersonCommand("infosupport", name), Executors.newSingleThreadExecutor())
                .thenApply(command -> {
                    System.out.println(Thread.currentThread().getName());
                    CommandHandler<Command> commandHandler = commands.get(CREATE);
                    commandHandler.handle(command);
                    return commandHandler;
                })
                .thenApply(CommandHandler::getResult);

        return completableFuture;
    }

    @Override
    public CompletableFuture<String> addFaceToPerson(String personId, BufferedImage image) {

        var commandHandler = commands.get(CommandEnum.ADDFACE);

        var addFaceCommand = new AddFaceCommand("infosupport", personId, image);

        commandHandler.handle(addFaceCommand);

        return CompletableFuture.supplyAsync(commandHandler::getResult);
    }
}
