package nl.infosupport.intern.recognition.domainservices;

import nl.infosupport.intern.recognition.domainservices.actions.CommandEnum;
import nl.infosupport.intern.recognition.domainservices.actions.CommandHandler;
import nl.infosupport.intern.recognition.domainservices.actions.person.Command;
import nl.infosupport.intern.recognition.domainservices.actions.person.add.AddFaceCommand;
import nl.infosupport.intern.recognition.domainservices.actions.person.create.CreatePersonCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class FaceApiService implements CloudRecognitionService {

    private Map<CommandEnum, CommandHandler<Command>> commands;
//    private Map<CommandEnum, CreatePersonCommandHandler> commands;

    @Autowired
    public FaceApiService(Map<CommandEnum, CommandHandler<Command>> commands) {
        this.commands = commands;
    }

    @Override
    public CompletableFuture<String> createPerson(String name) {

        var commandHandler = commands.get(CommandEnum.CREATE);
        var createPersonCommand = new CreatePersonCommand( "infosupport", name);

        commandHandler.handle(createPersonCommand);

        return CompletableFuture.supplyAsync(commandHandler::getResult);
    }

    @Override
    public CompletableFuture<String> addFaceToPerson(String personId, BufferedImage image){

        var commandHandler = commands.get(CommandEnum.ADDFACE);

        var addFaceCommand = new AddFaceCommand("infosupport",personId, image);

        commandHandler.handle(addFaceCommand);

        return CompletableFuture.supplyAsync(commandHandler::getResult);
    }
}
