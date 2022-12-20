import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Запускаем программу");
        logger.log("Просим пользователя ввести входные данные для списка");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int sizeList;
            int maxValue;
            while (true) {
                System.out.print("Введите размер списка: ");
                sizeList = Integer.parseInt(reader.readLine());

                System.out.print("Введите верхнюю границу для значений: ");
                maxValue = Integer.parseInt(reader.readLine());

                if (sizeList <= 0 || maxValue <= 0) {
                    logger.log("Некорректный ввод. Попробуйте снова");
                } else break;
            }

            logger.log("Создаём и наполняем список");
            List<Integer> list = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < sizeList; i++) {
                list.add(random.nextInt(maxValue));
            }

            System.out.print("Вот случайный список: ");
            printList(list);

            logger.log("Просим пользователя ввести входные данные для фильтрации");
            System.out.print("Введите порог для фильтра: ");
            int threshold = Integer.parseInt(reader.readLine());
            Filter filter = new Filter(threshold);
            list = filter.filterOut(list);

            logger.log("Выводим результат на экран");
            System.out.print("Отфильтрованный список: ");
            printList(list);

            logger.log("Завершаем программу");


        } catch (IOException e) {
            logger.log("ОШИБКА!");
            throw new RuntimeException(e);
        }
    }

    public static void printList(List<Integer> list) {
        for (int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}