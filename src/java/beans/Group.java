/*
 * Group.java
 *
 * Created on March 17, 2006, 11:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author superwtk
 */
public class Group
		extends Entry<Group>
		implements Accessible, Inheritable<Group>, Storable {
	
	///////////////////////////////////////////////////////
	// DB fields
	//
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	private int parentId;
	private int depth;
	private int childCount;
	private String name;
	private String description;
	private int attributes;
	
	private static final int USE_GROUP_CREATED_DATE = 0x00000001;
	private static final int USE_GROUP_MODIFIED_DATE = 0x00000002;
	private static final int USE_GROUP_DELETED_DATE = 0x00000004;
	private static final int USE_GROUP_PARENT_ID = 0x00000010;
	private static final int USE_GROUP_DEPTH = 0x00000020;
	private static final int USE_GROUP_CHILD_COUNT = 0x00000040;
	private static final int USE_GROUP_NAME = 0x00000100;
	private static final int USE_GROUP_DESCRIPTION = 0x00000200;
	private static final int USE_GROUP_ATTRIBUTES = 0x80000000;
	
	private static final String TABLE_NAME = "sbbs_groups";
	public static final String FIELD_GROUP_CREATED_DATE = "group_created_date";
	public static final String FIELD_GROUP_MODIFIED_DATE = "group_modified_date";
	public static final String FIELD_GROUP_DELETED_DATE = "group_deleted_date";
	public static final String FIELD_GROUP_PARENT_ID = "group_parent_id";
	public static final String FIELD_GROUP_DEPTH = "group_depth";
	public static final String FIELD_GROUP_CHILD_COUNT = "group_child_count";
	public static final String FIELD_GROUP_NAME = "group_name";
	public static final String FIELD_GROUP_DESCRIPTION = "group_description";
	public static final String FIELD_GROUP_ATTRIBUTES = "group_attributes";
	
	///////////////////////////////////////////////////////
	// non-DB fields
	//
	private Connection conn;
	
	/** Creates a new instance of Group */
	public Group() {
	}
	
	///////////////////////////////////////////////////////
	// getters & setters
	//
	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}
	
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public Date getDeletedDate() {
		return deletedDate;
	}
	
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}
	
	public int getParentId() {
		return parentId;
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public int getChildCount() {
		return childCount;
	}
	
	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getAttributes() {
		return attributes;
	}
	
	public void setAttributes(int attributes) {
		this.attributes = attributes;
	}
	
	///////////////////////////////////////////////////////
	// implementations of Inheritable
	//
	public Group getParent() {
		return null;
	}
	
	public Group[] getChildren() {
		return null;
	}
	
	public int getDepth() {
		return 0;
	}
	
	public void setDepth(int depth) {
	}
	
	public boolean hasParent() {
		return false;
	}
	
	public boolean hasChildren() {
		return false;
	}
	
	
	///////////////////////////////////////////////////////
	// implementations of Storable
	//
	public void save() throws SQLException {
	}
	
	public Group load() {
		return this;
	}
	
	public void insert() {
	}
	
	public void update() {
	}
	
	public void delete() {
	}
	
}
