package com.back.domain.wiseSaying.service;


import com.back.domain.wiseSaying.entity.WiseSaying;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WiseSayingService {

    private List<WiseSaying> wiseSayingList = new ArrayList<>() {{
        add(new WiseSaying(1, "삶이 있는 한 희망은 있다.", "키케로"));
        add(new WiseSaying(2, "하루에 3시간을 걸으면 7년 후에 지구를 한 바퀴 돌 수 있다.", "사무엘 존슨"));
        add(new WiseSaying(3, "언제나 현재에 집중할수 있다면 행복할것이다.", "파울로 코엘료"));
        add(new WiseSaying(4, "행복은 습관이다, 그것을 몸에 지니라.", "허버트"));
        add(new WiseSaying(5, "절대 어제를 후회하지 마라, 인생은 오늘의 나로 내일을 만든다.", "L.론허바드"));
    }};



    private int lastId = 0;

    public WiseSaying write(String content, String author) {

        if(content == null || content.trim().length() == 0) {
            throw new RuntimeException("명언을 입력해주세요.");
        }

        if(author == null || author.trim().length() == 0) {
            throw new RuntimeException("작가를 입력해주세요.");
        }


        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);
        wiseSayingList.add(wiseSaying);

        return wiseSaying;
    }
}
