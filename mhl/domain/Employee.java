package mhl.domain;

@SuppressWarnings({"unused"})
public class Employee {
    private Integer id;
    private String emp_id;
    private String pwd;
    private String name;
    private String job;

    public Employee() {
    }

    public Employee(Integer id, String emp_id, String pwd, String name, String job) {
        this.id = id;
        this.emp_id = emp_id;
        this.pwd = pwd;
        this.name = name;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
