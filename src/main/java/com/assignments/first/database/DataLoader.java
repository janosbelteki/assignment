package com.assignments.first.database;

import org.springframework.stereotype.Component;

public interface DataLoader {
    Boolean loadUserDataFromJson(String languageCode, AsdRequest saveCompetitionData);
}

@Component
class DefaultDataLoader implements DataLoader {
    @Override
    public Boolean loadUserDataFromJson(String languageCode, AsdRequest saveCompetitionData) {
        // TODO: Implement the readEventTxtIntoPreview method
        // Your logic goes here
        return null;
    }
}
