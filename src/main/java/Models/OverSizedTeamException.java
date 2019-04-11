package Models;

public class OverSizedTeamException extends Exception{


    OverSizedTeamException(int teamNumber, String level){

        super("There are more than two teams in the team number " + teamNumber + "  " + level);
    }
}
