package com.agung.project.service;

import com.agung.project.model.Rest;
import com.agung.project.model.RestResult;

import java.util.List;

public interface RestService {
    List<RestResult> getAll();
    RestResult getRestById(int id);
    void addRest(Rest rest);
    void updateRest(Rest rest, int id);
    void deleteRestById(int id);
    int lastestInput();

}
