package com.uni7.kmeans;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cluster {
    private int id;
    private Point center;
    private List<Point> points;

    public Cluster(int id, Point center) {
        this.id = id;
        this.center = center;
        this.points = new ArrayList<>();
    }

    public void clearPoints() {
        points.clear();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public double getDistanceToCenter(Point point) {
        double dx = center.getX() - point.getX();
        double dy = center.getY() - point.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void updateCenter() {
        double sumX = 0;
        double sumY = 0;

        for (Point point : points) {
            sumX += point.getX();
            sumY += point.getY();
        }

        int numPoints = points.size();
        if (numPoints > 0) {
            double newCenterX = sumX / numPoints;
            double newCenterY = sumY / numPoints;
            // Faça o novo Centróide C1 ter os valores das médias calculadas em 5.1 e 5.2
            center = new Point(newCenterX, newCenterY);
        }
    }
}
