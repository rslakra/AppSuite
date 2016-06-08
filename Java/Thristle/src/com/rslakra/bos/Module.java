package com.rslakra.bos;

import java.io.Serializable;
import java.util.Set;

/**
 * The <code>Module</code> represents the modules entity. A module may be
 * self-contained or may contain references to other modules (as parent or
 * child) that must be satisfied when linking occurs.
 * 
 * @author Rohtash Singh
 * @since v1.0.0
 */
public class Module extends BusinessObject implements Serializable {
	
	/** <code>serialVersionUID</code> */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	private String targetPath;
	private Module parent;
	private Set<Module> children;
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * The name to be set.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		if(this.name != name) {
			String oldValue = this.name;
			this.name = name;
			firePropertyChange("name", oldValue, name);
		}
	}
	
	/**
	 * Returns the description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * The description to be set.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		if(this.description != description) {
			String oldValue = this.description;
			this.description = description;
			firePropertyChange("description", oldValue, description);
		}
	}
	
	/**
	 * Returns the targetPath.
	 * 
	 * @return
	 */
	public String getTargetPath() {
		return targetPath;
	}
	
	/**
	 * The targetPath to be set.
	 * 
	 * @param targetPath
	 */
	public void setTargetPath(String targetPath) {
		if(this.targetPath != targetPath) {
			String oldValue = this.targetPath;
			this.targetPath = targetPath;
			firePropertyChange("targetPath", oldValue, targetPath);
		}
	}
	
	/**
	 * Returns the parent.
	 * 
	 * @return parent
	 */
	public Module getParent() {
		return parent;
	}
	
	/**
	 * The parent to be set.
	 * 
	 * @param parent
	 */
	public void setParent(Module parent) {
		if(this.parent != parent) {
			Module oldParent = this.parent;
			this.parent = parent;
			firePropertyChange("parent", oldParent, parent);
		}
	}
	
	/**
	 * Returns the children.
	 * 
	 * @return children
	 */
	public Set<Module> getChildren() {
		return children;
	}
	
	/**
	 * The children to be set.
	 * 
	 * @param children
	 */
	public void setChildren(Set<Module> children) {
		if(this.children != children) {
			Set<Module> oldChildren = this.children;
			this.children = children;
			firePropertyChange("children", oldChildren, children);
		}
	}
	
	/**
	 * Returns a string representation of this object.
	 * 
	 * @return
	 * @see com.rslakra.bos.BusinessObject#toString()
	 */
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Module <Id:").append(getId());
		sBuilder.append(", Name:").append(getName());
		sBuilder.append(", Description:").append(getDescription());
		sBuilder.append(", TargetPath:").append(getTargetPath());
		if(getParent() != null) {
			sBuilder.append(", parent:").append(getParent());
		}
		if(getChildren() != null) {
			sBuilder.append(", children:").append(getChildren());
		}
		sBuilder.append(super.toString()).append(">");
		return sBuilder.toString();
	}
}