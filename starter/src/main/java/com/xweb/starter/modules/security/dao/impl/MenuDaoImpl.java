package com.xweb.starter.modules.security.dao.impl;

import com.xweb.starter.common.enums.MenuTypeEnum;
import com.xweb.starter.modules.security.dao.MenuDao;
import com.xweb.starter.modules.security.domain.entity.MastMenu;
import com.xweb.starter.modules.security.domain.entity.MastMenuExample;
import com.xweb.starter.modules.security.mapper.MastMenuMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuDaoImpl implements MenuDao {

    private final MastMenuMapper menuMapper;

    @Override
    public List<MastMenu> selectValidMenus() {
        var example = new MastMenuExample();
        example.createCriteria().andMmStateEqualTo((short) 1);
        example.setOrderByClause("mm_id asc");
        return menuMapper.selectByExample(example);
    }

    @Override
    public String[] needAuthenticationUrlPath() {
        var result = new HashSet<String>();
        var menuExample = new MastMenuExample();
        menuExample.createCriteria().andMmStateEqualTo((short) 1).andMmTypeEqualTo(MenuTypeEnum.PATH.getType()).andMmPathIsNotNull();
        var pathMenus = menuMapper.selectByExample(menuExample);
        for (var menu : pathMenus) {
            result.add(menu.getMmPath());
        }
        menuExample = new MastMenuExample();
        menuExample.createCriteria().andMmStateEqualTo((short) 1).andMmTypeEqualTo(MenuTypeEnum.BUTTON.getType());
        var buttonMenus = menuMapper.selectByExample(menuExample);
        for (var buttonMenu : buttonMenus) {
            var count = StringUtils.countMatches(buttonMenu.getMmPath(), '/');
            var moduleUrlPath = buttonMenu.getMmPath()+"/**";
            if (count == 1){
                result.add(moduleUrlPath);
            }
        }
        return result.toArray(new String[0]);
    }

}
