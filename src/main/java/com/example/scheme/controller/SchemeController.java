package com.example.scheme.controller;

import com.example.scheme.entity.RequestPayload;
import com.example.scheme.entity.Scheme;
import com.example.scheme.entity.model.Root;
import com.example.scheme.exception.ResourceNotFoundException;
import com.example.scheme.service.SchemeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
// scheme 3 try completed
import java.util.List;

@RestController
@RequestMapping("/mf")
public class SchemeController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SchemeServiceImpl schemeServiceImpl;

    @PostMapping("/postSchemes")
    public String addScheme() {

        String url = "https://api.mfapi.in/mf";
        ResponseEntity<List<Scheme>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Scheme>>() {
        });
        List<Scheme> schemes = response.getBody();
        return schemeServiceImpl.addScheme(schemes);

    }

    @GetMapping("/getSchemeByName/{schemeName}")
    public List<Scheme> findSchemeByName(@PathVariable String schemeName) throws ResourceNotFoundException{

        return schemeServiceImpl.findSchemeByName(schemeName);

    }


    @PostMapping("/filterScheme")
    public Root fetchSchemeByFilter(@RequestBody RequestPayload requestPayload) throws ResourceNotFoundException {

        long schemeId = requestPayload.getRequest().getSchemeId();
        String filter = requestPayload.getRequest().getFilter();

        return schemeServiceImpl.fetchSchemeByFilter(schemeId, filter);

    }


}
