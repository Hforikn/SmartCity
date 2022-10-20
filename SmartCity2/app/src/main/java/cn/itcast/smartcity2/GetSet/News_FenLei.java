package cn.itcast.smartcity2.GetSet;

public class News_FenLei {
    private String code;                //状态码 string
    private String msg;                 //消息内容 string
    //rows //列表数据（数组类型） 新闻分类实体
    private int id;                     //分类编号 integer(int64)
    private String name;                //分类名称 string
    private int sort;                   //分类序号 integer(int32)
    private String total;               //总记录数 string
    private String appType;             //app 类型 string

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }
}
