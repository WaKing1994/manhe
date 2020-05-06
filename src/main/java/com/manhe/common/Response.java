package com.manhe.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 用于返回统一数据格式
 */
@Data
public class Response implements Serializable {

    private static final long serialVersionUID = -5809782578272943999L;

    private String code;
    private String message;
    private Boolean success;
    private Object data;
    private Integer totalCounts;
    public Response() {
        this.code = "200";
        this.message = null;
        this.success = true;
        this.data = null;
        this.totalCounts = 0;
    }

    public Response buildFailure(WebErrors webErrors) {
        if (webErrors.getErrorCodes() != null && webErrors.getErrorCodes().size() > 0) {
            this.setCode(code = webErrors.getErrorCodes().get(0));
        }
        if (webErrors.getErrors() != null && webErrors.getErrors().size() > 0) {
            this.setMessage(webErrors.getErrors().get(0));
        }
        this.setSuccess(false);
        return this;
    }

    public void setError(String message){
        this.success = false;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
    }
}
