package com.back;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class PersonRepository {

    private final int version;

    public int count() {

        System.out.println("version = " + version);
        return 3;
    }

}
