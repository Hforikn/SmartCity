package cn.itcast.smartcity2.GetSet.WaiMai;

import cn.itcast.smartcity2.Tools.SendRequest;

public class Wai_FenLei {
    private String code;// 状态码 string
    private String msg;// 消息内容 string
    //rows 列表数据（数组类型） 主题分类
    private int id;// integer(int64)
    private String imgUrl;// 图标 string
    private int sort;// 排序 integer(int64)
    private String themeDesc;// 描述 string
    private String themeName;// 主题名称 string

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return SendRequest.IP + imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getThemeDesc() {
        return themeDesc;
    }

    public void setThemeDesc(String themeDesc) {
        this.themeDesc = themeDesc;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
}
