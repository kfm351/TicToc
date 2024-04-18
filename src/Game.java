import java.util.Scanner;

// Класс Game - отвечает за управление логикой игры, а также за взаимодействие с игроками и доской
public class Game {
    // Объект класса Board, на котором происходит игра
    private Board board;
    // Первый игрок
    private Player player1;
    // Второй игрок
    private Player player2;
    // Текущий игрок
    private Player currentPlayer;

    // Конструктор по умолчанию
    public Game() {
        // Создаем новый объект класса Board
        board = new Board();
        // Запрашиваем у пользователя имена игроков
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя первого игрока (крестик):");
        String name1 = scanner.nextLine();
        System.out.println("Введите имя второго игрока (нолик):");
        String name2 = scanner.nextLine();
        // Создаем новые объекты класса HumanPlayer с заданными именами и символами
        player1 = new HumanPlayer(name1, 'X');
        player2 = new HumanPlayer(name2, 'O');
        // Устанавливаем currentPlayer равным player1
        currentPlayer = player1;
    }

    // Метод, выводящую основную информацию об игре
    public void start() {
        // Выводим на экран приветственное сообщение и правила игры
        System.out.println("Добро пожаловать в игру Рэндзю!");
        System.out.println("Цель игры - собрать пять своих символов в ряд по горизонтали, вертикали или диагонали.");
        System.out.println("Игроки ходят по очереди, ставя крестики и нолики на свободные клетки поля.");
        System.out.println("Игра заканчивается, когда один из игроков выигрывает или когда поле заполняется полностью.");
        System.out.println("Если игрок хочет сдаться досрочно, то нужно в качестве первой координаты хода ввести ноль.");
        System.out.println("Желаем вам удачи и приятной игры!");
        // Вызываем метод play()
        play();
    }
    // Метод play() - осуществляет основной цикл игры, пока не будет объявлен победитель или ничья.
    // На каждом шаге цикла вызывает метод makeTurn() у currentPlayer, проверяет, есть ли победа или ничья, и передаёт ход.
    // По окончании игры, выводит на экран сообщение о результате и предлагает сыграть еще раз.
    public void play() {
        // Выводим игровое поле
        board.printBoard();
        // Создаем булеву переменную для хранения флага окончания игры
        boolean gameOver = false;
        // Пока игра не закончилась, повторяем цикл
        while (!gameOver) {
            // Определяем, выиграл ли игрок или сдался
            gameOver = isGameOver() || !currentPlayer.makeTurn(this.board);
            // Если игра закончилась, выводим на экран сообщение о результате, вызывая метод showResult() у объекта board
            // Метод showResult() выводит на экран результат игровой сессии
            if (gameOver) {
                showResult();
            } else {
                // Если игра не закончилась, меняем currentPlayer на противоположного игрока
                switchPlayer();
            }
        }
        // После выхода из цикла, спрашиваем у пользователя, хочет ли он сыграть еще раз
        System.out.println("Хотите сыграть еще раз? Введите Y, если да, или N, если нет:");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        // Если ответ Y, создаем новый объект класса Game и вызываем его метод start()
        if (answer.equalsIgnoreCase("Y")) {
            Game game = new Game();
            game.start();
        } else {
            // Если ответ N, выводим на экран прощальное сообщение и завершаем программу
            System.out.println("Спасибо за игру! До свидания!");
            System.exit(0);
        }
    }

    // Метод смены игрока на противоположного
    public void switchPlayer() {
        // Если currentPlayer равен player1, то меняем его на player2, иначе на player1
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    // Метод проверки на победу и ничью
    public boolean isGameOver() {
        // Проверяем, есть ли победа или ничья у текущего игрока, используя его символ
        if (board.isWin(currentPlayer.getSymbol()) || board.isDraw()) {
            // Если да, возвращаем true
            return true;
        }
        // Если нет, возвращаем false
        return false;
    }

    // Метод вывода результата игры
    public void showResult() {
        // Проверяем, есть ли победа у текущего игрока, используя его символ
        if (board.isWin(currentPlayer.getSymbol())) {
            // Если есть победа, то выводим на экран поздравление с победой, используя его имя и символ
            System.out.println("Поздравляем! " + currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ") выиграл!");
        } else if (board.isDraw()) {
            // Если ничья, то выводим на экран сообщение о ничьей
            System.out.println("Ничья! Никто не выиграл!");
        } else {
            // Иначе игрок сдался
            System.out.println("Игрок " + currentPlayer.getName() + " (" + currentPlayer.getSymbol() + ") сдался!");
        }
    }

}

