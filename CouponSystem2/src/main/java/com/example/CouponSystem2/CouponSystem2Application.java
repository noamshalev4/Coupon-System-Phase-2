package com.example.CouponSystem2;

import com.example.CouponSystem2.utilities.Color;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponSystem2Application {

	public static void main(String[] args) {

		SpringApplication.run(CouponSystem2Application.class, args);
		System.out.println(Color.CYAN_BOLD + "  ___ ___ _____   _____ ___   ___ ___   ___ _   _ _  _ _  _ ___ _  _  ___ \n" +
				" / __| __| _ \\ \\ / / __| _ \\ |_ _/ __| | _ \\ | | | \\| | \\| |_ _| \\| |/ __|\n" +
				" \\__ \\ _||   /\\ V /| _||   /  | |\\__ \\ |   / |_| | .` | .` || || .` | (_ |\n" +
				" |___/___|_|_\\ \\_/ |___|_|_\\ |___|___/ |_|_\\\\___/|_|\\_|_|\\_|___|_|\\_|\\___|\n" +
				"                                                                          " + Color.RESET);
	}
}
