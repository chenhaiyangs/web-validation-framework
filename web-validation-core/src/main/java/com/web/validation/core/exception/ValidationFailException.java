package com.web.validation.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 参数验证失败异常
 * @author chenhaiyang
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class ValidationFailException extends Exception {
    /**
     * 错误码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;
    public ValidationFailException(String code, String message) {
        super("code:"+code+",message:"+message);
        this.code = code;
        this.message = message;

    }
}
