package com.xweb.starter.common.exception;

import com.xweb.starter.utils.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RootErrorInfo {

    private Integer lineNumber;
    private String className;
    private String methodName;

    @Override
    public String toString() {
        return MessageUtil.getMessage("error_root_error_position", className,methodName,lineNumber+"");
    }
}
