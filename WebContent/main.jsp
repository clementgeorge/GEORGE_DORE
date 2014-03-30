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
	<div id="main">
	<div id="titre">Le Jacasseur</div>
	<div id="description">Bienvenue sur votre réseau social</div> 
	<span id="connectlog"> <a href="login.html"
			id="connexion.link">Connexion</a> / <a href="register.html"
			id="enregistrement.link">Enregistrement</a>
	</span>


	</div>
</body>
</html>