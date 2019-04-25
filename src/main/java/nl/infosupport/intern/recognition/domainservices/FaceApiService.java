package nl.infosupport.intern.recognition.domainservices;

import nl.infosupport.intern.recognition.domainservices.actions.CommandEnum;
import nl.infosupport.intern.recognition.domainservices.actions.CommandHandler;
import nl.infosupport.intern.recognition.domainservices.actions.person.Command;
import nl.infosupport.intern.recognition.domainservices.actions.person.add.AddFaceCommand;
import nl.infosupport.intern.recognition.domainservices.actions.person.create.CreatePersonCommand;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Map;

import static nl.infosupport.intern.recognition.domainservices.actions.CommandEnum.ADDFACE;
import static nl.infosupport.intern.recognition.domainservices.actions.CommandEnum.CREATE;

@Service
public class FaceApiService implements CloudRecognitionService {

    private Map<CommandEnum, CommandHandler<Command>> commands;

    public FaceApiService(Map<CommandEnum, CommandHandler<Command>> commands) {
        this.commands = commands;
    }

    @Override
    public String createPerson(String name) {
        var handler = commands.get(CREATE);
        handler.handle(new CreatePersonCommand("infosupport", name));

        return handler.getResult();
    }

    @Override
    public String addFaceToPerson(String personId, BufferedImage image) {
        var handler = commands.get(ADDFACE);
        handler.handle(new AddFaceCommand("infosupport", personId, image));

        return handler.getResult();

    }
}
