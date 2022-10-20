package cn.itcast.smartcity2.GetSet;

import cn.itcast.smartcity2.Tools.SendRequest;

public class News {
    private String code;                        //状态码 string
    private String msg;                         //消息内容 string
    //rows //列表数据（数组类型） 新闻实体
    private int commentNum;                     //评论数 integer(int64)
    private String content;                     //新闻内容 string
    private String cover;                       //新闻封面图片地址 string
    private String hot;                         //是否热点，参见字典名：系统是否 string
    private int id;                             //新闻 ID integer(int64)
    private int likeNum;                        //点赞数 integer(int64)
    private String publishDate;                 //发布日期 string(date-time)
    private int readNum;                        //阅读数 integer(int64)
    private String subTitle;                    //新闻副标题 string
    private String title;                       //新闻标题 string
    private String top;                         //是否推荐，参见字典名：系统是否 string
    private String total;                       //总记录数 string
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return SendRequest.IP + cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
