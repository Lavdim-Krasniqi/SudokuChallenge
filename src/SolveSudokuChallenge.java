import java.io.*;
import java.util.Arrays;

public class SolveSudokuChallenge {

    public static int[][] convertFromCsvTo2DArray(String path) {
        int[][] matrix = new int[9][9];
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            int count = 0;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(",");
                for (int i = 0; i < 9; i++) {
                    matrix[count][i] = Integer.parseInt(str[i]);
                }
                count++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrix;
    }

    public static boolean solution(String path) {
        int[][] sudoku = convertFromCsvTo2DArray(path);
        for (int i = 0; i < 9; i++) {
            var row = extractRow(i, sudoku);
            if (!isCorrect(row))
                return false;
            var col = extractColumn(i, sudoku);
            if (!isCorrect(col))
                return false;
            if (i % 3 == 0) {
                for (int j = 0; j <= 6; j += 3) {
                    var subGrid = extractSubGrid(i, j, sudoku);
                    if (!isCorrect(subGrid))
                        return false;
                }
            }
        }
        return true;
    }

    public static int[] extractRow(int row, int[][] array) {
        return array[row];
    }

    public static int[] extractColumn(int col, int[][] array) {
        int[] column = new int[9];
        for (int i = 0; i < 9; i++) {
            column[i] = array[i][col];
        }
        return column;
    }

    public static int[] extractSubGrid(int i, int j, int[][] array) {
        int[] subGrid = new int[9];
        int count = 0;
        for (int i1 = 0; i1 < 3; i1++) {
            for (int j1 = 0; j1 < 3; j1++) {
                subGrid[count] = array[i1 + i][j1 + j];
                count++;
            }
        }
        return subGrid;
    }


    public static boolean isCorrect(int[] array) {
        int[] ints = Arrays.copyOf(array, 9);
        Arrays.sort(ints);
        for (int i = 0; i < 9; i++) {
            if (ints[i] != i + 1)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        File folder = new File("src/testcases");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.print(file.getName()+" : ");
                System.out.println(solution("src/testcases/" + file.getName()));
            }
        }


    }
}
