package com.xweb.starter.common.exception;

import com.xweb.starter.StarterApplication;
import com.xweb.starter.common.constants.Constants;
import com.xweb.starter.common.exception.enums.BusinessExceptionEnum;
import com.xweb.starter.common.resp.JsonResp;
import com.xweb.starter.utils.JsonUtil;
import com.xweb.starter.utils.LogUtil;
import com.xweb.starter.utils.MessageUtil;
import com.xweb.starter.utils.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.IOException;
import java.util.Objects;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResp handleBindException(BindException e) {
        var err = e.getFieldError();
        var resp = JsonResp.error()
                .setExceptionTypeWithTraceId(Constants.EXCEPTION_TYPE.WARN)
                .setCode(BusinessExceptionEnum.INVALID_REQUEST_PARAMETER.getCode());
        if (Objects.nonNull(err)) {
            if (Objects.nonNull(err.getDefaultMessage())) {
                return resp.setMsg(err.getDefaultMessage());
            }
        }
        var message = MessageUtil.getMessage("error_invalid_request_parameters");
        return resp.setMsg(message);
    }

    @ExceptionHandler({IllegalArgumentException.class, MissingServletRequestParameterException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResp requestParamException() {
        return JsonResp.error(MessageUtil.getMessage("error_invalid_request_parameters")).setCode(HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResp notAcceptableException() {
        return JsonResp.error(MessageUtil.getMessage("error_http_media_type_not_acceptable")).setCode(HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public JsonResp httpMediaTypeNotSupportedException() {
        return JsonResp.error(MessageUtil.getMessage("error_request_media_type_not_supported")).setCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonResp httpRequestMethodNotSupportedException() {
        return JsonResp.error(MessageUtil.getMessage("error_request_method_not_supported")).setCode(HttpStatus.METHOD_NOT_ALLOWED.value());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public void noResourceFoundException(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        if (RequestUtil.isAjaxRequest(request)) {
            setResponseDetails(resp);
            var result = JsonResp.error(MessageUtil.getMessage("error_resource_not_found")).setCode(HttpStatus.NOT_FOUND.value());
            resp.getWriter().write(JsonUtil.obj2Json(result));
        } else {
            resp.sendRedirect("/404");
        }
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResp noHandlerFoundException(NoHandlerFoundException e) {
        return JsonResp.error(e.getMessage()).setCode(HttpStatus.NOT_FOUND.value());
    }

    /**
     * 业务异常统一处理
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResp businessException(BusinessException e) {
        var resp = JsonResp.error(e.getMessage()).setCode(e.getCode())
                .setExceptionTypeWithTraceId(e.getType());
        var rootErrorInfo = getRootErrorInfo(e);

        if (e.shouldLog()) {
            if (Objects.nonNull(rootErrorInfo)) {
                LogUtil.error("Business " + e.getErrorInfo(), e);
            } else {
                LogUtil.error(MessageUtil.getMessage("Business exception occurred !"), e);
            }
        }
        return resp;
    }

    /**
     * 系统异常统一处理
     */
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResp systemException(SystemException e) {
        var resp = JsonResp.error(e.getMessage())
                .setCode(e.getCode())
                .setExceptionTypeWithTraceId(e.getType());

        var errorInfo = getRootErrorInfo(e);
        if (e.isShouldLog()) {
            if (Objects.nonNull(errorInfo)) {
                LogUtil.error("System " + errorInfo, e);
            } else {
                LogUtil.error("System exception occurred !", e);
            }
        }
        return resp;
    }

    /**
     * 系统未知异常处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResp exception(Exception e) {
        var message = MessageUtil.getMessage("error_server_internal_error");
        LogUtil.error(message,e);
        return JsonResp.error(message)
                .setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setExceptionTypeWithTraceId(Constants.EXCEPTION_TYPE.ERROR);
    }

    private RootErrorInfo getRootErrorInfo(Throwable e) {
        var rootCause = ExceptionUtils.getRootCause(e);
        if (Objects.isNull(rootCause)) {
            return null;
        }
        var stackTrace = rootCause.getStackTrace();
        if (ArrayUtils.isEmpty(stackTrace)) {
            return null;
        }
        // 获取
        var rootPackage = ClassUtils.getPackageName(StarterApplication.class);
        return getRootInfoDetail(stackTrace, rootPackage);
    }

    private RootErrorInfo getRootInfoDetail(StackTraceElement[] stackTrace, String rootPackage) {
        var info = stackTrace[0];
        for (var stackTraceElement : stackTrace) {
            var stackElStr = stackTraceElement.toString();
            if (stackElStr.contains(rootPackage)) {
                info = stackTraceElement;
                break;
            }
        }
        return new RootErrorInfo(info.getLineNumber(),info.getClassName(),info.getMethodName());
    }

    private void setResponseDetails(HttpServletResponse resp) {
        resp.setStatus(HttpStatus.OK.value());
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }


}
