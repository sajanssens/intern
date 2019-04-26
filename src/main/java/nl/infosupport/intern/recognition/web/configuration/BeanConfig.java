package nl.infosupport.intern.recognition.web.configuration;

import nl.infosupport.intern.recognition.domainservices.azure.actions.CommandType;
import nl.infosupport.intern.recognition.domainservices.azure.actions.CommandHandler;
import nl.infosupport.intern.recognition.domainservices.azure.actions.Subscription;
import nl.infosupport.intern.recognition.domainservices.azure.actions.Command;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.add.AddFaceCommandHandler;
import nl.infosupport.intern.recognition.domainservices.azure.actions.person.create.CreatePersonCommandHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.AbstractMap;
import java.util.Map;

import static nl.infosupport.intern.recognition.domainservices.azure.actions.CommandType.ADDFACE;
import static nl.infosupport.intern.recognition.domainservices.azure.actions.CommandType.CREATE;

@Configuration
public class BeanConfig {

    @Value("${azure.subscription}")
    private String subscription;

    @Bean
    public Map<CommandType, CommandHandler<Command>> getFaceApiCommands(CreatePersonCommandHandler createPersonCommandHandler, AddFaceCommandHandler addFaceCommandHandler) {

        return
                Map.ofEntries(
                        new AbstractMap.SimpleEntry<CommandType, CommandHandler<Command>>(CREATE, (CommandHandler) createPersonCommandHandler),
                        new AbstractMap.SimpleEntry<CommandType, CommandHandler<Command>>(ADDFACE, (CommandHandler) addFaceCommandHandler));
    }

    @Bean
    public Subscription getAzureSubscription() {
        return new Subscription(subscription);
    }

}
