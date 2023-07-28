package com.kmeans.cluster.mining;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.kmeans.cluster.data.Data;
import com.kmeans.cluster.data.OutOfRangeSampleSize;

public class KMeansMiner {
    private ClusterSet C;

    public KMeansMiner(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName));
            this.C = (ClusterSet) reader.readObject();

            reader.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File non trovato");
        } catch (IOException e) {
            throw new IOException("Errore di I/O");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Classe non trovata");
        }
    }

    public KMeansMiner(int k) throws OutOfRangeSampleSize {
        this.C = new ClusterSet(k);
    }

    public ClusterSet getC() {
        return this.C;
    }

    public void salva(String fileName) throws FileNotFoundException, IOException {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName));
            writer.writeObject(this.C);

            writer.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File non trovato");
        } catch (IOException e) {
            throw new IOException("Errore di I/O");
        }
    }

    public int kmeans(Data data) throws OutOfRangeSampleSize {
        int numberOfIterations = 0;

        // STEP 1 . Scelta casuale di centroidi per k clusters
        C.initializeCentroids(data);
        boolean changedCluster = false;

        do {
            numberOfIterations++;
            // STEP 2 Assegnazione di ciascuna riga della matrice in data al cluster avente
            // centroide più vicino all'esempio

            changedCluster = false;

            for (int i = 0; i < data.getNumberOfExamples(); i++) {
                Cluster nearestCluster = this.C.nearestCluster(data.getItemSet(i));
                Cluster oldCluster = this.C.currentCluster(i);
                boolean currentChange = nearestCluster.addData(i);

                if (currentChange) {
                    changedCluster = true;
                }

                // rimuovo la tupla dal vecchio cluster
                if (currentChange && oldCluster != null) {
                    // il nodo va rimosso dal suo vecchio cluster
                    oldCluster.removeTuple(i);
                }
            }
            // STEP 3 Calcolo dei nuovi centroidi per ciascun cluster
            this.C.updateCentroids(data);
        } while (changedCluster);

        return numberOfIterations;
    }
}
