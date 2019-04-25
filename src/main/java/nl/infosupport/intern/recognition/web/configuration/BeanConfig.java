package nl.infosupport.intern.recognition.web.configuration;

import nl.infosupport.intern.recognition.domainservices.actions.CommandEnum;
import nl.infosupport.intern.recognition.domainservices.actions.CommandHandler;
import nl.infosupport.intern.recognition.domainservices.actions.Subscription;
import nl.infosupport.intern.recognition.domainservices.actions.person.Command;
import nl.infosupport.intern.recognition.domainservices.actions.person.add.AddFaceCommandHandler;
import nl.infosupport.intern.recognition.domainservices.actions.person.create.CreatePersonCommandHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.AbstractMap;
import java.util.Map;

import static nl.infosupport.intern.recognition.domainservices.actions.CommandEnum.ADDFACE;
import static nl.infosupport.intern.recognition.domainservices.actions.CommandEnum.CREATE;

@Configuration
public class BeanConfig {

    @Value("${azure.subscription}")
    private String subscription;

//    @Bean
//    FaceApiService getFaceApiService(Map<CommandEnum, CommandHandler<Command>> commands){
//        return new FaceApiService(commands);
//    }

    @Bean
    public Map<CommandEnum, CommandHandler<Command>> getFaceApiCommands(CreatePersonCommandHandler createPersonCommandHandler, AddFaceCommandHandler addFaceCommandHandler) {

        return
                Map.ofEntries(
                        new AbstractMap.SimpleEntry<CommandEnum, CommandHandler<Command>>(CREATE, (CommandHandler) createPersonCommandHandler),
                        new AbstractMap.SimpleEntry<CommandEnum, CommandHandler<Command>>(ADDFACE, (CommandHandler) addFaceCommandHandler));

    }

    @Bean
    public Subscription getAzureSubscription() {
        return new Subscription(subscription);
    }

}
