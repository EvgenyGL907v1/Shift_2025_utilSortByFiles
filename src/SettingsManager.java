import java.io.File;
import java.util.ArrayList;

public class SettingsManager { // Класс обработки опций
    private String OutputPathDirectory = ".";
    private String prefixFileName = "";
    private boolean appendNewTextInsteadRewrite = false;

    private boolean isValidprefixFileName(String prefixFileName) {
        return prefixFileName.matches("[а-яА-Яa-zA-Z0-9_\\-]+");
    }

    private boolean isValidFileName(String faileName) {
        return faileName.matches("[а-яА-Яa-zA-Z0-9_\\-]+([ ][а-яА-Яa-zA-Z0-9_\\-]){0,100}\\.txt");
    }

    public String set_o_pathDirectory(int i, String[] input){
        if (i + 1 < input.length) {
            OutputPathDirectory = input[i + 1];
            File dir = new File(OutputPathDirectory);

            if (!dir.isDirectory()) {
                System.out.println("Ошибка: " + OutputPathDirectory + " не является существующей директорией!");
                return null;
            }

            return OutputPathDirectory;
        } else {
            System.out.println("Ошибка: После параметра -o должен следовать путь к директории!");
            return null;
        }
    }

    public String set_p_prefixFilename(int i, String[] input){
        if (i + 1 < input.length) {
            prefixFileName = input[i + 1];

            if (!isValidprefixFileName(prefixFileName)) {
                System.out.println("Ошибка: Префикс содержит недопустимые символы!");
                return null;
            }

            return prefixFileName;
        } else {
            System.out.println("Ошибка: После параметра -p должен следовать префикс!");
            return null;
        }
    }

    public boolean set_txt_InputFile(int i, String[] input, ArrayList<String> inputFilesNames){
        if(!input[i].endsWith(".txt")){
            System.out.println("Ошибка: Тип файла " + input[i] + " указан неверно!");
            return false;
        }

        if (isValidFileName(input[i])) {
            File file = new File(input[i]);
            if (file.canRead()) {
                inputFilesNames.add(input[i]);
                return true;
            } else {
                System.out.println("Ошибка: " + input[i] + " не доступен для чтения!");
                return false;
            }
        } else {
            System.out.println("Ошибка: " + input[i] + " не является корректным файлом!");
            return false;
        }
    }
}
