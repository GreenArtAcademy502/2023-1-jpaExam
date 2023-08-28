package com.green.jpaexam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

@Slf4j
@SpringBootTest
public class PageableTest {

    @Test
    public void test() {
        Pageable p = Pageable.ofSize(30);
        Pageable p2 = p.withPage(2);
        log.info("p : {}", p);
        log.info("p2 : {}", p2);
    }
}
