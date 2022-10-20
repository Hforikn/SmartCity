package cn.itcast.smartcity2.GetSet.WaiMai;

import cn.itcast.smartcity2.Tools.SendRequest;

public class Wai_DianPu {
    private String code;// 状态码 string
    private String msg;// 消息内容 string
    //rows 列表数据（数组类型） 店家信息
    private String address;// 店家地址 string
    private Number avgCost;// 人均消费 number
    private int deliveryTime;// 配送时间 integer(int32)
    private Number distance;// 到店距离 number
    private int id;// integer(int64)
    private String imgUrl;// 店家图片 string
    private String introduction;// 店家简介 string
    private String name;// 店家名称 string
    private String recommend;// 是否推荐 string
    private int saleQuantity;// 月销量 integer(int64)
    private Number score;// 评分 number
    private int themeId;// 经营类型;// integer(int64)
    private int saleNum3hour;// 3 小时销量 integer(int64)
    private String total;// 总记录数 string

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Number getAvgCost() {
        return avgCost;
    }

    public void setAvgCost(int avgCost) {
        this.avgCost = avgCost;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Number getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public int getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(int saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public Number getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getSaleNum3hour() {
        return saleNum3hour;
    }

    public void setSaleNum3hour(int saleNum3hour) {
        this.saleNum3hour = saleNum3hour;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
