$(document).ready(function() {


	/*
		CHECK REGISTRATION FORM BEFORE SENDING	
	*/
	let passwordFault = false;
	$('#modal_signup_submit').click(function(e){
		
		const pass = $('#field_password .input').val();
        const pass2 = $('#field_passwordConfirmation .input').val();
        if(pass != pass2){
			passwordFault = true;
			setPwdDecoration();
			e.preventDefault();
			e.stopPropagation();
			e.stopImmediatePropagation();
        }else{
			//REMOVE DECORATION
		}
		
	});
	
	function setPwdDecoration(){
		const pass = $('#field_password .input').val();
        const pass2 = $('#field_passwordConfirmation .input').val();
        if(pass != pass2){//SET THE ERROR STATE
			$('#field_passwordConfirmation .input').addClass("is-danger");
			$('#field_password .input').addClass("is-danger");
            $('#field_password .help').text("Entered passwords are differents");
			$('#field_passwordConfirmation .help').text("Entered passwords are differents");
        }else{//SET THE OK STATE
			$('#field_passwordConfirmation .input').removeClass("is-danger");
			$('#field_password .input').removeClass("is-danger");
            $('#field_password .help').text("");
			$('#field_passwordConfirmation .help').text("");
		}
	}
	
	$('#field_passwordConfirmation .input, #field_password .input').on('input',function(){
		if(passwordFault){
			setPwdDecoration();	
		}
	});
	


	// Check for click events on the navbar burger icon
	$(".navbar-burger").click(function() {
		// Toggle the "is-active" class on both the "navbar-burger" and the "navbar-menu"
		$(".navbar-burger").toggleClass("is-active");
		$(".navbar-menu").toggleClass("is-active");

	});

	// open login modal
	$('#button_login').click(function(e) {
		e.stopPropagation();
		$('#modal_login').addClass("is-active");

	});
	
	// open signup modal
	$('#button_signup').click(function(e) {
		e.stopPropagation();
		$('#modal_signup').addClass("is-active");

	});
	
	// Close any modal with cancel button
	$('.modal_close_action').click(function(e) {
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		$('.modal').removeClass("is-active");
	});

	// Close any modal when click outside of it
	$("body").mousedown(function(e) {
		e.stopPropagation();
		$('.modal').removeClass("is-active");
	});

	// Prevent events from getting pass .popup
	$(".modal-card").mousedown(function(e) {
		e.stopPropagation();
	});
	
	//Close modal on escape
	$(document).keydown(function(event) {
		if (event.keyCode == 27) {
			$('.modal').removeClass("is-active");
		}
	});


	function adjustStatsPosition() {
		const width = $(window).width();

		let margin = 0.1 * width;
		if (width < 769) margin = 0;
		if (margin >= 70) margin = 70;
		$('#stats_level_container').css("margin-top", "-" + margin + "px");
		if (width >= 769) {
			$('#stats_level_box').css("background-color", "#FFFFFFDD");
		} else {
			$('#stats_level_box').css("background-color", "#FFFFFF");
		}
	}
	
	function adjustLoginBoxPosition() {
		const width = $(window).width();

		let margin = 0.1 * width;
		if (margin >= 200) margin = 200;
		$('#login_level').css("margin-top", "-" + margin + "px");
		if (width >= 769) {
			$('#login_level.box').css("background-color", "#FFFFFFDD");
		} else {
			$('#login_level.box').css("background-color", "#FFFFFF");
		}
	}

	$(window).resize(function() {
		adjustStatsPosition();
		adjustLoginBoxPosition();
	});

	adjustStatsPosition();
	adjustLoginBoxPosition();
	
	

	

	

});


