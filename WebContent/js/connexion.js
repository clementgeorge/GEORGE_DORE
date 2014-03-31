

function connexion(formulaire){
	var login= formulaire.login.value;
	var password= formulaire.pass.value;
	var ok=verif_forme(login,password);
	if(ok){
		connecte(login,password);
	}
}

function verif_forme(login,password){
	if(login.length==0){
		func_erreur("Login obligatoire");
		return false;
	}
	if(login.length>20){
		func_erreur("Login trop long");
		return false;
	}
	return true;
}

/**
 * Affiche les messages d'erreur direction dans la fen�tre
 * @param msg 
 */

function func_erreur(msg) {
	var msg_box = "<div id=\"msg-err-connexion\">" + msg + "</div>";
	var old_msg = $("#msg-err-connexion");
	if (old_msg.length == 0) {
		$("form").prepend(msg_box);
		$("#msg-err-connexion").css({
			"color" : "#A00327"
		});
	} else {
		old_msg.replaceWith(msg_box);
		$("#msg-err-connexion").css({
			"color" : "#A00327"
		});
	}
}

function connecte(login, password) {
	var dataf='login=' + login + '&password=' + password;
	$.ajax({
		type : "get",
		url : "login",
		data : dataf,
		dataType : "JSON",
		success : traiteReponseConnexion,
		error : function(XHR, testStatus, errorThrown) {
			alert(XHR + "" + testStatus + "" + errorThrown);
		}
	});
}

function traiteReponseConnexion(reponse) {
	if(reponse.error != undefined){
		func_erreur(reponse.error);
	}else{
		window.location.href= 'main.jsp?key=' + reponse.key +'&login='+ reponse.login + '&id=' +reponse.id;
	}
}

