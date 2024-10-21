package com.xweb.starter.modules.menus.service;

import com.xweb.starter.modules.menus.resp.MenuListResp;
import com.xweb.starter.modules.security.mapper.MastMenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MastMenuMapper menuMapper;

    public List<MenuListResp> list() {
        var dbList =  menuMapper.selectByExample(null);
        var result = new ArrayList<MenuListResp>();
        dbList.forEach(menuItem-> {
            var item = new MenuListResp();
            BeanUtils.copyProperties(menuItem, item);
            item.setMmName(menuItem.getMmName().getZh_CN());
            result.add(item);
        });
        return result;
    }

}
