package com.alilitech.domain;

import com.alilitech.mybatis.jpa.anotation.GeneratedValue;
import com.alilitech.mybatis.jpa.parameter.GenerationType;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "t_role")
public class TestRole {

	/*
	 * 角色id
	 */
	@Id
	@GeneratedValue(value = GenerationType.AUTO)
	private String id;

	/*
	 * 角色名称
	 */
	//@Column(name = "role_name")
	private String roleName;

	/*
	 * 角色编码
	 */
	private String roleCode;

	/*
	 * 角色描述
	 */
	private String roleDescription;

	/**
	 * 用户列表
	 */
	@ManyToMany(mappedBy = "roles")
	private List<TestUser> users;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public List<TestUser> getUsers() {
		return users;
	}

	public void setUsers(List<TestUser> users) {
		this.users = users;
	}
}
