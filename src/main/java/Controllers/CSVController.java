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
import com.opencsv.CSVReaderBuilder;


public class CSVController {



    public void export(File file1, File file2, File outputFile) throws IOException, FileFormatException {


        HashMap<FileType, File> files = distinguishFiles( new File[] {file1, file2});


        HashMap<Integer, Team> team = readData(files.get(FileType.judgesFile), files.get(FileType.studentsInformation));

        exportCsv(team, outputFile);

    }

    private HashMap<Integer, Team> readData(File teamsInformation, File studentInformation) throws IOException {


        Reader studentsData = Files.newBufferedReader(studentInformation.toPath());
        Reader judgesData = Files.newBufferedReader(teamsInformation.toPath());


        HashMap<Integer, Team> teams = readStudentsInformation(studentsData);
        updateScores(teams, judgesData);

        return teams;

    }






    private HashMap<Integer, Team> readStudentsInformation(Reader reader) throws IOException {

        HashMap<Integer,Team> teams = new HashMap<>();

        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();


        String[] line;

        while ((line = csvReader.readNext()) != null) {

            removeSpaces(line);

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

        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

        String[] line;

        while ((line = csvReader.readNext()) != null) {

            removeSpaces(line);


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

    private void updatePlace(){

        //TODO: Update students place by their score


    }




    private HashMap<FileType, File> distinguishFiles(File[] files) throws IOException, FileFormatException {

        //MAKE SURE THERE ARE TWO ITEMS IN THE ABOVE

        Reader reader1 = Files.newBufferedReader(files[0].toPath());
        Reader reader2 = Files.newBufferedReader(files[1].toPath());

        HashMap<FileType, File> result = new HashMap<>();

        FileType typeFile1 = checkFileType(reader1);
        FileType typeFile2 = checkFileType(reader2);


        if(typeFile1 == FileType.None ||
                typeFile2 == FileType.None ||
                typeFile1 == typeFile2)

            throw new FileFormatException("One of the files first row format is not correct.");


        result.put(typeFile1, files[0]);
        result.put(typeFile2, files[1]);


    return result;
    }



    private static FileType checkFileType(Reader reader) throws IOException {

        CSVReader csvReader = new CSVReader(reader, ',');

        String[] line;
        String[] order1 = {"Name", "School", "Team#", "Level"};
        String[] order2 = {"Team#", "P1", "P2", "P3", "P4", "P5", "P6"};


        if((line = csvReader.readNext()) != null) {

            removeSpaces(line);
            printList(line);


            if (line.length == 4) {

                boolean isStudentsInfo = order1[0].equals(line[0]) &&
                        order1[1].equals(line[1]) &&
                        order1[2].equals(line[2]) &&
                        order1[3].equals(line[3]);

                if (isStudentsInfo)
                    return FileType.studentsInformation;

            } else if (line.length == 7) {


                boolean isJudgesInfo =

                                order2[0].equals(line[0]) &&
                                order2[1].equals(line[1]) &&
                                order2[2].equals(line[2]) &&
                                order2[3].equals(line[3]) &&
                                order2[4].equals(line[4]) &&
                                order2[5].equals(line[5]) &&
                                order2[6].equals(line[6]);

                if (isJudgesInfo)
                    return FileType.judgesFile;

            }

        }


            return FileType.None;
    }


    private static void removeSpaces(String[] line){


        for(int i = 0; i < line.length; i++){


            line[i] = line[i].replaceAll("\\s+","");
        }

    }


    private static void printList(String[] asd){


        for(String str : asd){

            System.out.print(str);
        }

        System.out.println("\n");

    }
}