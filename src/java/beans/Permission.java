/*
 * Permission.java
 *
 * Created on March 18, 2006, 12:20 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package beans;

/**
 *
 * @author superwtk
 */
public final class Permission {
	
	private int rawData;
	
	public static final int READABLE = 0x0001;
	public static final int WRITABLE = 0x0002;
	public static final int MODIFIABLE = 0x0004;
	public static final int DELETABLE = 0x0008;
	
	/** Creates a new instance of Permission */
	public Permission() {
	}
	
	public int getRawData() {
		return rawData;
	}
	
	public void setRawData(int rawData) {
		this.rawData = rawData;
	}
	
	public boolean isReadable() {
		if((rawData & READABLE) == 0)
			return false;
		else
			return true;
	}
	
	public boolean isWritable() {
		if((rawData & WRITABLE) == 0)
			return false;
		else
			return true;
	}
	
	public boolean isModifiable() {
		if((rawData & MODIFIABLE) == 0)
			return false;
		else
			return true;
	}
	
	public boolean isDeletable() {
		if((rawData & DELETABLE) == 0)
			return false;
		else
			return true;
	}
}
