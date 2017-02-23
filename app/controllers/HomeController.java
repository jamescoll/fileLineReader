package controllers;

import com.google.inject.Inject;
import play.Logger;

import play.mvc.*;
import services.FileLineServer;



public class HomeController extends Controller {

    private FileLineServer fileLineServer;

    @Inject
    HomeController(FileLineServer fileLineServer){
        this.fileLineServer = fileLineServer;
    }

    public Result serveLine(Long lineNumber) {

        Logger.trace("HomeController - Entering serveLine Method with parameter " + lineNumber);

        if(fileLineServer.retrieveFileLineSize() > lineNumber && lineNumber > 0) {

            return ok("Line number: " + lineNumber + " " + fileLineServer.retrieveLineByNumber(lineNumber.intValue()));
        }

        else return forbidden();
    }

}
