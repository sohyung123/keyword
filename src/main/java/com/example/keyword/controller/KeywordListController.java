package com.example.keyword.controller;

import com.example.keyword.dto.KeywordListDto;
import com.example.keyword.service.KeywordListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v2")
public class KeywordListController {

    private final KeywordListService keywordListService;

    @GetMapping("keyword")
    public KeywordListDto getKeywordList() {
        KeywordListDto keywordListDto = keywordListService.getKeywordList();
        return keywordListDto;
    }

}
