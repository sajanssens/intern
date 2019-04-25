package nl.infosupport.intern.recognition.domainservices.actions;

import nl.infosupport.intern.recognition.domainservices.actions.person.Command;

public interface CommandHandler<TCommand extends Command> {

    void handle(TCommand command);
    String getResult();
}
