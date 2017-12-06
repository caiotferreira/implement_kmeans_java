/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

import kmeans.Utils.ConstantesUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author caioferreira
 */
public class Kmeans {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Nome do arquivo
        String fileName = "iris.txt";
        //le o arquivo
        List<Point> points = new ArrayList<Point>();
        FileDataReader reader = new FileDataReader(fileName);
        //recupera os pontos do dataset 
        points = reader.retrievePointsFromDataSet();
        //lista de clusters
        List<Cluster> clusters = new ArrayList<Cluster>();

        //numero de clusters
        //System.out.print("Digite o numero de clusters: ");
        //Scanner sc = new Scanner(System.in);
        //int numberOfClusters = sc.nextInt();
        int numberOfClusters = 3;
        
        // escolhe randomicamente os centroides iniciais
        for (int i = 0; i < numberOfClusters; i++) {
            Cluster cluster = new Cluster(Point.randomCentroid(points, ConstantesUtil.NUMBER_OF_COORDINATES));
            clusters.add(cluster);
            System.out.println(cluster.getPoint().coordinates.toString());
        }
        
        // inicia a clusterização
        int cont = 0;
        int clusterSize = clusters.size();
        while (cont < ConstantesUtil.NUMBER_MAX_ITERATIONS) {

            //limpa clusters

            for(int i = 0; i< clusterSize ; i++){

                clusters.get(i).clearPointsOfGroup();
            }

            for (int i = 0; i < points.size(); i++) {
                Point p = points.get(i);
                CandidateCluster candidate =
                        new CandidateCluster(clusters.get(0), Point.calcEuclidianDistance(clusters.get(0).getPoint(), p, ConstantesUtil.NUMBER_OF_COORDINATES), p);
                for (int j = 1; j < numberOfClusters; j++) {
                    Cluster c = clusters.get(j);
                    double dist = Point.calcEuclidianDistance(c.getPoint(), p, ConstantesUtil.NUMBER_OF_COORDINATES);
                    if (dist < candidate.getDist()) {
                        candidate.setC(c);
                        candidate.setDist(dist);
                    }

                }
                int indexOfCluster = clusters.indexOf(candidate.getC());
                clusters.get(indexOfCluster).addPointToCluster(p);

            }
            //calcula os novos centroides
            for (int j = 0; j < numberOfClusters; j++) {
                Cluster c = clusters.get(j);

                Cluster cAux = clusters.get(j);
                cAux.setPoint(Point.meanPoint(c.getPointsOfGroup(), ConstantesUtil.NUMBER_OF_COORDINATES));

                if(Point.calcEuclidianDistance(c.getPoint(),cAux.getPoint(),ConstantesUtil.NUMBER_OF_COORDINATES) == 0 ){
                    cont++;
                }

                c.setPoint(Point.meanPoint(c.getPointsOfGroup(), ConstantesUtil.NUMBER_OF_COORDINATES));
            }
        }
        System.out.println("---------- total points ----- : " + points.size());
        for (int i = 0; i < numberOfClusters; i++) {
            System.out.println("------------------------" + "CLUSTER (" + i + ")------------------------");

            if(clusters.get(i).getPointsOfGroup().size() > 0 ){
                System.out.println(clusters.get(i).getPoint().coordinates.toString());
                System.out.println(clusters.get(i).getPointsOfGroup().size());
                System.out.println("---------------------------------------------------------------------------");
                for (int j = 0; j < clusters.get(i).getPointsOfGroup().size(); j++) {

                    System.out.println(clusters.get(i).getPointsOfGroup().get(j).coordinates.toString() + " -> " + clusters.get(i).getPointsOfGroup().get(j).getDescription());
                }
            }
            else {
                System.out.println("Empty cluster!");
            }
        }
        
    }
    
}