package com.sargent.wordsearchdemo.searchwords.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.runner.RunWith;

import java.io.File;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SearchServiceTests {

    @Autowired
    private SearchService searchService;

    private String basDirectoryPath;

    @Before
    public void setup() {
        String path = "src/test/resources";
        File file = new File(path);
        basDirectoryPath = file.getAbsolutePath();
    }

    @Test
    public void searchOneWordTest() {
        List<String> result = searchService.searchWords("credence", basDirectoryPath);
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo("file_3");
    }

    @Test
    public void searchTwoWordsTest() {
        List<String> result = searchService.searchWords("poor inferior", basDirectoryPath);
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0)).isEqualTo("file_3");
        assertThat(result.get(1)).isEqualTo("file_1");
    }

    @Test
    public void searchThreeWordsTest() {
        List<String> result = searchService.searchWords("revere pandemic outstanding", basDirectoryPath);
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo("file_2");
    }

    @Test
    public void noWordMatchTest() {
        List<String> result = searchService.searchWords("fathomid", basDirectoryPath);
        assertThat(result).isEmpty();
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void matchInAllFilesTest() {
        List<String> result = searchService.searchWords("inferior", basDirectoryPath);
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0)).isEqualTo("file_2");
        assertThat(result.get(1)).isEqualTo("file_3");
        assertThat(result.get(2)).isEqualTo("file_1");
    }

    @Test
    public void caseInsensitiveSearchTest() {
        List<String> result = searchService.searchWords("InfErIor", basDirectoryPath);
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0)).isEqualTo("file_2");
        assertThat(result.get(1)).isEqualTo("file_3");
        assertThat(result.get(2)).isEqualTo("file_1");
    }

}