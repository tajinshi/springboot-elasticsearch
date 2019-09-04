package com.elasticsearch.controller;
import com.elasticsearch.bean.Employee;
import com.elasticsearch.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linzhiqiang
 */
@RestController
@RequestMapping("es")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 添加
     * @return
     */
    @RequestMapping("add")
    public String add() {
        long start = System.currentTimeMillis();
        for (int i = 50000; i < 51000; i++) {
            Employee employee = new Employee();
            employee.setId(i+"");
            employee.setFirstName("hah");
            employee.setLastName("ta");
            employee.setAge(i);
            employee.setAbout("i am in peking");
            employeeRepository.save(employee);
        }
        long end = System.currentTimeMillis();

        System.err.println("add a obj"+(end - start)/1000+"s");
        return "success";
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping("delete")
    public String delete() {
        Employee employee = employeeRepository.queryEmployeeById("1");
        employeeRepository.delete(employee);
        return "success";
    }

    /**
     * 局部更新
     * @return
     */
    @RequestMapping("update")
    public String update() {
        Employee employee = employeeRepository.queryEmployeeById("1");
        employee.setFirstName("哈哈");
        employeeRepository.save(employee);
        System.err.println("update a obj");
        return "success";
    }
    /**
     * 查询
     * @return
     */
    @RequestMapping("query")
    public List<Employee> queryAll() {
//        Employee accountInfo = employeeRepository.queryEmployeeById("1");
        Iterable<Employee> all = employeeRepository.findAll();
        List<Employee> list = new ArrayList<>();
        for (Employee employee : all) {
            list.add(employee);
        }
        System.out.println(list.size());
        return list;
    }
    @RequestMapping("queryName")
    public List<Employee> queryDistinctByFirstNameAndLastName() {
//        Employee accountInfo = employeeRepository.queryEmployeeById("1");
        List<Employee> all = employeeRepository.queryAllByFirstNameOrLastName("hah","ta");

        return all;
    }


}
