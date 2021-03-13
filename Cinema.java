package cinema;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int seatsPerRow = scanner.nextInt();
        char[][] cinema = createCinema(rows, seatsPerRow);


        while (true) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int input = scanner.nextInt();
            if (input == 1) {
                printCinema(cinema);
            }
            if (input == 2) {
                buyTicket(cinema, scanner);
            }
            if (input == 3) {
                printStats(cinema);
            }
            if (input == 0) {
                break;
            }
        }
    }

    public static char[][] createCinema(int rows, int seatsPerRow) {
        char[][] cinema = new char[rows+1][seatsPerRow+1];

        for (int row = 0; row <= rows; row++) {
            for (int column = 0; column <= seatsPerRow; column++) {
                if (row == 0 && column == 0) {
                    cinema[row][column] = ' ';
                    continue;
                }
                if (row == 0) {
                    cinema[row][column] = (char)(column + '0');
                    continue;
                }
                if (column == 0) {
                    cinema[row][column] = (char)(row + '0');
                    continue;
                }
                cinema[row][column] = 'S';
            }
        }
        return cinema;
    }

    public static void buyTicket(char[][] cinema, Scanner scanner) {
        while (true) {
            int rows = cinema.length;
            int seatsPerRow = cinema[1].length;
            int seats = rows * seatsPerRow;
            int vipRows = rows / 2 - 1;

            System.out.println();
            System.out.println("Enter a row number: ");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row: ");
            int seat = scanner.nextInt();

            if (row < 0 || row > cinema.length - 1 || seat < 0 || seat > cinema[row].length - 1) {
                System.out.println("Wrong input!");
            } else if (cinema[row][seat] == 'B') {
                System.out.println("That ticket has already been purchased");
            } else {
                cinema[row][seat] = 'B';
                if (seats <= 60 || row <= vipRows) {
                    System.out.println("Ticket price: $10");
                } else {
                    System.out.println("Ticket price: $8");
                }
                break;
            }
        }
    }

    public static void printCinema(char[][] cinema) {
        System.out.println();
        System.out.print("Cinema: ");
        for (int row = 0; row < cinema.length; row++) {
            System.out.println();
            for (int column = 0; column < cinema[row].length; column++) {
                System.out.print(cinema[row][column] + " ");
            }
        }
        System.out.println();
    }

    public static void printStats(char[][] cinema) {
        int purchased = 0;
        double percentage;
        int income = 0;

        int rows = cinema.length - 1;
        int seatsPerRow = cinema[1].length - 1;
        int seats = rows * seatsPerRow;
        int vipRows = rows / 2;
        int cheapRows = rows - vipRows;
        int potential = seats * 10;
        if (seats > 60) {
            potential = vipRows * seatsPerRow * 10 + cheapRows * seatsPerRow * 8;
        }



        for (int row = 0; row < cinema.length; row++) {
            for(int column = 0; column < cinema[row].length; column++) {
                if (cinema[row][column] == 'B') {
                    purchased++;
                    if (seats <= 60 || row <= vipRows) {
                        income += 10;
                    } else {
                        income += 8;
                    }
                }
            }
        }
        percentage = (purchased * 1.00 / seats) * 100 ;

        System.out.println();
        System.out.println("Number of purchased tickets: " + purchased);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + income);
        System.out.println("Total income: $" + potential);

    }

}