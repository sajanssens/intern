package nl.infosupport.intern.recognition.applicationservices;

import lombok.Getter;

@Getter
public class RegisterResult {

    private final boolean succeed;
    private final String name;
    private final String reason;

    public RegisterResult(boolean succeed, String name, String reason) {
        this.succeed = succeed;
        this.name = name;
        this.reason = reason;
    }

}
