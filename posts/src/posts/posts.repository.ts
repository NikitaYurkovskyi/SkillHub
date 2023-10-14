import { DataSource, Repository } from 'typeorm';
import { CreatePostDto } from './dto/create-post.dto';
import { Post } from './post.entity';
import {
  Injectable,
  NotFoundException,
  ForbiddenException,
} from '@nestjs/common';

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

  async getUserPostById(postId: string, userId: string): Promise<Post> {
    const found = await this.findOne({ where: { postId } });

    if (!found) {
      throw new NotFoundException(`Post with ID "${postId}" not found`);
    }
    if (found.userId !== userId) {
      throw new ForbiddenException(
        `Post with ID ${postId} does not belong to user with ID ${userId}}`,
      );
    }

    return found;
  }
}
