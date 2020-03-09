package com.manhe.common;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class WebErrors {
    /**
     * email正则表达式
     */
    public static final Pattern EMAIL_PATTERN = Pattern
            .compile("^\\w+(\\.\\w+)*@\\w+(\\.\\w+)+$");

    private List<String> errors;
    private List<String> errorCodes;


    public WebErrors() {
    }

    public Boolean isNull(Object o, String field) {
        if (o == null || o == "") {
            addErrors("error.required", field + " 不能为空");
            return true;
        } else {
            return false;
        }
    }

    public Boolean isBlank(String s, String field, int maxLength) {
        if (StringUtils.isBlank(s)) {
            errors.add(field + " 不能为空");
            errorCodes.add("error.required");
            addErrors("error.required", field + " 不能为空");
            return true;
        }
        if (isMaxLength(s, field, maxLength)) {
            return true;
        }
        return false;
    }

    public Boolean isMaxLength(String s, String field, int maxLength) {
        if (s != null && s.length() > maxLength) {
            addErrors("error.maxLength", field + " 不能超过最大长度 " + maxLength);
            return true;
        }
        return false;
    }

    /**
     * 是否存在错误
     *
     * @return
     */
    public Boolean hasErrors() {
        return errors != null && errors.size() > 0;
    }

    /**
     * 错误数量
     *
     * @return
     */
    public Integer getCount() {
        return errors == null ? 0 : errors.size();
    }

    public void addErrors(String errorCode, String error) {
        if (this.errors == null) {
            errors = new ArrayList<String>();
        }
        if (this.errorCodes == null) {
            errorCodes = new ArrayList<String>();
        }
        this.errors.add(error);
        this.errorCodes.add(errorCode);
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public void addErrorCode(String errorCode) {
        this.errorCodes.add(errorCode);
    }

    /**
     * 错误列表
     *
     * @return
     */
    public List<String> getErrors() {
        if (errors == null) {
            errors = new ArrayList<String>();
        }
        return errors;
    }

    public List<String> getErrorCodes() {
        if (errorCodes == null) {
            errorCodes = new ArrayList<String>();
        }
        return errorCodes;
    }


    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void setErrorCodes(List<String> errorCodes) {
        this.errorCodes = errorCodes;
    }

}
