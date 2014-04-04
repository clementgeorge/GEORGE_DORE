function main(id,login,key){
	environnement = new Object();
	environnement.users=[];
	environnement.onlyFriends=false;
	environnement.recherche='';
	
	if((id!=undefined) && (login!=undefined) && (key!=undefined)){
		environnement.actif=new User(id,login);
		environnement.key=key;
	}
	gererDivConnexion();
	gererCheckboxContact();
	gererInputTweet();
	search();
}

function User(id, login, contact) {
	this.id = id;
	this.login = login;
	environnement.users[id] = this;
}

function Commentaire(id,login,texte,date){
	this.id=id;
	this.login=login;
	this.texte=texte;
	this.date=date;
	
	Commentaire.prototype.getHtml= function(){
		if((environnement.actif!=undefined) && (environnement.actif.login==this.login)){
			var s="<div class=\"tweet\"><htweet><ttlt2>"+this.login+"</ttlt2><date>"+this.date+"</date></htweet>"+this.texte+ "</div>";
			return(s);
		}
		else{
			var s="<div class=\"tweet\"><htweet><ttlt>"+this.login+"</ttlt><date>"+this.date+"</date></htweet>"+this.texte+ "</div>";
			return(s);
		}
	}
}

function RechercheCommentaire(resultat){
	this.resultat=resultat;
	
	RechercheCommentaire.prototype.getHtml=function(){
		var s="";
		for (var i=0;i<this.resultat.length;i++){
			s+=this.resultat[i].getHtml()+"\n";
		}
		return s;
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
	var friend=environnement.onlyFriends?'ok':'';
	var key='';
	if(environnement.key!=undefined){
		key=environnement.key;
	}
	/*var query=($("#requete").val());*/
	var dataf='key=' + key + '&friend=' + friend +'&recherche=' +environnement.recherche;
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

function gererDivConnexion(){
	var user =environnement.actif;
	if((user!=undefined) && (user.login!="")){
		$('#connectlog').html("<span id=\"login\">"+user.login+"</span> <a href=\"#\" id=\"disconnect\" style=\"display:none;\">Deconnexion</span>");
		$('#disconnect').css({
			"color" : "#778e8e"
		});
		$( document ).on('click', '#login', gererBoutonDisconnect);
		$( document ).on('click', '#disconnect', deconnexion);
	}
	else{
		$('#connectlog').html("<a href=\"login.html\"id=\"connexion.link\">Connexion</a> / <a href=\"register.html\"id=\"enregistrement.link\">Enregistrement</a>");
	}
}

function gererInputTweet(){
	var user=environnement.actif;
	if((user!=undefined) && (user.login!="")){
		$('#input_tweet').css({"display":"block"});
		$('#CommentButton').css({"display":"block"});
	}
	else{
		$('#input_tweet').css({"display":"none"});
		$('#CommentButton').css({"display":"none"});
	}
}


function gererBoutonDisconnect(){
	var hid=document.getElementById('disconnect').style.display;
	if(hid=='none'){
		document.getElementById('disconnect').style.display='inline';
	}
	else{
		document.getElementById('disconnect').style.display='none';
	}
}

function postTweet(formulaire){
	var message=formulaire.input_tweet.value;
	var key=environnement.key;
	if(key==undefined){
		return;
	}
	var dataf='session='+ key + '&message=' +message;
	$.ajax({
		type : "get",
		url : "addtweet",
		data : dataf,
		dataType : "JSON",
		success : traiteReponsePostTweet,
		error : function(XHR, testStatus, errorThrown) {
			alert(XHR + "" + testStatus + "" + errorThrown);
		}
	});
	$("#input_tweet").val('');
}

function traiteReponsePostTweet(json){
	if(json.error==undefined && json.tweet!=undefined);
	var lastCom=new Commentaire(json.tweet.auteur_id,json.tweet.auteur_login,json.tweet.text,json.tweet.date);
	$('#tweets_list').prepend(lastCom.getHtml());
}

function gererCheckboxContact(){
	var user =environnement.actif;
	if((user!=undefined) && (user.login!="")){
		$('#box_friends_span').css({"display":"inline"});
	}
	else{
		$('#box_friends_span').css({"display":"none"});
	}
	environnement.onlyFriends=false;
	if($('#box_friends').get(0).checked){
		environnement.onlyFriends=true;
	}
	search();
}

function rechercher(formulaire){
	var recherche=formulaire.recherche.value;
	environnement.recherche=recherche;
	search();
}
