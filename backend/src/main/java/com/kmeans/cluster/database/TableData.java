package com.kmeans.cluster.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kmeans.cluster.database.TableSchema.Column;

/**
 * Classe che gestisce i dati delle tabelle del database e permette di
 * effettuare
 * query su di esse e di ottenere i dati in esse contenuti sotto forma di lista
 * di esempi
 */
public class TableData {
    /** Database a cui accedere */
    DbAccess db;

    /**
     * Permette di accedere al database fornito come parametro
     * per effettuare query su di esso e ottenere i dati in esso contenuti sotto
     * forma di lista di esempi
     * 
     * @param db
     */
    public TableData(DbAccess db) {
        this.db = db;
    }

    /**
     * Permette di ottenere i dati contenuti nella tabella fornita come parametro
     * sotto forma di lista di esempi distinti
     * 
     * @param table Tabella da cui ottenere i dati
     * @return Lista di esempi contenenti i dati della tabella
     * @throws SQLException      Eccezione lanciata in caso di errore di connessione
     *                           al database
     * @throws EmptySetException Eccezione lanciata in caso di tabella vuota
     */
    public List<Example> getDistinctTransazioni(String table) throws SQLException, EmptySetException {
        TableSchema tableSchema = new TableSchema(this.db, table);
        Statement s = this.db.getConnection().createStatement();
        ResultSet resultSet = s.executeQuery("SELECT DISTINCT * " + "FROM " + table + ";");
        List<Example> list = new ArrayList<>();

        while (resultSet.next()) {
            Example ex = new Example();

            for (int i = 0; i < tableSchema.getNumberOfAttributes(); i++) {
                if (tableSchema.getColumn(i).isNumber()) {
                    ex.add(resultSet.getDouble(tableSchema.getColumn(i).getColumnName()));
                } else {
                    ex.add(resultSet.getString(tableSchema.getColumn(i).getColumnName()));
                }
            }
            list.add(ex);
        }

        s.close();
        resultSet.close();

        if (list.isEmpty()) {
            throw new EmptySetException("La tabella " + table + " e' vuota");
        }

        return list;
    }

    /**
     * Permette di ottenere i dati contenuti nella colonna della tabella fornita
     * come parametro sotto forma di lista di esempi distinti
     * 
     * @param table  Tabella da cui ottenere i dati della colonna
     * @param column Colonna da cui ottenere i valori dei dati
     * @return Set di esempi contenenti i dati della colonna
     * @throws SQLException Eccezione lanciata in caso di errore di connessione al
     *                      database
     */
    public Set<Object> getDistinctColumnValues(String table, Column column) throws SQLException {
        Statement s = this.db.getConnection().createStatement();
        ResultSet resultSet = s.executeQuery("SELECT DISTINCT " + column.getColumnName() + " " + "FROM " + table + " "
                + "ORDER BY " + column.getColumnName() + ";");

        HashSet<Object> set = new HashSet<>();

        while (resultSet.next()) {
            if (column.isNumber()) {
                set.add(resultSet.getDouble(column.getColumnName()));
            }

            else {
                set.add(resultSet.getString(column.getColumnName()));
            }
        }

        s.close();
        resultSet.close();

        return set;
    }

    /**
     * Permette di ottenere i dati contenuti nella tabella fornita come parametro
     * sotto forma di lista di esempi
     * a seguito di una query di aggregazione
     * 
     * @param table     Tabella da cui ottenere i dati
     * @param column    Colonna su cui effettuare l'aggregazione
     * @param aggregate Tipo di aggregazione da effettuare
     * @return Lista di esempi contenenti i dati della tabella
     * @throws SQLException     Eccezione lanciata in caso di errore di connessione
     *                          al database
     * @throws NoValueException Eccezione lanciata in caso di tabella vuota
     */
    public Object getAggregateColumnValue(String table, Column column, QUERY_TYPE aggregate)
            throws SQLException, NoValueException {
        Object agg;
        Statement s = this.db.getConnection().createStatement();
        ResultSet resultSet = s.executeQuery(
                "SELECT " + aggregate + "(" + column.getColumnName() + ") AS aggregata " + "FROM " + table + ";");

        try {
            if (resultSet.next()) {
                if (column.isNumber()) {
                    agg = resultSet.getDouble("aggregata");
                } else {
                    agg = resultSet.getString("aggregata");
                }

            } else {
                throw new NoValueException("Nessun valore per la colonna " + column.getColumnName());
            }
            ;

        } finally {
            s.close();
            resultSet.close();
        }

        return agg;
    }

}
