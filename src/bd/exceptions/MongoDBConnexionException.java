package bd.exceptions;

public class MongoDBConnexionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage(){
		return "Connexion impossible � la base de Donn�e MongoDB";
	}


}
