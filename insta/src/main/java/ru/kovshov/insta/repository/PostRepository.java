package ru.kovshov.insta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kovshov.insta.model.Post;
import ru.kovshov.insta.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUserOrderByCreatedDateDesc (User user);

    List<Post> findAllByOrderByCreatedDateDesc ();

    Optional<Post> findPostByIdAndUser (Long postId, User user);
}
