package university.project.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import university.project.dto.PostDto;
import university.project.entity.PostEntity;
import university.project.repository.PostRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public List<PostEntity> findAllPosts(LocalDateTime from, LocalDateTime to) {
        if (from != null && to != null) {
            return postRepository.findAllByCreatedDateTimeBetween(from, to);
        }
        return postRepository.findAll();
    }

    public List<PostEntity> findAllPostsSortedBy(String fieldName, boolean isAscending) {
        return postRepository.findAll(Sort.by(getSortDirection(isAscending), fieldName));
    }

    private static Sort.Direction getSortDirection(boolean isAscending) {
        return isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    public PostEntity findPostById(Long postId) {
        PostEntity foundPost = postRepository.findById(postId).orElse(null);
        if (foundPost == null) {
            throw new EntityNotFoundException();
        }
        return foundPost;
    }

    public PostEntity createPost(PostDto postDto, MultipartFile photoFile) throws IOException {
        PostEntity postEntity = new PostEntity();

        setFieldsToEntityFromDto(postEntity, postDto, photoFile);

        return postRepository.save(postEntity);
    }

    public PostEntity updatePost(Long postId, PostDto postDto, MultipartFile photoFile) throws IOException {
        PostEntity foundPost = findPostById(postId);

        setFieldsToEntityFromDto(foundPost, postDto, photoFile);

        return postRepository.save(foundPost);
    }

    public PostEntity deletePost(Long postId) {
        PostEntity foundPost = findPostById(postId);

        postRepository.delete(foundPost);

        return foundPost;
    }

    private void setFieldsToEntityFromDto(PostEntity postEntity, PostDto postDto, MultipartFile photoFile) throws IOException {
        postEntity.setUserId(postDto.userId());
        postEntity.setTitle(postDto.title());
        postEntity.setBody(postDto.body());
        addPhotoFilePath(postEntity, photoFile);
        postEntity.setCreatedDateTime(LocalDateTime.now());
    }

    private void addPhotoFilePath(PostEntity postEntity, MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException();
        }

        createDirectoryIfNotExists();

        String uuid = UUID.randomUUID().toString();
        String filename = uuid + "." + file.getOriginalFilename();

        file.transferTo(new File("%s/%s".formatted(uploadPath, filename)));

        postEntity.setPhotoFilepath(filename);
    }

    private void createDirectoryIfNotExists() {
        File uploadFilePath = new File(this.uploadPath);

        if (!uploadFilePath.exists()) {
            uploadFilePath.mkdir();
        }
    }

    public byte[] downloadImageFromFileSystem(Long id) throws IOException {
        PostEntity postEntity = findPostById(id);

        String filepath = uploadPath + postEntity.getPhotoFilepath();

        return Files.readAllBytes(new File(filepath).toPath());
    }
}
