package services;

import play.Logger;

import javax.inject.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



@Singleton
public class FileLineServer {

    private ArrayList<String> lines;

    FileLineServer(){
        Logger.trace("FileLineServer - Entering FileLineServer Constructor");
        lines = new ArrayList<>();
        readFile();
    }


    private void readFile() {

        Logger.trace("FileLineServer - Entering readFile Method");

        String file = "fileLarge.txt";
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

        } catch (FileNotFoundException e) {
            Logger.warn("File Not Found");
            e.printStackTrace();
        } catch (IOException e) {
            Logger.warn("IO Exception");
            e.printStackTrace();
        }

        Logger.info("File is in memory as an array list containing " + lines.size() + " entries.");
    }

    public int retrieveFileLineSize(){
        Logger.trace("FileLineServer - Entering retrieveFileLineSize Method");
        return lines.size() + 1;
    }

    public String retrieveLineByNumber(int lineNumber){
        Logger.trace("FileLineServer - Entering retrieveLineByNumber Method");
        return lines.get(lineNumber-1);
    }


}
