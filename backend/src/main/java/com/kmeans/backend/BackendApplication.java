package com.kmeans.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe che avvia il server
 */
@SpringBootApplication
public class BackendApplication {

	/**
	 * Metodo main usato come punto di partenza per il server
	 * 
	 * @param args Argomenti ottenuti da riga di comando
	 */
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
