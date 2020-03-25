package com.alilitech.domain;

import com.alilitech.mybatis.jpa.anotation.GeneratedValue;
import com.alilitech.mybatis.jpa.parameter.GenerationType;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "t_dept")
public class TestDept {

    @Id
    //@GeneratedValue(generatorClass = MyGenerator.class)
    @GeneratedValue(GenerationType.UUID)
    private String deptId;

    private String deptNo;

    private String deptName;

    @OneToMany(mappedBy = "dept")
    private List<TestUser> testUserList;

    public TestDept() {
    }

    public TestDept(String deptNo, String deptName) {
        this.deptNo = deptNo;
        this.deptName = deptName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<TestUser> getTestUserList() {
        return testUserList;
    }

    public void setTestUserList(List<TestUser> testUserList) {
        this.testUserList = testUserList;
    }

    @Override
    public String toString() {
        return "TestDept{" +
                "deptId='" + deptId + '\'' +
                ", deptNo='" + deptNo + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
