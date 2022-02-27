package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.(92)

// 롬복 어노테이션 : 클래스 내 모든 필드의 Getter 메소드를 자동생성
@Getter
// 롬복 어노테이션 : 기본 생성자 자동 추가
@NoArgsConstructor
// JPA 어노테이션 : 테이블과 링크될 클레스임을 나타냅니다.
// 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블이름을 매칭합니다.
// ex. SalesManager.java -> sales_manager table
@Entity
public class Posts extends BaseTimeEntity {

    // 테이블의 PK필드를 나타냅니다.
    @Id
    // PK의 생성 규칙을 나타냅니다. 스트링부트 2.0에서는 아이덴티티 옵션을 추가해야만 auto_increment가 됩니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 모든 필드는 모두 칼럼이 됩니다.
    // 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용합니다.
    // 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나(ex. title)
    // 타입을 TEXT로 변경하고 싶거나(ex. content) 등의 경우에 사용합니다.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 롬복 어노테이션 : 해당 클래스의 빌더 패턴 클래스를 생성하며, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함한다.
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}


// 웬만하면 Entity의 PK는 Long타입의 Auto_increment를 추천합니다.(MySQL기준으로 이렇게 하면 bigint 타입이 됩니다.)
// 주민등록번호와 같이 비즈니스상 유니크 키나, 여러 키를 조합한 복합키로 PK를 잡을 경우 난감한 상황이 종종 발생합니다.
// (1) FK를 맺을 때 다른 테이블에서 복합키 전부를 갖고 있거나, 중간 테이블을 하나 더 둬야하는 상황이 발생합니다.
// (2) 인덱스에 좋은 영향을 끼치지 못합니다.
// (3) 유니크한 조건이 변경될 경우 PK전체를 수정해야 하는 일이 발생합니다.
// 주민번호, 복합키 등은 유니크 키로 별도로 추가하시는 것을 추천드립니다.

// 서비스 초기 구축 단계에선 테이블 설계가 빈번하게 변경되는데, 이때 롬복의 어노테이션들은 코드 변경량을 최소화시켜주기 대문에 적극적으로 사용합니다.