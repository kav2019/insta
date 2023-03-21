package ru.kovshov.insta.facade;

import org.springframework.stereotype.Component;
import ru.kovshov.insta.dto.CommentDTO;
import ru.kovshov.insta.model.Comment;

@Component
public class CommentFacade {
    public CommentDTO commentToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setUsername(comment.getUsername());
        commentDTO.setMessage(comment.getMessage());

        return commentDTO;
    }
}
