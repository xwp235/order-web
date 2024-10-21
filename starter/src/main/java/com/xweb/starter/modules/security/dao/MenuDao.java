package com.xweb.starter.modules.security.dao;

import com.xweb.starter.modules.security.domain.entity.MastMenu;

import java.util.List;

public interface MenuDao {

    List<MastMenu> selectValidMenus();

    String[]  needAuthenticationUrlPath();

}
