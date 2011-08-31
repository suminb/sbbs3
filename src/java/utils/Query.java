/*
 * Query.java
 *
 * Created on March 17, 2006, 11:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package utils;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Query Builder
 * @author superwtk
 */
public final class Query {
	
	public static final int SELECT = 1;
	public static final int INSERT = 2;
	public static final int UPDATE = 3;
	public static final int DELETE = 4;
	
	private int behavior;
	private String tableName;
	private String where;
	private String order;
	private int limit;
	
	private List<String> fields;
	private List<String> values;
	
	/** Creates a new instance of Query */
	public Query() {
		fields = new Vector<String>();
		values = new Vector<String>();
	}
	
	public Query(int behavior) {
		this();
		setBehavior(behavior);
	}
	
	public Query(String tableName) {
		this();
		setTableName(tableName);
	}
	
	public Query(int behavior, String tableName) {
		this();
		setBehavior(behavior);
		setTableName(tableName);
	}
	
	public int getBehavior() {
		return behavior;
	}
	
	public void setBehavior(int behavior) {
		this.behavior = behavior;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
	public String getWhere() {
		if(where == null)
			return "";
		else
			return " WHERE " + where;
	}
	
	public void setWhere(String where) {
		this.where = where;
	}
	
	public String getOrder() {
		if(order == null)
			return "";
		else
			return " ORDER BY " + order;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}
	
	public String getLimit() {
		if(limit == 0)
			return "";
		else
			return " LIMIT " + limit;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public void add(String field, String value) {
		addField(field);
		addValue(value);
	}
	
	public void add(String field, int value) {
		addField(field);
		addValue(value);
	}
	
	public void add(String field, double value) {
		addField(field);
		addValue(value);
	}
	
	public void add(String field, Object value) {
		addField(field);
		addValue(value);
	}
	
	public void addField(String field) {
		if(field != null)
			fields.add(field);
	}
	
	public void addValue(String value) {
		if(value == null)
			fields.add("NULL");
		else
			values.add(value);
	}
	
	public void addValue(int value) {
		values.add(String.valueOf(value));
	}
	
	public void addValue(double value) {
		values.add(String.valueOf(value));
	}
	
	public void addValue(Object value) {
		values.add(value.toString());
	}
	
	public String getSelectQuery() {
		StringBuffer query = new StringBuffer(1024);
		
		query.append("SELECT ");
		
		if(fields.size() == 0) {
			query.append("* ");
		} else {
			Iterator<String> iterator = fields.iterator();
			while(iterator.hasNext()) {
				query.append(iterator.next());
				query.append(",");
			}
			query.setCharAt(query.length()-1, ' ');
		}
		
		query.append("FROM ");
		query.append(getTableName());
		query.append(getWhere());
		query.append(getOrder());
		query.append(getLimit());
		
		return new String(query);
	}
	
	public String getInsertQuery() {
		StringBuffer query = new StringBuffer(1024);
		Iterator<String> iterator = null;
		
		query.append("INSERT INTO ");
		query.append(getTableName());
		
		query.append(" (");
		iterator = fields.iterator();
		while(iterator.hasNext()) {
			query.append(iterator.next());
			query.append(',');
		}
		query.setCharAt(query.length()-1, ')');
		
		query.append(" VALUES(");
		iterator = values.iterator();
		while(iterator.hasNext()) {
			query.append("'");
			query.append(iterator.next());
			query.append("',");
		}
		query.setCharAt(query.length()-1, ')');
		
		return new String(query);
	}
	
	public String getUpdateQuery() {
		StringBuffer query = new StringBuffer(1024);
		
		query.append("UPDATE ");
		query.append(getTableName());
		query.append(" SET ");
		
		Iterator<String> iteratorOfFields = fields.iterator();
		Iterator<String> iteratorOfValues = values.iterator();
		while(iteratorOfFields.hasNext() && iteratorOfValues.hasNext()) {
			query.append(iteratorOfFields.next());
			query.append("='");
			query.append(iteratorOfValues.next());
			query.append("',");
		}
		query.deleteCharAt(query.length()-1);
		
		query.append(getWhere());
		query.append(getLimit());
		
		return new String(query);
	}
	
	public String getDeleteQuery() {
		StringBuffer query = new StringBuffer(1024);
		
		query.append("DELETE FROM ");
		query.append(getTableName());
		
		query.append(getWhere());
		query.append(getLimit());
		
		return new String(query);
	}
	
	public String toString() {
		String query = null;
		
		switch(getBehavior()) {
			case SELECT:
				query = getSelectQuery();
				break;
				
			case INSERT:
				query = getInsertQuery();
				break;
				
			case UPDATE:
				query = getUpdateQuery();
				break;
				
			case DELETE:
				query = getDeleteQuery();
				break;
		}
		return query;
	}
}
