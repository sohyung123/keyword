package com.example.keyword.service;

import com.example.keyword.dto.KeywordListDto;
import com.example.keyword.entity.Keyword;
import com.example.keyword.repository.KeywordRepository;
import com.example.keyword.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class KeywordListService {

    private final KeywordRepository keywordRepository;

    public KeywordListDto getKeywordList() {
        try{
            List<Keyword> keywordList = keywordRepository.findAll();
            CopyOnWriteArrayList<KeywordListDto.KeywordDto> resultList = keywordList.stream()
                    .sorted(Comparator.comparing(Keyword::getCount).reversed())
                    .limit(Constants.KEYWORDLISTSIZE)
                    .map(i->new KeywordListDto.KeywordDto(i.getKeywordName(),new AtomicInteger(i.getCount()).get()))
                    .collect(Collectors.toCollection(CopyOnWriteArrayList::new));

            KeywordListDto keywordListDto = new KeywordListDto();
            keywordListDto.setKeywords(resultList);
            return keywordListDto;
        }
        catch (RuntimeException e) {
            log.error("keyword List 조회 오류",e.getMessage(),e);
            return null;
        }
    }

}
