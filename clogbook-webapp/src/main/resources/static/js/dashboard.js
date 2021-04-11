$(document).ready(function(){
	/*
		LANCEMENT DE LA MISE A JOUR DES 20 Derniers vols
	*/
	initializeLastFlightsList();
	

	$(window).resize(function() {
		adjustStatsPosition();
	});

	adjustStatsPosition();
	
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