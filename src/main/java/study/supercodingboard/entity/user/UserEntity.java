package study.supercodingboard.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import study.supercodingboard.entity.Comment;
import study.supercodingboard.entity.Post;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String role;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

}
