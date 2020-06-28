package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans;

import lombok.Data;

import java.util.List;

@Data
public class AddMicroBlog {

    private String title;

    private String content;

    private List<String> images;

    private String eid;

    private Boolean isRewards;
}
