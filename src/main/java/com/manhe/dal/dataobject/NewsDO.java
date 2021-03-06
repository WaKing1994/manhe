package com.manhe.dal.dataobject;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NewsDO implements Serializable {

    private Long id;
    private String name;
    private Integer priority;
    private String images;
    private String details;
    private String banner;
    private Integer viewCount;
    private Date createTime;
    //虚拟字段
    private String day;
    //虚拟字段
    private String month;

    public NewsDO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }


    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(createTime);
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        String dayStr = day < 10 ? "0" + day : day.toString();
        return dayStr;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(createTime);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH) + 1;
        String monthStr = month < 10 ? "0" + month : month.toString();
        return year + "-" + monthStr;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
