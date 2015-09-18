/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Student;
import entity.Studypoint;
import jpapersistence3.Facade;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Nikolaj
 */
public class test {
    
    static Facade facade;

    @BeforeClass
    public static void setUpClass() {
        facade = new Facade();
    }
    
     @Test
     public void createStudent() {
         Student stud = new Student("Ole","Mortensen");
         facade.create(stud);
         
         assertEquals(stud, facade.findByFirstName("Ole"));
         
         facade.delete(stud);
     }
     
      @Test
     public void addStudyPoint() {
         Student stud = new Student("Allan","Petersen");
         Studypoint point = new Studypoint("Study of doom", 2, 1);
         facade.create(point);
         facade.create(stud);
         
         stud.addStudyPoint(point);
         point.setStudentId(stud);
         
         facade.updateMany(stud,point);
         
         assertEquals(1, facade.findByFirstName("Allan").getStudypointCollection().size());
         
         facade.delete(point);
         facade.delete(stud);
     }
}
