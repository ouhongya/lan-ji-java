package club.iexhibition.MonsterIexhibition.Common.Result;


import com.fasterxml.jackson.annotation.JsonFormat;




@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CommonCode {
    SUCCESS(1,"操作成功"),
    SYSTEMBUSY(2,"系统繁忙，请稍后再试。"),
    PASSWORD_ERROR(3,"用户名或密码错误！"),
    FAILED(4,"操作失败"),
    ROLE_NAME_REPEAT(5,"角色名称重复！"),
    CANNOT_DELETE_ACCOUNT(6,"无法删除账户，因为该账户绑定了权限！"),
    CANNOT_DELETE_ROLE(7,"无法删除角色,因为该角色绑定了账户！"),
    PHONE_NUM_REPEAT(8,"该手机号已被绑定！"),
    USERNAME_REPEAT(9,"该用户名已存在！"),
    PHONE_NUM_ERROR(10,"请输入正确的手机号"),
    CANNOT_DELETE_CITY(11,"无法删除城市，因为该城市绑定了区域！"),
    CANNOT_DELETE_AREA(12,"无法删除区域，因为该区域绑定了展馆！"),
    CANNOT_DELETE_EXHIBITIONHALL(12,"无法删除展馆，因为该展馆绑定了活动！"),
    CANNOT_INSERT_CITY(13,"无法添加城市，该城市名称已经存在！"),
    CANNOT_INSERT_AREA(14,"无法添加区域，该区域名称在该城市已经存在！"),
    CANNOT_INSERT_EXHIBITIONHALL(15,"无法添加展馆，该展馆名称在该区已经存在！"),
    CANNOT_UPDATE_CITY(16,"无法编辑城市，该城市名称已经存在！"),
    CANNOT_UPDATE_AREA(17,"无法编辑区域，该区域名称在该城市已经存在！"),
    CANNOT_UPDATE_EXHIBITIONHALL(18,"无法编辑展馆，该展馆名称在该区已经存在！"),
    CANNOT_UPDATE_EXHIBITIONTAG(19,"无法编辑展览标签，该标签名称已经存在!"),
    CANNOT_INSERT_EXHIBITIONTAG(20,"无法添加展览标签，该标签名称已经存在！"),
    CANNOT_OPERATION_BIGSTAR(21,"该大咖名称已经存在！"),
    CANNOT_CLASS(22,"该分类名称已经存在！"),
    CANNOT_DELETE_CLASS(23,"无法删除该分类，该分类下有未删除的商品！"),
    NUMBER_FULL(24,"人数已满！"),
    TOKEN_TIME_OUT(25,"请重新登录！")

    ;

    private Integer code;
    private String message;


    CommonCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
