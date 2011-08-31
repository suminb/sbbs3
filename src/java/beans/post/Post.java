/*
 * Post.java
 *
 * Created on March 17, 2006, 11:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans.post;

import beans.Accessible;
import beans.board.Board;
import beans.Categorizable;
import beans.category.Category;
import beans.Entry;
import beans.Extendable;
import beans.ExtendedAttributes;
import beans.Inheritable;
import beans.user.User;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import utils.Query;

/**
 *
 * @author superwtk
 */
public final class Post
		extends Entry<Post>
		implements Accessible, Categorizable, Extendable, Inheritable<Post>
{
	
	///////////////////////////////////////////////////////
	// DB fields
	//
	private int boardId;
	private int categoryId;
	private int userId;
	
	private int parentId;
	private int depth;
	private int childCount;
	
	private Date createdDate;
	private Date modifiedDate;
	private Date deletedDate;
	
	private String slug;
	private String excerpt;
	private String url;
	
	private String title;
	private String content;
	
	private int attributes;
	
	
	public static final int USE_POST_ID =				0x00000001;
	public static final int USE_BOARD_ID =				0x00000002;
	public static final int USE_CATEGORY_ID =			0x00000004;
	public static final int USE_POST_PARENT_ID =		0x00000010;
	public static final int USE_POST_DEPTH =			0x00000020;
	public static final int USE_POST_CHILD_COUNT =		0x00000040;
	public static final int USE_POST_CREATED_DATE =		0x00000100;
	public static final int USE_POST_MODIFIED_DATE =	0x00000200;
	public static final int USE_POST_DELETED_DATE =		0x00000400;
	public static final int USE_POST_SLUG =				0x00001000;
	public static final int USE_POST_EXCERPT =			0x00002000;
	public static final int USE_POST_URL =				0x00004000;
	public static final int USE_POST_TITLE =			0x00010000;
	public static final int USE_POST_CONTENT =			0x00020000;
	public static final int USE_POST_ATTRIBUTES =		0x80000000;
	
	private static final String TABLE_NAME = "sbbs_posts";
	public static final String FIELD_POST_ID = "post_id";
	public static final String FIELD_BOARD_ID = "board_id";
	public static final String FIELD_CATEGORY_ID = "category_id";
	public static final String FIELD_POST_PARENT_ID = "post_parent_id";
	public static final String FIELD_POST_DEPTH = "post_depth";
	public static final String FIELD_POST_CHILD_COUNT = "post_child_count";
	public static final String FIELD_POST_CREATED_DATE = "post_created_date";
	public static final String FIELD_POST_MODIFIED_DATE = "post_modified_date";
	public static final String FIELD_POST_DELETED_DATE = "post_deleted_date";
	public static final String FIELD_POST_SLUG = "post_slug";
	public static final String FIELD_POST_EXCERPT = "post_excerpt";
	public static final String FIELD_POST_URL = "post_url";
	public static final String FIELD_POST_TITLE = "post_title";
	public static final String FIELD_POST_CONTENT = "post_content";
	public static final String FIELD_POST_ATTRIBUTES = "post_attributes";
	
	///////////////////////////////////////////////////////
	// non-DB fields
	//
	
	
	private Board board;
	private Category category;
	private User user;
	
	
	
	/** Creates a new instance of Post */
	public Post() {
		extendedAttributes = new PostExtendedAttributes();
	}
	
	public Post(int id) {
		this();
		setId(id);
	}
	
	
	///////////////////////////////////////////////////////
	// getters & setters
	//
	public int getBoardId() {
		return boardId;
	}
	
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getParentId() {
		return parentId;
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public int getChildCount() {
		return childCount;
	}
	
	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}
	
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
	
	public String getSlug() {
		return slug;
	}
	
	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	public String getExcerpt() {
		return excerpt;
	}
	
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getAttributes() {
		return attributes;
	}
	
	public void setAttributes(int attributes) {
		this.attributes = attributes;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public User getUser() {
		
		if(userId != 0) {
			// registered user
			
			user = new User(userId);
			//user.load();
		} else {
			// anonymous user
			
			user = new User();
			//user.setUsername(getExtendedElement("username"));
			//user.setPassword(getExtendedElement("password"));
		}
		
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	///////////////////////////////////////////////////////
	// Implementations of Accessible
	//
	public boolean isAccessible(Entry accessor) {
		// loads all items in an access list
		// compares
		
		return false;
	}
	
	///////////////////////////////////////////////////////
	// Implementations of Categorizable
	//
	
	///////////////////////////////////////////////////////
	// Implementations of Extendable
	//
	private final class PostExtendedAttributes extends ExtendedAttributes {
		
		public void loadAll() throws SQLException {
			Query query = new Query(Query.SELECT, "sbbs_post_extended");
			query.setWhere(String.format("post_id=%d", id));

			Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query.toString());
			
			while(rs.next()) {
				String key = rs.getString("postx_key");
				String value = rs.getString("postx_value");
				
				put(key, value);
			}
			
			rs.close();
			stmt.close();
		}
		
		public void saveAll() throws SQLException {
			
			// case 1: the key exists in the table and the values are the same
			// > do nothing
			//
			// case 2: the key exists in the table and the values are different
			// > update
			//
			// case 3: the key does not exist in the table
			// > insert
			//
			// case 4: the key exists in the table but not in the map
			// > delete
			//
		}
	}
	private PostExtendedAttributes extendedAttributes;
	
	public PostExtendedAttributes extended() {
		return extendedAttributes;
	}
	
	///////////////////////////////////////////////////////
	// Implementations of Inheritable
	//
	public boolean hasParent() {
		if(parentId == 0)
			return false;
		else
			return true;
	}
	
	public boolean hasChildren() {
		if(childCount == 0)
			return false;
		else
			return true;
	}
	
	public Post getParent() throws SQLException, Exception {
		
		if(hasParent()) {
			Post parent = new Post(getParentId());
			parent.load();
			
			return parent;
		} else {
			return null;
		}
	}
	
	// Should this be recursive?
	public Post[] getChildren() throws SQLException, Exception {
		return null;
	}
	
	
	///////////////////////////////////////////////////////
	// Implementations of Storable
	//
	public Post load() throws PostNotFoundException, SQLException {
		Query query = new Query(Query.SELECT, TABLE_NAME);
		query.setWhere(String.format("%s=%d", FIELD_POST_ID, id));
		query.setLimit(1);
		
		// DEBUG
		System.out.println(query);
		
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(query.toString());
		
		if(rs.next()) {
			setBoardId(rs.getInt(FIELD_BOARD_ID));
			setCategoryId(rs.getInt(FIELD_CATEGORY_ID));
			
			setParentId(rs.getInt(FIELD_POST_PARENT_ID));
			setDepth(rs.getInt(FIELD_POST_DEPTH));
			setChildCount(rs.getInt(FIELD_POST_CHILD_COUNT));
			
			setCreatedDate(rs.getDate(FIELD_POST_CREATED_DATE));
			setModifiedDate(rs.getDate(FIELD_POST_MODIFIED_DATE));
			setDeletedDate(rs.getDate(FIELD_POST_DELETED_DATE));
			
			setSlug(rs.getString(FIELD_POST_SLUG));
			setExcerpt(rs.getString(FIELD_POST_EXCERPT));
			setUrl(rs.getString(FIELD_POST_URL));
			
			setTitle(rs.getString(FIELD_POST_TITLE));
			setContent(rs.getString(FIELD_POST_CONTENT));
			
			setAttributes(rs.getInt(FIELD_POST_ATTRIBUTES));
			
			setLoaded(true);
			
			rs.close();
			stmt.close();
			
			extended().loadAll();
		} else {
			throw new PostNotFoundException("There is no such a post");
		}
		
		return this;
	}
	
	public void save() throws SQLException, Exception {
		if(isLoaded()) {
			if(getContent() == null) {
				delete();
			} else {
				update();
			}
		} else {
			insert();
		}
		
		extended().saveAll();
	}
	
	public void insert() throws SQLException, Exception {
		//	insert(0xFFFFFFFF);
		
		int flags = 0;
		
		if(getCreatedDate() != null)
			flags |= USE_POST_CREATED_DATE;
		
		// ...
		
		flags |= USE_POST_TITLE;
		flags |= USE_POST_CONTENT;
		
		insert(flags);
	}
	
	public void insert(int fields) throws SQLException, Exception {
		
		if(fields == 0)
			throw new Exception("Nothing to insert");
		
		Query query = new Query(Query.INSERT, TABLE_NAME);
		
		if((fields & USE_BOARD_ID) == USE_BOARD_ID) {
			query.add(FIELD_BOARD_ID, getBoardId());
		}
		if((fields & USE_CATEGORY_ID) == USE_CATEGORY_ID) {
			query.add(FIELD_CATEGORY_ID, getCategoryId());
		}
		if((fields & USE_POST_PARENT_ID) == USE_POST_PARENT_ID) {
			query.add(FIELD_POST_PARENT_ID, getParentId());
		}
		if((fields & USE_POST_DEPTH) == USE_POST_DEPTH) {
			query.add(FIELD_POST_DEPTH, getDepth());
		}
		if((fields & USE_POST_CHILD_COUNT) == USE_POST_CHILD_COUNT) {
			query.add(FIELD_POST_CHILD_COUNT, getChildCount());
		}
		if((fields & USE_POST_CREATED_DATE) == USE_POST_CREATED_DATE) {
			query.add(FIELD_POST_CREATED_DATE, getCreatedDate());
		}
		if((fields & USE_POST_MODIFIED_DATE) == USE_POST_MODIFIED_DATE) {
			query.add(FIELD_POST_MODIFIED_DATE, getModifiedDate());
		}
		if((fields & USE_POST_DELETED_DATE) == USE_POST_DELETED_DATE) {
			query.add(FIELD_POST_DELETED_DATE, getDeletedDate());
		}
		if((fields & USE_POST_SLUG) == USE_POST_SLUG) {
			query.add(FIELD_POST_SLUG, getSlug());
		}
		if((fields & USE_POST_EXCERPT) == USE_POST_EXCERPT) {
			query.add(FIELD_POST_EXCERPT, getExcerpt());
		}
		if((fields & USE_POST_URL) == USE_POST_URL) {
			query.add(FIELD_POST_URL, getUrl());
		}
		if((fields & USE_POST_TITLE) == USE_POST_TITLE) {
			query.add(FIELD_POST_TITLE, getTitle());
		}
		if((fields & USE_POST_CONTENT) == USE_POST_CONTENT) {
			query.add(FIELD_POST_CONTENT, getContent());
		}
		if((fields & USE_POST_ATTRIBUTES) == USE_POST_ATTRIBUTES) {
			query.add(FIELD_POST_ATTRIBUTES, getAttributes());
		}
		
		// This possibly throws an Exception
		Statement stmt = getConnection().createStatement();
		
		// DEBUG
		System.out.println(query);
		
		stmt.execute(query.toString());
		stmt.close();
	}
	
	public void update() throws SQLException, Exception {
		update(0xFFFFFFFF);
	}
	
	public void update(int fields) throws SQLException, Exception {
		
		if(fields == 0)
			throw new Exception("Nothing to update");
		
		Query query = new Query(Query.UPDATE, TABLE_NAME);
		
		if((fields & USE_BOARD_ID) == USE_BOARD_ID) {
			query.add(FIELD_BOARD_ID, getBoardId());
		}
		if((fields & USE_CATEGORY_ID) == USE_CATEGORY_ID) {
			query.add(FIELD_CATEGORY_ID, getCategoryId());
		}
		if((fields & USE_POST_PARENT_ID) == USE_POST_PARENT_ID) {
			query.add(FIELD_POST_PARENT_ID, getParentId());
		}
		if((fields & USE_POST_DEPTH) == USE_POST_DEPTH) {
			query.add(FIELD_POST_DEPTH, getDepth());
		}
		if((fields & USE_POST_CHILD_COUNT) == USE_POST_CHILD_COUNT) {
			query.add(FIELD_POST_CHILD_COUNT, getChildCount());
		}
		if((fields & USE_POST_CREATED_DATE) == USE_POST_CREATED_DATE) {
			query.add(FIELD_POST_CREATED_DATE, getCreatedDate());
		}
		if((fields & USE_POST_MODIFIED_DATE) == USE_POST_MODIFIED_DATE) {
			query.add(FIELD_POST_MODIFIED_DATE, getModifiedDate());
		}
		if((fields & USE_POST_DELETED_DATE) == USE_POST_DELETED_DATE) {
			query.add(FIELD_POST_DELETED_DATE, getDeletedDate());
		}
		if((fields & USE_POST_SLUG) == USE_POST_SLUG) {
			query.add(FIELD_POST_SLUG, getSlug());
		}
		if((fields & USE_POST_EXCERPT) == USE_POST_EXCERPT) {
			query.add(FIELD_POST_EXCERPT, getExcerpt());
		}
		if((fields & USE_POST_URL) == USE_POST_URL) {
			query.add(FIELD_POST_URL, getUrl());
		}
		if((fields & USE_POST_TITLE) == USE_POST_TITLE) {
			query.add(FIELD_POST_TITLE, getTitle());
		}
		if((fields & USE_POST_CONTENT) == USE_POST_CONTENT) {
			query.add(FIELD_POST_CONTENT, getContent());
		}
		if((fields & USE_POST_ATTRIBUTES) == USE_POST_ATTRIBUTES) {
			query.add(FIELD_POST_ATTRIBUTES, getAttributes());
		}
		
		query.setWhere(String.format("%s=%d", FIELD_POST_ID, id));
		query.setLimit(1);
		
		Statement stmt = getConnection().createStatement();
		stmt.execute(query.toString());
		stmt.close();
	}
	
	public void delete() throws SQLException, Exception {
		Query query = new Query(Query.DELETE, TABLE_NAME);
		query.setWhere(String.format("%s=%d", FIELD_POST_ID, id));
		query.setLimit(1);
		
		Statement stmt = getConnection().createStatement();
		stmt.execute(query.toString());
		stmt.close();
		
		// TODO
		// make the values of all extended elements null.
	}
}
