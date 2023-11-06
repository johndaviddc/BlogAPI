package dave.dev.blogapi.service;

import dave.dev.blogapi.exception.ResourceNotFoundException;
import dave.dev.blogapi.model.Comment;
import dave.dev.blogapi.model.Post;
import dave.dev.blogapi.repository.CommentRepository;
import dave.dev.blogapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public Comment createComment(Long postId, Comment comment) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            comment.setPost(post);
            return commentRepository.save(comment);
        } else {
            throw new ResourceNotFoundException("Post with ID " + postId + " not found.");
        }
    }
}
