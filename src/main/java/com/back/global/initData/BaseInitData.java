package com.back.global.initData;

import com.back.domain.member.entity.Member;
import com.back.domain.member.service.MemberService;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    @Autowired
    @Lazy
    private BaseInitData self;
    private final MemberService memberService;
    private final WiseSayingService wiseSayingService;

    @Bean
    ApplicationRunner initDataRunner() {
        return args -> {
            self.work1();
            self.work2();
            work3();
        };
    }

    @Transactional
    void work1() {
        if(memberService.count() > 0) return;

        Member member1 = memberService.join("systemUser", "시스템");
        Member member2 = memberService.join("adminUser", "관리자");
        Member member3 = memberService.join("user1", "유저1");
        Member member4 = memberService.join("user2", "유저2");
        Member member5 = memberService.join("user3", "유저3");
    }

    @Transactional
    void work2() {
        Member member4 = memberService.findByUserName("user2").get();

        member4.setNickname("new user2");   // jpa가 변경감지(Dirty Checking)해서 update쿼리 날림
    }


    void work3() {

        if(wiseSayingService.count() > 0) {
            return;
        }

        wiseSayingService.write("명언1", "작가1");
        wiseSayingService.write("명언2", "작가2");
        wiseSayingService.write("명언3", "작가3");
        wiseSayingService.write("명언4", "작가4");
        wiseSayingService.write("명언5", "작가5");
    }
}
