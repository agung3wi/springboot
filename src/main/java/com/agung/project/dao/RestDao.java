package com.agung.project.dao;

import com.agung.project.model.Rest;
import com.agung.project.model.RestResult;

import java.util.List;

public interface RestDao {
    List<RestResult> getAll();
    RestResult getRestById(int id);
    void addRest(Rest rest);
    void updateRest(Rest rest, int id);
    void deleteRestById(int id);
    int lastestInput();

}