package com.cdf.factory.consumer.repository;

import java.util.List;

import com.cdf.factory.consumer.domain.SysMenu;

/**
 * Created on 2018/2/6 0028.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface SysMenuRepository extends MerryyouRepository<SysMenu> {

	List<SysMenu> findAllByOrderByOrderNumAsc();

	List<SysMenu> findAllByMenuType(Byte menuType);

}
