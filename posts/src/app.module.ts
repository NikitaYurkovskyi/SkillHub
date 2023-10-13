// app.module.ts
import { Module } from '@nestjs/common';
import { JwtModule } from '@nestjs/jwt';
import { AppController } from './app.controller';
import { JwtAuthGuard } from './middlewares/require-auth.middleware';
import { JWT_ACCESS_SECRET } from './credentials';
import { PostsModule } from './posts/posts.module';

@Module({
  imports: [
    JwtModule.register({
      secret: JWT_ACCESS_SECRET,
    }),
    PostsModule,
  ],
  controllers: [AppController],
  providers: [JwtAuthGuard],
})
export class AppModule {}
