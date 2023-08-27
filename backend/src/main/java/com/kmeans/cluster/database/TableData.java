package com.kmeans.cluster.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kmeans.cluster.database.TableSchema.Column;

public class TableData {

    DbAccess db;

    public TableData(DbAccess db) {
        this.db = db;
    }

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
