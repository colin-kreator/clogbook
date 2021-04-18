let CONTEXT_PATH;
let ABSOLUTE_PATH;


$(document).ready(function() {

	CONTEXT_PATH = $('meta[name=context-path]').attr("content");
	ABSOLUTE_PATH = window.location.origin + CONTEXT_PATH;

	$("#locales").change(function () {
     var selectedOption = $('#locales').val();
     if (selectedOption != ''){
        window.location.replace(window.location.href.split('?')[0]+'?lang=' + selectedOption);
     }
  });

	// Check for click events on the navbar burger icon
	$(".navbar-burger").click(function() {
		console.log('click');
		// Toggle the "is-active" class on both the "navbar-burger" and the "navbar-menu"
		$(".navbar-burger").toggleClass("is-active");
		$(".navbar-menu").toggleClass("is-active");
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

});


