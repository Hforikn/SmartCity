package cn.itcast.smartcity2.GetSet.WaiMai;

import cn.itcast.smartcity2.Tools.SendRequest;

public class Wai_CaiPin {
    private String code;// 状态码 string
    private String msg;// 消息内容 string
    //data 列表数据（数组类型） 菜品信息
    private int categoryId;// 分类 id integer(int64)
    private String detail;// 菜品详情 string
    private Number favorRate;// 好评率 number
    private int id;// integer(int64)
    private String imgUrl;// 图片地址 string
    private String name;// 菜名 string
    private Number price;// 价格 number
    private int saleQuantity;// 月销量 integer(int32)
    private int sellerId;// 所属店家 integer(int64)
    private String status;// 状态 string

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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Number getFavorRate() {
        return favorRate;
    }

    public void setFavorRate(Number favorRate) {
        this.favorRate = favorRate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public int getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(int saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
