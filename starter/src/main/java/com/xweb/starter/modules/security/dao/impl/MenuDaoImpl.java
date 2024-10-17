package com.xweb.starter.modules.security.dao.impl;

import com.xweb.starter.modules.security.dao.MenuDao;
import com.xweb.starter.modules.security.domain.entity.MastMenu;
import com.xweb.starter.modules.security.domain.entity.MastMenuExample;
import com.xweb.starter.modules.security.mapper.MastMenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

}
