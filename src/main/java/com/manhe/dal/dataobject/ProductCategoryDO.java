package com.manhe.dal.dataobject;


import java.io.Serializable;

public class ProductCategoryDO implements Serializable {

    private Long id;
    private String name;
    private Integer priority;

    public ProductCategoryDO() {
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
}
