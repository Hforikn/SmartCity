package cn.itcast.smartcity2.GetSet;

import cn.itcast.smartcity2.Tools.SendRequest;

public class Car {
    private String code;// 状态码 string
    private String msg;// 消息内容 string
    //rows 列表数据（数组类型） 停车场
    private String address;// 地址 string
    private String allPark;// 总停车位 string
    private String distance;// 距离（米） string
    private int id;// 主键 id integer(int64)
    private String imgUrl;// 图片地址 string
    private String open;// 对外开放 string
    private String parkName;// 停车场名称 string
    private String priceCaps;// 封顶价格 string
    private String rates;// 收费 string
    private String vacancy;// 空位个数 string
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

    public String getAllPark() {
        return allPark;
    }

    public void setAllPark(String allPark) {
        this.allPark = allPark;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
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

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getPriceCaps() {
        return priceCaps;
    }

    public void setPriceCaps(String priceCaps) {
        this.priceCaps = priceCaps;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
