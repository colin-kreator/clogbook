
$(document).ready(function() {

	$(window).resize(function() {
		adjustLoginBoxPosition();
	});

	adjustLoginBoxPosition();



});


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