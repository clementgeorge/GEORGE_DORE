function main(id,login,key){
	environnement = new Object();
	environnement.users=[];
	environnement.friends=[];
	environnement.onlyFriends=false;
	environnement.recherche='';
	environnement.oldrecherche='';
	environnement.resultatrecherche=undefined;
	
	if((id!=undefined) && (login!=undefined) && (key!=undefined)){
		environnement.actif=new User(id,login);
		environnement.key=key;
		initialiseListeFriends();
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

// Gere l'affichage de "Connexion/Deconnexion"

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

// Gere l'affichage tu textarea pour noter les tweets

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

//Gere l'affichage du bouton deconnexion (affiche lors du clic sur le nom de l'utilisateur)

function gererBoutonDisconnect(){
	var hid=document.getElementById('disconnect').style.display;
	if(hid=='none'){
		document.getElementById('disconnect').style.display='inline';
	}
	else{
		document.getElementById('disconnect').style.display='none';
	}
}



function encode(s) {
	 var r=unescape(encodeURIComponent(s));
	 r=r.replace(/\?/g, "%3F");
	 r=r.replace(/\&/g,"%26");
	 r=r.replace(/\#/g,"%23");
	 return r;
}

function traiteReponsePostTweet(json){
	if(json.error==undefined && json.tweet!=undefined);
	var lastCom=new Commentaire(json.tweet.auteur_id,json.tweet.auteur_login,json.tweet.text,json.tweet.date);
	$('#tweets_list').prepend(lastCom.getHtml());
}

// Affiche une checkBox "Filtre Amis" sur les tweets, si l'utilisateur est connecte 
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
	$('#tweets_list').empty();
	if(environnement.resultatrecherche==undefined){
		search();
	}
	$('#tweets_list').append(environnement.resultatrecherche.getHtml());
}

// Fonction de recherche 

function rechercher(formulaire){
	var recherche=formulaire.recherche.value;
	environnement.recherche=recherche;
	search();
	environnement.oldrecherche=recherche;
}

