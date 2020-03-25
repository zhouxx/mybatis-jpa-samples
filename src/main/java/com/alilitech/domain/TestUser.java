package com.alilitech.domain;

import com.alilitech.mybatis.jpa.anotation.GeneratedValue;
import com.alilitech.mybatis.jpa.anotation.*;
import com.alilitech.mybatis.jpa.parameter.GenerationType;
import com.alilitech.mybatis.jpa.parameter.TriggerValueType;
import com.alilitech.utils.DateUtil;
import org.apache.ibatis.mapping.SqlCommandType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "t_user")
public class TestUser {

    @Id
    @GeneratedValue(GenerationType.AUTO)
    private String id;

    private String name;

    private Integer sex;

    private Integer age;

    @TriggerValue(triggers = @Trigger(triggerType = SqlCommandType.INSERT, valueType = TriggerValueType.JavaCode, valueClass = DateUtil.class, methodName = "getDate"))
    @Column(name = "createTime")
    private Date createTime;

    private String deptNo;

    @ManyToOne
    @JoinColumn(name = "deptNo", referencedColumnName = "deptNo")
    @SubQuery(
            predicates = @SubQuery.Predicate(property = "deptNo",condition = "> '0'"),
            orders = @SubQuery.Order(property = "deptNo"))
    @MappedStatement(exclude = {"findPageSpecification", "findSpecification"})
    private TestDept dept;

    @MappedStatement(include = {"findById"})
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "roleId"))
    @SubQuery(
            predicates = {@SubQuery.Predicate(property = "roleCode",condition = "<> '0'"), @SubQuery.Predicate(property = "roleCode",condition = "> '0'")},
            orders = @SubQuery.Order(property = "roleCode"))
    private List<TestRole> roles;

    public TestUser() {
    }

    public TestUser(String id, String name, Integer sex, Integer age, String deptNo) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.deptNo = deptNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public TestDept getDept() {
        return dept;
    }

    public void setDept(TestDept dept) {
        this.dept = dept;
    }

    public List<TestRole> getRoles() {
        return roles;
    }

    public void setRoles(List<TestRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", createTime=" + createTime +
                ", deptNo='" + deptNo + '\'' +
                ", dept=" + dept +
                ", roles=" + roles +
                '}';
    }
}
