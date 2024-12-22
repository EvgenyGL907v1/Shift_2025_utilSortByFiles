import java.util.ArrayList;

public class СallProcessing { // Класс для обработки ввода из консоли и идентификации опций
    SettingsManager settingsManager = new SettingsManager();
    SortDataByFiles sortFileData = new SortDataByFiles();
    StatisticManager statisticManager = StatisticManager.getInstance();
    ArrayList<String> inputFilesNames = new ArrayList<String>();
    private String outputPathDirectory;
    private String prefixFilesNames = "";
    private boolean appendNewTextInsteadRewrite = false;
    private boolean inputFileIsExist = false;
    private boolean shortStatistic = false;
    private boolean fullStatistic = false;

    public СallProcessing(String[] input){
        for (int i = 0; i < input.length; i++) {
            switch (input[i]) {
                case "-o":
                    outputPathDirectory = settingsManager.set_o_pathDirectory(i, input);
                    if(outputPathDirectory != null)
                        i++;
                    break;

                case "-p":
                    prefixFilesNames = settingsManager.set_p_prefixFilename(i, input);
                    if(prefixFilesNames != null)
                        i++;
                    break;

                case "-a":
                    appendNewTextInsteadRewrite = true;
                    break;

                case "-s":
                    shortStatistic = true;
                    break;

                case "-f":
                    fullStatistic = true;
                    break;

                default:
                    if(input[i].startsWith("-")){
                        System.out.println("Ошибка: Неизвестный параметр " + input[i] + "!");
                        break;
                    }

                    inputFileIsExist = settingsManager.set_txt_InputFile(i, input, inputFilesNames);
            }
        }

        // Запуск сортировки данных по файлам
        if(inputFileIsExist) {
            sortFileData.sortByFiles(inputFilesNames, outputPathDirectory, prefixFilesNames, appendNewTextInsteadRewrite);
            if(shortStatistic)
                statisticManager.getShortStatistic(prefixFilesNames);

            if(fullStatistic)
                statisticManager.getFullStatistic(prefixFilesNames);
        } else
            System.out.println("Остановка работы программы: Нет ни одного входного файла!");
    }
}

