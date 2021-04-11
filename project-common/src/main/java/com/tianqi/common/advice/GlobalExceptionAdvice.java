package com.tianqi.common.advice;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.exception.BaseException;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.result.rest.entity.ValidateEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 异常处理器
 *
 * @author yuantianqi
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 校验出现错误
     *
     * @param validateEx
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public ResultEntity validateHandler(Exception validateEx) {

        BindingResult bindingResult = null;
        if (validateEx instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) validateEx).getBindingResult();
        }else{
            bindingResult = (BindingResult) validateEx;
        }
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        List<ValidateEntity> validates = new ArrayList<>();
        allErrors.stream().forEach(error -> {
            ValidateEntity validateEntity = new ValidateEntity();
            if (error instanceof FieldError) {
                validateEntity.setFields(((FieldError) error).getField());
            }
            validateEntity.setMessage(error.getDefaultMessage());
            validates.add(validateEntity);
        });
        return RestResult.builder()
                .withValidates(validates)
                .withStatus(StatusEnum.VALIDATE_PARAM_ERROR)
                .build();
    }

    /**
     * 处理JSON解析异常
     *
     * @param jsonParseEx
     * @return
     */
    @ExceptionHandler(value = JsonMappingException.class)
    public ResultEntity jsonParseHandler(JsonMappingException jsonParseEx) {

        List<String> errorField = jsonParseEx.getPath().stream().map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.toList());
        ValidateEntity validateEntity = new ValidateEntity();
        validateEntity.setFields(errorField.get(0));
        validateEntity.setMessage(jsonParseEx.getOriginalMessage());
        return RestResult.builder()
                .withValidate(validateEntity)
                .withStatus(StatusEnum.PARAM_PARSE_ERROR)
                .build();
    }

    /**
     * 基础异常
     *
     * @param baseException
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public ResultEntity baseException(BaseException baseException) {
        return RestResult.builder()
                .withStatus(StatusEnum.BUS_ERROR)
                .withError(baseException)
                .build();
    }

    /**
     * 其他异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResultEntity exception(Exception exception) {
        BaseException baseException = new BaseException(exception.getMessage());
        baseException.setStackTrace(exception.getStackTrace());
        return RestResult.builder()
                .withStatus(StatusEnum.SERVER_ERROR)
                .withError(baseException)
                .build();
    }
}
