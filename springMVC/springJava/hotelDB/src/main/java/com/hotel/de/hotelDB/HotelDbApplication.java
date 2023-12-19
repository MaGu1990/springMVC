//Aplicativo principal

package com.example.binatur_db;

import com.example.binatur_db.uiconsole.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.binatur_db")
public class Binatur_dbApplication implements CommandLineRunner {

	@Autowired
	private Menu menu;

	public static void main(String[] args) {
		SpringApplication.run(Binatur_dbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menu.exibirMenu();
	}
}
