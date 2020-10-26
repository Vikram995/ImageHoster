package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.repository.CommentRepository;
import ImageHoster.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    //The method calls the getAllComments() method in the Repository and passes the id of the image to be fetched
    public List<Comment> getAllComments(Integer imageId) {
        return commentRepository.getAllComments(imageId);
    }

    //The method calls the uploadComment() method in the Repository and passes the comment to be persisted in the database
    public void uploadComment(Comment comment) {
        commentRepository.uploadComment(comment);
    }


}
