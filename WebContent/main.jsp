<!doctype html>
<html>
<head>
<title>Accueil</title>
<link href="macss.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/deconnexion.js"></script>
<script type="text/javascript" src="js/dateformat.js"></script>
</head>
<body>
	<script type="text/javascript">
		function go() {
	<%String id = request.getParameter("id");
			String login = request.getParameter("login");
			String key = request.getParameter("key");
			if (id != null && login != null && key != null) {
				out.println("main('" + id + "','" + login + "','" + key + "');");
			} else {
				out.println("main();");
			}%>
		}
		$(go);
	</script>
		<div id="menu">
		<div id="baniere">
			<div id="titre">Le Jacasseur</div>
			<div id="description">Bienvenue sur votre réseau social</div>
			<span id="connectlog"> <a href="login.html"
				id="connexion.link">Connexion</a> / <a href="register.html"
				id="enregistrement.link">Enregistrement</a>
			</span>
			<form id="postForm" action="javascript:(function(){return;})()" method="get" OnSubmit="javascript:postTweet(this);">
			<textarea name="input_tweet" rows="3" id="input_tweet"
				placeholder="Tapez votre commentaire ici..."></textarea>
			<a href="#" onClick="$('#postForm').submit();" id="CommentButton">Commenter</a>
			</form>
		</div>
		</div>
		<div id="tweets">
		</div>
</body>
</html>