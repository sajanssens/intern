package nl.infosupport.intern.recognition.web.configuration;

import nl.infosupport.intern.recognition.applicationservices.AzureCognitiveServicesEntryService;
import nl.infosupport.intern.recognition.applicationservices.EntryService;
import nl.infosupport.intern.recognition.domainservices.CloudRecognitionService;
import nl.infosupport.intern.recognition.domainservices.FaceApiService;
import nl.infosupport.intern.recognition.domainservices.actions.CommandEnum;
import nl.infosupport.intern.recognition.domainservices.actions.CommandHandler;
import nl.infosupport.intern.recognition.domainservices.actions.Subscription;
import nl.infosupport.intern.recognition.domainservices.actions.person.Command;
import nl.infosupport.intern.recognition.domainservices.actions.person.create.CreatePersonCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeanConfig {


    @Bean
    public Map<CommandEnum, CommandHandler<Command>> commands(){
        Map<CommandEnum, CommandHandler<Command>> commands = new HashMap<>();

        CommandHandler createPersonCommandHandler =
                 new CreatePersonCommandHandler(azureSubscription());

        commands.put(CommandEnum.CREATE, createPersonCommandHandler);

        return commands;
    }

    @Bean
    @Primary
    public CloudRecognitionService faceApiServiceBean() {
        CloudRecognitionService faceApiService = new FaceApiService(commands());
        return faceApiService;
    }
//    @Bean
//    public FaceApiService faceApiBean(Map<CommandEnum, CommandHandler<Command>> commands){
//        return new FaceApiService(commands);

//    }
//
//    @Bean
//    public Map<CommandEnum, CreatePersonCommandHandler> commands() {
//
//        Map<CommandEnum, CreatePersonCommandHandler> commandMap = new HashMap<>();
//
//        CreatePersonCommandHandler cmnd = new CreatePersonCommandHandler(azureSubscription());
//
//        commandMap.put(CommandEnum.CREATE, cmnd);
//
//        return commandMap;

//    }

    @Bean
    public Subscription azureSubscription() {
        return new Subscription();
    }

    @Bean
    @Primary
    public EntryService entryServiceBean(){
        EntryService azureCognitiveServicesEntryService =
                new AzureCognitiveServicesEntryService( faceApiServiceBean());

        return azureCognitiveServicesEntryService;
    }

}
