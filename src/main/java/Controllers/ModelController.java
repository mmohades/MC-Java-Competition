package Controllers;

public class ModelController {



    public static int extractNumber(String str){

        String number = str.replaceAll("[^0-9]+", "");

        return Integer.parseInt(number);

    }

}
