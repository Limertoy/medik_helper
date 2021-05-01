package sample;

import obiekty.Wyposazenie;
import services.WyposazenieService;
import java.util.Date;


public class App {

    public static void main(String[] args) {





//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//
//
//        Student student = new Student();
//        student.setFirstName("John");
//        student.setLastName("Bloch");
//        student.setContactNo("+1-408-575-1317");
//
//        session.save(student);
//        session.getTransaction().commit();
//
//        Query<Student> q = session.createQuery("From Student", Student.class);
//
//        List<Student> resultList = q.list();
//        System.out.println("total sudents:" + resultList.size());
//
//        for (Student s : resultList) {
//            System.out.println("student : " + s);
//        }
        Date date = new Date();

         WyposazenieService service = new WyposazenieService();

        Wyposazenie wyp = new Wyposazenie("test","lek",date,5);

        service.saveOrUpdate(wyp);

        System.out.println(service.findAll());



    }
}
