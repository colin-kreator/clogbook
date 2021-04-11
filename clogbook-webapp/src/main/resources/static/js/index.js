
$(document).ready(function() {

	/*
		CHECK REGISTRATION FORM BEFORE SENDING	
	*/
	let passwordFault = false;
	$('#modal_signup_submit').click(function(e) {
		const pass = $('#field_password .input').val();
		const pass2 = $('#field_passwordConfirmation .input').val();
		if (pass != pass2) {
			passwordFault = true;
			setPwdDecoration();
			e.preventDefault();
			e.stopPropagation();
			e.stopImmediatePropagation();
		} 
	});

	function setPwdDecoration() {
		const pass = $('#field_password .input').val();
		const pass2 = $('#field_passwordConfirmation .input').val();
		if (pass != pass2) {//SET THE ERROR STATE
			$('#field_passwordConfirmation .input').addClass("is-danger");
			$('#field_password .input').addClass("is-danger");
			$('#field_password .help').text("Entered passwords are differents");
			$('#field_passwordConfirmation .help').text("Entered passwords are differents");
		}else{ //set the ok state
			$('#field_passwordConfirmation .input').removeClass("is-danger");
			$('#field_password .input').removeClass("is-danger");
			$('#field_password .help').text("");
			$('#field_passwordConfirmation .help').text("");
		}	
	}

	$('#field_passwordConfirmation .input, #field_password .input')
							.on('input', function() {
		if (passwordFault) {
			setPwdDecoration();
		}
	});


	// open login modal
	$('#button_login').click(function(e) {
		e.stopPropagation();
		console.log('lcick');
		$('#modal_login').addClass("is-active");

	});

	// open signup modal
	$('#button_signup').click(function(e) {
		e.stopPropagation();
		console.log('lcick');
		$('#modal_signup').addClass("is-active");
	});

	


});


