package study.supercodingboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.supercodingboard.dto.PostDto;
import study.supercodingboard.entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(String Author);
}
