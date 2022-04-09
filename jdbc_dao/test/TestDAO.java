package jdbc_dao.test;

import jdbc_dao.dao.PersonDAO;
import jdbc_dao.domain.Person;

import java.util.List;

public class TestDAO {
    public static void main(String[] args) {
        PersonDAO personDAO = new PersonDAO();

        List<Person> list = personDAO.queryMultiRow("select * from person",
                Person.class);
        for (Person person : list) {
            System.out.println(person);
        }

        System.out.println("=======================================");

        Person person = personDAO.querySingleRow("select * from person where id = ?",
                Person.class, 200);
        System.out.println(person);
        System.out.println("=======================================");
        Object o = personDAO.queryScalar("select `name` from person where id =300");
        System.out.println(o);
        System.out.println("============================================");
        int rows = personDAO.update("delete from person where id = ?", 900);
        System.out.println(rows);
    }
}
