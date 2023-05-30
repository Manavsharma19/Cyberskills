package com.cyberskills.mastersbuilder;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final EndUserRepository repository;

	@Autowired
	public DatabaseLoader(EndUserRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... strings) throws Exception {
		this.repository.save(new EndUser("Frodo", "Baggins", "ring bearer"));
		this.repository.save(new EndUser("Bilbo", "Baggins", "burglar"));
		this.repository.save(new EndUser("Gandalf", "the Grey", "wizard"));
		this.repository.save(new EndUser("Samwise", "Gamgee", "gardener"));
		this.repository.save(new EndUser("Meriadoc", "Brandybuck", "pony rider"));
		this.repository.save(new EndUser("Peregrin", "Took", "pipe smoker"));	}
}
