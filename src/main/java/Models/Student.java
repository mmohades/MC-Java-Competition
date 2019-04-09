package Models;


public class Student{


    private String name, school, level;
    private int id, place;
    private Team team;


    public Student(){ }


    /**
     *
     * @param name
     * @param school
     * @param level
     */
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

    public Integer getScore(){

        return this.team.calculateScore();
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

    public void setPlace(int place){

        this.place = place;
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

        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if(obj.getClass() == obj.getClass())
            return this.id == ((Student) obj).id;

        return false;

    }

}
