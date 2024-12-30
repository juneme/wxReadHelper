package cn.wxreader.domain;

public class FriendLikeReq {
    private Integer like;
    private Integer type;
    private Integer vid;

    public FriendLikeReq(Integer like, Integer type, Integer vid) {
        this.like = like;
        this.type = type;
        this.vid = vid;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }
}
