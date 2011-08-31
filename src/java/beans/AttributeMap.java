/*
 * AttributeMap.java
 *
 * Created on May 20, 2006, 2:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans;

import java.util.TreeMap;
import utils.Query;

/**
 *
 * @author superwtk
 */
public final class AttributeMap<T extends Entry> extends TreeMap<String, String> {
	
	private T parent;
	private String tableName;
	
	/** Creates a new instance of AttributeMap */
	public AttributeMap() {
	}
	
	public T getParent() {
		return parent;
	}
	
	public void setParent(T parent) {
		this.parent = parent;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public void loadAll() {
		Query query = new Query(Query.SELECT);
		query.setTableName(tableName);
		query.setWhere(String.format("id=%d", parent.getId()));
	}
	
	public void saveAll() {
		
	}
	
}
