import {
  Controller,
  Get,
  Post,
  Body,
  UseGuards,
  Request,
  Param,
  Delete,
  Patch,
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
  createPost(
    @Body() createPostDto: CreatePostDto,
    @Request() req,
  ): Promise<PostModel> {
    const userId = req.userId;
    return this.postsService.createPost(userId, createPostDto);
  }

  @Get()
  getPosts(): Promise<PostModel[]> {
    return this.postsService.getPosts();
  }

  @Get('/:postId')
  getPostById(@Param('postId') postId: string): Promise<PostModel> {
    return this.postsService.getPostById(postId);
  }

  @Delete('/:postId')
  @UseGuards(JwtAuthGuard)
  deletePost(
    @Param('postId') postId: string,
    @Request() req,
  ): Promise<{ message: string }> {
    const { userId } = req;
    return this.postsService.deletePost(postId, userId);
  }

  @Patch('/:postId/title')
  @UseGuards(JwtAuthGuard)
  updatePostTitle(
    @Param('postId') postId: string,
    @Request() req,
    @Body('title') title: string,
  ): Promise<PostModel> {
    const { userId } = req;
    return this.postsService.updatePostTitle(postId, userId, title);
  }

  @Patch('/:postId/body')
  @UseGuards(JwtAuthGuard)
  updatePostBody(
    @Param('postId') postId: string,
    @Request() req,
    @Body('body') body: string,
  ): Promise<PostModel> {
    const { userId } = req;
    return this.postsService.updatePostBody(postId, userId, body);
  }
}
