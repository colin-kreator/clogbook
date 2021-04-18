$(document).ready(function(){
	activateActuators($('.safe-actuator .selector'));
	
	$(window).click(function() {
		resetSafeActuators();
	});
});

//Le selector donné en paramètre ne sera pas impacté
function resetSafeActuators(selector){
	$('.actuator').hide();
	if(selector == null){
		$('.selector').show();
		$('.selector').addClass('is-light');	
	}else{
		$('.selector').not(selector).show();
		$('.selector').not(selector).addClass('is-light');	
	}
}

function activateActuators(selectors){
	selectors.click(function(event){
		event.stopPropagation();
		
		const selector = this;
		$(selector).toggleClass('is-light');
		
		if($(selector).hasClass('is-light')){
			
			$.when($(selector).next('.actuator').toggle( "slide", "fast" )).done(function(){
				$(selector).closest('.actuator-group').find('.selector').not(this).each(function(){
					$(this).show("fade", "fast");
				});
			});
		}else{
			resetSafeActuators(selector);
			$.when($(selector).closest('.actuator-group').find('.selector').not(this).each(function(){
				$(this).hide("fade", "fast");
			}))
			.done(function(){
				$(selector).next('.actuator').toggle( "slide", "fast");
			});
		}
	});
}