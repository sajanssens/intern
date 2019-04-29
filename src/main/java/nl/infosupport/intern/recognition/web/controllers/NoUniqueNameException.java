package nl.infosupport.intern.recognition.web.controllers;

public class NoUniqueNameException extends RuntimeException {

    private final String name;

    public NoUniqueNameException(String name) {
        super("Name is not unique: " + name);
        this.name = name;
    }
}
