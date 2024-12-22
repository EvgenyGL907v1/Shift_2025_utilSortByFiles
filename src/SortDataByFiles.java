import java.io.*;
import java.util.ArrayList;

public class SortDataByFiles { // Класс сортировки данных по файлам
    StatisticManager statisticManager = StatisticManager.getInstance();

    public static final String INT_NAME = "integers.txt";
    public static final String FLOAT_NAME = "floats.txt";
    public static final String STRING_NAME = "strings.txt";

    public void sortByFiles(ArrayList<String> inputFilesNames, String outputPathDirectory, String prefixFilesNames, boolean append){
        try{
            BufferedWriter intWriter = null;
            BufferedWriter stringWriter = null;
            BufferedWriter floatWriter = null;

            for(int i = 0; i < inputFilesNames.size(); i++){
                BufferedReader br = new BufferedReader(new FileReader(inputFilesNames.get(i)));

                String line;
                while ((line = br.readLine()) != null) {
                    if(line.matches("[-|+]?[0-9]+([E|e]\\+[0-9]+)?")) {
                        if(intWriter == null)
                            intWriter = new BufferedWriter(new FileWriter(new File(outputPathDirectory,prefixFilesNames+INT_NAME ), append));

                        intWriter.write(line + System.lineSeparator());
                        statisticManager.addIntElements(line);
                    } else if ((line.matches("[-|+]?[0-9]+\\.[0-9]+([E|e][+|-][0-9]+)?")) || (line.matches("[-|+]?[0-9]+([E|e][-][0-9]+)?"))) {
                        if(floatWriter == null)
                            floatWriter = new BufferedWriter(new FileWriter(new File(outputPathDirectory,prefixFilesNames+FLOAT_NAME), append));

                        floatWriter.write(line + System.lineSeparator());
                        statisticManager.addFloatElements(line);
                    } else {
                        if(stringWriter == null)
                            stringWriter = new BufferedWriter(new FileWriter(new File(outputPathDirectory,prefixFilesNames+STRING_NAME), append));

                        stringWriter.write(line + System.lineSeparator());
                        statisticManager.addStringElements(line);
                    }
                }

            }

            if(intWriter != null)
                intWriter.close();
            if(stringWriter != null)
                stringWriter.close();
            if(floatWriter != null)
                floatWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
