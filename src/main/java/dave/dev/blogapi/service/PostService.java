package dave.dev.blogapi.service;

import dave.dev.blogapi.model.Post;
import dave.dev.blogapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, Post updatedPost) {
        if (postRepository.existsById(postId)) {
            updatedPost.setId(postId);
            return postRepository.save(updatedPost);
        } else {
            throw new ResourceNotFoundException("Post with ID " + postId + " not found.");
        }
    }

    public void deletePost(Long postId) {
        if (postRepository.existsById(postId)) {
            postRepository.deleteById(postId);
        } else {
            throw new ResourceNotFoundException("Post with ID " + postId + " not found.");
        }
    }
}
