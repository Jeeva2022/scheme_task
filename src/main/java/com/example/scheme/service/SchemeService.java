package com.example.scheme.service;

import com.example.scheme.entity.Scheme;
import com.example.scheme.entity.model.Dataum;
import com.example.scheme.entity.model.Root;
import com.example.scheme.exception.ResourceNotFoundException;

import java.util.List;

public interface SchemeService {

    String addScheme(List<Scheme> scheme);

    List<Scheme> findSchemeByName(String schemeName) throws ResourceNotFoundException;

    Root fetchSchemeByFilter(long schemeId, String filter) throws ResourceNotFoundException;

    List<Dataum> filterByDate(List<Dataum> data, String filter);

}
