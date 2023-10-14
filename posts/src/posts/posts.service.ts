import { Injectable, NotFoundException } from '@nestjs/common';
import { PostsRepository } from './posts.repository';
import { CreatePostDto } from './dto/create-post.dto';
import { Post } from './post.entity';

@Injectable()
export class PostsService {
  constructor(private postsRepository: PostsRepository) {}

  async createPost(
    userId: string,
    createPostDto: CreatePostDto,
  ): Promise<Post> {
    return this.postsRepository.createPost(userId, createPostDto);
  }

  async getPosts(): Promise<Post[]> {
    return this.postsRepository.getPosts();
  }

  async getPostById(postId: string): Promise<Post> {
    const foundedPost = await this.postsRepository.findOne({
      where: { postId },
    });

    if (!foundedPost) {
      throw new NotFoundException(`Post with ID "${postId}" not found`);
    }

    return foundedPost;
  }

  async deletePost(
    postId: string,
    userId: string,
  ): Promise<{ message: string }> {
    const result = await this.postsRepository.delete({ postId, userId });

    if (result.affected === 0) {
      throw new NotFoundException(`Post with ID "${postId}" not found`);
    }

    return { message: `Task with ID ${postId} succesfully deleted` };
  }

  async updatePostTitle(
    postId: string,
    userId: string,
    title: string,
  ): Promise<Post> {
    const post = await this.postsRepository.getUserPostById(postId, userId);

    post.title = title;
    await this.postsRepository.save(post);

    return post;
  }
  async updatePostBody(
    postId: string,
    userId: string,
    body: string,
  ): Promise<Post> {
    const post = await this.postsRepository.getUserPostById(postId, userId);

    post.body = body;
    await this.postsRepository.save(post);

    return post;
  }
}
