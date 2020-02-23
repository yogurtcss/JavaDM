package pers.yo.entity;

import org.apache.solr.client.solrj.beans.Field;

import java.util.ArrayList;
import java.util.List;

public class Page { //2020年尚存的属性
    //页面内容
    private String content; //混杂一堆HTML标签
    @Field
    private String url; //网址，通过page对象设置进来嗷！
    @Field
    private String tvName; //电视剧名称
    @Field
    private String heatText; //热度值
    @Field
    private String score; //评分

    private String director; //导演
    private String mainActor; //主演
    private String info; //概要
    @Field
    private String tag; //该电视剧所属的标签类型

    //电视剧id --通过网址的url可知
    @Field
    private String tvId;

    //2020-02-22 16:30:04
    //存储电视剧url(包含电视剧某一页列表url 和详情页的url)
    private List<String> urlList = new ArrayList<>();

    public List<String> getUrlList() {
        return urlList;
    }
    public void addUrl( String url ){
        this.urlList.add(url);
    }

    //-----------以下是优酷页没有显示的信息-----------
    //子集数据
    private String episodeNumber;


    @Override
    public String toString() {
        return "Page{" +

                "url='" + url + '\'' +
                ", tvName='" + tvName + '\'' +
                ", heatText='" + heatText + '\'' +
                ", score='" + score + '\'' +
                ", director='" + director + '\'' +
                ", mainActor='" + mainActor + '\'' +
                ", info='" + info + '\'' +
                ", tag='" + tag + '\'' +
                ", tvId='" + tvId + '\'' +
                //", episodeNumber='" + episodeNumber + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getHeatText() {
        return heatText;
    }

    public void setHeatText(String heatText) {
        this.heatText = heatText;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTvId() {
        return tvId;
    }

    public void setTvId(String tvId) {
        this.tvId = tvId;
    }

    public String getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(String episodeNumber) {
        this.episodeNumber = episodeNumber;
    }
}
