/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpapersistence3;

import entity.Student;

/**
 *
 * @author Nikolaj
 */
public class JPAPersistence3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Facade facade = new Facade();
        
        for (Student s : facade.findAllStudents()){
            System.out.println(s);
        }
        
        System.out.println(facade.findByFirstName("jan"));
        
        Student student = facade.findByLastName("Olsen");
        
        System.out.println(student);
        
        System.out.println(facade.getTotalScoreByStudent(student));
        System.out.println(facade.getTotalScores());
        
        System.out.println(facade.getHighestScore());
//        
//        System.out.println(facade.getLowestScore());
    }
    
}
