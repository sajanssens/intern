package nl.infosupport.intern.recognition.domainservices;

import nl.infosupport.intern.recognition.domainservices.azure.actions.CommandHandler;
import nl.infosupport.intern.recognition.domainservices.azure.actions.group.TrainGroupCommand;
import nl.infosupport.intern.recognition.domainservices.azure.actions.group.TrainGroupCommandHandler;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.add.AddFaceCommand;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.add.AddFaceCommandHandler;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.add.AzureAddFaceCommand;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.add.AzureAddFaceCommandHandler;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.create.AzureCreatePersonCommand;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.create.AzureCreatePersonCommandHandler;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.create.CreatePersonCommand;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.create.CreatePersonCommandHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class CommandHandlerTest {

    @Value("${azure.subscription}")
    private String subscription = "0bc91eb2c95f4b62ae746aa42884e6dc";

    @Value("${azure.groupid}")
    private String groupId = "infosupport";

    @Test
    void createPersonCommandShouldReturnNewPersonId() {
        CreatePersonCommandHandler<CreatePersonCommand> commandHandler = new AzureCreatePersonCommandHandler(subscription);

        CreatePersonCommand command = new AzureCreatePersonCommand(groupId);

        commandHandler.handle(command.setData("Rico"));

        System.out.println(commandHandler.getResult());
    }

    @Test
    void addFaceCommandShouldReturnNewFaceId() {
        AddFaceCommandHandler<AddFaceCommand> commandHandler = new AzureAddFaceCommandHandler(subscription);

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:\\Users\\ricob\\Google Drive\\School\\Informatica\\Afstuderen\\Opdracht\\Visualisatie\\woman-face.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        AzureAddFaceCommand command = new AzureAddFaceCommand(groupId);
        commandHandler.handle(command.setData("2cdcf822-6aa3-4c6f-8930-f3000ec6c353", image));

        System.out.println(commandHandler.getResult());

    }

    @Test
    void trainGroupShouldReturnEmptyBodyWithStatus202() {
        CommandHandler<TrainGroupCommand> commandHandler = new TrainGroupCommandHandler(subscription);

        TrainGroupCommand command = new TrainGroupCommand(groupId);

        commandHandler.handle(command);
        System.out.println(commandHandler.getResult());
    }
}