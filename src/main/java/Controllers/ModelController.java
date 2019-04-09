package Controllers;

import Models.Team;

public class ModelController {


//  If team score exist, but not student exist with that team number, then it won't show it in the output.
    //

    public static int extractNumber(String str){

        String number = str.replaceAll("[^0-9]+", "");
        if(number.equals(""))
            return 0;

        return Integer.parseInt(number);

    }

    static class Place{

        int place;
        Team team;

        Place(int place, Team team){
            this.place = place;
            this.team = team;
        }



}
}
