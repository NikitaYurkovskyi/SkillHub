import { Injectable } from '@nestjs/common';
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
}
