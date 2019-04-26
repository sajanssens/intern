package nl.infosupport.intern.recognition.domain;

import lombok.*;
import nl.infosupport.intern.recognition.domainservices.azure.actions.group.TrainGroupCommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Person {

    private static Logger logger = LoggerFactory.getLogger(TrainGroupCommandHandler.class);

    @Id
    private String name;

    private String faceId;

    public Person(String name, String faceId) {
        this.name = name;
        this.faceId = faceId;
    }

    public void setName(String name){
        logger.debug("set name: {}", name);
        this.name = name;
    }

    public void setFaceId(String faceId){
        logger.debug("set faceId: {}", faceId);
        this.faceId = faceId;
    }
}
