package study.supercodingboard.repository;

import study.supercodingboard.model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository
{
    private final String url = "jdbc:mysql://localhost:3306/super_board";
    private final String username = "root";
    private final String password = "12341234";

    // 댓글 저장 메서드
    public void save(Comment comment)
    {
        String sql = "INSERT INTO comments (content, author, post_id) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password); // sql 연결
             PreparedStatement input = connection.prepareStatement(sql))  // 값을 넣도록하자
        {
            input.setString(1, comment.getContent()); // VALUE (?, ? , ? ) 의 각 인덱스임
            input.setString(2, comment.getAuthor());
            input.setLong(3, comment.getPostId());
            input.executeUpdate(); // 쿼리 실행
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Comment> findAll()
    {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement input = connection.prepareStatement(sql);
             ResultSet resultSet = input.executeQuery())
        {
            while (resultSet.next()) // 쿼리에서 다음행이 없으면 false
            {
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id")); // id 값 설정
                comment.setContent(resultSet.getString("content")); // content 값 설정
                comment.setAuthor(resultSet.getString("author")); // author 값 설정
                comment.setPostId(resultSet.getLong("post_id")); // post_id 값 설정
                comment.setCreatedAt(resultSet.getString("created_at"));

                comments.add(comment); // 리스트에 댓글 추가

            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return comments; // 댓글 목록 반환

    }

    public void updateContent(Long commentId, String content)
    {
        String sql = "UPDATE comments SET content = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement input = connection.prepareStatement(sql)) {
            input.setString(1, content); // 수정할 content 설정
            input.setLong(2, commentId); // 수정할 댓글 ID 설정
            input.executeUpdate(); // 쿼리 실행
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContent(Long commentId)
    {
        String sql = "DELETE FROM comments WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement input = connection.prepareStatement(sql)) {
            input.setLong(1, commentId); // 삭제할 댓글 ID 설정
            input.executeUpdate(); // 쿼리 실행
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
