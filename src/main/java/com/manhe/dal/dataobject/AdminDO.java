package com.manhe.dal.dataobject;


import java.io.Serializable;
import java.util.Date;

public class AdminDO implements Serializable {

    private Long id;
    private String username;
    private String phone;
    private String password;
    private Long roleId;
    private Date createTime;
    private Boolean isDeleted;
    private Date updateTime;

    public AdminDO(String username, String phone, Long roleId, String password, Boolean isDeleted){
        this.username = username;
        this.phone = phone;
        this.roleId = roleId;
        this.password = password;
        this.isDeleted = isDeleted;
    }

    public void init(){
        this.isDeleted = false;
    }

    public AdminDO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
