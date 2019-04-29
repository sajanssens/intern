package nl.infosupport.intern.recognition.applicationservices;

import nl.infosupport.intern.recognition.web.models.Person;

public interface EntryService {

    Person.SavedPerson register(String name);
}
