package com.sir.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ChatbotApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ChatbotApplication.class, args);

		// Initialize Api Context
		ApiContextInitializer.init();

		// Instantiate Telegram Bots API
		TelegramBotsApi botsApi = new TelegramBotsApi();
		
		// Register our bot
		try {
			botsApi.registerBot(new MyAmazingBot());
		} 
		catch (TelegramApiException e) {
			e.printStackTrace();
			log.error("TelegramApiException : ", e);
		}
	}
}
