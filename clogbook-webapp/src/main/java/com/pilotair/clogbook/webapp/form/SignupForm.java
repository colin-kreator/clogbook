package com.pilotair.clogbook.webapp.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.text.WordUtils;

import com.pilotair.clogbook.core.entity.User;
import com.pilotair.clogbook.core.entity.auth.Role;
import com.pilotair.clogbook.webapp.form.uniqueemail.UniqueEmail;

public class SignupForm {

	@NotBlank( message = "Please enter your e-mail address" )
	@Size( max = 255, message = "The length can not exceed 255 characters" )
	@Email( message = "please enter a valid email" )
	@UniqueEmail
	private String	email;

	@NotBlank( message = "You must provide a password" )
	@Size( min = 8, max = 40, message = "The length must be between 8 and 40 characters" )
	private String	password;

	@Size( max = 20, message = "The length can not exceed 20 characters" )
	private String	phone;

	@NotBlank( message = "Please enter your last name" )
	@Size( max = 255, message = "The length can not exceed 255 characters" )
	private String	lastName;

	@NotBlank( message = "Please enter your first name" )
	@Size( max = 255, message = "The length can not exceed 255 characters" )
	private String	firstName;

	/**
	 * Utilisé pour retourner un User à partir des infos saisies dans le
	 * formulaire. ATTENTION : Le Mot de passe n'est pas encodé.
	 * 
	 * @return Un User potentiellement incomplet.
	 */
	public User buildUser() {
		return new User( WordUtils.capitalizeFully( lastName, ' ', '-' ),
		        WordUtils.capitalizeFully( firstName, ' ', '-' ), email, phone, null, new Role( "USER" ), email,
		        password, true );
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail( String email ) {
		this.email = email.trim();
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword( String password ) {
		this.password = password;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone( String phone ) {
		this.phone = phone.trim();
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName( String lastName ) {
		this.lastName = WordUtils.capitalizeFully( lastName.trim(), ' ', '-' );
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName( String firstName ) {
		this.firstName = WordUtils.capitalizeFully( firstName.trim(), ' ', '-' );
	}

}
