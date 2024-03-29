package com.elasticsearch.dao;
import com.elasticsearch.bean.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author linzhiqiang
 */
@Component
public interface EmployeeRepository extends ElasticsearchRepository<Employee,String> {

    /**
     * 查询雇员信息
     *
     * @param id
     * @return
     */
    Employee queryEmployeeById(String id);

    List<Employee> queryAllByFirstNameOrLastName(String fName, String lasName);

}