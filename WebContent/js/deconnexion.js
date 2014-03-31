function deconnexion(){
	var key=environnement.key;
	if(key==undefined){
		return;
	}
	var dataf='key=' + key;
	$.ajax({
		type : "get",
		url : "logout",
		data : dataf,
		dataType : "JSON",
		success : traiteReponseDeconnexion,
		error : function(XHR, testStatus, errorThrown) {
			alert(XHR + "" + testStatus + "" + errorThrown);
		}
	});
	environnement=null;
}

function traiteReponseDeconnexion(){
	window.location.href='main.jsp';
}