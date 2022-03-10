package com.sargent.wordsearchdemo.searchwords.controller;

import com.sargent.wordsearchdemo.searchwords.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    private static String basDirectoryPath = "C:\\Users\\HiranmoyBiswas\\Desktop\\HB_Profile\\Test";

    @GetMapping(path = "/{word}", produces = "application/json")
    public List<String> getMatchingFileNames(@PathVariable String word) {
        return searchService.searchWords(word, basDirectoryPath);
    }
}
