package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // 보통 ibatis나 MyBatis 등에서 Dao라고 물리는 DB Layer 접근자입니다.
    // JPA에선 Repository라고 부르며 인터페이스로 생성합니다.
    // 단순히 인터페이스를 생성 후, JpaRepository<Entity클래스, PK타입>를 상속하면 기본적인 CRUD메소드가 자동으로 생성됩니다.

    // @Repository를 추가할 필요도 없습니다. 여기서 주의할 점은 Entity 클래스와 기본 Entity Repository는 함께 위치해야 하는 점입니다.
    // 둘은 아주 밀접한 관계고, Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수가 없습니다.

    // SpirngDataJpa에서 제공하지 않는 메소드는 아래처럼 쿼리로 작성해도 된다. 아래 코드는 JPA에서 제공하는 기본 메소드만으로 해결이 가능하다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
