package com.uni7.kmeans;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {
    public int k; // Número de clusters
    public int maxIterations; // Número máximo de iterações
    public List<Point> points; // Lista de pontos
    public List<Cluster> clusters; // Lista de clusters

    public KMeans(int k, int maxIterations) {
        this.k = k;
        this.maxIterations = maxIterations;
        this.points = new ArrayList<>();
        this.clusters = new ArrayList<>();
    }

    public void run() {
        // Inicialização dos clusters
        initializeClusters();

        int iteration = 0;
        boolean isUpdated;
        do {
            clearClusters();

            // Atribuição dos pontos aos clusters
            isUpdated = assignPointsToClusters();

            // Recalcula o centro de cada cluster
            updateClusterCenters();

            iteration++;
        } while (iteration < maxIterations && isUpdated);

        // Imprime os resultados
        printClusters();
    }

    public void addPoint(double x, double y) {
        points.add(new Point(x, y));
    }

    private void initializeClusters() {
        // Seleciona aleatoriamente k pontos como os centros iniciais dos clusters
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            Point center = points.get(random.nextInt(points.size()));
            Cluster cluster = new Cluster(i, center);
            clusters.add(cluster);
        }
    }

    private void clearClusters() {
        // Limpa os pontos atribuídos em cada cluster
        for (Cluster cluster : clusters) {
            cluster.clearPoints();
        }
    }

    private boolean assignPointsToClusters() {
        boolean isUpdated = false;

        for (Point point : points) {
            Cluster nearestCluster = null;
            double minDistance = Double.MAX_VALUE;

            // Encontra o cluster mais próximo para o ponto atual
            for (Cluster cluster : clusters) {
                double distance = cluster.getDistanceToCenter(point);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestCluster = cluster;
                }
            }

            // Atribui o ponto ao cluster mais próximo
            if (nearestCluster != null) {
                nearestCluster.addPoint(point);
                isUpdated = true;
            }
        }

        return isUpdated;
    }

    private void updateClusterCenters() {
        // Recalcula o centro de cada cluster
        for (Cluster cluster : clusters) {
            cluster.updateCenter();
        }
    }

    private void printClusters() {
        // Imprime os pontos em cada cluster
        for (Cluster cluster : clusters) {
            System.out.println("Cluster " + cluster.getId() + " Center: " + cluster.getCenter());
            System.out.println("Points:");
            for (Point point : cluster.getPoints()) {
                System.out.println(point);
            }
            System.out.println();
        }
    }
}