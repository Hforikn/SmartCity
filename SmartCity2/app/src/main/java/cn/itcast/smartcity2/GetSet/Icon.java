package cn.itcast.smartcity2.GetSet;

import cn.itcast.smartcity2.Tools.SendRequest;

public class Icon implements Comparable<Icon> {
    private String code;                            //状态码 string
    private String msg;                             //消息内容 string
    //rows //列表数据（数组类型） 服务管理
    private int id;                                 //ID integer(int64)
    private String imgUrl;                          //服务图片 string
    private String link;                            //跳转链接 string
    private String isRecommend;                     //是否推荐，参见字典名：系统是否 string
    private String serviceDesc;                     //服务简介 string
    private String serviceName;                     //服务名称 string
    private String serviceType;                         //服务类别 string
    private int sort;                               //序号 integer(int64)
    private String total;                           //总记录数 string

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public int compareTo(Icon o) {
        return -(this.sort - o.sort);
    }
}
