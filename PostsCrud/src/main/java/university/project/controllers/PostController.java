package university.project.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import university.project.dto.PostDto;
import university.project.entity.PostEntity;
import university.project.service.PostService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostEntity>> getAllPosts() {
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostEntity> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findPostById(id));
    }

    @GetMapping("/{id}/picture")
    public ResponseEntity<byte[]> downloadImageFromFileSystem(@PathVariable Long id) throws IOException {
        byte[] imageBytes = postService.downloadImageFromFileSystem(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageBytes);
    }

    @PostMapping
    public ResponseEntity<PostEntity> createPost(@RequestParam Long userId,
                                                 @RequestParam String title,
                                                 @RequestParam String body,
                                                 @RequestParam MultipartFile image) throws IOException {
        PostDto postDto = new PostDto(userId, title, body);
        return new ResponseEntity<>(postService.createPost(postDto, image), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PostEntity> updatePost(@RequestBody Long postId,
                                                 @RequestParam Long userId,
                                                 @RequestParam String title,
                                                 @RequestParam String body,
                                                 @RequestParam MultipartFile photoFile) throws IOException {
        PostDto postDto = new PostDto(userId, title, body);
        return new ResponseEntity<>(postService.updatePost(postId, postDto, photoFile), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<PostEntity> deletePost(@RequestBody Long postId) {
        return new ResponseEntity<>(postService.deletePost(postId), HttpStatus.NO_CONTENT);
    }
}
