package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Service.Impl;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Utils.TokenUtil;
import club.iexhibition.MonsterIexhibition.Common.Utils.UUIDGenerator;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibition.Mapper.CExhibitionMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans.ExhibitionFeel;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans.ExhibitionFeelAlbum;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans.ExhibitionFeelComment;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans.Url;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Mapper.ExhibitionFeelMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Service.ExhibitionFeelService;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.AddMicroBlog;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans.Exhibition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExhibitionFeelServiceImpl implements ExhibitionFeelService {

    @Autowired
    private ExhibitionFeelMapper exhibitionFeelMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private CExhibitionMapper cExhibitionMapper;

    /**
     * 查询展评列表
     * @param paramBean
     * @param uid
     * @returnExhibitionFeelComment
     */
    @Override
    public PageInfo<ExhibitionFeel> findExhibitionFeel(ParamBean paramBean, String uid) {

        uid="1118AD57D90A4117809A1E2EFC898B0F";
        //分页
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());

        List<ExhibitionFeel> exhibitionFeels=exhibitionFeelMapper.findExhibitionFeel(paramBean.getSearch(),uid);

        //判断用户点赞状态
        for (ExhibitionFeel exhibitionFeel : exhibitionFeels) {
                if(exhibitionFeel.getUpUserId()==null){
                    exhibitionFeel.setIsUp(false);
                }
        }

        PageInfo<ExhibitionFeel> pageInfo = new PageInfo<>(exhibitionFeels);

        return pageInfo;
    }

    /**
     * 我要展评
     */
    @Override
    public String addExhibitionFeel(AddMicroBlog addMicroBlog, String sessionId) {
        ExhibitionFeel exhibitionFeel = new ExhibitionFeel();
        //设置展评属性
        exhibitionFeel.setTitle(addMicroBlog.getTitle());
        exhibitionFeel.setContent(addMicroBlog.getContent());
        exhibitionFeel.setExhibitionId(addMicroBlog.getEid());
        //查询展览封面图
        String url = cExhibitionMapper.findExhibitionUrlById(addMicroBlog.getEid());
        exhibitionFeel.setUrl(url);
        if(addMicroBlog.getIsRewards()){
            exhibitionFeel.setIsRewards(1);
        }else {
            exhibitionFeel.setIsRewards(2);
        }

        if(addMicroBlog.getImages()!=null&&addMicroBlog.getImages().size()>0){
            List<ExhibitionFeelAlbum> albums = new ArrayList<>();

            for (int i = 0; i < addMicroBlog.getImages().size(); i++) {
                 ExhibitionFeelAlbum exhibitionFeelAlbum = new ExhibitionFeelAlbum();

                 exhibitionFeelAlbum.setId(UUIDGenerator.uuid());
                 exhibitionFeelAlbum.setUrl(addMicroBlog.getImages().get(i));
                 exhibitionFeelAlbum.setUrlSort(i);

                 albums.add(exhibitionFeelAlbum);
            }

            exhibitionFeel.setExhibitionFeelAlbums(albums);
        }

        String uid = tokenUtil.getOpenId(sessionId);
        exhibitionFeel.setUserId(uid);
        //添加展评
        exhibitionFeel.setId(UUIDGenerator.uuid());
        exhibitionFeelMapper.addExhibitionFeel(exhibitionFeel);

        if(exhibitionFeel.getExhibitionFeelAlbums()!=null&&exhibitionFeel.getExhibitionFeelAlbums().size()>0){
            //添加展评图片
            for (ExhibitionFeelAlbum exhibitionFeelAlbum : exhibitionFeel.getExhibitionFeelAlbums()) {
                exhibitionFeelAlbum.setId(UUIDGenerator.uuid());
            }

            exhibitionFeelMapper.addExhibitionFeelAlbum(exhibitionFeel.getId(),exhibitionFeel.getExhibitionFeelAlbums());
        }

        return exhibitionFeel.getId();
    }

    /**
     * 通过id查询展评详情
     * @param fid
     * @param paramBean
     * @return
     */
    @Override
    public ExhibitionFeel findExhibitionFeelById(String sessionId, String fid, ParamBean paramBean) {
       String uid = tokenUtil.getOpenId(sessionId);
        //String uid = "";
        //查询展评详情
        ExhibitionFeel exhibitionFeel=exhibitionFeelMapper.findExhibitionFeelById(uid,fid);

        if(exhibitionFeel.getSUrls()!=null&&exhibitionFeel.getSUrls().size()>0){
            for (String sUrl : exhibitionFeel.getSUrls()) {

                Url url = new Url();

                url.setUrl(sUrl);

                exhibitionFeel.getUrls().add(url);
            }
        }

        if(exhibitionFeel!=null){
            //判断用户点赞收藏状态
            if(exhibitionFeel.getUpUserId()==null){
                exhibitionFeel.setIsUp(false);
            }
            if(exhibitionFeel.getToAssembleUserId()==null){
                exhibitionFeel.setIsFollow(false);
            }
            if(exhibitionFeel.getUserId().equals(uid)){
                exhibitionFeel.setIsFollow(null);
            }
            if(exhibitionFeel.getCommentNum()>0){
                //查询评论
                //分页
                PageHelper.startPage(paramBean.getPage(),paramBean.getSize());

                List<ExhibitionFeelComment> exhibitionFeelComments=exhibitionFeelMapper.findExhibitionFeelComment(exhibitionFeel.getId());

                PageInfo<ExhibitionFeelComment> pageInfo = new PageInfo<>(exhibitionFeelComments);

                exhibitionFeel.setPageInfo(pageInfo);
            }
        }

        return exhibitionFeel;
    }


    /**
     * 展评点赞或取消点赞
     * @param fid
     */
    @Override
    public void exhibitionFeelUp(String fid, String sessionId) {
        String uid = tokenUtil.getOpenId(sessionId);
        //查询当前用户是否给此展评点过赞
        String userId=exhibitionFeelMapper.findExhibitionFeelUp(fid,uid);

        if(userId!=null){
            //取消点赞
            exhibitionFeelMapper.deleteExhibitionFeelUp(fid,uid);
            return;
        }

            //点赞
            exhibitionFeelMapper.addExhibitionFeelUp(fid,uid);
    }

    /**
     * 展评评论
     * @param exhibitionFeelComment
     * @param sessionId
     */
    @Override
    public void exhibitionFeelComment(ExhibitionFeelComment exhibitionFeelComment, String sessionId) {

        if(sessionId==null||"".equals(sessionId)){
            return;
        }

        String openId = tokenUtil.getOpenId(sessionId);
        //添加评论
        exhibitionFeelComment.setId(UUIDGenerator.uuid());
        exhibitionFeelComment.setUserId(openId);

        exhibitionFeelMapper.exhibitionFeelComment(exhibitionFeelComment);
    }


    /**
     * 删除展评评论
     * @param id
     * @param sessionId
     */
    @Override
    public void deleteExhibitionFeelComment(String id, String sessionId) {
        if(sessionId==null||"".equals(sessionId)){
            return;
        }
         String openId = tokenUtil.getOpenId(sessionId);
        //校验这条评论是否存在
        String  userId=exhibitionFeelMapper.findExhibitionFeelCommentId(id,openId);

        if(userId==null){
            return;
        }else {
            exhibitionFeelMapper.updateExhibitionFeelCommentStatus(id,-1);
        }
    }


    /**
     * 展评转发
     * @param id
     * @param sessionId
     */
    @Override
    public void exhibitionFeelForward(String id, String sessionId) {
        if(sessionId==null||"".equals(sessionId)){
            return;
        }

         String openId = tokenUtil.getOpenId(sessionId);

        //转发
        exhibitionFeelMapper.exhibitionFeelForward(UUIDGenerator.uuid(),id,openId);
    }
}
