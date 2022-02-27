package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// JPA Auditing 활성화
@EnableJpaAuditing
// 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정됩니다.
// 특히 이 어노테이션이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야 합니다.
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        // 아래 run 함수로 인해 내장 WAS(Web Application Server)를 실행합니다.
        // 내장 WAS란 별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS를 실행하는 것을 이야기 합니다.
        // 이렇게 되면 항상 서버에 톰켓을 설치할 필요가 없게되고, 스프링부트로 만들어진 Jar 파일(실행 가능한 JAVA 패키징 파일)로 실행하면 됩니다.
        // 스프링 부트에서는 내장 WAS의 사용을 권장하고 있습니다. '언제 어디서나 같은 환경에서 스프링 부트를 배포' 할 수 있기 때문입니다.
        SpringApplication.run(Application.class, args);
    }
}
