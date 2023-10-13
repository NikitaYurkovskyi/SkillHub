// app.controller.ts
import {
  Controller,
  Get,
  UseGuards,
  // SetMetadata,
  Request,
} from '@nestjs/common';
import { JwtAuthGuard } from './middlewares/require-auth.middleware';

@Controller()
export class AppController {
  @Get('/protected-route')
  @UseGuards(JwtAuthGuard)
  protectedRoute(@Request() req) {
    return req.user;
  }
}
