let CONTEXT_PATH;
let ABSOLUTE_PATH;

$(document).ready(function() {

	CONTEXT_PATH = $('meta[name=context-path]').attr("content");
	ABSOLUTE_PATH = window.location.origin + CONTEXT_PATH;

	/*
		LANCEMENT DE LA MISE A JOUR DES 20 Derniers vols
	*/
	initializeLastFlightsList();



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
		} else {
			//REMOVE DECORATION
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

	$('#field_passwordConfirmation .input, #field_password .input').on('input', function() {
		if (passwordFault) {
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

function initializeLastFlightsList() {
	$("#table_20_flights tbody").empty();
	$.ajax({
		url: CONTEXT_PATH+"api/flight/top",
		method: "GET",
		dataType: "json"
	})
		.done(function(flights) {
			for (flt of flights) {
				const act = flt.aircraft;
				
				let totalTime = '-' ;
				if(flt.totalTime != null ) totalTime = Math.floor(flt.totalTime/60)+":"+((''+flt.totalTime%60).length<2 ? ('0'+flt.totalTime%60)+'' : (''+flt.totalTime%60) );
				let nightTime = '-' ;
				if(flt.nightTime != null ) nightTime = Math.floor(flt.nightTime/60)+":"+((''+flt.nightTime%60).length<2 ? ('0'+flt.nightTime%60)+'' : (''+flt.nightTime%60) );
				let instrumentTime = '-' ;
				if(flt.instrumentTime != null ) instrumentTime = Math.floor(flt.instrumentTime/60)+":"+((''+flt.instrumentTime%60).length<2 ? ('0'+flt.instrumentTime%60)+'' : (''+flt.instrumentTime%60) );
				let simTime = '-' ;
				if(flt.simTime != null ) simTime = Math.floor(flt.simTime/60)+":"+((''+flt.simTime%60).length<2 ? ('0'+flt.simTime%60)+'' : (''+flt.simTime%60) );
				console.log(flt);			
			
			
				let conditions='';
				if(flt.multiEngine != null && flt.multiEngine){
					conditions+='ME'
				}else{
					conditions+='SE'
				}
				if(flt.multiPilot != null && flt.multiPilot){
					conditions+=', MP'
				}else{
					conditions+=', SP'
				}
				if(flt.crossCountryTime != null && flt.crossCountryTime>0){
					conditions+=', XC'
				}
				if(flt.picTime != null && flt.picTime>0){
					conditions+=', PIC'
				}
				if(flt.dualTime != null && flt.dualTime>0){
					conditions+=', DUAL'
				}
				if(flt.copilotTime != null && flt.copilotTime>0){
					conditions+=', COPI'
				}
				if(flt.instructorTime != null && flt.instructorTime>0){
					conditions+=', INSTR'
				}
				if(simTime != '-'){
					conditions='';
				}
			
				let $tr = $('<tr>').append(
					$('<th>').text(flt.date),
					$('<td>').text(flt.departureAirport == null ? '-' : flt.departureAirport.icaoCode),
					$('<td>').text(flt.departureTime),
					$('<td>').text(flt.arrivalAirport == null ? '-' : flt.arrivalAirport.icaoCode),
					$('<td>').text(flt.arrivalTime),
					$('<td>').text(act != null ? (act.aircraftModel !=null ? act.aircraftModel.customName : "-") : "-"),
					$('<td>').text(act != null ? act.registration : '-'),
					$('<td>').text(flt.pilotDto != null ? flt.pilotDto.lastName : '-'),
					$('<td>').text(totalTime),
					$('<td>').text(nightTime),
					$('<td>').text(instrumentTime),
					$('<td>').text(conditions),
					$('<td>').text(flt.simType != null ? flt.simType : '-'),
					$('<td>').text(simTime),
					$('<td>').text(flt.remarks)
				);


				$tr.appendTo($("#table_20_flights tbody"));
			}
		})
		.fail(function() {
			//alert("It was not possible to load your pilots list");
		});
}
