$(function() {
	$("#discontinued").change(function() {
	    var startDate = $("#introduced").val();
	    var endDate = $("#discontinued").val();

	    if ((Date.parse(endDate) <= Date.parse(startDate))) {
	      alert("La date de fin doit être plus grande que la date de début");
	      $("#discontinued").val("");
	    }
	});
	$("#introduced").change(function() {
	    var startDate = $("#introduced").val();
	    var endDate = $("#discontinued").val();

	    if ((Date.parse(endDate) <= Date.parse(startDate))) {
	      alert("La date de fin doit être plus grande que la date de début");
	      $("#discontinued").val("");
	    }
	});
  $("form[name='newComputer']").validate({
    
    rules: {
      
      computerName: "required"
      
    },
    
    messages: {
    	computerName: "Veuillez entrer le nom de l'ordinateur",
    },
    
    submitHandler: function(form) {
      form.submit();
    }
  });
});
