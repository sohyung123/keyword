package com.example.keyword.service;

import com.example.keyword.dto.KeywordListDto;
import com.example.keyword.entity.Keyword;
import com.example.keyword.repository.KeywordRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class KeywordListServiceTest {

    @InjectMocks
    private KeywordListService keywordListService;

    @Mock
    private KeywordRepository keywordRepository;

    @DisplayName("getKeywordList 메서드 테스트")
    @Test
    void getKeywordListTest() {
        //given
        KeywordListDto keywordListDto = new KeywordListDto();
        ArrayList<Keyword> list = new ArrayList<>();
        list.add(Keyword.builder().keywordName("동작구").count(100).build());
        list.add(Keyword.builder().keywordName("잠실").count(400).build());
        list.add(Keyword.builder().keywordName("수서동").count(20).build());
        list.add(Keyword.builder().keywordName("판교").count(50).build());
        list.add(Keyword.builder().keywordName("치킨").count(110).build());
        Mockito.doReturn(list).when(keywordRepository).findAll();

        //when
        KeywordListDto resultList = keywordListService.getKeywordList();

        //then
        Assertions.assertThat(resultList).isNotNull();
        Assertions.assertThat(resultList.getKeywords().get(0).getKeyword()).isEqualTo("잠실");
        Assertions.assertThat(resultList.getKeywords().get(1).getKeyword()).isEqualTo("치킨");
        Assertions.assertThat(resultList.getKeywords().get(2).getKeyword()).isEqualTo("동작구");
        Assertions.assertThat(resultList.getKeywords().get(3).getKeyword()).isEqualTo("판교");
        Assertions.assertThat(resultList.getKeywords().get(4).getKeyword()).isEqualTo("수서동");

    }
}