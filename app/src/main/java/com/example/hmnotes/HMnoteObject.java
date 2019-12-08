package com.example.hmnotes;

public class HMnoteObject {
    private long id;
    private String subject;
    private String detail;
    private String createDate;
    private String createTime;

    //Empty Constructor
    HMnoteObject() {
        //empty
    }
    //no id constructor
    HMnoteObject(String setSubject, String setDetail, String setCreateDate, String setCreateTime) {
        subject = setSubject;
        detail = setDetail;
        createDate = setCreateDate;
        createTime = setCreateTime;
    }

    //Full constructor
    HMnoteObject(long setId, String setSubject, String setDetail, String setCreateDate,
                 String setCreateTime) {
        id = setId;
        subject = setSubject;
        detail = setDetail;
        createDate = setCreateDate;
        createTime = setCreateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
