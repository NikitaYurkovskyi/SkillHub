import {
  Controller,
  Get,
  Post,
  Body,
  UseGuards,
  Request,
} from '@nestjs/common';
import { PostsService } from './posts.service';
import { CreatePostDto } from './dto/create-post.dto';
import { JwtAuthGuard } from 'src/middlewares/require-auth.middleware';
import { Post as PostModel } from './post.entity';

@Controller('posts')
export class PostsController {
  constructor(private postsService: PostsService) {}

  @Post()
  @UseGuards(JwtAuthGuard)
  createPost(@Body() createPostDto: CreatePostDto, @Request() req) {
    const userId = req.userId;
    return this.postsService.createPost(userId, createPostDto);
  }

  @Get()
  getPosts(): Promise<PostModel[]> {
    return this.postsService.getPosts();
  }
}
