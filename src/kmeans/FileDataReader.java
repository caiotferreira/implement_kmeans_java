/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caioferreira
 */
public class FileDataReader {
    private String fileName;

    public FileDataReader(String name )
    {
        this.fileName = name;
    }
    public List<Point> retrievePointsFromDataSet(){
        List<Point> points = new ArrayList<Point>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
            try {
                while(reader.ready()){
                    String line = reader.readLine();
                    List<Double> coordinates = new ArrayList<Double>();
                    coordinates.add(Double.valueOf(line.split(",")[0]));
                    coordinates.add(Double.valueOf(line.split(",")[1]));
                    coordinates.add(Double.valueOf(line.split(",")[2]));
                    coordinates.add(Double.valueOf(line.split(",")[3]));
                    Point p = new Point(coordinates);
                    p.setDescription(line.split(",")[4]);
                    points.add(p);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return points;
    }
}
