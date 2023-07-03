package com.assignments.first.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorObject {
    public ErrorObject(String message) {
        Map<String, String> labelToMessageMap = new HashMap<>();
        labelToMessageMap.put("label", message);

        List<Map<String, String>> messagesList = new ArrayList<>();
        messagesList.add(labelToMessageMap);
    }
}
