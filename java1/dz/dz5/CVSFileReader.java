package java1.dz.dz5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CVSFileReader {
    String fileName;
    String separator;

    public CVSFileReader(String fileName, String separator) {
        this.fileName = fileName;
        this.separator = separator;
    }

    public AppData getData() {
        AppData CVSData = new AppData();
        List<String> strokes = new ArrayList<>();
        String[] header = null;
        int[][] data;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String str = reader.readLine();
            if (str != null) {
                header = str.split(separator);
            }
            while ((str = reader.readLine()) != null) {
                strokes.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (header != null || strokes.size() > 0) {
            data = new int[strokes.size()][];
            for (int i = 0; i < data.length; i++) {
                String[] str = strokes.get(i).split(String.valueOf(separator));
                data[i] = new int [str.length];
                for (int j = 0; j < str.length; j++) {
                    data[i][j] = Integer.parseInt(str[j]);
                }
            }
            CVSData.setHeader(header);
            CVSData.setData(data);
        } else {
            CVSData = null;
        }



        return CVSData;
    }
}
