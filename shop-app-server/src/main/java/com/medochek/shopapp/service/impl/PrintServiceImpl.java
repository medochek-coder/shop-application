package com.medochek.shopapp.service.impl;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.medochek.shopapp.service.PrintService;

@Service
public class PrintServiceImpl implements PrintService {

    @Override
    public String print(Object printableObject) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(printableObject);
    }
}
