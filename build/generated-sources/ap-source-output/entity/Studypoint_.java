package entity;

import entity.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-09-18T15:13:53")
@StaticMetamodel(Studypoint.class)
public class Studypoint_ { 

    public static volatile SingularAttribute<Studypoint, Student> studentId;
    public static volatile SingularAttribute<Studypoint, Integer> score;
    public static volatile SingularAttribute<Studypoint, Integer> maxval;
    public static volatile SingularAttribute<Studypoint, String> description;
    public static volatile SingularAttribute<Studypoint, Integer> id;

}