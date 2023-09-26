import { BookCoverProps } from '@src/types/props';
import * as S from './index.styled';

export default function BookCover({ imgsrc, size, onClick }: BookCoverProps) {
  return (
    <S.ImgContainer size={size} imgsrc={imgsrc} onClick={onClick}>
      <S.Img src={imgsrc} alt="bookCoverImg"></S.Img>
    </S.ImgContainer>
  );
}
