import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';
import { ValidationPipe } from '@nestjs/common';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  app.setGlobalPrefix('api');

  app.useGlobalPipes(new ValidationPipe());

  const PORT = 3000;

  await app.listen(PORT, () => {
    console.log(`Posts service listen at ${PORT}`);
  });
}
bootstrap();
