/**
 *    Copyright 2017-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import com.AppStart;
import com.alilitech.domain.TestDept;
import com.alilitech.domain.TestUser;
import com.alilitech.mapper.TestDeptMapper;
import com.alilitech.mapper.TestUserMapper;
import com.alilitech.mybatis.jpa.criteria.specification.Specifications;
import com.alilitech.mybatis.jpa.domain.Direction;
import com.alilitech.mybatis.jpa.domain.Order;
import com.alilitech.mybatis.jpa.domain.Page;
import com.alilitech.mybatis.jpa.domain.Sort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Zhou Xiaoxiang
 * @since 1.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppStart.class)
public class JpaTest {

    @Autowired
    private TestUserMapper testUserMapper;

    @Autowired
    private TestDeptMapper testDeptMapper;

    @Test
    public void insertTest() {

        TestUser testUser = new TestUser("1", "Jack", 1, 20, "002");
        testUserMapper.insert(testUser);

        TestUser testUser1 = new TestUser("2", "Hellen", 0, 20, "003");
        TestUser testUser2 = new TestUser("3", "Tom", null, 20, "003");
        testUserMapper.insertSelective(testUser1);
        testUserMapper.insertSelective(testUser2);

        testDeptMapper.insertBatch(Arrays.asList(new TestDept("002", "Dept2"), new TestDept("003", "Dept3")));

        testUserMapper.insert(new TestUser("4", "Test", 1, 18, "004"));
        testUserMapper.insert(new TestUser("5", "Test", 1, 18, "004"));
        testUserMapper.insert(new TestUser("6", "Test", 1, 18, "004"));
    }

    @Test
    public void updateTest() {

        TestUser testUser = new TestUser("1", "Jack", 1, 21, "002");
        testUserMapper.update(testUser);

        testUser = new TestUser();
        testUser.setId("1");
        testUser.setName("Jackson");
        testUserMapper.updateSelective(testUser);

        TestUser testUser1 = new TestUser("2", "Hellen", 0, 22, "003");
        TestUser testUser2 = new TestUser("3", "Tom", 1, 23, "003");
        testUserMapper.updateBatch(Arrays.asList(testUser1, testUser2));

    }

    @Test
    public void findTest() {

        Page page = new Page();
        page.setPage(1);
        page.setSize(2);

        Sort sort = new Sort();
        sort.setOrders(Arrays.asList(new Order(Direction.DESC, "id")));

        System.out.println(testUserMapper.findAll());
        System.out.println(testUserMapper.findAllById(Arrays.asList("1", "2")));
        System.out.println(testUserMapper.findById("2"));
        System.out.println(testUserMapper.findByName("Jackson").isPresent());
        System.out.println(testUserMapper.findAllPage(page));
        System.out.println(testUserMapper.findAllPageSort(page, sort));
        System.out.println(testUserMapper.findPageByNameLikeAndDeptNo(page, sort, null, "002"));
        System.out.println(testUserMapper.findByNameLikeAndDeptNo(null, null));
        System.out.println(testUserMapper.findList());
        System.out.println(testUserMapper.findByNameStartsWithAndDeptNoLikeOrderByNameDesc("Jack", "002"));
        System.out.println(testUserMapper.findByNameStartsWithOrDeptNoAndAgeGreaterThan("Jack", "002", 18));
        System.out.println(testUserMapper.countByNameAndDeptNo("Jackson", "002"));
        System.out.println(testUserMapper.existsByNameAndDeptNo("Jackson", "002"));
        System.out.println(testUserMapper.existsById("1"));

    }

    @Test
    public void findSpecificationTest() {

        //WHERE ( dept_no = ? AND ( age > ? AND name like ?) ) order by name ASC
        List<TestUser> testUsers = testUserMapper.findSpecification(Specifications.and()
                .equal("deptNo", "002")
                .nested(builder -> {
                    builder.and()
                            .greaterThan("age", 18)
                            .like("name", "Jack");
                })
                .order().asc("name").build());

        System.out.println(testUsers);

        Page page = new Page();
        page.setPage(1);
        page.setSize(2);

        testUsers = testUserMapper.findPageSpecification(page, Specifications.and()
                .equal("deptNo", "002")
                .order().asc("name").build());

        System.out.println(testUsers);

        testUsers = testUserMapper.findCustomSpecification(Specifications
                .order().asc("name").build());

        System.out.println(testUsers);
    }

    @Test
    public void deleteTest() {
        testUserMapper.deleteById("4");
        testUserMapper.deleteBatch(Arrays.asList("5", "6"));
        testUserMapper.deleteByNameAndDeptNo("Jackson", "002");
    }

}
