

function inscription(formulaire){
	var login= formulaire.login.value;
	var password= formulaire.pass.value;
	var prenom= formulaire.prenom.value;
	var nom= formulaire.nom.value;
	var password2= formulaire.pass2.value;
	var ok=verif_forme(login,password,password2,prenom,nom);
	if(ok){
		inscrire(login,password,prenom,nom);
	}
}

function verif_forme(login,password,password2,prenom,nom){
	if(prenom.length==0){
		func_erreur("Veuillez saisir un prenom");
		return false;
	}
	if(nom.length==0){
		func_erreur("Veuillez saisir un nom");
		return false;
	}
	if(login.length==0){
		func_erreur("Veuillez saisir un identifiant");
		return false;
	}
	if(login.length>20){
		func_erreur("L'identifiant est trop long (longueur max:20)");
		return false;
	}
	if(password!=password2){
		func_erreur("Le second mot de passe ne correspond pas");
		return false;
	}
	return true;
}

/**
 * Affiche les messages d'erreur direction dans la fenêtre
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

function inscrire(login, password,prenom,nom) {
	var dataf='login=' + login + '&password=' + password + '&nom=' + nom + '&prenom=' + prenom;
	$.ajax({
		type : "get",
		url : "createUser",
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
		window.location.href= 'main.jsp?key=' + reponse.key + '&login='+ reponse.login + '&id=' +reponse.id;
	}
}

