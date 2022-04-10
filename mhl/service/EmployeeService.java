package mhl.service;

import mhl.dao.EmployeeDAO;
import mhl.domain.Employee;

/**
 * 员工相关的业务
 */
public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    /**
     * 用于用户登录
     * @param empId 员工id
     * @param pwd   员工密码
     * @return      如果登录成功，返回Employee对象;如果登录失败，返回null
     */
    public Employee logIn(String empId, String pwd) {
        String sql = "select * from employee where emp_id = ? and pwd =md5(?);";
        return employeeDAO.querySingleRow(sql, Employee.class, empId, pwd);
    }

}
