package bd.exceptions;

public class NoSqlConnectionException extends Exception {
	
	private String message;
	
	public NoSqlConnectionException(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage(){
		return message;
	}

}
