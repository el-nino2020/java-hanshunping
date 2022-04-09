package jdbc_dao.domain;

/**
 * 对应数据库中的person表
 */
public class Person {
    private Integer id;
    private String name;
    private Double salary;
    private Integer kpi;
    private Integer jobID;
    private Integer departID;
    private Integer mgr;

    //无参构造器用于反射
    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", kpi=" + kpi +
                ", jobID=" + jobID +
                ", departID=" + departID +
                ", mgr=" + mgr +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getKpi() {
        return kpi;
    }

    public void setKpi(Integer kpi) {
        this.kpi = kpi;
    }

    public Integer getJobID() {
        return jobID;
    }

    public void setJobID(Integer jobID) {
        this.jobID = jobID;
    }

    public Integer getDepartID() {
        return departID;
    }

    public void setDepartID(Integer departID) {
        this.departID = departID;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }
}
