/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caioferreira
 */
public class Cluster {
   private Point point;
    private List<Point> pointsOfGroup;

    public Cluster(Point point) {
        this.point = point;
        pointsOfGroup = new ArrayList<>();
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void addPointToCluster(Point p) {
        this.pointsOfGroup.add(p);
    }

    public List<Point> getPointsOfGroup() {
        return this.pointsOfGroup;
    }
    public void clearPointsOfGroup(){
        this.pointsOfGroup.clear();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((point == null) ? 0 : point.hashCode());
        result = prime * result + ((pointsOfGroup == null) ? 0 : pointsOfGroup.hashCode());
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
        Cluster other = (Cluster) obj;
        if (point == null) {
            if (other.point != null)
                return false;
        } else if (!point.equals(other.point))
            return false;
        if (pointsOfGroup == null) {
            if (other.pointsOfGroup != null)
                return false;
        } else if (!pointsOfGroup.equals(other.pointsOfGroup))
            return false;
        return true;
    } 
}
