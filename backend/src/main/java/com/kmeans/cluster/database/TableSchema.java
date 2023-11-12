package com.kmeans.cluster.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Contiene le informazioni sullo schema di una tabella di un database
 */
public class TableSchema {
	/** Connessione al database */
	private DbAccess db;

	/**
	 * Classe che rappresenta una colonna della tabella del database con il nome e
	 * il tipo
	 */
	public class Column {
		/** Nome della colonna */
		private String name;
		/** Tipo della colonna */
		private String type;

		/**
		 * Costruttore della classe Column
		 * 
		 * @param name Nome della colonna
		 * @param type Tipo della colonna
		 */
		Column(String name, String type) {
			this.name = name;
			this.type = type;
		}

		/**
		 * Restituisce il nome della colonna
		 * 
		 * @return Nome della colonna
		 */
		public String getColumnName() {
			return name;
		}

		/**
		 * Verifica se la colonna è di tipo numerico
		 * 
		 * @return Vero se la colonna è di tipo stringa altrimenti falso
		 */
		public boolean isNumber() {
			return type.equals("number");
		}

		/**
		 * Restituisce una stringa che rappresenta la colonna
		 * 
		 * @return Stringa che rappresenta la colonna
		 */
		public String toString() {
			return name + ":" + type;
		}
	}

	/**
	 * Lista delle colonne della tabella del database
	 */
	List<Column> tableSchema = new ArrayList<>();

	/**
	 * Costruttore della classe TableSchema che crea lo schema della tabella del
	 * database
	 * 
	 * @param db        Connessione al database
	 * @param tableName Nome della tabella del database
	 * @throws SQLException Eccezione lanciata in caso di errore di connessione al
	 *                      database
	 */
	public TableSchema(DbAccess db, String tableName) throws SQLException {
		this.db = db;
		HashMap<String, String> mapSQL_JAVATypes = new HashMap<>();
		// http://java.sun.com/j2se/1.3/docs/guide/jdbc/getstart/mapping.html
		mapSQL_JAVATypes.put("CHAR", "string");
		mapSQL_JAVATypes.put("VARCHAR", "string");
		mapSQL_JAVATypes.put("LONGVARCHAR", "string");
		mapSQL_JAVATypes.put("BIT", "string");
		mapSQL_JAVATypes.put("SHORT", "number");
		mapSQL_JAVATypes.put("INT", "number");
		mapSQL_JAVATypes.put("LONG", "number");
		mapSQL_JAVATypes.put("FLOAT", "number");
		mapSQL_JAVATypes.put("DOUBLE", "number");

		Connection conn = db.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM " + tableName + " WHERE 1=0;");
		ResultSetMetaData md = rs.getMetaData();

		for (int i = 1; i <= md.getColumnCount(); i++) {
			if (mapSQL_JAVATypes.containsKey(md.getColumnTypeName(i)))
				tableSchema.add(new Column(md.getColumnName(i), mapSQL_JAVATypes.get(md.getColumnTypeName(i))));
		}
		st.close();
		rs.close();
	}

	/**
	 * Restituisce il numero di attributi della tabella del database
	 * 
	 * @return Numero di attributi della tabella del database
	 */
	public int getNumberOfAttributes() {
		return tableSchema.size();
	}

	/**
	 * Restituisce la colonna della tabella del database all'indice specificato
	 * 
	 * @param index Indice della colonna della tabella del database
	 * @return Colonna della tabella del database all'indice specificato
	 */
	public Column getColumn(int index) {
		return tableSchema.get(index);
	}

}
