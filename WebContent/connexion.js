function connexion(formulaire){
	var login= formulaire.login.value;
	var password= formulaire.pass.value;
	var ok=verif_form(login,password);
	if(ok){
		connecte(login,password);
	}
}

function verif_forme(login,password){
	if(login.length==0){
		alert("Login obligatoire");
		return false;
	}
	if(login.length>20){
		alert("Login trop long");
		return false;
	}
	return true;
}

function connect(login,password){
	alert("Connexion: "+login+","+password);
}

