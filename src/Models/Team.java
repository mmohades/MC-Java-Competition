package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Team {


    private int number;

    private ArrayList<Student> teamMembers;
    private HashMap<String, Integer> problemsMap = new HashMap<String, Integer>();


    public Team(int number){
        this.number = number;
    }

    public Team(ArrayList<Student> students){
        initProblems();
        this.teamMembers = students;
    }





    public int getNumber() {
        return number;
    }

    public ArrayList<Student> getTeamMembers() {
        return teamMembers;
    }

    public int calculateScore() {

        int sum = 0;

        for(HashMap.Entry<String, Integer> problem : problemsMap.entrySet())

            sum += problem.getValue();


        return sum;

    }


    /**
     *
     * @param st1: Student
     */
    public void addMember(Student st1){

        if (teamMembers.size() >=2)
            throw new RuntimeException("There are more than two students in the team");

        teamMembers.add(st1);
        st1.setTeam(this);
    }


    /**
     * add new problem's score
     * @param problemKey should be P1, P2, ...
     * @param pScore : int, score to that problem
     */
    public void addProblemScore(Integer problemKey, int pScore){

        String problemK = "P" + problemKey.toString();

        if(problemsMap.containsKey(problemK))

            problemsMap.put(problemK, pScore);

        else
            throw new RuntimeException("Key not found");

    }



    /**
     * init the problems map with only six problems.
     */

    private void initProblems(){

        problemsMap = new HashMap<String, Integer>() {{
            put("P1", 0);
            put("P2", 0);
            put("P3", 0);
            put("P4", 0);
            put("P5", 0);
            put("P6", 0);
        }};
    }


    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        for(Student st : this.teamMembers)
            result.append(st.toString());

        return result.toString();

    }

    @Override
    public int hashCode() {

        return this.number;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj.getClass() == this.getClass())
            return this.number == ((Team) obj).number;
        else
            return false;
    }

}
