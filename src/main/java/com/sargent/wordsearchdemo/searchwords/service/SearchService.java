package com.sargent.wordsearchdemo.searchwords.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;


@Service
public class SearchService {
    private List<String> matchedFileList = null;

    public List<String> searchWords(String word, String basePath) {

        matchedFileList = new ArrayList<>();

        File directory = new File(basePath);

        if (directory == null || !directory.exists()) {
            return null;
        }
        return searchWordsInAllDirectories(directory, word, matchedFileList);
    }


    public List<String> searchWordsInAllDirectories(File directory, String searchWords, List matchedFileList) {

        File[] filesAndDirs = directory.listFiles();

        for (File file : filesAndDirs) {

            if (file.isFile()) {
                findExactWords(file, searchWords, matchedFileList);

            } else {
                searchWordsInAllDirectories(file, searchWords, matchedFileList);
            }
        }
        return matchedFileList;
    }

    private void findExactWords(File file, String searchWords, List matchedFileList) {
        Scanner scanFile = null;
        String wordsArr[] = searchWords.split(" ");
        Integer matchCount = 0;

        try {
            for (String searchWord : wordsArr) {
                scanFile = new Scanner(file);
                while (null != scanFile.findWithinHorizon("(?i)\\b" + searchWord.trim() + "\\b", 0)) {
                    MatchResult mr = scanFile.match();
                    matchCount++;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Search File Not Found !!!!! ");
            e.printStackTrace();
        }
        catch (Exception e){
            System.err.println("Generic Exception:"+e.getMessage());
        }
        finally {
            scanFile.close();
        }
        if (matchCount == wordsArr.length)
            matchedFileList.add(file.getName());
    }
}

