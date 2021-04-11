$(document).ready(function() {
    
	$("#button_save_newflight").click(function(){
		recordFlight();
	});

	function recordFlight(){
		
		
		const checker = $("#modal_newflight .is-danger:visible");
		if(checker.length > 0) {
			console.log("haha corige tes erreurs");
			alert("Please correct the fileds in red before saving the flight");
		}
		else{
			$.ajax({
				type: "POST",
		        data: createJsonToSend(),
    			contentType: "application/json; charset=utf-8",
    			dataType: "json",
		        cache: false,
		        url: CONTEXT_PATH+"api/flight"
			})
			.done(function(){
				initializeLastFlightsList();
				$('#modal_newflight').removeClass("is-active");
				resetForm();
				
			})
			.fail(function(){
				alert("DTC");
			});
		}
	}
	
	$("#actualFlight, #simulator").click(function(){
		if($('#actualFlight').is(':checked')){
			$('.flight-only').removeClass('is-hidden');
			$('.sim-only').addClass('is-hidden');
		}else{
			$('.flight-only').addClass('is-hidden');
			$('.sim-only').removeClass('is-hidden');
		}
	});
	
	function createJsonToSend(){
		let json = {};
		
		//DATE
			const j_date = $("#flight_date input").val();
			json["date"] = j_date;
		
		if($('#actualFlight').is(':checked')){
		
			//AIRCRAFT
		
			let j_aircraft = {};
			if($("#flight_aircraft .reg input").data("act") == null && $("#flight_aircraft .reg input").val() != ""){
				j_aircraft ["registration"] = $("#flight_aircraft .reg input").val().toUpperCase();
				json["aircraft"] = j_aircraft;
			}else if($("#flight_aircraft .reg input").data("act") != null){
				j_aircraft = $("#flight_aircraft .reg input").data("act");
				json["aircraft"] = j_aircraft;
			}
		
			const j_aircraftmodel = {};
			if($('#flight_aircraft .type input').data("am") == null && $('#flight_aircraft .type input').val() != ""){
				j_aircraftmodel ["customName"] = $('#flight_aircraft .type input').val();
				j_aircraft["aircraftModel"] = j_aircraftmodel;
			}else if($('#flight_aircraft .type input').data("am") != null){
				j_aircraft["aircraftModel"] = $('#flight_aircraft .type input').data("am");
			}
		
		//AIRPORTS
			j_dep_airport = $("#flight_departure .place input").data("apt");
			j_arr_airport = $("#flight_arrival .place input").data("apt");
			if(j_dep_airport != null){
				json["departureAirport"] = j_dep_airport;
			}
			if(j_arr_airport != null){
				json["arrivalAirport"] = j_arr_airport;
			}
		
		//TIMES OF ARRIVAL / DEPARTURE
			j_dep_time = $("#flight_departure .time input").val();
			j_arr_time = $("#flight_arrival .time input").val();
			if(j_dep_time !=""){
				json["departureTime"] = j_dep_time;
			}
			if(j_arr_time !=""){
				json["arrivalTime"] = j_arr_time;
			}
		
		//PILOT
			if($('#flight_pic input').data("plt") == null && $('#flight_pic input').val() != ""){
				const j_pilot = {};
				j_pilot["lastName"] = $('#flight_pic input').val();
				json["pilotDto"] = j_pilot;
			}else if($('#flight_pic input').data("plt") !=null){
				json["pilotDto"] = $('#flight_pic input').data("plt");
			}
			
		//Take offs and landings
			if($('#flight_to-ldg .to-day input').val()!=""){
				json["dayTO"] = $('#flight_to-ldg .to-day input').val();
			}
			if($('#flight_to-ldg .to-night input').val()!=""){
				json["nightTO"] = $('#flight_to-ldg .to-night input').val();
			}
			if($('#flight_to-ldg .ldg-day input').val()!=""){
				json["dayLdg"] = $('#flight_to-ldg .ldg-day input').val();
			}
			if($('#flight_to-ldg .ldg-night input').val()!=""){
				json["nightLdg"] = $('#flight_to-ldg .ldg-night input').val();
			}
			
		//FLIGHT CONDITIONS
			json["multiPilot"] = $('#MP_CB').is(':checked');
			json["ifrFlight"] = $('#IFR_CB').is(':checked');
			json["multiEngine"] = $('#ME_CB').is(':checked');
		
		//FLIGHT TIMES
			const j_totalTime = $('.flight_time .total-time input').val();
			if(j_totalTime != "") json['totalTime'] = timeInMin(j_totalTime);
			
			const j_nightTime = $('.flight_time .night-time input').val();
			if(j_nightTime != "") json['nightTime'] = timeInMin(j_nightTime);
			
			const j_instrumentTime = $('.flight_time .ifr-time input').val();
			if(j_instrumentTime != "") json['instrumentTime'] = timeInMin(j_instrumentTime);
			
			const j_crossCountryTime = $('.flight_time .xc-time input').val();
			if(j_crossCountryTime != "") json['crossCountryTime'] = timeInMin(j_crossCountryTime);
			
			const j_picTime = $('.flight_time .pic-time input').val();
			if(j_picTime != "") json['picTime'] = timeInMin(j_picTime);
			
			const j_dualTime = $('.flight_time .dual-time input').val();
			if(j_dualTime != "") json['dualTime'] = timeInMin(j_dualTime);
			
			const j_copilotTime = $('.flight_time .copilot-time input').val();
			if(j_copilotTime != "") json['copilotTime'] = timeInMin(j_copilotTime);
			
			const j_instructorTime = $('.flight_time .instructor-time input').val();
			if(j_instructorTime != "") json['instructorTime'] = timeInMin(j_instructorTime);
			
		}else{
			const j_simType = $('.sim .sim-type input').val();
			if(j_simType != "") json["simType"] = j_simType;
			
			const j_simTime = $('.sim .sim-time input').val();
			if(j_simTime != "") json['simTime'] = timeInMin(j_simTime);
						
		}
		
		
		
		//REMARKS
			const j_remarks = $('#remarks input').val();
			if(j_remarks!="") json['remarks'] = j_remarks;
		
		console.log(JSON.stringify( json));
		
		return JSON.stringify( json);
	}

    //Capture du clic sur le bounton nouveau vol
    $("#button_newflight").click(function(e){
        e.stopPropagation();
        
		
        if($('#flight_date input').val() == ""){
            resetForm();
        }
        $('#modal_newflight').addClass("is-active");
    });


	//Gestion des autocomplete airport
	$("#flight_departure .place input, #flight_arrival .place input").change(function(){
		validateAirportAjax( $(this) );
	});
	
	$("#flight_date input").change(function(){
		updateAirportSrSs($("#flight_departure"));
		updateAirportSrSs($("#flight_arrival"));
	});

	$("#remarks input").on('input', function(){
		if($(this).val().length > 50){
			$("#remarks .help").removeClass("is-dark");
			$("#remarks .help").addClass("is-danger");
			$(this).addClass("is-danger");
		}else{
			$("#remarks .help").addClass("is-dark");
			$("#remarks .help").removeClass("is-danger");
			$(this).removeClass("is-danger");
		}
	});
	
	$(".sim .sim-type input").on('input', function(){
		const helper = $('#sim_type_helper');
		if($(this).val().length > 30){
			helper.removeClass("has-text-dark");
			helper.addClass("has-text-danger");
			$(this).addClass("is-danger");
		}else{
			helper.addClass("has-text-dark");
			helper.removeClass("has-text-danger");
			$(this).removeClass("is-danger");
		}
	});

	//Manage aircraft model change
	$('#flight_aircraft .type input').change(function(){
		const selected_am =  $('#flight_aircraft .type option[value="'+ $(this).val() +'"]').data('am');
		if(selected_am != null){
			$(this).data("am", selected_am);
			if(selected_am.multiEngine){
				$("#ME_CB").prop("checked", true);
				//$("#SE_CB").prop("checked", false);
			}else{
				$("#SE_CB").prop("checked", true);
				//$("#ME_CB").prop("checked", false);
			}
			if(selected_am.multiPilot){
				$("#MP_CB").prop("checked", true);
				$("#IFR_CB").prop("checked", true);
			}else{
				$("#MP_CB").prop("checked", false);
				$("#IFR_CB").prop("checked", false);
			}
		}else{
			$(this).data("am", null);
		}
		/*
		const aml_data = $(this).data("am");
		const aml_value = $(this).val();
		if(aml_value.toUpperCase() != aml_data.customName.toUpperCase()){
			 $(this).data("aircraftModel", null);
		}*/
	});
	
	//Manage pilot change
	$('#flight_pic input').change(function(){
		const selected_plt =  $('#flight_pic option[value="'+ $(this).val() +'"]').data('plt');
		if(selected_plt != null){
			$(this).data("plt", selected_plt);	
		}else{
			$(this).data("plt", null);
		}
		
		const plt_data = $(this).data("plt");
		const plt_value = $(this).val();
		if(plt_value.toUpperCase() != (plt_data.firstName+" "+plt_data.lastName).toUpperCase()){
			 $(this).data("aircraftModel", null);
		}
	});

	//Manage aircraft Selection change
	$('#flight_aircraft .reg input').change(function(){
		if($(this).val() != ""){
			const selected_aircraft =  $('#flight_aircraft .reg option[value="'+ $(this).val().toUpperCase() +'"]').data('act');
			if(selected_aircraft != null){
				$(this).data("act", selected_aircraft);	
				$('#flight_aircraft .type input').data("am", selected_aircraft.aircraftModel);
				if(selected_aircraft.aircraftModel != null){
					$('#flight_aircraft .type input').val(selected_aircraft.aircraftModel.customName);
					$('#flight_aircraft .type input').prop( "disabled", true );
				}else{
					$('#flight_aircraft .type input').data("am", null);
					$('#flight_aircraft .type input').val("");
					$('#flight_aircraft .type input').prop( "disabled", false );
				}
				
			}else{
				$(this).data("act", null);	
				$('#flight_aircraft .type input').data("am", null);
				$('#flight_aircraft .type input').val("");
				$('#flight_aircraft .type input').prop( "disabled", false );
			}
		}else{
			$(this).data("act", null);	
			$('#flight_aircraft .type input').data("am", null);
			$('#flight_aircraft .type input').val("");
			$('#flight_aircraft .type input').prop( "disabled", false );
		}
	});

    //Validation du champs date
    $('#flight_date input').on('input', function(){
		validateDateElement($('#flight_date input'), $('#flight_date .help'));
    });

    //Validation des champs dep et arr time
    $('#flight_departure .time input, #flight_arrival .time input').on('input', function(){
		validateFlightTimeElement($(this));
    });
    
    //AutoCompletion des champs time
    $('#flight_arrival input, #flight_departure input').on('input', function(){
        autoCompleteFlightTimes(false);
    });
    
    //Validation des champs temps de vol
    $(".flight_time input, .sim .sim-time input").on('input', function(){
            validateFlightTimeElement($(this));
    });

    //capture du clic sur le checkbox IFR
    $('#flight_cond .ifr input').click(function(){
        autoCompleteFlightTimes(true);
    });

	$("#flight_to-ldg input").on('input', function(){
		const val = $(this).val();
		if(val == ""){
			$(this).removeClass("is-danger");
			$(this).removeClass("is-success");
		}else if($.isNumeric(val) && val%1 === 0 && val >= 0  && val < 100) {
			$(this).addClass("is-success");
			$(this).removeClass("is-danger");
		} else {
			$(this).addClass("is-danger");
			$(this).removeClass("is-success");
		}
	});

	$(".initializable").focusin(function(){
		$(this).val( $(".flight_time .total-time input").val());
		validateFlightTimeElement($(this));
	});

    function autoCompleteFlightTimes(onlyifr){
        const duree = duration($("#flight_departure .time input").val(), $("#flight_arrival .time input").val());
        if(duree!==null){
           
            if($('#flight_cond .ifr input').prop('checked')){
                $('.flight_time .ifr-time input').val(duree);
            }else{
                $('.flight_time .ifr-time input').val("");
            }

			if(!onlyifr){
				 $(".flight_time .total-time input").val(duree);
				if($('#flight_departure .place input').val() != "" && 
	                $('#flight_arrival .place input').val() != ""){
	                if($('#flight_departure .place input').val() != 
	                        $('#flight_arrival .place input').val()){
	                    $('.flight_time .xc-time input').val(duree);
	                }else{
	                    $('.flight_time .xc-time input').val("");
	                }
	            }
			}
			
        }else{
            if(onlyifr) $('.flight_time .ifr-time input').val("");
			else $(".flight_time input").val("");
        }

        $(".flight_time input").each(function(){
            validateFlightTimeElement($(this));
        });
    }

    /*Temps entre deux heures : 
        Param (heure depart, heure arrivée) sous format hh:mm
        retourne la durée sous format hh:mm
    */
    function duration(dep, arr){
        if(isTimeValid(dep) && isTimeValid(arr)){
            const dep_min = dep.split(":")[0] * 60 + dep.split(":")[1] * 1;
            const arr_min = arr.split(":")[0] * 60 + arr.split(":")[1] * 1;
            let duration = 0;
            if(dep_min <= arr_min)
                duration = arr_min - dep_min;
            else
                duration = arr_min - dep_min + 24 * 60;
            
            let minutes = duration % 60 +"";
            let heures = (duration - minutes) / 60+"";
            
            if(minutes === 0) minutes = "00";
            else if(minutes.length === 1) minutes = "0"+minutes;
           
            return heures+":"+minutes;   
        }
        return null;
    }

    function validateDateElement(element, helpelement){
        if(element.val().length == 0) {
            helpelement.text("Date is required");
 			element.addClass("is-danger");
            element.removeClass("is-success");
			helpelement.addClass("is-danger");
            helpelement.removeClass("is-success");
        }else if(isDateValid(element.val())){
            element.removeClass("is-danger");
            element.addClass("is-success");
 			helpelement.removeClass("is-danger");
            helpelement.addClass("is-success");
			helpelement.text("");
        }else{
            element.addClass("is-danger");
            element.removeClass("is-success");
            helpelement.text("Date is not valid, use dd/mm/yy");
			helpelement.addClass("is-danger");
            helpelement.removeClass("is-success");
        }
    }

    function validateFlightTimeElement(element){

        if(element.val().length == 0){
            element.removeClass("is-danger");
            element.removeClass("is-success")
        }else{
            if(element.val().length == 4 && $.isNumeric(element.val())){
                element.val(element.val().substr(0,2)+":"+element.val().substr(2));
            }
            if(isTimeValid(element.val())){
                element.removeClass("is-danger");
                element.addClass("is-success");
            }else{
                element.addClass("is-danger");
                element.removeClass("is-success");
            }
        }
    }

    //Validation que le temps est correctement rentré
    function isTimeValid(value){
        return /^([0-1]?[0-9]|2[0-3]):([0-5][0-9])(:[0-5][0-9])?$/.test(value);
    }

    //Fonction utilitaire pour vérifier que la date rentrée est valide
    function isDateValid(date){
        if(date.length < 6) return false;
        if(date.split("/").length !== 3 ) return false;

        const dd = date.split("/")[0];
        const mm = date.split("/")[1];
        const yy = date.split("/")[2];
        if(yy.length !== 2) return false;
        if(mm.length !== 1 && mm.length !== 2) return false;
        if(dd.length !== 1 && dd.length !== 2) return false;
        if( ! $.isNumeric(dd) 
            || ! $.isNumeric(mm) 
            || ! $.isNumeric(yy) ) return false;
        if(mm < 1 || mm > 12) return false;
        if(mm == 2){
            if(isYearLeap(yyyy(yy))){
                if(dd<1 || dd>29) return false;
            }else{
                if(dd<1 || dd>28) return false;
            }
        }else{
            if(mm == 1 ||
                mm == 3 ||
                mm == 5 ||
                mm == 7 ||
                mm == 8 ||
                mm == 10 ||
                mm == 12){
                    if(dd<1 || dd>31) return false;
            }else{
                if(dd<1 || dd>30) return false;
            }
        }
        //$('#flight_date .help').text(dd+'/'+mm+'/'+yyyy(yy));
        return true;
    }

    //Convertie un année YY en date YYYY
    const yyyy = yy => {
        const zzzz = '20'+yy;
        const xxxx = '19'+yy;
        if(zzzz > new Date().getUTCFullYear()){
            console.log(xxxx);
            return xxxx;
        }else{
            console.log(zzzz);
            return zzzz;
        }
    }
    
    //Pour checker si une année est bissextile
    function isYearLeap(year){
        if(year % 4 === 0){
            if(year % 100 === 0){
                if(year % 400 === 0)
                    return true;
                else
                    return false;
            }else
                return true;
        }else
            return false;
    }

    function resetForm(){
		//Reinitialise tous les champs
	 	$('#modal_newflight input').val('');
        $('#modal_newflight input').removeClass("is-danger")
        $('#modal_newflight input').removeClass("is-success")
	
		// Va chercher les données pour charger les datalists
		initializeAircraftDataList();
		initializeAircraftModelsDataList();
		initializePilotDataList();
		initializeSimTypesDataList();
		
        //capture de la date du jour et affichage dans la modale
        const today = new Date();
                    
        const month = today.getUTCMonth()+1;
        const day = today.getUTCDate();
        const year = today.getUTCFullYear();
        const ye   = year.toString().substr(-2)
        const out = ((''+day).length<2 ? '0' : '') + day + "/" +
                    ((''+month).length<2 ? '0' : '') + month + "/" +
                    ye;

       
        //affichage de la date dans le champs date
        $('#flight_date input').val( out);
        $('#flight_date .help').text('');
        validateDateElement($('#flight_date input'), $('#flight_date .help'));
    }

	function timeInMin(timeString){
		return timeString.split(':')[0] * 60 + timeString.split(':')[1] * 1 ; 
	}

	function initializeAircraftModelsDataList(){
		$("#act_typ_datalist").empty();
		$.ajax({
			url: CONTEXT_PATH+"api/aircraftmodel",
			method:"GET",
			dataType:"json"
		})
		.done(function(response){
			for(am of response){
				if(am.brand != null && am.model != null){
					$("#act_typ_datalist").append($("<option>").attr('value', am.customName).attr('label', am.brand+" "+am.model).data("am", am));
				}else if(am.brand != null){
					$("#act_typ_datalist").append($("<option>").attr('value', am.customName).attr('label', am.brand).data("am", am));
				}else if(am.model != null){
					$("#act_typ_datalist").append($("<option>").attr('value', am.customName).attr('label', am.model).data("am", am));
				}else{
					$("#act_typ_datalist").append($("<option>").attr('value', am.customName).data("am", am));
				}
					
			}
			
		})
		.fail(function(){
			alert("It was not possible to load your aircraft model list");
		});
	}
	
	function initializeSimTypesDataList(){
		$("#sim_type_list").empty();
		$.ajax({
			url:"../api/flight/simtype",
			method:"GET",
			dataType:"json"
		})
		.done(function(response){
			for(ty of response){
				$("#sim_type_list").append($("<option>").attr('value', ty));
			}
		})
		.fail(function(){
			alert("It was not possible to load the previous simulator types");
		});
	}

	function initializeAircraftDataList(){
		$("#act_reg_datalist").empty();
		$.ajax({
			url:"../api/aircraft",
			method:"GET",
			dataType:"json"
		})
		.done(function(response){
			for(act of response){
				if(act.aircraftModel != null){
					$("#act_reg_datalist").append($("<option>").attr('value', act.registration).attr('label', act.aircraftModel.customName).data("act", act));	
				}else{
					$("#act_reg_datalist").append($("<option>").attr('value', act.registration).data("act", act));
				}
				
			}
			
		})
		.fail(function(){
			alert("It was not possible to load your aircraft list");
		});
	}
	
	function initializePilotDataList(){
		$("#pic_datalist").empty();
		$.ajax({
			url:"../api/pilot",
			method:"GET",
			dataType:"json"
		})
		.done(function(response){
			for(plt of response){
				$("#pic_datalist").append($("<option>").attr('value', plt.firstName+" "+plt.lastName).data("plt", plt));
			}
		})
		.fail(function(){
			alert("It was not possible to load your pilots list");
		});
	}
	
	function validateAirportAjax(inputElement){
		
		if(inputElement.val() == ""){
			inputElement.removeClass("is-success");
			inputElement.removeClass("is-danger");
			inputElement.data("apt", null);
			return;
		}
		
		$.ajax({
			url:"../api/airport/icao/"+inputElement.val(),
			method:"GET",
			dataType:"json",
		})
		.then(function(apt){
			if(apt != null){
				inputElement.addClass("is-success");
				inputElement.removeClass("is-danger");
				inputElement.data("apt", apt);
				updateAirportSrSs(inputElement.closest('.field'));
			}else{
				inputElement.addClass("is-danger");
				inputElement.removeClass("is-success");
				inputElement.data("apt", null);
				inputElement.closest('.field').find(".srss").text("");
			}
			
		})
		.fail(function(){
			inputElement.addClass("is-danger");
			inputElement.removeClass("is-success");
			inputElement.closest('.field').find(".srss").text("");
			inputElement.data("apt", null);
		});
	}

	

	function updateAirportSrSs(element){
		
		if($("#flight_date input").hasClass("is-success") &&
			element.find(".place input").hasClass("is-success")){
			
			const dateToSend = $("#flight_date input").val().replaceAll("/","-");
			const aptToSend = element.find(".place input").val();
			
			$.ajax({
				url:"../api/airport/srss/"+aptToSend+"/"+dateToSend,
				method:"GET",
				dataType:"json"
			})
			.done(function(srss){
				if(srss != null){
					element.find(".srss").text("  (SR: "+srss[0]+"z, SS: "+srss[1]+"z)");
				}else{
					element.find(".srss").text("");
				}
				
			})
			.fail(function(){
				element.find(".srss").text("");
			});
		
		}else{
			element.find(".srss").text("");
		}
		
	}

});

