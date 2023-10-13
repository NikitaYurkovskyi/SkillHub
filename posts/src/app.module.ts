// app.module.ts
import { Module } from '@nestjs/common';
import { JwtModule } from '@nestjs/jwt';
import { JwtAuthGuard } from './middlewares/require-auth.middleware';
import { JWT_ACCESS_SECRET } from './credentials';
import { PostsModule } from './posts/posts.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Post } from './posts/post.entity';

@Module({
  imports: [
    JwtModule.register({
      secret: JWT_ACCESS_SECRET,
    }),
    TypeOrmModule.forRoot({
      type: 'postgres',
      host: 'posts-postgres-srv',
      port: 5432,
      username: 'posts_user',
      password: 'posts_password',
      database: 'posts_database',
      autoLoadEntities: true,
      synchronize: true,
      entities: [Post],
    }),
    PostsModule,
  ],
  providers: [JwtAuthGuard],
})
export class AppModule {}
