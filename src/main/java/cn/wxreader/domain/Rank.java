package cn.wxreader.domain;

public class Rank {
    private RankUser user;
    private Integer readingTime;
    private Integer rankWeek;
    private Integer likedCount;
    private Integer pokedCount;
    private Integer isLiked;
    private Integer isPoked;
    private Integer order;

    public RankUser getUser() {
        return user;
    }

    public void setUser(RankUser user) {
        this.user = user;
    }

    public Integer getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(Integer readingTime) {
        this.readingTime = readingTime;
    }

    public Integer getRankWeek() {
        return rankWeek;
    }

    public void setRankWeek(Integer rankWeek) {
        this.rankWeek = rankWeek;
    }

    public Integer getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(Integer likedCount) {
        this.likedCount = likedCount;
    }

    public Integer getPokedCount() {
        return pokedCount;
    }

    public void setPokedCount(Integer pokedCount) {
        this.pokedCount = pokedCount;
    }

    public Integer getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Integer isLiked) {
        this.isLiked = isLiked;
    }

    public Integer getIsPoked() {
        return isPoked;
    }

    public void setIsPoked(Integer isPoked) {
        this.isPoked = isPoked;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Rank() {
    }

    public Rank(RankUser user, Integer readingTime, Integer rankWeek, Integer likedCount, Integer pokedCount, Integer isLiked, Integer isPoked, Integer order) {
        this.user = user;
        this.readingTime = readingTime;
        this.rankWeek = rankWeek;
        this.likedCount = likedCount;
        this.pokedCount = pokedCount;
        this.isLiked = isLiked;
        this.isPoked = isPoked;
        this.order = order;
    }
}
