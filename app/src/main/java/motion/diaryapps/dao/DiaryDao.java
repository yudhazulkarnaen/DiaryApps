package motion.diaryapps.dao;

import java.io.Serializable;
import java.util.Date;

import motion.diaryapps.utils.Tools;

public class DiaryDao implements Serializable {
    private String id;
    private String title;
    private String description;
    private String url_cover;
    private String date;

    public DiaryDao() {
    }

    public DiaryDao(String id, String title, String description, String url_cover, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url_cover = url_cover;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl_cover() {
        return url_cover;
    }

    public void setUrl_cover(String url_cover) {
        this.url_cover = url_cover;
    }

    public String getDate() {
        return date;
    }

    public String getTitleDate() {
        return Tools.getTitleDate(date);
    }

    public String getNormalDate() {
        return Tools.getNormalDate(date);
    }

    public Date getValueDate() {
        return Tools.getDateFromTimestamp(date);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDate(Date date) {
        this.date = Tools.setTimestampFromDate(date);
    }

    public void setDate() {
        this.date = Tools.getCurrentDateISO8601();
    }
}
