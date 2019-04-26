package nl.infosupport.intern.recognition.domainservices.azure;

import nl.infosupport.intern.recognition.domainservices.azure.actions.CommandType;
import nl.infosupport.intern.recognition.domainservices.azure.actions.CommandHandler;
import nl.infosupport.intern.recognition.domainservices.azure.actions.Command;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.add.AddFaceCommand;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.create.CreatePersonCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Map;

import static nl.infosupport.intern.recognition.domainservices.azure.actions.CommandType.ADDFACE;
import static nl.infosupport.intern.recognition.domainservices.azure.actions.CommandType.CREATE;

@Service
public class FaceApiService implements CloudService {

    @Value("${azure.groupid}")
    private String groupId;

    private Map<CommandType, CommandHandler<Command>> commands;

    public FaceApiService(Map<CommandType, CommandHandler<Command>> commands) {
        this.commands = commands;
    }

    @Override
    public String createPerson(String name) {
        var handler = commands.get(CREATE);
        handler.handle(new CreatePersonCommand(groupId, name));

        return handler.getResult();
    }

    @Override
    public String addFaceToPerson(String personId, BufferedImage image) {
        var handler = commands.get(ADDFACE);
        handler.handle(new AddFaceCommand(groupId, personId, image));

        return handler.getResult();

    }
}
