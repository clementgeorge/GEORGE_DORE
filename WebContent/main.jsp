<!doctype html>
<html>
<head>
<title>Accueil</title>
<link href="macss.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/deconnexion.js"></script>
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
			<textarea name="input_tweet" rows="3" id="input_tweet"
				placeholder="Tapez votre commentaire ici..."></textarea>
			<a href="javascript:$('#registerForm').submit();return false;" id="CommentButton">Commenter</a>
		</div>
		</div>
		<div id="tweets">
			<div class="tweet">
				<ttlt>boby</ttlt>
				voici un exemple de tweet plus long plus long plus long plus long
				plus long plus long et même super super long bla bla bla bla bla.Il
				fait beau derhors le vent souffle etc...
			</div>
			<div class="tweet">
				<ttlt>boby2</ttlt>
				voici un exemple de tweet plus long plus long plus long plus long
				plus long plus long et même super super long bla bla bla bla bla.Il
				fait beau derhors le vent souffle etc...
			</div>
			<div class="tweet">
				<ttlt>boby2</ttlt>
				voici un exemple de tweet plus long plus long plus long plus long
				plus long plus long et même super super long bla bla bla bla bla.Il
				fait beau derhors le vent souffle etc...
			</div>
			<div class="tweet">
				<ttlt>boby2</ttlt>
				voici un exemple de tweet plus long plus long plus long plus long
				plus long plus long et même super super long bla bla bla bla bla.Il
				fait beau derhors le vent souffle etc...
			</div>
			<div class="tweet">
				<ttlt>boby2</ttlt>
				voici un exemple de tweet plus long plus long plus long plus long
				plus long plus long et même super super long bla bla bla bla bla.Il
				fait beau derhors le vent souffle etc...
			</div>
			<div class="tweet">
				<ttlt>boby2</ttlt>
				voici un exemple de tweet plus long plus long plus long plus long
				plus long plus long et même super super long bla bla bla bla bla.Il
				fait beau derhors le vent souffle etc...
			</div>
			<div class="tweet">
				<ttlt>boby2</ttlt>
				voici un exemple de tweet plus long plus long plus long plus long
				plus long plus long et même super super long bla bla bla bla bla.Il
				fait beau derhors le vent souffle etc...
			</div>
			<div class="tweet">
				<ttlt>boby2</ttlt>
				voici un exemple de tweet plus long plus long plus long plus long
				plus long plus long et même super super long bla bla bla bla bla.Il
				fait beau derhors le vent souffle etc...
			</div>
			<div class="tweet">
				<ttlt>boby2</ttlt>
				voici un exemple de tweet plus long plus long plus long plus long
				plus long plus long et même super super long bla bla bla bla bla.Il
				fait beau derhors le vent souffle etc...
			</div>
	</div>
</body>
</html>