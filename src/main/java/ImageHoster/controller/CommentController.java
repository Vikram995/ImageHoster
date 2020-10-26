package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    //This controller method is called when the request pattern is of type 'images/{imageId}/{imageTitle}/comments' and also the incoming request is of POST type
    //The method receives all the details of the comment to be stored in the database, and now the comment will be sent to the business logic to be persisted in the database
    //After you get the comment, set the user of the image by getting the logged in user from the Http Session
    //Convert the image to Base64 format and store it as a string in the 'imageFile' attribute
    //Set the date on which the image is posted
    //Set the image of the comment based on the Image retrieved from database. on which the image is posted
    //After storing the comment, this method directs to the image displaying all the comments

    //Get the 'comment' request parameter using @RequestParam annotation which is just a string of Comment Text
    //Get the imageId, imageTitle from path using @PathVariable annotation

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@RequestParam("comment") String commentText, @PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, Comment newComment, HttpSession session, Model model) throws IOException {

        Image image = imageService.getImage(imageId);
        User user = (User) session.getAttribute("loggeduser");
        newComment.setText(commentText);
        newComment.setUser(user);
        newComment.setCreatedDate(new Date());
        newComment.setImage(image);
        commentService.uploadComment(newComment);

        return "redirect:/images/" + imageId + "/" + imageTitle;
    }
}
