package nl.infosupport.intern.recognition.domainservices;

import nl.infosupport.intern.recognition.domainservices.actions.CommandHandler;
import nl.infosupport.intern.recognition.domainservices.actions.Subscription;
import nl.infosupport.intern.recognition.domainservices.actions.group.TrainGroupCommand;
import nl.infosupport.intern.recognition.domainservices.actions.group.TrainGroupCommandHandler;
import nl.infosupport.intern.recognition.domainservices.actions.person.add.AddFaceCommand;
import nl.infosupport.intern.recognition.domainservices.actions.person.add.AddFaceCommandHandler;
import nl.infosupport.intern.recognition.domainservices.actions.person.create.CreatePersonCommand;
import nl.infosupport.intern.recognition.domainservices.actions.person.create.CreatePersonCommandHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class CommandHandlerTest {

    @Autowired
    private Subscription subscription;

    @BeforeEach
    void setUp() {
        subscription = new Subscription("0bc91eb2c95f4b62ae746aa42884e6dc");
    }

    @Test
    void createPersonCommandShouldReturnNewPersonId() {
        CommandHandler<CreatePersonCommand> commandHandler = new CreatePersonCommandHandler(subscription);
        CreatePersonCommand command = new CreatePersonCommand("infosupport", "Rico");

        commandHandler.handle(command);

        System.out.println(commandHandler.getResult());
    }

    @Test
    void addFaceCommandShouldReturnNewFaceId() {
        CommandHandler<AddFaceCommand> commandHandler = new AddFaceCommandHandler(subscription);

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:\\Users\\ricob\\Google Drive\\School\\Informatica\\Afstuderen\\Opdracht\\Visualisatie\\woman-face.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        AddFaceCommand command = new AddFaceCommand("infosupport", "1271a95f-7eec-48b7-aedd-59e7f0689ac9", image);
        commandHandler.handle(command);

        System.out.println(commandHandler.getResult());

    }

    @Test
    void trainGroupShouldReturnEmptyBodyWithStatus202() {
        CommandHandler<TrainGroupCommand> commandHandler = new TrainGroupCommandHandler(subscription);

        TrainGroupCommand command = new TrainGroupCommand("infosupport");

        commandHandler.handle(command);
        System.out.println(commandHandler.getResult());
    }
}