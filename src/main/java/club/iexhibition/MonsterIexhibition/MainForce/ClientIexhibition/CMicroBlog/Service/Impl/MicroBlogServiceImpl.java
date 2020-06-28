package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Service.Impl;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Utils.TokenUtil;
import club.iexhibition.MonsterIexhibition.Common.Utils.UUIDGenerator;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans.Url;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.AddMicroBlog;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.MicroBlog;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.MicroBlogAlbum;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.MicroBlogComment;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Mapper.MicroBlogMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Service.MicroBlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class MicroBlogServiceImpl implements MicroBlogService {

    @Autowired
    private MicroBlogMapper microBlogMapper;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 查询分享
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<MicroBlog> findMicroBlog(ParamBean paramBean,String sessionId) {

        String uid = tokenUtil.getOpenId(sessionId);

        //分页
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());

        List<MicroBlog> microBlogs = microBlogMapper.findMicroBlog(paramBean.getSearch(),uid);

        //校验用户是否点赞

        for (MicroBlog microBlog : microBlogs) {
            if(microBlog.getUpUserId()==null){
                microBlog.setIsUp(false);
            }
        }

        //如果是分享  查询分享图片
        for (MicroBlog microBlog : microBlogs) {
            if("1".equals(microBlog.getExhibitionId())){
                //查询图片
                 List<String> albumS = microBlogMapper.findMicroBlogAlbum(microBlog.getId());

                for (String album : albumS) {
                    Url url = new Url();
                    url.setUrl(album);
                    microBlog.getUrls().add(url);
                }
            }
        }

        PageInfo<MicroBlog> pageInfo = new PageInfo<>(microBlogs);

        return pageInfo;
    }

    /**
     * 通过id查询分享详情
     * @param id
     * @return
     */
    @Override
    public MicroBlog findMiCroBlogById(String id,ParamBean paramBean,String sessionId) {
        String uid = tokenUtil.getOpenId(sessionId);
        MicroBlog microBlog=microBlogMapper.findMicroBlogById(id,uid);
        if(microBlog!=null){
            //校验用户是否点赞和收藏
            if(microBlog.getUpUserId()==null){
                microBlog.setIsUp(false);
            }
            if(microBlog.getToAssembleUserId()==null){
                microBlog.setIsFollow(false);
            }
            if(uid.equals(microBlog.getToAssembleUserId())){
                microBlog.setIsFollow(null);
            }
            //查询评论
            PageHelper.startPage(paramBean.getPage(),paramBean.getSize());

            List<MicroBlogComment> comments=microBlogMapper.findCommentByMid(microBlog.getId());

            PageInfo<MicroBlogComment> pageInfo = new PageInfo<>(comments);

            microBlog.setPageInfo(pageInfo);
        }

        return microBlog;
    }

    /**
     * 增加分享
     */
    @Override
    public String addMicroBlog(AddMicroBlog addMicroBlog, String sessionId) {
        MicroBlog microBlog = new MicroBlog();
        //设置分享属性
        microBlog.setTitle(addMicroBlog.getTitle());
        microBlog.setContent(addMicroBlog.getContent());

        if(addMicroBlog.getImages()!=null&&addMicroBlog.getImages().size()>0){
            List<MicroBlogAlbum> albums = new ArrayList<>();

            for (int i = 0; i < addMicroBlog.getImages().size(); i++) {
                MicroBlogAlbum microBlogAlbum = new MicroBlogAlbum();
                microBlogAlbum.setId(UUIDGenerator.uuid());
                microBlogAlbum.setUrl(addMicroBlog.getImages().get(i));
                microBlogAlbum.setUrlSort(i);
                albums.add(microBlogAlbum);
            }

            microBlog.setMicroBlogAlbums(albums);
         }


        String uid = tokenUtil.getOpenId(sessionId);
        microBlog.setUserId(uid);
        //添加分享
        microBlog.setId(UUIDGenerator.uuid());
        microBlogMapper.addMicroBlog(microBlog);
        //添加图片
        if(microBlog.getMicroBlogAlbums()!=null&&microBlog.getMicroBlogAlbums().size()>0){
            for (MicroBlogAlbum microBlogAlbum : microBlog.getMicroBlogAlbums()) {
                microBlogAlbum.setId(UUIDGenerator.uuid());
            }

            microBlogMapper.addMicroBlogAlbum(microBlog.getId(),microBlog.getMicroBlogAlbums());
        }

        return microBlog.getId();
    }

    /**
     * 分享评论
     * @param microBlogComment
     */
    @Override
    public void microBlogComment(MicroBlogComment microBlogComment,String sessionId) {
        String uid = tokenUtil.getOpenId(sessionId);
        microBlogComment.setUserId(uid);
        microBlogComment.setId(UUIDGenerator.uuid());
        microBlogMapper.microBlogComment(microBlogComment);
    }

    /**
     * 删除分享评论
     * @param id
     */
    @Override
    public void deleteMicroBlogComment(String id) {
        microBlogMapper.updateMicroBlogCommentStatus(id,-1);
    }

    /**
     * 分享点赞
     * @param mid
     */
    @Override
    public void microBlogUp(String sessionId, String mid) {
        String uid = tokenUtil.getOpenId(sessionId);
        //查询用户是否点过赞
        String userId=microBlogMapper.findMicroBlogUp(uid,mid);

        if(userId!=null){
            //该用户赞过  取消赞
            microBlogMapper.deleteMicroBlogUp(uid,mid);
            return;
        }

        microBlogMapper.microBlogUp(uid,mid);
    }

    /**
     * 分享转发
     * @param mid
     */
    @Override
    public void microBlogForWard(String sessionId, String mid) {
        String uid = tokenUtil.getOpenId(sessionId);
        microBlogMapper.microBlogForWard(UUIDGenerator.uuid(),uid,mid);
    }
}
