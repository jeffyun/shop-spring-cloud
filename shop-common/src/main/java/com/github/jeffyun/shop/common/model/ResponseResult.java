package com.github.jeffyun.shop.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author jeff
 * @date 2019/6/21 11:51
 */
@ApiModel(
        value = "ResponseResult（响应结果）",
        description = "数据"
)
public class ResponseResult<T> implements Serializable {

    @ApiModelProperty(
            value = "返回结果:true 成功 false失败",
            notes = " true成功 false失败",
            required = true
    )
    private boolean success;
    @ApiModelProperty("返回消息")
    private String msg;
    @ApiModelProperty("返回的业务数据")
    private T data;


    public static <T> ResponseResult<T> ok() {
        return (new ResponseResult()).setSuccess(true);
    }

    public static <T> ResponseResult<T> fail() {
        return (new ResponseResult()).setSuccess(false);
    }

    public static <T> ResponseResult<T> create(int record) {
        return new ResponseResult(record);
    }

    public ResponseResult() {
    }

    ResponseResult(int record) {
        this.success = record > 0;
    }


    public boolean isSuccess() {
        return success;
    }

    public ResponseResult<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseResult)) return false;

        ResponseResult<?> that = (ResponseResult<?>) o;

        if (isSuccess() != that.isSuccess()) return false;
        if (!getMsg().equals(that.getMsg())) return false;
        return getData().equals(that.getData());
    }

    @Override
    public int hashCode() {
        int result = (isSuccess() ? 1 : 0);
        result = 31 * result + getMsg().hashCode();
        result = 31 * result + getData().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ResponseResult.class.getSimpleName() + "[", "]")
                .add("success=" + success)
                .add("msg='" + msg + "'")
                .add("data=" + data)
                .toString();
    }
}
