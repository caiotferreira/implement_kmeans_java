/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author caioferreira
 */
public class Point {
    public List<Double> coordinates;
    private String description;

    public Point(List c){

        this.coordinates = c;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static double calcEuclidianDistance(Point p1, Point p2, int numberCoordinates){

        double sum = 0 ;
        for(int i =0 ; i < numberCoordinates; i++){
            sum += Math.pow((p1.coordinates.get(i) - p2.coordinates.get(i)),2);
        }
        double distance  = Math.sqrt(sum);

        return distance;

    }
    public static Point randomCentroid(List<Point> dataset, int numberCoordinates){

        Random random = new Random();
        double min = Point.minCoordinate(dataset, numberCoordinates);
        double max = Point.maxCoordinate(dataset, numberCoordinates);;
        List<Double> coordinates = new ArrayList<>();
        for(int i = 0 ; i < numberCoordinates; i++){
            coordinates.add(min + (max - min) * random.nextDouble());
        }


        return new Point(coordinates);
    }
    public static double maxCoordinate(List<Point> dataset, int numberCoordinates){
        int datasetSize = dataset.size();
        double maxValue = dataset.get(0).coordinates.get(0); // first coordinate from first point
        for(int i = 0 ; i < datasetSize; i++){
            Point current = dataset.get(i);
            for(int j = 0 ; j< numberCoordinates; j++){
                if(current.coordinates.get(j) > maxValue){
                    maxValue = current.coordinates.get(j);

                }
            }
        }
        return maxValue;
    }
    public static double minCoordinate(List<Point> dataset, int numberCoordinates){
        int datasetSize = dataset.size();
        double minValue = dataset.get(0).coordinates.get(0); // first coordinate from first point
        for(int i = 0 ; i < datasetSize; i++){
            Point current = dataset.get(i);
            for(int j = 0 ; j< numberCoordinates; j++){
                if(current.coordinates.get(j) < minValue){
                    minValue = current.coordinates.get(j);
                }
            }
        }
        return minValue;
    }
    public static Point meanPoint(List<Point> points, int numberCoordinates){
        int pointsSize = points.size();
        List<Double> coordinates = new ArrayList<Double>();
        //initialize
        for(int k = 0 ; k < numberCoordinates; k++){
            coordinates.add(0.00);
        }

        for(int i = 0; i < pointsSize; i++){
            double value = 0;
            for(int j = 0 ; j < numberCoordinates; j++){
                coordinates.set(j,coordinates.get(j)+ points.get(i).coordinates.get(j));
            }
        }

        for(int j = 0 ; j < numberCoordinates; j++){
            coordinates.set(j,coordinates.get(j) / pointsSize );
        }
        return new Point(coordinates);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (coordinates == null) {
            if (other.coordinates != null)
                return false;
        } else if (!coordinates.equals(other.coordinates))
            return false;
        return true;
    }

}
