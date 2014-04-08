function initialiseListeFriends(){
	var dataf='key='+ environnement.key;
	$.ajax({
		type : "get",
		url : "getfriends",
		data : dataf,
		dataType : "JSON",
		success : traiteReponseGetFriends,
		error : function(XHR, testStatus, errorThrown) {
			alert(XHR + "" + testStatus + "" + errorThrown);
		}
	});
}

function traiteReponseGetFriends(reponse){
	if(reponse.error == undefined){
		var tab=reponse.friends;
		for(var i=0;i<tab.length;i++){
			environnement.friends[i]=tab[i];
		}
	}
}

function addFriend(formulaire){
	var dataf='key='+ environnement.key+'&friend='+formulaire.friend.value;
	$.ajax({
		type : "get",
		url : "addfriend",
		data : dataf,
		dataType : "JSON",
		success : traiteReponseAddFriend,
		error : function(XHR, testStatus, errorThrown) {
			alert(XHR + "" + testStatus + "" + errorThrown);
		}
	});
}

function traiteReponseAddFriend(reponse){
	if(reponse.error == undefined){
		var idFriend=reponse.friend;
		environnement.friends.push(idFriend);
		alert("Liste d'ami:"+environnement.friends);
		search();
	}
}

function estAmi(id){
	if(environnement.actif.id==id){
		return true;
	}
	for(var i=0; i < environnement.friends.length; i++){
		if(id == environnement.friends[i])
			return true;
	}
	return false;
}