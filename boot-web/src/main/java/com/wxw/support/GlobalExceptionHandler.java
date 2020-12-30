package com.wxw.support;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({Exception.class})
    public Response globalExceptionHandler(Exception e, WebRequest request) {
        return handleException(e, request, null);
    }

    @ExceptionHandler({ServiceException.class})
    public Response BusinessExceptionHandler(ServiceException e, WebRequest request) {
        Response response;
        Result result = e.getResult();
        if (result == null) {
            response = new Response(Result.FAILURE, e.getMessage());
        } else {
            response = new Response(result, e.getMessage());
        }
        return handleException(e, request, response);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public Response missingServletRequestParameter(MissingServletRequestParameterException e, WebRequest request) {
        Response response = new Response(Result.REQUEST_PARAMETER_ERROR, "参数" + e.getParameterName() + "值不能为空");
        return handleException(e, request, response);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public Response constraintViolation(ConstraintViolationException e, WebRequest request) {
        ConstraintViolation constraintViolation = e.getConstraintViolations().iterator().next();
        Response response = new Response(Result.REQUEST_PARAMETER_ERROR, constraintViolation.getMessage());
        return handleException(e, request, response);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public Response methodArgumentTypeMismatch(MethodArgumentTypeMismatchException e, WebRequest request) {
        Response response = new Response(Result.REQUEST_PARAMETER_ERROR, "参数" + e.getName() + "值不正确");
        return handleException(e, request, response);
    }

    @ExceptionHandler({BindException.class})
    public Response bind(BindException e, WebRequest request) {
        FieldError error = e.getFieldErrors().get(0);
        String errorMessage = error.getDefaultMessage();
        if (errorMessage.startsWith("Failed")) {
            errorMessage = "参数" + error.getField() + "值不正确";
        }
        Response response = new Response(Result.REQUEST_PARAMETER_ERROR, errorMessage);
        return handleException(e, request, response);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Response methodArgumentNotValid(MethodArgumentNotValidException e, WebRequest request) {
        FieldError error = e.getBindingResult().getFieldErrors().get(0);
        String errorMessage = error.getDefaultMessage();
        Response response = new Response(Result.REQUEST_PARAMETER_ERROR, errorMessage);
        return handleException(e, request, response);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Response httpMessageNotReadable(HttpMessageNotReadableException e, WebRequest request) {
        Response response = new Response(Result.REQUEST_PARAMETER_ERROR, e.getCause().getMessage());
        return handleException(e, request, response);
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public Response httpMediaTypeNotSupported(HttpMediaTypeNotSupportedException e, WebRequest request) {
        MediaType mediaType = e.getSupportedMediaTypes().get(0);
        Response response = new Response(Result.REQUEST_PARAMETER_ERROR, "Content-Type必须为" + mediaType.getType() + "/" + mediaType.getSubtype());
        return handleException(e, request, response);
    }

    @ExceptionHandler({ClientAbortException.class})
    public Response clientAbort(ClientAbortException e, WebRequest request) {
        Response response = new Response(Result.REQUEST_PARAMETER_ERROR, "客户端断开连接");
        return handleException(e, request, response);
    }

    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public Response maxUploadSizeExceeded(MaxUploadSizeExceededException e, WebRequest request) {
        String maxFileSize = getMaxUploadFileSize(e);
        Response response = new Response(Result.REQUEST_PARAMETER_ERROR, "文件最大上传大小超过" + maxFileSize + "限制");
        return handleException(e, request, response);
    }

    private String getMaxUploadFileSize(MaxUploadSizeExceededException e) {
        String message = e.getMessage();
        if (message.contains("SizeLimitExceededException")) {
            String maxFileSize = message.substring(message.indexOf("maximum")).replaceAll("[^\\d]", "");
            if (StringUtils.isNumeric(maxFileSize)) {
                return Utils.formatSize(Long.valueOf(maxFileSize));
            } else {
                return "";
            }
        } else {
            return Utils.formatSize(e.getMaxUploadSize());
        }
    }

    private Response handleException(Exception e, WebRequest request, Response response) {
                HttpServletRequest httpServletRequest = ((ServletWebRequest) request).getRequest();
                if (httpServletRequest instanceof ContentCachingRequestWrapper) {
                    ContentCachingRequestWrapper contentCachingRequestWrapper = (ContentCachingRequestWrapper) httpServletRequest;
                    String body = new String(contentCachingRequestWrapper.getContentAsByteArray());
                    }
        if (response == null) {
            String sid = UUID.randomUUID().toString().replaceAll("-", "");
            log.error("SID[{}] of  {},message:{} ", sid, e.getClass().getName(), e.getMessage(), e);
        }
        return response;
    }

}