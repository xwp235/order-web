package com.xweb.starter.common.resp;

import com.xweb.starter.modules.security.domain.vo.MenuVO;
import lombok.Data;

import java.util.List;

@Data
public class LoginAccountPermissionsResp {

    private List<String> buttonList;
    private List<MenuVO> menuList;

}
