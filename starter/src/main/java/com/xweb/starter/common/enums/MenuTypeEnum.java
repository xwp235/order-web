package com.xweb.starter.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MenuTypeEnum {

    PATH((short) 1),
    BUTTON((short) 2),
    URL((short) 3),
    LINK((short) 4);

    private final short type;

}
