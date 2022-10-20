package cn.itcast.smartcity2.GetSet;

public class MingXi {
    private String code;// 状态码 string
    private String msg;// 消息内容 string
    //rows 列表数据（数组类型） 钱包金额变动记录
    private String appType;// 应用类别 string
    private int changeAmount;// 变更金额 number
    private String changeTime;// 变更日期 string(date-time)
    private String changeType;// 变更类型 string
    private String event;// 变更事件 string
    private int id;// integer(int64)
    private int userId;// 用户 id integer(int64)
    private String userName;// 用户名 string
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

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public int getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(int changeAmount) {
        this.changeAmount = changeAmount;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
