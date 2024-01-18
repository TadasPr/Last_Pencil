package lastpencil;

import java.util.Random;
import java.util.Scanner;

import static lastpencil.Data.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();


    public static void main(String[] args) {
        int pencilCount = 0;

        pencilLeft = pencilCountStart(pencilCount);

        choosePlayer();

        drawPencils(pencilLeft);

        pencils(pencilLeft);

        winner(name);
    }

    public static void choosePlayer() {
        System.out.println("Who will be the first (" + nameOne + ", " + nameTwo + ")");
        do {
            name = scanner.nextLine();
            if (!name.equals(nameOne) && !name.equals(nameTwo)) {
                System.out.println("Choose between " + nameOne + " and " + nameTwo);
            }
        } while (!name.equals(nameOne) && !name.equals(nameTwo));
    }

    public static int pencilCountStart(int pencilCount) {
        boolean numericPencil;
        System.out.println("How many pencils would you like to use:");
        do {
            try {
                pencilCount = Integer.parseInt(scanner.nextLine());
                if (pencilCount <= 0) {
                    System.out.println("The number of pencils should be positive");
                    numericPencil = false;
                } else {
                    numericPencil = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
                numericPencil = false;
            }
        } while (!numericPencil);
        return pencilCount;
    }

    public static void pencils(int pencilCount) {
        boolean positiveNUmber = true;
        boolean numericPencil = false;
        System.out.println(name + "'s turn!");
        do {
            if (name.equals(nameTwo)) {
                botTurnLogic();
                name = turnName(name);
                if (pencilLeft > 0) {
                    drawPencils(pencilLeft);
                    System.out.println(name + "'s turn!");
                }
            } else {
                do {
                    try {
                        pencilCount = Integer.parseInt(scanner.nextLine());
                        if (pencilCount > 0 && pencilCount <= 3) {
                            if (pencilCount <= pencilLeft) {
                                pencilLeft -= pencilCount;
                                name = turnName(name);
                                if (pencilLeft != 0) {
                                    drawPencils(pencilLeft);
                                    System.out.println(name + "'s turn!");
                                }
                            } else {
                                System.out.println("Too many pencils were taken");
                            }
                            positiveNUmber = true;
                        } else {
                            System.out.println("Possible values: '1', '2' or '3'");
                            positiveNUmber = false;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Possible values: '1', '2' or '3'");
                        numericPencil = false;
                    }
                } while (!numericPencil && !positiveNUmber);
            }
        } while (pencilLeft > 0);
    }

    public static void drawPencils(int pencilCount) {
        StringBuilder pencil = new StringBuilder();
        for (int i = 0; i < pencilCount; i++) {
            pencil.append("|");
        }
        System.out.println(pencil);
    }

    public static String turnName(String turnName) {
        if (turnName.equals(nameOne)) {
            return nameTwo;
        }
        return nameOne;
    }

    public static void winner(String name) {
        switch (name) {
            case "J":
                System.out.println(nameOne + " won!");
                break;
            case "K":
                System.out.println(nameTwo + " won!");
                break;
            default:
                System.out.println("no winner!");
        }
    }

    public static void botTurnLogic() {
        int botNumber;
        if (pencilLeft == 1) {
            System.out.println(1);
            pencilLeft -= 1;
        } else if (pencilLeft % 2 != 0 && numberSequence(pencilLeft) == 5) {
            botNumber = random.nextInt(3 - 1 + 1) + 1;
            System.out.println(botNumber);
            pencilLeft -= botNumber;
        } else if (pencilLeft % 2 == 0 && numberSequence(pencilLeft) == 4) {
            System.out.println(3);
            pencilLeft -= 3;
        } else if (pencilLeft % 2 != 0 && numberSequence(pencilLeft) == 3) {
            System.out.println(2);
            pencilLeft -= 2;
        } else if (pencilLeft % 2 == 0 && numberSequence(pencilLeft) == 2) {
            System.out.println(1);
            pencilLeft -= 1;
        }
    }

    public static int numberSequence(int pencilLeft) {
        while (pencilLeft > 5) {
            pencilLeft -= 4;
        }
        return pencilLeft;
    }
}

class Data {
    public static String nameOne = "J";
    public static String nameTwo = "K";
    public static int pencilLeft;
    static String name;
}
