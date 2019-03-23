package Controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import Models.*;
import com.opencsv.CSVReader;





public class CSVController {




    public void export(File teamsInformation, File studentInformation, File outputFile) throws IOException {


        readData(teamsInformation, studentInformation);


    }



    private void readData(File teamsInformation, File studentInformation) throws IOException {


        Reader studentsData = Files.newBufferedReader(studentInformation.toPath());
        Reader judgesData = Files.newBufferedReader(teamsInformation.toPath());


        HashMap<Integer, Team> teams = readStudentsInformation(studentsData);
        updateScores(teams, judgesData);

    }









    private HashMap<Integer, Team> readStudentsInformation(Reader reader) throws IOException {

        HashMap<Integer,Team> teams = new HashMap<>();

        CSVReader csvReader = new CSVReader(reader, ',');
//        CSVReaderBuilder csvReader = new CSVReaderBuilder(reader);


        //order: "Name", School, Team#, Level
        //          0   , 1     , 2 ,   3
        String[] line;

        while ((line = csvReader.readNext()) != null) {

            if (line.length >= 4) {

                Integer teamNumber = Integer.parseInt(line[2]);
                Student newStd = new Student(line[0], line[1], line[3]);

                if(teams.containsKey(teamNumber))
                    teams.get(teamNumber).addMember(newStd);
                else {

                    Team newTeam = new Team(teamNumber);
                    newTeam.addMember(newStd);
                    teams.put(teamNumber, newTeam);

                }

            }


        }


        return teams;

    }



    private void updateScores(HashMap<Integer, Team> teams, Reader reader) throws IOException {

        //order: Team#, P1, P2, P3, P4, P5, P6
        //          0    1   2   3   4   5   6

        CSVReader csvReader = new CSVReader(reader, ',');

        String[] line;

        while ((line = csvReader.readNext()) != null) {

            int teamNumber = Integer.parseInt(line[0]);

            if(teams.containsKey(teamNumber)){

                //add all the problem's score
                for(int i = 1; i < 7; i++) {
                    int score = Integer.parseInt(line[i]);
                    teams.get(teamNumber).addProblemScore(i, score);
                }

            }


        }

    }



    private void exportCsv(HashMap<Integer, Team> teams, File file) throws IOException {

        StringBuilder result = new StringBuilder();
        String allStudentsData = mapToString(teams);

        //add subjects
        result.append(" \"Team#\", Student Name, School, Level, Score, Place\n");
        result.append(allStudentsData);

        saveFile(result.toString(), file);

    }


    private void saveFile(String content, File file) throws IOException {

        if (file != null) {

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();

        }
    }


    private String mapToString(HashMap<Integer, Team> teams){

        StringBuilder result = new StringBuilder();

        for(Map.Entry<Integer, Team> entry : teams.entrySet())

                result.append(entry.getValue());


        return result.toString();
    }

}
