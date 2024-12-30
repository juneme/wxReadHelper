package cn.wxreader.domain;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class RankUser {
    private Integer userVid;
    private String name;
    private Integer gender;
    private String avatar;
    private Integer isV;
    private JSONArray roleTags;
    private String followPromote;
    private Boolean isDeepV;
    private String deepVTitle;
    private Integer isHide;
    private JSONObject medalInfo;

    public Integer getUserVid() {
        return userVid;
    }

    public void setUserVid(Integer userVid) {
        this.userVid = userVid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getIsV() {
        return isV;
    }

    public void setIsV(Integer isV) {
        this.isV = isV;
    }

    public JSONArray getRoleTags() {
        return roleTags;
    }

    public void setRoleTags(JSONArray roleTags) {
        this.roleTags = roleTags;
    }

    public String getFollowPromote() {
        return followPromote;
    }

    public void setFollowPromote(String followPromote) {
        this.followPromote = followPromote;
    }

    public Boolean getDeepV() {
        return isDeepV;
    }

    public void setDeepV(Boolean deepV) {
        isDeepV = deepV;
    }

    public String getDeepVTitle() {
        return deepVTitle;
    }

    public void setDeepVTitle(String deepVTitle) {
        this.deepVTitle = deepVTitle;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    public JSONObject getMedalInfo() {
        return medalInfo;
    }

    public void setMedalInfo(JSONObject medalInfo) {
        this.medalInfo = medalInfo;
    }
}
