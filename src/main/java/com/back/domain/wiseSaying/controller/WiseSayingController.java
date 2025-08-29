package com.back.domain.wiseSaying.controller;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class WiseSayingController {

   private final WiseSayingService wiseSayingService;

    @GetMapping("/wiseSayings/write")
    @ResponseBody
    public String write(
            String content, String author
    ){

        WiseSaying wiseSaying = wiseSayingService.write(content, author);

        return"%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId());
    }

    @GetMapping("/wiseSayings/list")
    @ResponseBody
    public String list(){
        String wiseSayings = wiseSayingList.stream()
                .map(w -> "<li>%s / %s / %s</li>".formatted(w.getId(), w.getContent(), w.getAuthor()))
                .collect(Collectors.joining("\n"));

        return """
                <ul>
                %s
                </ul>
                """.formatted(wiseSayings);
    }


    @GetMapping("/wiseSayings/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable int id){
        WiseSaying wiseSaying = findById(id);
        wiseSayingList.remove(wiseSaying);

        return "%d번 명언이 삭제되었습니다.".formatted(id);
    }


    @GetMapping("/wiseSayings/modify/{id}")
    @ResponseBody
    public String modify(
            @PathVariable int id,
            @RequestParam(defaultValue = "기본값") String content,
            @RequestParam(defaultValue = "기본값") String author
    ){
        WiseSaying wiseSaying = findById(id);
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);

        return "%d번 명언이 수정되었습니다.".formatted(wiseSaying.getId());
    }

    @GetMapping("/wiseSayings/{id}")
    @ResponseBody
    public String detail(@PathVariable int id){
        WiseSaying wiseSaying = findById(id);

        return "<h1>%s</h1>\n<div>%s</div>\n<div>%s</div>"
                .formatted(wiseSaying.getId(), wiseSaying.getContent(), wiseSaying.getAuthor());
    }

    private WiseSaying findById(int id) {
        return wiseSayingList.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
