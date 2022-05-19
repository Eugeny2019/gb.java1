package java1.dz.dz2;

public class App {
    public static void main(String[] args) {
        String[][] st = {{"asdf", "asdf", "asdf", "asdf"},
                {"asdfsadf", "fdsaf", "asdf", "asdf"}};
        String[][] st1 = {{"1", "2", "3", "4"},
                {"5", "fdsaf", "asdf", "asdf"},
                {"asdfsadf", "fdsaf", "asdf", "asdf"},
                {"asdfsadf", "fdsaf", "asdf", "asdf"}};
        String[][] st2 = {{"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}};
        try {
            System.out.println(new App().summ(st));
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        }
        catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(new App().summ(st1));
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        }
        catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(new App().summ(st2));
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        }
        catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int summ(String[][] stringArray) throws Exception {
        if (stringArray.length != 4 || stringArray[0].length != 4) {
            throw new MyArraySizeException("Неверная размерность массива");
        }
        int summ = 0;
        try {

            for (int i = 0; i < stringArray.length; i++) {
                for (int j = 0; j < stringArray[i].length; j++) {
                    summ += Integer.parseInt(stringArray[i][j]);
                }

            }
        } catch (NumberFormatException e) {
            throw new MyArrayDataException("Не числовое значение");
        }
        return summ;
    }
}