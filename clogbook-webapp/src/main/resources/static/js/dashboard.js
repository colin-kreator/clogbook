let flt_offset = 0;
let flt_end = false;
const FLT_CACHE_SIZE = 20;
const FLIGHT_FADE_DELAY = 50;

$(document).ready(function() {
	/*
		LANCEMENT DE LA MISE A JOUR DES 20 Derniers vols
	*/
	loadFlights(true);

	$(window).scroll(function() {
		if ($(window).scrollTop() == $(document).height() - $(window).height()) {
			loadFlights(false);
			$(window).scrollTop($(window).scrollTop() - 1);
		}
	});

	adjustStatsPosition();
	$(window).resize(function() {
		adjustStatsPosition();
	});
	
	computeCounters();
	
});

function computeCounters(){
	
	$('.cnt').each(function(){
		const cnt = $(this);
		$.ajax({
			url: CONTEXT_PATH+'api/stat/cnt/'+ $(this).data('value')
		}).done(function(data){
			
			if(data[0] != null){ 
				cnt.find('.cnt-main').text(data[0]);
			}
			if(data[1] != null){ 
				cnt.find('.cnt-sec').text(data[1]);
			}
		});
	});
} 

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


function loadFlights(init) {
	showHiddenFlights(FLIGHT_FADE_DELAY);

	if ($(window).data('fltAjaxReady') === false) return;

	if (init) {
		$('#end_message').hide();
		flt_offset = 0;
		flt_end = false;
	}
	if (flt_end) {
		$('#end_message').show();
		return;
	}

	$(window).data('fltAjaxReady', false);

	const url = CONTEXT_PATH + 'api/flight/' + flt_offset + '/' + FLT_CACHE_SIZE;

	$.ajax({
		url: url,
		method: "GET",
		dataType: "json"
	}).done(function(data) {
		if (data.length < FLT_CACHE_SIZE) {
			flt_end = true;
		}
		appendFlights(data);
		flt_offset += data.length;
	}).fail(function(jqXHR, textStatus, errorThrown) {
		console.log(jqXHR);
		console.log(textStatus);
		console.log(errorThrown);
	}).then(function() {
		$(window).data('fltAjaxReady', true);
		if (init) {
			showHiddenFlights(FLIGHT_FADE_DELAY);
			loadFlights(false);
		}
	});

}

async function showHiddenFlights(delay) {
	if (delay == null) {
		delay = 30;
	}
	const trs = $('#flights_table').find('tr:hidden');
	let i = 0;
	let loop = window.setInterval(function() {
		$(trs[i++]).fadeIn(delay);
		if (i == trs.length) {
			clearInterval(loop);
		}
	}, delay);
}

function appendFlights(flights) {
	for (flt of flights) {
		const act = flt.aircraft;

		let totalTime = '-';
		if (flt.totalTime != null) totalTime = Math.floor(flt.totalTime / 60) + ":" + (('' + flt.totalTime % 60).length < 2 ? ('0' + flt.totalTime % 60) + '' : ('' + flt.totalTime % 60));
		let nightTime = '-';
		if (flt.nightTime != null) nightTime = Math.floor(flt.nightTime / 60) + ":" + (('' + flt.nightTime % 60).length < 2 ? ('0' + flt.nightTime % 60) + '' : ('' + flt.nightTime % 60));
		let instrumentTime = '-';
		if (flt.instrumentTime != null) instrumentTime = Math.floor(flt.instrumentTime / 60) + ":" + (('' + flt.instrumentTime % 60).length < 2 ? ('0' + flt.instrumentTime % 60) + '' : ('' + flt.instrumentTime % 60));
		let simTime = '-';
		if (flt.simTime != null) simTime = Math.floor(flt.simTime / 60) + ":" + (('' + flt.simTime % 60).length < 2 ? ('0' + flt.simTime % 60) + '' : ('' + flt.simTime % 60));

		let conditions = '';
		if (flt.multiEngine != null && flt.multiEngine) {
			conditions += 'ME'
		} else {
			conditions += 'SE'
		}
		if (flt.multiPilot != null && flt.multiPilot) {
			conditions += ', MP'
		} else {
			conditions += ', SP'
		}
		if (flt.crossCountryTime != null && flt.crossCountryTime > 0) {
			conditions += ', XC'
		}
		if (flt.picTime != null && flt.picTime > 0) {
			conditions += ', PIC'
		}
		if (flt.dualTime != null && flt.dualTime > 0) {
			conditions += ', DUAL'
		}
		if (flt.copilotTime != null && flt.copilotTime > 0) {
			conditions += ', COPI'
		}
		if (flt.instructorTime != null && flt.instructorTime > 0) {
			conditions += ', INSTR'
		}
		if (simTime != '-') {
			conditions = '';
		}

		/*Craete the delete button*/
		const deleteHtml = '<span class="actuator-group"> '
			+ '<span class="safe-actuator"> '
			+ '	<a class="button is-small is-danger is-light selector" '
			+ '		title="Delete this intervention"> '
			+ '		<span class="icon"> '
			+ '			<i class="fas fa-trash-alt"></i>'
			+ '		</span>'
			+ '	</a>'
			+ '	<a class="button is-small is-danger is-light actuator delete-db" title="Delete this flight" style="display: none;">'
			+ '		<span class="icon"> '
			+ '			<i class="fas fa-check"></i>'
			+ '		</span>'
			+ '	</a>'
			+ '</span>'
			+ '</span>';

		const $tr = $('<tr>').append(
			$('<th>').text(flt.date.split('/')[0] + '/' + flt.date.split('/')[1] + '/' + flt.date.split('/')[2].substring(2)),
			$('<td>').text(flt.departureAirport == null ? '-' : flt.departureAirport.icaoCode),
			$('<td>').text(flt.departureTime),
			$('<td>').text(flt.arrivalAirport == null ? '-' : flt.arrivalAirport.icaoCode),
			$('<td>').text(flt.arrivalTime),
			$('<td>').text(act != null ? (act.aircraftModel != null ? act.aircraftModel.customName : "-") : "-"),
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
		$tr.data('flt', flt)
			.attr('hidden', true)
			.addClass('is-clickable');

		$tr.appendTo($("#flights_table tbody"));

		$tr.append($('<td>').html(deleteHtml).addClass('pr-0'));
		activateActuators($tr.find('.safe-actuator .selector'));

		const fltId = flt.id;
		$tr.find('.actuator.delete-db').click(function(e) {
			e.stopPropagation();
			$.ajax({
				url: CONTEXT_PATH + 'api/flight/' + fltId,
				method: 'delete'
			}).done(function() { $tr.remove(); })
				.fail(function() { alert('Error while deleting this flight') });
		});

		$tr.click(function() {
			resetFormWithFlight($tr);
		});
	}//end for



}