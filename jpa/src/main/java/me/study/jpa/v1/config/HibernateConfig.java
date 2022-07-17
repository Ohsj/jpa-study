package me.study.jpa.v1.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    /**
     * 지연로딩 객체의 경우 실제 엔티티 대신에 프록시가 존재한다.
     * jackson 라이브러리는 기본적으로 이 프록시 객체를 json으로 어떻게 생생해야 하는지 모름 -> 예외 발생
     * Hibernate5Module을 스프링 빈으로 등록하면 해결
     */

//    @Bean
//    Hibernate5Module hibernate5Module() {
//         기본적으로 초기화 된 프록시 객체만 노출, 초기화 되지 않은 프록시 객체는 노출 안함.
//        return new Hibernate5Module();
//    }

    @Bean
    Hibernate5Module hibernate5Module() {
        Hibernate5Module hibernate5Module = new Hibernate5Module();

        // 강제 지연 로딩 설정
        // 해당 옵션을 키면 양방향 연관관계를 계속 로딩하게 된다. 따라서 @JsonIgnore 옵션을 한곳에 주어야한다.
        hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
        return hibernate5Module;
    }
}
