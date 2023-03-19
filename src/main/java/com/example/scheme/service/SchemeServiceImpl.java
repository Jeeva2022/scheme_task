package com.example.scheme.service;

import com.example.scheme.entity.Scheme;
import com.example.scheme.entity.model.Dataum;
import com.example.scheme.entity.model.Root;
import com.example.scheme.exception.InvalidInputException;
import com.example.scheme.exception.ResourceNotFoundException;
import com.example.scheme.repository.SchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchemeServiceImpl implements SchemeService {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SchemeRepository schemeRepository;

    public String addScheme(List<Scheme> schemes) {

        schemeRepository.saveAll(schemes);

        return "Successfully Stored";

    }

    public List<Scheme> findSchemeByName(String schemeName) throws ResourceNotFoundException {

        //List<Scheme> scheme = Optional.of(schemeRepository.findAll().stream().filter(x -> schemeName.equalsIgnoreCase(x.getSchemeName())).collect(Collectors.toList())).orElseThrow(() -> new ResourceNotFoundException("No such scheme with this name: " + schemeName));

        List<Scheme> scheme = Optional.of(schemeRepository.findBySchemeName(schemeName)).orElse(null);

        if(scheme.isEmpty()) {
        throw new ResourceNotFoundException("No scheme with this name: "+schemeName);
        }else {
        return scheme; }

    }


    public Root fetchSchemeByFilter(long schemeId, String filter) throws ResourceNotFoundException{

        String url = "https://api.mfapi.in/mf/" + schemeId;

        Root response = restTemplate.getForObject(url, Root.class);

        if(response.getMeta().getScheme_name()==null){
            throw new ResourceNotFoundException("No Scheme with this ID: "+schemeId);
        }

        List<Dataum> dataList = filterByDate(response.getData(), filter);


        Root root = new Root();

        root.setMeta(response.getMeta());
        root.setData(dataList);

        if(root!=null) {
            return root;
        }else {
            throw new ResourceNotFoundException("Fetching scheme by Date filter is Not containg Datas: ");
        }


    }


    public List<Dataum> filterByDate(List<Dataum> data, String filter) {

        List<Dataum> filteredData = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();

        filteredData = switch (filter) {
            case "1M" -> data.stream().filter(d ->
                            LocalDate.parse(d.getDate(), DATE_FORMAT).isAfter(currentDate.minusDays(31)))
                    .collect(Collectors.toList());

            case "1W" -> data.stream().filter(d ->
                            LocalDate.parse(d.getDate(), DATE_FORMAT).isAfter(currentDate.minusDays(7)))
                    .collect(Collectors.toList());

            case "1Y" -> data.stream().filter(d ->
                            LocalDate.parse(d.getDate(), DATE_FORMAT).isAfter(currentDate.minusYears(1)))
                    .collect(Collectors.toList());
            case "5Y" -> data.stream().filter(d ->
                            LocalDate.parse(d.getDate(), DATE_FORMAT).isAfter(currentDate.minusYears(5)))
                    .collect(Collectors.toList());
            default -> throw new InvalidInputException("Try with : 1M,1W,1Y,5Y, "+"Invalid Input Exception : "+filter);
        };

        return filteredData;
    }


}
