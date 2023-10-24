package ua.com.alevel.Controller;

import ua.com.alevel.Service.ReaderAndWriterService;
import ua.com.alevel.Validatort.ValidateInputStrings;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    private final static String INPUT_FILE_NAME = "input.txt";
    private final static String OUTPUT_FILE_NAME = "output.txt";
    private int NumbersOfCity;
    ReaderAndWriterService readerService = new ReaderAndWriterService(INPUT_FILE_NAME);
    ValidateInputStrings validateInputStrings = new ValidateInputStrings();
    public void start(){
        List<String> inputStringList = new ArrayList<>(readerService.startReadAndCreate());
        System.out.println("MainController.start");
        System.out.println("inputStringList.size() = " + inputStringList.size());
        validateInputStrings.startValidateInputStrings(inputStringList);
    }

}
