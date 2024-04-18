import java.util.Scanner;

// Класс HumanPlayer - наследник класса Player, который реализует логику хода игрока-человека
public class HumanPlayer extends Player {
    // Конструктор с параметрами
    public HumanPlayer(String name, char symbol) {
        super(name, symbol);
    }

    // Метод совершения хода игроком-человеком
    public boolean makeTurn(Board board) {
        // Создаем объект класса Scanner для считывания ввода с консоли
        Scanner scanner = new Scanner(System.in);
        // Определяем координаты хода
        int row;
        int col;
        // Выводим сообщение о том, кто ходит
        System.out.println("Ходит игрок " + this.getName() + " (" + this.getSymbol() + ").");
        // Вводим координаты хода в цикле, пока не получим корректный ввод
        do {
            // Запрашиваем координаты хода у игрока
            System.out.print("Введите координаты хода в формате \"строка столбец\": ");
            // Считываем строку ввода
            if (scanner.hasNextInt()) {
                row = scanner.nextInt() - 1;
                // Если был введён ноль, то значит игрок сдался, возвращаем false
                if (row == -1) {
                    return false;
                }
                if (scanner.hasNextInt()) {
                    col = scanner.nextInt() - 1;
                } else {
                    scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Неверный формат ввода. Пожалуйста, введите два целых числа от 1 до 15.");
                    continue;
                }
            } else {
                scanner.nextLine();
                System.out.println("Неверный формат ввода. Пожалуйста, введите два целых числа от 1 до 15.");
                continue;
            }
            // Пробуем сделать ход
            if (!board.makeTurn(row, col, this.getSymbol())) {
                System.out.println("Клетка с такими координатами занята или не существует.");
                System.out.println("Попробуйте ещё раз ... ");
                continue;
            }
            // Если все проверки пройдены, то возвращаем true;
            return true;
        } while (true);
    }
}
