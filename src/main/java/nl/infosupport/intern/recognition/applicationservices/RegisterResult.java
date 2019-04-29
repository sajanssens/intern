package nl.infosupport.intern.recognition.applicationservices;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResult {

    private boolean unique;
    private String name;
    private String personId;
    private String reason;

    public RegisterResult(boolean unique, String name,String personId, String reason) {
        this.unique = unique;
        this.name = name;
        this.personId = personId;
        this.reason = reason;
    }

}
