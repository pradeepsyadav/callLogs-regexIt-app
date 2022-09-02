package com.example.calllogdelete.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import ru.skornei.restserver.server.converter.BaseConverter;

public class JsonConverter implements BaseConverter {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte[] writeValueAsBytes(Object objectToSerailize) {
        try {
            return mapper.writeValueAsBytes(objectToSerailize);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T writeValue(byte[] serialzedBytes, Class<T> deserializedObj) {
        try {
            return mapper.readValue(serialzedBytes, deserializedObj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
