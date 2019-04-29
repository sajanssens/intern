package nl.infosupport.intern.recognition.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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

    private static Logger logger = LoggerFactory.getLogger(Person.class);

    @Id
    private String name;

    private String personId;

    public Person(String name, String personId) {
        this.name = name;
        this.personId = personId;
    }

    public void setName(String name){
        logger.debug("set name: {}", name);
        this.name = name;
    }

    public void setPersonId(String personId){
        logger.debug("set personId: {}", personId);
        this.personId = personId;
    }
}
