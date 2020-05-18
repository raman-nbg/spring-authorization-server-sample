package com.github.ramannbg.springauthorizationserversample.authorizationserver;

import com.github.ramannbg.springauthorizationserversample.authorizationserver.model.Client;
import com.github.ramannbg.springauthorizationserversample.authorizationserver.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AuthorizationServerApplication implements CommandLineRunner {
	private final ClientRepository clientRepository;

	@Autowired
	public AuthorizationServerApplication(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (clientRepository.findByClientId("first-client").isEmpty()) {
			Set<String> grantTypes = new HashSet<>();
			grantTypes.add("code");
			grantTypes.add("id_token");
			grantTypes.add("authorization_code");

			Set<String> redirectUris = new HashSet<>();
			redirectUris.add("http://localhost:4200/index.html");
			redirectUris.add("http://localhost:4200/silent-refresh.html");

			Set<String> scopes = new HashSet<>();
			scopes.add("openid");
			scopes.add("email");
			scopes.add("profile");

			Client client = new Client();
			client.setClientId("first-client");
			client.setClientSecret("foobar");
			client.setAuthorizedGrantTypes(grantTypes);
			client.setDescription("The first client");
			client.setRegisteredAt(new Date());
			client.setRegisteredRedirectUri(redirectUris);
			client.setScopes(scopes);

			clientRepository.insert(client);
		}
	}
}
