import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class Post {
  @PrimaryGeneratedColumn('uuid')
  postId: string;

  @Column()
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
