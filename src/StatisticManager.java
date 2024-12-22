import java.util.ArrayList;

public class StatisticManager { // Класс для вывода статистики

    private static StatisticManager instance;
    private StatisticManager(){};
    public static StatisticManager getInstance() {
        if (instance == null) {
            instance = new StatisticManager();
        }
        return instance;
    }

    private ArrayList<String> listIntElements = new ArrayList<String>();
    private ArrayList<String> listFloatElements = new ArrayList<String>();
    private ArrayList<String> listStringElements = new ArrayList<String>();

    private double average (ArrayList<String> list) { // Функция возвращает среднее значение для списка чисел
        double sum = 0;

        for (int i = 0; i < list.size(); i++) {
            sum += Double.parseDouble(list.get(i));
        }
        return sum/list.size();
    }

    private double getMaxNumber (ArrayList<String> list) { // Функция возвращает максимальное значение из списка чисел
        double maxCount = Double.MIN_VALUE;

        for(int i = 0; i < list.size(); i++){
            if(Double.parseDouble(list.get(i)) > maxCount)
                maxCount = Double.parseDouble(list.get(i));
        }

        return maxCount;
    }

    private double getMinNumber (ArrayList<String> list) { // Функция возвращает минимальное значение из списка чисел
        double minCount = Double.MAX_VALUE;

        for(int i = 0; i < list.size(); i++){
            if(Double.parseDouble(list.get(i)) < minCount)
                minCount = Double.parseDouble(list.get(i));
        }

        return minCount;
    }

    private int getStringSizeMin(ArrayList<String> list){ // Функция возвращает минимальную длину строки из списка строк
        int minCount = Integer.MAX_VALUE;

         for(int i = 0; i < list.size(); i++){
             if(list.get(i).length() < minCount)
                 minCount = list.get(i).length();
         }

         return minCount;
    }

    private int getStringSizeMax(ArrayList<String> list){ // Функция возвращает максимальную длину строки из списка строк
        int maxCount = Integer.MIN_VALUE;

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).length() > maxCount)
                maxCount = list.get(i).length();
        }

        return maxCount;
    }

    public void addIntElements(String element) { // Функция для добавления элемента в список целых чисел
        listIntElements.add(element);
    }

    public void addFloatElements(String element){ // Функция для добавления элемента в список вещественных чисел
        listFloatElements.add(element);
    }

    public void addStringElements(String element){ // Функция для добавления элемента в список строк
        listStringElements.add(element);
    }

    public void getShortStatistic(String prefixFilesNames){ // Функция выводит в консоль краткую статистику
        System.out.println("\n---Краткая статистика---");
        System.out.println("Количество записей в файле " + prefixFilesNames + SortDataByFiles.INT_NAME + " : " + listIntElements.size());
        System.out.println("Количество записей в файле " + prefixFilesNames + SortDataByFiles.FLOAT_NAME + " : " + listFloatElements.size());
        System.out.println("Количество записей в файле " + prefixFilesNames + SortDataByFiles.STRING_NAME + " : " + listStringElements.size());
    }

    public void getFullStatistic(String prefixFilesNames){ // Функция выводит в консоль полную статистику
        System.out.println("\n---Полная статистика---");
        System.out.println("Количество записей в файле " + prefixFilesNames + SortDataByFiles.INT_NAME + " : " + listIntElements.size());
        if(listIntElements.size()>0) {
            System.out.println("Максимальный элемент: " + getMaxNumber(listIntElements));
            System.out.println("Минимальный элемент: " + getMinNumber(listIntElements));
            System.out.println("Среднее значение: " + average(listIntElements));
            System.out.println("Сумма значений: " + average(listIntElements)*listIntElements.size());
        }
        System.out.println();
        System.out.println("Количество записей в файле "+ prefixFilesNames + SortDataByFiles.FLOAT_NAME + " : " + listFloatElements.size());
        if(listFloatElements.size()>0){
            System.out.println("Максимальный элемент: " + getMaxNumber(listFloatElements));
            System.out.println("Минимальный элемент: " + getMinNumber(listFloatElements));
            System.out.println("Среднее значение: " + average(listFloatElements));
            System.out.println("Сумма значений: " + average(listFloatElements)*listFloatElements.size());
        }
        System.out.println();
        System.out.println("Количество записей в файле "+ prefixFilesNames + SortDataByFiles.STRING_NAME + " : " + listStringElements.size());
        if(listStringElements.size()>0) {
            System.out.println("Размер самой короткой строки: " + getStringSizeMin(listStringElements));
            System.out.println("Размер самой длинной строки: " + getStringSizeMax(listStringElements));
        }
        System.out.println();
    }
}
