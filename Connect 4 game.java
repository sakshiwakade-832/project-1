import java.util.Objects;
import java.util.Scanner;

public class four_game {

    public static String[][] createPattern() {

        String[][] f = new String[7][15];

        for (int i =0;i<f.length;i++) {

            for (int j =0;j<f[i].length;j++) {
                if (j% 2 == 0){
                    f[i][j] ="|";
                } else {
                    f[i][j] = " ";
                }

                if (i==6) f[i][j]= "-";
            }

        }
        return f;
    }

    public static void printPattern(String[][] f) {
        for (int i =0;i<f.length;i++) {
            for (int j=0;j<f[i].length;j++) {
                System.out.print(f[i][j]);
            }
            System.out.println();
        }
    }

    public static void dropRedPattern(String[][] f) {
        System.out.print("Drop a red disk at column (0–6): ");
        Scanner scan = new Scanner (System.in);
        int c = 2*scan.nextInt()+1;
        for (int i =5;i>=0;i--) {
            if (f[i][c] == " ") {
                f[i][c] = "R";
                break;
            }
        }
    }

    public static void dropBlackPattern(String[][] f) {
        System.out.print("Drop a black disk at column (0–6): ");
        Scanner scan = new Scanner (System.in);
        int c = 2*scan.nextInt()+1;
        for (int i =5;i>=0;i--) {
            if (f[i][c] == " ") {
                f[i][c] = "B";
                break;
            }
        }
    }


    public static String checkWinner(String[][] f) {
        for (int i =0;i<6;i++) {
            for (int j=0;j<7;j+=2) {
                if ((f[i][j+1] != " ")
                        && (!Objects.equals(f[i][j + 3], " "))
                        && (!Objects.equals(f[i][j + 5], " "))
                        && (!Objects.equals(f[i][j + 7], " "))
                        && ((Objects.equals(f[i][j + 1], f[i][j + 3]))
                        && (Objects.equals(f[i][j + 3], f[i][j + 5]))
                        && (Objects.equals(f[i][j + 5], f[i][j + 7]))))

                    return f[i][j+1];
            }
        }

        for (int i=1;i<15;i+=2) {
            for (int j =0;j<3;j++) {
                if((f[j][i] != " ")
                        && (!Objects.equals(f[j + 1][i], " "))
                        && (!Objects.equals(f[j + 2][i], " "))
                        && (!Objects.equals(f[j + 3][i], " "))
                        && ((Objects.equals(f[j][i], f[j + 1][i]))
                        && (Objects.equals(f[j + 1][i], f[j + 2][i]))
                        && (Objects.equals(f[j + 2][i], f[j + 3][i]))))
                    return f[j][i];
            }
        }

        for (int i=0;i<3;i++) {

            for (int j=1;j<9;j+=2) {
                if((!f[i][j].equals(" "))
                        && (!f[i + 1][j + 2].equals(" "))
                        && (!f[i + 2][j + 4].equals(" "))
                        && (!f[i + 3][j + 6].equals(" "))
                        && ((f[i][j].equals(f[i + 1][j + 2]))
                        && (f[i + 1][j + 2].equals(f[i + 2][j + 4]))
                        && (f[i + 2][j + 4].equals(f[i + 3][j + 6]))))
                    return f[i][j];
            }
        }

        for (int i=0;i<3;i++) {
            for (int j=7;j<15;j+=2) {
                if((f[i][j] != " ")
                        && (f[i+1][j-2] != " ")
                        && (f[i+2][j-4] != " ")
                        && (f[i+3][j-6] != " ")
                        && ((f[i][j] == f[i+1][j-2])
                        && (f[i+1][j-2] == f[i+2][j-4])
                        && (f[i+2][j-4] == f[i+3][j-6])))
                    return f[i][j];
            }
        }

        return null;
    }

    public static void main (String[] args) {
        String[][] f = createPattern();
        boolean loop = true;
        int count = 0;
        printPattern(f);
        while(loop) {
            if (count % 2 == 0) dropRedPattern(f);
            else dropBlackPattern(f);
            count++;
            printPattern(f);
            if (checkWinner(f) != null) {
                if (checkWinner(f) == "R"){
                    System.out.println("Red won.");
                } else if (checkWinner(f)== "B") {
                    System.out.println("Black won.");
                }
                loop = false;
            }
        }
    }
}