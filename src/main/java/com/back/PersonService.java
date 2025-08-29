package com.back;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(@Qualifier("personRepository") PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public int count(){
        return personRepository.count();
    }
}
