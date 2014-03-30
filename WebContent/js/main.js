function main(id,login,key){
	environnement = new Object();
	environnement.users=[];
	if((id!=undefined) && (login!=undefined) && (key!=undefined)){
		environnement.actif=new User(id,login);
		environnement.key=key;
	}
	gererDivConnexion();
	//search();
	//$("#disconnect").click(disconnect);
	//$("#box_friends").click(func_filtre);
}

function User(id, login, contact) {
	this.id = id;
	this.login = login;
	if(contact!=undefined){
		this.contact = contact;
	}
	environnement.users[id] = this;

	User.prototype.modifStatus=function(){
		this.contact=!this.contact;
	}
}

function search(){};

function Commentaire(id,auteur,texte,data,score){
	this.id=id;
	this.auteur=auteur;
	this.texte=texte;
	this.data=data;
	this.score=score;


	Commentaire.prototype.getHtml= function(){
		var s="\t <div id=\"Commentaire_"+this.id+"\ class=\"Comment\">\n";
		s+="\t\t <div class=\"text_comment\">"+this.texte+ "</div>\n";
		if(this.auteur.contact){
			s+="\t <img class=\"image_"+this.auteur.id+"\" src= \"\"Images/image.jpg\" title=\"AjoutContact";
			Onclick="\"javascript:ajout_contact("+this.auteur.id+")\"/>\n";	
		}
		return(s);
	}
}

function RechercheCommentaire(resultat, recherche, contact_online, auteur, date){
	this.resultat=resultat;
	this.auteur=auteur;
	this.recherche=recherche;
	if(recherche==undefined){
		this.recherche="";
	}
	this.date=date;
	if(date==undefined){
		this.date= new Date();
	}
	environnement.recherche=this;


	RechercheCommentaire.prototype.getHtml=function(){
		var s="<div id=\"commentaires\">\n";
		for (var i=0;i<this.resultat.length;i++){
			s+=this.resultat[i].getHtml()+"\n";
		}
		s+="</div>";
		return s;
	}

	RechercheCommentaire.revival=function revival(key,value){
		if(key.length ==0){
			var r; 
			if((value.erreur == undefined)||(value.erreur == 0)){
				r= new RechercheCommentaire(value.resultat,value.recherche,value.date,value.auteur);
			}
			else{
				r=new Object();
				r.erreur=value.erreur;
			}
			return r;
		}
		else if (value.auteur instanceof User){
			var c=new Commentaire(value.id,value.auteur,value.texte, value.date,value.score);
			return c;
		}
		else if (key == "auteur"){
			var a;
			if ((environnement != undefined) && (environnement.users[value.id]!= undefined)){
				a=environnement.users[value.id];
			}
			else{
				a=new User(value.id,value.login,value.contact);
			}
			return a;
		}
		else{
			return value;
		}
	}

	RechercheCommentaire.traiteJSON= function(JSONtext){
		var obj=JSON.parse(JSONtext, RechercheCommentaire.revival);
		if(obj.erreur==undefined){
			alert(obj.getHtml());
		}
		else{
			alert(obj.erreur);
		}
	}
}

function search(){
	/*var friend=($("#boxfriends").get(0).checked)?1:0;
	var query=($("#requete").val());*/
	$.ajax({
		type : "get",
		url : "search",
		dataType : "JSON",
		success : rechercheCommentaire.traiteReponsesJSON,
		error : function(XHR, testStatus, errorThrown) {
			alert(XHR + "" + testStatus + "" + errorThrown);
		}
	});
	
}

function gererDivConnexion(){
	var user =environnement.actif;
	if((user!=undefined) && (user.login!="")){
		$('#connectlog').html("<span id=\"login\">"+user.login+"</span> <span id=\"disconnect\" style=\"display:none;\">Deconnexion</span>");
		$('#disconnect').css({
			"color" : "red"
		});
		$( document ).on('click', '#login', GererBoutonDisconnect);
		$( document ).on('click', '#disconnect', deconnexion);
	}
	else{
		$('#connectlog').html("<a href=\"login.html\"id=\"connexion.link\">Connexion</a> / <a href=\"register.html\"id=\"enregistrement.link\">Enregistrement</a>");
	}
}

function GererBoutonDisconnect(){
	var hid=document.getElementById('disconnect').style.display;
	if(hid=='none'){
		document.getElementById('disconnect').style.display='inline';
	}
	else{
		document.getElementById('disconnect').style.display='none';
	}
}

