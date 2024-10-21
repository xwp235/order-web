package com.xweb.starter.modules.security.service;

import com.xweb.starter.common.enums.MenuTypeEnum;
import com.xweb.starter.common.resp.LoginAccountPermissionsResp;
import com.xweb.starter.modules.security.SecurityUtil;
import com.xweb.starter.modules.security.config.authorization.voting.VotingStrategy;
import com.xweb.starter.modules.security.config.authorization.voting.impl.AffirmativeBasedVotingStrategy;
import com.xweb.starter.modules.security.config.metadatasource.PermissionMetadataSource;
import com.xweb.starter.modules.security.dao.MenuDao;
import com.xweb.starter.modules.security.domain.entity.MastMenu;
import com.xweb.starter.modules.security.domain.vo.MenuVO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final MenuDao menuDao;
    private final PermissionMetadataSource permissionMetadataSource;
    private final VotingStrategy votingStrategy = new AffirmativeBasedVotingStrategy();

    public LoginAccountPermissionsResp getLoginAccountPermissions() {

        var allMenus = menuDao.selectValidMenus();
        var buttonList = new ArrayList<String>();
        List<MenuVO> menuList = new ArrayList<>();
        var resp = new LoginAccountPermissionsResp();

        var authentication = SecurityUtil.getAuthentication();
        var authorizedMenus = new ArrayList<MastMenu>();
        // 对allMenus里的菜单进行验权，不是超级管理员就需要校验权限，判断哪些菜单和按钮允许被访问
        for (var everyMenu: allMenus) {
            var path = everyMenu.getMmPath();
            var method = everyMenu.getMmMethod();
            if (StringUtils.isAnyBlank(path,method) || !everyMenu.getMmRequireAuth()) {
                authorizedMenus.add(everyMenu);
                continue;
            }
            var configAttr = permissionMetadataSource.getRequiredAttributes(everyMenu.getMmPath(), everyMenu.getMmMethod(), false);
            if (CollectionUtils.isEmpty(configAttr)){
               authorizedMenus.add(everyMenu);
               continue;
            }
            var decision = votingStrategy.vote(authentication, configAttr);
            if (decision.isGranted()){
               authorizedMenus.add(everyMenu);
            }
        }
        for (var menu : authorizedMenus) {
            var menuType = menu.getMmType();
            // 得到所有按钮权限
            if (menuType.equals(MenuTypeEnum.BUTTON.getType())){
                buttonList.add(menu.getMmCode());
            }
            if (menuType.equals(MenuTypeEnum.PATH.getType()) || menuType.equals(MenuTypeEnum.URL.getType())){
                var menuVO = new MenuVO();
                menuVO.setId(menu.getMmId());
                menuVO.setParentId(menu.getMmParentId());
                menuVO.setPath(menu.getMmPath());
                menuVO.setIcon(menu.getMmIcon());
                menuVO.setMenuName(menu.getMmName());
                menuVO.setMenuType(menu.getMmType());
                menuVO.setSort(menu.getMmSort());
                menuVO.setLevel(menu.getMmLevel());
                menuVO.setLevelChain(menu.getMmLevelChain());
                menuList.add(menuVO);
            }
        }

        // 递归生成树状菜单
        menuList = generateTreeMenu(menuList, null);
        menuList.removeIf(item -> StringUtils.isBlank(item.getPath()) && CollectionUtils.isEmpty(item.getChildren()));

        resp.setButtonList(buttonList);
        resp.setMenuList(menuList);

        return resp;
    }

    private List<MenuVO> generateTreeMenu(List<MenuVO> allMenus, Integer parentId) {
        var menuList = new ArrayList<MenuVO>();
        var childTreeMenuList = new ArrayList<MenuVO>();
        for (var item : allMenus) {
            if (item.getParentId()==null || item.getParentId().equals(parentId)){
                menuList.add(item);
            } else {
                childTreeMenuList.add(item);
            }
        }
        menuList.sort(Comparator.comparingInt(MenuVO::getSort));
        childTreeMenuList.sort(Comparator.comparingInt(MenuVO::getSort));
        for (var topMenu : menuList) {
            var childrenMenuList = new ArrayList<MenuVO>();
            for (var childMenu : childTreeMenuList) {
                if (childMenu.getParentId().equals(topMenu.getId())) {
                    childrenMenuList.add(childMenu);
                }
            }
            if (CollectionUtils.isNotEmpty(childrenMenuList)) {
                generateTreeMenu(childrenMenuList,topMenu.getId());
            }
            topMenu.setChildren(childrenMenuList);
        }
        return menuList;
    }

}
