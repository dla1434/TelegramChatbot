package com.sir.chatbot.emoji;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Execute {
	public static void main(String[] args) {
		// Initialize Api Context
		ApiContextInitializer.init();

		// Instantiate Telegram Bots API
		TelegramBotsApi botsApi = new TelegramBotsApi();
		
		try
		{
			EmojiBot myBot = new EmojiBot();
			botsApi.registerBot(new EmojiBot());
			
		} catch (TelegramApiRequestException e) {
			log.error("TelegramApiRequestException : ", e);
			e.printStackTrace();
		} catch (TelegramApiException e) {
			log.error("TelegramApiException : ", e);
			e.printStackTrace();
		}
	}
}
