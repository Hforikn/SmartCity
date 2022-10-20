package cn.itcast.smartcity2.GetSet;

import cn.itcast.smartcity2.Tools.SendRequest;

public class BannerImg implements Comparable<BannerImg> {
    private String code;                    //状态码 string
    private String msg;                     //消息内容 string
    //rows //列表数据（数组类型） 广告轮播实体
    private String advImg;                  //广告图片 string
    private String advTitle;                //广告标题 string
    private int id;                         //广告 ID integer(int64)
    private String servModule;              //业务模块 string
    private int sort;                       //排序 integer(int64)
    private int targetId;                   //跳转详情 id integer(int64)
    private String total;                   //总记录数 string

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

    public String getAdvImg() {
        return SendRequest.IP + advImg;
    }

    public void setAdvImg(String advImg) {
        this.advImg = advImg;
    }

    public String getAdvTitle() {
        return advTitle;
    }

    public void setAdvTitle(String advTitle) {
        this.advTitle = advTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServModule() {
        return servModule;
    }

    public void setServModule(String servModule) {
        this.servModule = servModule;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public int compareTo(BannerImg o) {
        return -(this.sort - o.sort);
    }
}
