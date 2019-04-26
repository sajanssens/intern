package nl.infosupport.intern.recognition.domainservices.azure.actions;

public interface CommandHandler<C extends Command> {

    void handle(C command);
    String getResult();
}
