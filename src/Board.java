import java.text.DecimalFormat;

// Класс Board - отвечает за представление и отрисовку игрового поля, а также за проверку наличия победной комбинации или ничьей
public class Board {
    // Двумерный массив символов, который хранит состояние игрового поля
    private char[][] board;
    // Размер поля
    private final int size;
    // Символ, который обозначает пустую клетку поля
    private final char empty = '.';
    // Длина победной комбинации
    private final int winLength;
    // Счётчик свободных полей
    private int emptyCounter;

    // Конструктор по умолчанию
    public Board() {
        size = 15;
        winLength = 5;
        emptyCounter = size * size;
        // Вызываем метод инициализации
        initBoard();
    }
    // Конструктор с параметрами
    public Board(int size, int winLength) {
        this.size = size;
        this.winLength = winLength;
        emptyCounter = size * size;
        // Вызываем метод инициализации
        initBoard();
    }
    // Метод инициализации игрового поля
    public void initBoard() {
        // Создаем новый двумерный массив символов размером size на size
        board = new char[size][size];
        // Заполняем массив пустыми символами
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = empty;
            }
        }
        // Обновляем значение счётчика пустых клеток
        emptyCounter = size * size;
    }

    // Метод вывода поля board на экран в виде сетки с координатами
    public void printBoard() {
        // Создаём объект для форматирования чисел
        // шаблон "00" означает, что будут выведены как минимум 2 цифры
        DecimalFormat dF = new DecimalFormat("00");
        // Выводим верхнюю строку с номерами столбцов
        System.out.print("   ");
        for (int j = 0; j < size; j++) {
            // Применяем шаблон к номеру столбца
            System.out.print(dF.format(j+1) + " ");
        }
        System.out.println();
        // Выводим остальные строки с номерами строк и символами поля
        for (int i = 0; i < size; i++) {
            // Применяем шаблон к номеру строки
            System.out.print(dF.format(i+1) + "  ");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }
    // Метод проверки корректности координат
    public boolean isTurnValid(int row, int col) {
        // Возвращаем false, если row и col выходят за границы массива
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }
        // Возвращаем false, если клетка с координатами row и col пуста
        if (board[row][col] != empty) {
            return false;
        }
        // В других случаях возвращаем true
        return true;
    }
    // Если координаты корректны, то делаем ход - записываем символ в поле
    public boolean makeTurn(int row, int col, char player) {
        // Проверяем, что ход возможен
        if (isTurnValid(row, col)) {
            // Записываем символ в клетку
            board[row][col] = player;
            // Уменьшаем счётчик пустых клеток
            emptyCounter--;
            // Выводим поле на экран
            printBoard();
            // Возвращаем true
            return true;
        }
        // Если ход невозможен, возвращаем false
        return false;
    }

    // Метод проверки наличия победной комбинации для игрока
    public boolean isWin(char player) {
        // Проверяем все возможные направления победной комбинации
        // Горизонталь
        for (int i = 0; i < size; i++) {
            // Считаем количество подряд идущих символов player в строке i
            int count = 0;
            for (int j = 0; j < size; j++) {
                if (board[i][j] == player) {
                    count++;
                } else {
                    count = 0;
                }
                // Если количество достигло winLength, возвращаем true
                if (count == winLength) {
                    return true;
                }
            }
        }
        // Вертикаль
        for (int j = 0; j < size; j++) {
            // Считаем количество подряд идущих символов player в столбце j
            int count = 0;
            for (int i = 0; i < size; i++) {
                if (board[i][j] == player) {
                    count++;
                } else {
                    count = 0;
                }
                // Если количество достигло winLength, возвращаем true
                if (count == winLength) {
                    return true;
                }
            }
        }
        // Главная диагональ
        for (int k = 0; k <= size - winLength; k++) {
            // Считаем количество подряд идущих символов player на главной диагонали, начиная с клетки (k, 0)
            int count = 0;
            for (int i = k, j = 0; i < size && j < size; i++, j++) {
                if (board[i][j] == player) {
                    count++;
                } else {
                    count = 0;
                }
                // Если количество достигло winLength, возвращаем true
                if (count == winLength) {
                    return true;
                }
            }
            // Считаем количество подряд идущих символов player на главной диагонали, начиная с клетки (0, k)
            count = 0;
            for (int i = 0, j = k; i < size && j < size; i++, j++) {
                if (board[i][j] == player) {
                    count++;
                } else {
                    count = 0;
                }
                // Если количество достигло winLength, возвращаем true
                if (count == winLength) {
                    return true;
                }
            }
        }
        // Побочная диагональ
        for (int k = 0; k <= size - winLength; k++) {
            // Считаем количество подряд идущих символов player на побочной диагонали, начиная с клетки (k, size - 1)
            int count = 0;
            for (int i = k, j = size - 1; i < size && j >= 0; i++, j--) {
                if (board[i][j] == player) {
                    count++;
                } else {
                    count = 0;
                }
                // Если количество достигло winLength, возвращаем true
                if (count == winLength) {
                    return true;
                }
            }
            // Считаем количество подряд идущих символов player на побочной диагонали, начиная с клетки (0, size - 1 - k)
            count = 0;
            for (int i = 0, j = size - 1 - k; i < size && j >= 0; i++, j--) {
                if (board[i][j] == player) {
                    count++;
                } else {
                    count = 0;
                }
                // Если количество достигло winLength, возвращаем true
                if (count == winLength) {
                    return true;
                }
            }
        }
        // Если ни одна из проверок не вернула true, возвращаем false
        return false;
    }
    // Метод проверки на ничью
    public boolean isDraw() {
        // Если пустых клеток не осталось, то ничья
        return emptyCounter == 0;
    }
}
