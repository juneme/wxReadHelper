package cn.wxreader.domain;

import java.util.List;

public class FriendRankRes {
    private Integer syncKey;
    private Integer rankSecret;
    private List<Rank> ranking;
    private List<Integer> likeVids;
    private List<Integer> pokeVids;

    public Integer getSyncKey() {
        return syncKey;
    }

    public void setSyncKey(Integer syncKey) {
        this.syncKey = syncKey;
    }

    public Integer getRankSecret() {
        return rankSecret;
    }

    public void setRankSecret(Integer rankSecret) {
        this.rankSecret = rankSecret;
    }

    public List<Rank> getRanking() {
        return ranking;
    }

    public void setRanking(List<Rank> ranking) {
        this.ranking = ranking;
    }

    public List<Integer> getLikeVids() {
        return likeVids;
    }

    public void setLikeVids(List<Integer> likeVids) {
        this.likeVids = likeVids;
    }

    public List<Integer> getPokeVids() {
        return pokeVids;
    }

    public void setPokeVids(List<Integer> pokeVids) {
        this.pokeVids = pokeVids;
    }
}
