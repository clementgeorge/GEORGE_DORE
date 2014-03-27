function main(){
	environnement = new Object();
	environnement.users=[];
}

function user(id,login,password){
	this.id=id;
	this.login=login;
	this.contact=false;
	if(contact!=undefined){
		this.contact=contact;
	}
	if(environnement==undefined){
		environnement=new Object();
		environnement.users=[];
	}
	environnement.users[this.id]=this;



	user.prototype.modifStatus=function(){
		this.contact=!this.contact;
	}
}

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
				r=new Obejct();
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
