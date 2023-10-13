import {
  IsNotEmpty,
  MaxLength,
  MinLength,
  //   IsDateString,
} from 'class-validator';

export class CreatePostDto {
  @IsNotEmpty()
  @MaxLength(50)
  title: string;

  @IsNotEmpty()
  @MinLength(10)
  @MaxLength(4000)
  body: string;
}
