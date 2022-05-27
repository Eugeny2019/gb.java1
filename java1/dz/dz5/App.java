package java1.dz.dz5;

public class App {
    public static void main(String[] args) {
        AppData data = new CVSFileReader("src\\main\\java\\java1\\dz\\dz5\\res\\test.csv", "[;,]").getData();
        if (data != null) {
            System.out.println("Headers:");
            for (String header : data.getHeader()) {
                System.out.print(header + "\t");
            }
            System.out.println();
            int strokes[][] = data.getData();
            for (int stroke[] : strokes) {
                for (int number : stroke) {
                    System.out.print(number + "\t");
                }
                System.out.println();
            }
        }
    }
}
