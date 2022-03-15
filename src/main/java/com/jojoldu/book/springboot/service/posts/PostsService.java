package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;

        // update 기능에서 데이터베이스에 쿼리를 날리는 부분이 없는 이유는 JPA의 영속성 컨텍스트 때문입니다. 영속성 컨텍스트란, 엔티티를 영구 저장하는 환경입니다.
        // JPA의 엔티티 메니저가 활성화 된 상태로(Sprint Data Jpa를 쓴다면 기본옵션) 트랜젝션 안에서 데이터베이스를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태입니다.
        // 이 상태에서 해당 데이터의 값을 변경하면 트렌젝션이 끝나는 시점에 해당 테이블에 변경분을 반영합니다.
        // 즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다는 것이죠. 이 개념을 더티체킹이라고 합니다.
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }

    // readOnly를 주면 트랜잭션 범위는 유지하되, 조회기능만 남겨두어 조회 속도가 개선됩니다.
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts);
    }
}
