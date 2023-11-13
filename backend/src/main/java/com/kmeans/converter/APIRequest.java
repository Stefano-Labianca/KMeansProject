package com.kmeans.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

/**
 * <p>
 * Rappresenta le entry della collezione "calculation" del database MongoDB.
 * </p>
 *
 * <p>
 * Questa stessa struttura viene usata nel client per inviare al server
 * la computazione che si vuole salvare nella collezione.
 * </p>
 */
@Document(collection = "calculation")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIRequest {

    /**
     * Identificativo della entry all'intero della collezione
     */
    @Id
    private String id;

    /**
     * Data di creazione dell'entry
     */
    private String date;

    /**
     * Nome della computazione
     */
    private String title;

    /**
     * Lista contenente i nomi delle tabelle del database MySQL, da cui si è fatta
     * una computazione.
     */
    private ArrayList<String> columnsName = new ArrayList<String>();

    /**
     * Lista di cluster
     */
    private ArrayList<Object> clusters = new ArrayList<>();

    /**
     * Iterazioni fatte dall'algoritmo per ottenere i cluster.
     */
    private Integer iteration;

    /**
     * Numero di centroidi richiesti.
     */
    private Integer k;

    /**
     * Costruttore di default
     */
    public APIRequest() {
    }

    /**
     * Restituisce l'id della entry del database
     * @return ID della entry nel database
     */
    public String getId() {
        return this.id;
    }

    /**
     * Restituisce la data di creazione di una entry del database
     * @return Data di creazione
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Restituisce il nome della computazionie
     * @return nome della computazionie
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Restituisce i nomi delle colonne salvate
     * @return Nomi delle colonne
     */
    public ArrayList<String> getColumnsName() {
        return this.columnsName;
    }

    /**
     * Restituisce i cluster salvati
     * @return Cluster salvati
     */
    public ArrayList<Object> getClusters() {
        return this.clusters;
    }

    /**
     * Restituisce il numero di iterazioni
     * @return Numero di iterazioni
     */
    public Integer getIteration() {
        return this.iteration;
    }

    /**
     * Restituisce il numero di centroidi
     * @return Numero di centroidi
     */
    public Integer getK() {
        return this.k;
    }

    /**
     * Imposta l'id della entry nel database
     * @param id ID del database
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Imposta la data, in formato dd-mm-yyyy, di una entry
     *
     * @param date Data della computazione
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Imposta il nome della entry
     *
     * @param title Nome della entry
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Imposta i nomi delle colonna
     *
     * @param columnsName Nomi delle colonne
     */
    public void setColumnsName(ArrayList<String> columnsName) {
        this.columnsName = columnsName;
    }

    /**
     * Imposta i cluster da salvare
     *
     * @param clusters Cluster da salvare
     */
    public void setClusters(ArrayList<Object> clusters) {
        this.clusters = clusters;
    }

    /**
     * Imposta il numero di iterazioni
     *
     * @param iteration Numero di iterazioni
     */
    public void setIteration(Integer iteration) {
        this.iteration = iteration;
    }

    /**
     * Imposta il numero di cluster
     * @param k Numero di cluster
     */
    public void setK(Integer k) {
        this.k = k;
    }

    /**
     * Verifica se l'oggetto corrente e l'oggetto {@code o} sono uguali, restituendo true in caso affermativo,
     * altrimenti false
     * @param o Oggetto da controllare
     * @return Esito del controllo
     */
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof APIRequest)) return false;
        final APIRequest other = (APIRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$columnsName = this.getColumnsName();
        final Object other$columnsName = other.getColumnsName();
        if (this$columnsName == null ? other$columnsName != null : !this$columnsName.equals(other$columnsName))
            return false;
        final Object this$clusters = this.getClusters();
        final Object other$clusters = other.getClusters();
        if (this$clusters == null ? other$clusters != null : !this$clusters.equals(other$clusters)) return false;
        final Object this$iteration = this.getIteration();
        final Object other$iteration = other.getIteration();
        if (this$iteration == null ? other$iteration != null : !this$iteration.equals(other$iteration)) return false;
        final Object this$k = this.getK();
        final Object other$k = other.getK();
        if (this$k == null ? other$k != null : !this$k.equals(other$k)) return false;
        return true;
    }

    /**
     * Verifica se possono usare il metodo {@code equals} su un oggetto, restituendo vero se {@other} è un oggetto
     * di tipo APIRequest, altrimenti restituisce false.
     *
     * @param other Oggetto da controllare
     * @return Esito del controllo
     */
    protected boolean canEqual(final Object other) {
        return other instanceof APIRequest;
    }

    /**
     * Restituisce l'hash di un oggetto
     *
     * @return Hash dell'oggetto
     */
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $date = this.getDate();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $columnsName = this.getColumnsName();
        result = result * PRIME + ($columnsName == null ? 43 : $columnsName.hashCode());
        final Object $clusters = this.getClusters();
        result = result * PRIME + ($clusters == null ? 43 : $clusters.hashCode());
        final Object $iteration = this.getIteration();
        result = result * PRIME + ($iteration == null ? 43 : $iteration.hashCode());
        final Object $k = this.getK();
        result = result * PRIME + ($k == null ? 43 : $k.hashCode());
        return result;
    }

    /**
     * Restituisce una rappresentazione, sottoforma di stringa, di un oggetto di tipo APIRequest
     * @return Stringa rappresentate l'oggetto
     */
    public String toString() {
        return "APIRequest(id=" + this.getId() + ", date=" + this.getDate() + ", title=" + this.getTitle() + ", columnsName=" + this.getColumnsName() + ", clusters=" + this.getClusters() + ", iteration=" + this.getIteration() + ", k=" + this.getK() + ")";
    }
}