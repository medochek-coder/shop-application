package com.medochek.shopapp.service.impl;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medochek.shopapp.service.PrintService;

@Service
public class PrintServiceImpl implements PrintService {

    @Override
    public String print(Object printableObject) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(printableObject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
