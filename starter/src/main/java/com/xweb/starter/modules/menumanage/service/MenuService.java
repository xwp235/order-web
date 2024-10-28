package com.xweb.starter.modules.menumanage.service;

import com.xweb.starter.modules.menumanage.resp.MenuListResp;
import com.xweb.starter.modules.security.domain.entity.MastMenu;
import com.xweb.starter.modules.security.domain.entity.MastMenuExample;
import com.xweb.starter.modules.security.mapper.MastMenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    public MastMenu getById(Integer id) {
        var example = new MastMenuExample();
        example.createCriteria().andMmIdEqualTo(id);
        var menuList = menuMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(menuList)){
            return null;
        }
        return menuList.get(0);
    }

    public void saveOrUpdate(MastMenu menu) {

    }

}
