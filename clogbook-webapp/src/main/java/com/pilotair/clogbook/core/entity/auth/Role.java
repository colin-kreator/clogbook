package com.pilotair.clogbook.core.entity.auth;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table( name = "TS_ROLE_ROL" )
public class Role {

	@Id
	@Column( name = "rol_id" )
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer				id;
	@Column( name = "rol_name" )
	private String				name;
	@ManyToMany
	@JoinTable( name = "TS_ROLE_RIGHT_URT", joinColumns = { @JoinColumn( name = "rrt_rol_id" ) }, inverseJoinColumns = { @JoinColumn( name = "rrt_prm_id" ) } )
	private List<Permission>	permissions;

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", permissions=" + permissions + "]";
	}

	/**
	 * 
	 */
	public Role() {
		super();
	}

	/**
	 * @param name
	 */
	public Role( String name ) {
		super();
		this.name = name;
		this.permissions = new ArrayList<Permission>();
	}

	/**
	 * @param name
	 * @param permissions
	 */
	public Role( String name, List<Permission> permissions ) {
		super();
		this.name = name;
		this.permissions = permissions;
	}

	/**
	 * @param id
	 * @param name
	 * @param permissions
	 */
	public Role( Integer id, String name, List<Permission> permissions ) {
		super();
		this.id = id;
		this.name = name;
		this.permissions = permissions;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId( Integer id ) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * @return the permissions
	 */
	public List<Permission> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions
	 *            the permissions to set
	 */
	public void setPermissions( List<Permission> permissions ) {
		this.permissions = permissions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		result = prime * result + ( ( permissions == null ) ? 0 : permissions.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Role other = (Role) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( name == null ) {
			if ( other.name != null )
				return false;
		} else if ( !name.equals( other.name ) )
			return false;
		if ( permissions == null ) {
			if ( other.permissions != null )
				return false;
		} else if ( !permissions.equals( other.permissions ) )
			return false;
		return true;
	}

}
