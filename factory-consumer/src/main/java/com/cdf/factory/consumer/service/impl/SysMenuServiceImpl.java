package com.cdf.factory.consumer.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdf.factory.consumer.domain.SysMenu;
import com.cdf.factory.consumer.dto.MenuDto;
import com.cdf.factory.consumer.repository.SysMenuRepository;
import com.cdf.factory.consumer.repository.SysUserRepository;
import com.cdf.factory.consumer.service.SysMenuService;

/**
 * Created on 2018/2/8.
 *
 * @author zlf
 * @since 1.0
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuRepository repository;

	@Autowired
	private SysUserRepository userRepository;

	@Override
	public List<MenuDto> getMenus(String username, int menuType) {
		Set<SysMenu> menuSet = new HashSet<>();
		userRepository.findByUsername(username).getRoles().forEach(e -> menuSet.addAll(e.getMenus()));

		List<MenuDto> menuDtoList = menuSet.stream().filter(e -> e.getMenuType() == menuType)
				.map(e -> new MenuDto(e.getId(), e.getPId(), e.getName(), e.getUrl())).distinct()
				.collect(Collectors.toList());

		return menuDtoList;
	}

	@Override
	public List<MenuDto> getMenusList() {
		return repository.findAll().stream().map(e -> new MenuDto(e.getId(), e.getPId(), e.getName(), e.getUrl()))
				.collect(Collectors.toList());
	}

	@Override
	public Set<String> getUrlByname(String username) {
		Set<SysMenu> mesnus = new HashSet<>();
		userRepository.findByUsername(username).getRoles().forEach(e -> mesnus.addAll(e.getMenus()));
		return mesnus.stream().map(e -> e.getUrl()).collect(Collectors.toSet());
	}

	@Override
	public String getPermissions(String username) {
		Set<SysMenu> mesnus = new HashSet<>();
		userRepository.findByUsername(username).getRoles().forEach(e -> mesnus.addAll(e.getMenus()));

		return mesnus.stream().map(e -> e.getPermission()).collect(Collectors.joining(","));
	}
}
