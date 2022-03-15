package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩(){
        //when
        String body = this.restTemplate.getForObject("/", String.class);

        //then
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");

        // HTML도 결국 규칙이 있는 문자열입니다. TestRestTemplate를 통해 "/"로 호출했을 때
        // index.mustache에 포함된 코드들이 있는지 확인하면 됩니다. 전체 코드를 다 검증할 필요는 없으니,
        // "스프링 부트로 시작하는 웹 서비스" 문자열이 포함되어 있는지만 비교합니다.
    }
}
