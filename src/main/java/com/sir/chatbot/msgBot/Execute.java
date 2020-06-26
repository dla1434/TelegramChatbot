package com.sir.chatbot.msgBot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Execute {

	public static void main(String[] args) {
//		SpringApplication.run(ChatbotApplication.class, args);

		// Initialize Api Context
		ApiContextInitializer.init();

		// Instantiate Telegram Bots API
		TelegramBotsApi botsApi = new TelegramBotsApi();
		
		// Register our bot
		try {
			botsApi.registerBot(new MsgSendBot());
		} 
		catch (TelegramApiException e) {
			e.printStackTrace();
			log.error("TelegramApiException : ", e);
		}
	}
}
