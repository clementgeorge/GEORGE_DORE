function Commentaire(id,login,texte,date){
	this.id=id;
	this.login=login;
	this.texte=texte;
	this.date=date;
	
	Commentaire.prototype.getHtml= function(){
		if(environnement.actif==undefined){
			var s="<div class=\"tweet\"><htweet><ttlt>"+this.login+"</ttlt><date>"+this.date+"</date></htweet>"+this.texte+ "</div>";
			return(s);
		}
		else if((environnement.actif!=undefined) && (environnement.actif.login==this.login)){
			var s="<div class=\"tweet\"><htweet><ttlt2>"+this.login+"</ttlt2><date>"+this.date+"</date></htweet>"+this.texte+ "</div>";
			return(s);
		}
		else if((environnement.actif!=undefined) && (estAmi(this.id))){
			var s="<div class='tweet'>";
			s+="<htweet><ttlt>"+this.login;
			s+="<a href='#' onClick='rmFriend("+this.id+");' class='friendButton'>-</a>";
			s+="</ttlt><date>"+this.date+"</date></htweet>"+this.texte+"</div>";
			return(s);
		
		}
		else{
			var s="<div class='tweet'>";
			s+="<htweet><ttlt>"+this.login;
			s+="<a href='#' onClick='addFriend("+this.id+");' class='friendButton'>+</a>";
			s+="</ttlt><date>"+this.date+"</date></htweet>"+this.texte+"</div>";
			return(s);
		}	
	}
}

// resultat= tableau des commentaires

function RechercheCommentaire(resultat){
	this.resultat=resultat;
	
	RechercheCommentaire.prototype.getHtml=function(){
		var s="";
		if(environnement.key == undefined || environnement.onlyFriends == false){
			for (var i=0;i<this.resultat.length;i++){
				s+=this.resultat[i].getHtml()+"\n";
			}
			return s;
		}
		else{
			for (var i=0;i<this.resultat.length;i++){
				if(estAmi(resultat[i].id)){
					s+=this.resultat[i].getHtml()+"\n";
				}
			}
			return s;
		}	
	}
}



traiteJSONCommentaires= function(JSONobject){
	var tweets=JSONobject.tweets;
	var resultat=new Array();
	var taille=tweets.length;
	for(var i=0;i<taille;i++){
		var tweet=tweets[taille-1-i];
		resultat[i]=new Commentaire(tweet.auteur_id,tweet.auteur_login,tweet.text,tweet.date);
	}
	var rc=new RechercheCommentaire(resultat);
	$('#tweets_list').empty();
	$('#tweets_list').append(rc.getHtml());
}

function search(){
	var key='';
	if(environnement.key!=undefined){
		key=environnement.key;
	}
	/*var query=($("#requete").val());*/
	var dataf='key=' + key + '&recherche=' +environnement.recherche;
	$.ajax({
		type : "get",
		url : "search",
		data : dataf,
		dataType : "JSON",
		success : traiteJSONCommentaires,
		error : function(XHR, testStatus, errorThrown) {
			alert(XHR + "" + testStatus + "" + errorThrown);
		}
	});
	
}