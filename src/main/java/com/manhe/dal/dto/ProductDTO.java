package com.manhe.dal.dto;


import com.manhe.dal.dataobject.ProductDO;


public class ProductDTO extends ProductDO {

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
