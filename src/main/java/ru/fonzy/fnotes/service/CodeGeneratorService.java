package ru.fonzy.fnotes.service;

import java.util.UUID;

public interface CodeGeneratorService {

    default String getUniqueCode(){
        return UUID.randomUUID().toString();
    }
}
