import { DataSource, Repository } from 'typeorm';
import { CreatePostDto } from './dto/create-post.dto';
import { Post } from './post.entity';
import { Injectable } from '@nestjs/common';

@Injectable()
export class PostsRepository extends Repository<Post> {
  constructor(private dataSource: DataSource) {
    super(Post, dataSource.createEntityManager());
  }

  async createPost(
    userId: string,
    createPostDto: CreatePostDto,
  ): Promise<Post> {
    const { title, body } = createPostDto;
    const post = this.create({ title, body, userId });
    await this.save(post);

    return post;
  }

  async getPosts(): Promise<Post[]> {
    const posts = await this.find();
    return posts;
  }
}
