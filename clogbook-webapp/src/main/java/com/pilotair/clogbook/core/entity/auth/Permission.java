package com.pilotair.clogbook.core.entity.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "TS_PERMISSION_PRM" )
public class Permission {

	@Id
	@Column( name = "prm_id" )
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer	id;
	@Column( name = "prm_context" )
	private String	context;
	@Column( name = "prm_operation" )
	private String	operation;

	@Override
	public String toString() {
		return "Permission [id=" + id + ", context=" + context + ", operation=" + operation + "]";
	}

	/**
	 * Bean Norm
	 */
	public Permission() {
		super();
	}

	/**
	 * @param context
	 * @param operation
	 */
	public Permission( String context, String operation ) {
		super();
		this.context = context;
		this.operation = operation;
	}

	/**
	 * @param id
	 * @param context
	 * @param operation
	 */
	public Permission( Integer id, String context, String operation ) {
		super();
		this.id = id;
		this.context = context;
		this.operation = operation;
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
	 * @return the context
	 */
	public String getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext( String context ) {
		this.context = context;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation( String operation ) {
		this.operation = operation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( context == null ) ? 0 : context.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( operation == null ) ? 0 : operation.hashCode() );
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
		Permission other = (Permission) obj;
		if ( context == null ) {
			if ( other.context != null )
				return false;
		} else if ( !context.equals( other.context ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( operation == null ) {
			if ( other.operation != null )
				return false;
		} else if ( !operation.equals( other.operation ) )
			return false;
		return true;
	}

}
