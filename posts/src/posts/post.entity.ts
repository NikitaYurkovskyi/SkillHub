import { IsString } from 'class-validator';
import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class Post {
  @PrimaryGeneratedColumn('uuid')
  postId: string;

  @IsString()
  userId: string;

  @Column()
  title: string;

  @Column()
  body: string;

  //время создания
  @Column({
    default: () => 'CURRENT_TIMESTAMP',
  })
  date: Date;
}
