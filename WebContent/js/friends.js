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