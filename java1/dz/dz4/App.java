package java1.dz.dz4;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String s = "Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore Lorem ipsum dolor";
        List<String> arrayWords = Arrays.asList(s.split("\\s"));

        UnicWords uw = new UnicWords(arrayWords);
        uw.printUnicWords();
        uw.printHowManyWordsRepeats();

        PhoneBook pb = new PhoneBook();
        pb.add("+79121244565", "Misha");
        pb.add("+79141244565", "Masha");
        pb.add("+79181244565", "Sasha");
        pb.add("+79121244550", "Ksusha");
        pb.add("+79121244570", "Natasha");
        pb.add("+79121230585", "Pasha");
        pb.add("+79122321435", "Nusha");

        System.out.println("Masha's phone number is " + pb.get("Masha"));
    }
}
