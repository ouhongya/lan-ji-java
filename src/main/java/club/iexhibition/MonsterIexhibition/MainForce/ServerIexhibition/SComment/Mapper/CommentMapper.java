package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SComment.Mapper;

import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SComment.Beans.Hulk;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SComment.Beans.TipOffComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    List<TipOffComment> findTipOffComment(@Param("search") String search);

    List<Hulk> findExhibitionFeelHulk(String efcId);

    List<Hulk> findMicroBlogHulk(String efcId);
}
