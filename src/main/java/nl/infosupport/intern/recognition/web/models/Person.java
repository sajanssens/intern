package nl.infosupport.intern.recognition.web.models;

import lombok.Data;

public class Person {

    @Data
    static public class SavedPerson {

        private final String name;
        private final String id;

    }

    @Data
    static public class NewPerson {

        private final String name;
    }
}

