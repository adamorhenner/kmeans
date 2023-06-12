package com.uni7.kmeans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KmeansApplication {

	public static void main(String[] args) {
		SpringApplication.run(KmeansApplication.class, args);

		KMeans kmeans = new KMeans(2, 100);
		kmeans.addPoint(1, 1);
		kmeans.addPoint(1, 2);
		kmeans.addPoint(2, 2);
		kmeans.addPoint(5, 5);
		kmeans.addPoint(6, 6);
		kmeans.addPoint(7, 7);
		kmeans.run();
	}

}
