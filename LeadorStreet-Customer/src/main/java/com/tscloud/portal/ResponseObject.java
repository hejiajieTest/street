package com.tscloud.portal;

import com.tscloud.common.framework.Constants;

/**
 *
 * Created by Administrator on 2015/12/24.
 */
public class ResponseObject {

    private String status;
    private String message;
    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 因在table_template.js文件中，请求deleteByIds方法时，
     * 如果删除成功，需要对status和content同时校验
     * 固，此处增加success方法，用来固定后台删除成功后的调用，
     * 避免前后台数据格式不同意导致前台提示删除失败假象
     *
     * @author vain@ccuu.me
     */
    public ResponseObject success(){
        this.setContent(true);
        this.setStatus(Constants.jsonView.STATUS_SUCCESS);
        return this;
    }

    public ResponseObject successPack(Object result){
        this.setMessage("");
        this.setContent(result);
        this.setStatus(Constants.jsonView.STATUS_SUCCESS);
        return this;
    }

    public ResponseObject successPack(Object result,String msg){
        this.setContent(result);
        this.setMessage(msg);
        this.setStatus(Constants.jsonView.STATUS_SUCCESS);
        return this;
    }

    public ResponseObject failPack(Exception e){
        setMessage(e.getMessage());
        setContent(null);
        setStatus(Constants.jsonView.STATUS_FAIL);
        return this;
    }

    public ResponseObject failPack(String errMsg){
        setMessage(errMsg);
        setContent(null);
        setStatus(Constants.jsonView.STATUS_FAIL);
        return this;
    }

    public ResponseObject failPack(Object result,String errMsg){
        setMessage(errMsg);
        setContent(result);
        setStatus(Constants.jsonView.STATUS_FAIL);
        return this;
    }

    public ResponseObject failPackMessage(String errMsg,Object content){
        setMessage(errMsg);
        setContent(content);
        setStatus(Constants.jsonView.STATUS_FAIL);
        return this;
    }
}
