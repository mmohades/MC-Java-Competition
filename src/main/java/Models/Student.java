package Models;

import java.util.HashMap;
import java.util.Map;

public class Student {


    private String name, school, level;
    private int id, place;
    private Team team;

    //TODO: consider place in both student and team.


    public Student(){ }



    public Student(String name, String school, String level){

        this.name = name;
        this.school = school;
        this.level = level;
    }

    public String getName() {
        return name;
    }
    public String getSchool() {
        return school;
    }
    public String getLevel(){return level;}
    public Team getTeam() {
        return team;
    }



    /**
     * Only once team can be set
     * @param team team of student
     */
    public void setTeam(Team team){

        if (this.team == null)
            this.team = team;
        else
            throw new RuntimeException("Team already assigned.");
    }


    @Override
    public String toString() {

        return String.valueOf(team.getNumber()) +
                ", " +
                name +
                ", " +
                school +
                ", " +
                level +
                ", " +
                team.calculateScore() +
                ", " +
                place +
                '\n';
    }

    @Override
    public int hashCode() {

        return this.id;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj.getClass() == obj.getClass())
            return this.id == ((Student) obj).id;

        return false;

    }
}
