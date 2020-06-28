package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SComment.Service.Impl;
//                            _ooOoo_
//                           o8888888o
//                           88" . "88
//                           (| -_- |)
//                            O\ = /O
//                        ____/`---'\____
//                      .   ' \\| |// `.
//                       / \\||| : |||// \
//                     / _||||| -:- |||||- \
//                       | | \\\ - /// | |
//                     | \_| ''\---/'' | |
//                      \ .-\__ `-` ___/-. /
//                   ___`. .' /--.--\ `. . __
//                ."" '< `.___\_<|>_/___.' >'"".
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//         ======`-.____`-.___\_____/___.-`____.-'======
//                            `=---='
//
//         .............................................
//                  佛祖保佑             永无BUG
//          佛曰:
//                  写字楼里写字间，写字间里程序员；
//                  程序人员写程序，又拿程序换酒钱。
//                  酒醒只在网上坐，酒醉还来网下眠；
//                  酒醉酒醒日复日，网上网下年复年。
//                  但愿老死电脑间，不愿鞠躬老板前；
//                  奔驰宝马贵者趣，公交自行程序员。
//                  别人笑我忒疯癫，我笑自己命太贱；
//                  不见满街漂亮妹，哪个归得程序员？
import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Mapper.ExhibitionFeelMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Mapper.MicroBlogMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SComment.Beans.Hulk;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SComment.Beans.TipOffComment;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SComment.Mapper.CommentMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SComment.Service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ExhibitionFeelMapper feelMapper;

    @Autowired
    private MicroBlogMapper microBlogMapper;

    /**
     * 查询被举报过的评论
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<TipOffComment> findTipOffComment(ParamBean paramBean) {
        //分页
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());
        List<TipOffComment> tipOffComments= commentMapper.findTipOffComment(paramBean.getSearch());

        //查询展评或者分享内容
        for (TipOffComment tipOffComment : tipOffComments) {
            if("1".equals(tipOffComment.getTableName())){
                //查询展评内容
                tipOffComment.setContent(feelMapper.findExhibitionContent(tipOffComment.getFromId()));
                //查询展品图片
                tipOffComment.setContentUrls(feelMapper.findExhibitionAlbum(tipOffComment.getFromId()));
                //查询举报人和举报原因
                tipOffComment.setHulks(commentMapper.findExhibitionFeelHulk(tipOffComment.getEfcId()));
            }
            if("2".equals(tipOffComment.getTableName())){
                //查询分享内容
                tipOffComment.setContent(microBlogMapper.findMicroBlogContent(tipOffComment.getFromId()));
                //查询分享图片
                tipOffComment.setContentUrls(microBlogMapper.findMicroBlogAlbum(tipOffComment.getFromId()));
                //查询举报人和举报原因
                tipOffComment.setHulks(commentMapper.findMicroBlogHulk(tipOffComment.getEfcId()));
            }
        }
         PageInfo<TipOffComment> pageInfo = new PageInfo<>(tipOffComments);

        return pageInfo;
    }
}
