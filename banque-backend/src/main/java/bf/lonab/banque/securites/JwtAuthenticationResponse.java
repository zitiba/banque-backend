package bf.lonab.banque.securites;

public class JwtAuthenticationResponse {

	 private String accessToken;
	 private String tokenType= "bearer";
	 
	public JwtAuthenticationResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	 
}
