package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Service.Impl;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Utils.TokenUtil;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Beans.SUser;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Beans.SUserAssemble;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Beans.SUserComment;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Mapper.SUserMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Service.SUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SUserServiceImpl implements SUserService {

    @Autowired
    private SUserMapper sUserMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Value("${clientTokenTime}")
    private Long clientTokenTime;


    /**
     * 查询WX用户列表
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<SUser> findAllUser(ParamBean paramBean) {
        //分页
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());

        List<SUser> sUsers = sUserMapper.findAllUser(paramBean.getSearch());

        //将用户id加密
        for (SUser sUser : sUsers) {
            Map<String,Object>  tokenMap = new HashMap<>();
            tokenMap.put("endTime",new Date().getTime()+clientTokenTime);
            tokenMap.put("openId",sUser.getId());
            String token = tokenUtil.createToken(tokenMap);
            sUser.setId(token);
        }

        PageInfo<SUser> pageInfo = new PageInfo<>(sUsers);

        return pageInfo;

    }

    /**
     * 通过id查询wx用户详细信息
     * @param id
     * @return
     */
    @Override
    public SUser findSUserById(String id) {
        String openId = tokenUtil.getOpenId(id);
        return sUserMapper.findSUserById(openId);
    }

    /**
     * 通过用户id查询评论
     * @param id
     * @param paramBean
     */
    @Override
    public PageInfo<SUserComment> findCommentByUid(String id, ParamBean paramBean) {

        String openId = tokenUtil.getOpenId(id);

        //分页
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());

        List<SUserComment> sUserComments=sUserMapper.findUserCommentByUid(openId);

        PageInfo<SUserComment> pageInfo = new PageInfo<>(sUserComments);

        return pageInfo;
    }


    /**
     * 查询用户参加过的召集
     * @param id
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<SUserAssemble> findInAssembleByUid(String id, ParamBean paramBean) {
        String openId = tokenUtil.getOpenId(id);

        //分页
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());

        List<SUserAssemble> sUserAssembles=sUserMapper.findInAssembleByUid(openId);

        PageInfo<SUserAssemble> pageInfo = new PageInfo<>(sUserAssembles);

        return pageInfo;
    }

    /**
     * 查询用户发起的召集
     * @param id
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<SUserAssemble> findAssembleByUid(String id, ParamBean paramBean) {
        String openId = tokenUtil.getOpenId(id);

        //分頁
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());

        List<SUserAssemble> sUserAssembles=sUserMapper.findAssembleByUid(openId);

        PageInfo<SUserAssemble> pageInfo = new PageInfo<>(sUserAssembles);

        return pageInfo;
    }
}
