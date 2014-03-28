<!doctype html>
<html>
<head>
<title>Accueil</title>
<link href="macss.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="main.js"></script>
</head>
<body>
	<div id="main">
		<span class="actionlog">
			<a href="login.html" id="connexion.link"> Connexion</a>/<a href="register.html" id="enregistrement.link"> Enregistrement</a>
		</span>
		<script type="text/javascript">
			function go(){
				<% String id=request.getParameter("id");
					String login= request.getParameter("login");
					String key=request.getParameter("key");
					if(id!=null && login!=null && key!=null){
						out.println("main('"+id+"','"+login+"','"+key+"')");
					}else{
						out.println("main()");
					}
				%>		 
			}
			$(go);
		</script>

	</div>
</body>
</html>