/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpapersistence3;

import entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Nikolaj
 */
public class Facade {

    EntityManager manager;

    public Facade() {
        manager = Persistence.createEntityManagerFactory("PU").createEntityManager();
    }

    public List<Student> findAllStudents() {
        Query query = manager.createNamedQuery("Student.findAll");
        return query.getResultList();
    }

    public Student findByFirstName(String name) {
        Query query = manager.createNamedQuery("Student.findByFirstname");
        query.setParameter("firstname", name);
        return (Student) query.getSingleResult();
    }

    public Student findByLastName(String name) {
        Query query = manager.createNamedQuery("Student.findByLastname");
        query.setParameter("lastname", name);
        return (Student) query.getSingleResult();
    }

    public Long getTotalScores() {
        Query query = manager.createQuery("SELECT SUM(a.score) FROM Studypoint a");
        return (Long) query.getSingleResult();
    }

    public List<Student> getHighestScore() {
        Query query = manager.createNativeQuery("select MAX(total),student.ID FROM student,(select SUM(s.`SCORE`) as total from studypoint s Group by s.STUDENT_ID ORDER By `SCORE` DESC) g");

        List< Object[]> rows = query.getResultList();
        List<Student> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(manager.find(Student.class, row[1]));     
        }
        return result;
    }

//    
//    public List<Student> getLowestScore() {
//        Query query = manager.createQuery("SELECT b FROM Studypoint a Join a.studentId b WHERE a.score = (SELECT MIN(x.score) FROM Studypoint x)");
//        return query.getResultList();
//    }

public Long getTotalScoreByStudent(Student student) {
        Query query = manager.createQuery("SELECT SUM(a.score) FROM Studypoint a WHERE a.studentId = :studentid");
        query.setParameter("studentid", student);
        return (Long) query.getSingleResult();
    }

    public <T> void create(T entity) {
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(entity);
        transaction.commit();
    }

    public <T> void updateMany(T... entities) {
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        for (T entity : entities) {
            manager.merge(entity);
        }
        transaction.commit();
    }

    public <T> void delete(T entity) {
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.remove(entity);
        transaction.commit();
    }
}
