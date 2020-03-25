package com.alilitech.mapper;

import com.alilitech.domain.TestRole;
import com.alilitech.mybatis.jpa.domain.Pageable;
import com.alilitech.mybatis.jpa.domain.Sort;
import com.alilitech.mybatis.jpa.mapper.PageMapper;

import java.util.List;

public interface TestRoleMapper extends PageMapper<TestRole, String> {

	List<TestRole> findPageByRoleName(Pageable page, Sort sort, String roleName);

}
