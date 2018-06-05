package com.sir.chatbot.test;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TelegramSender {
	public static void main(String[] args) {
		// Initialize Api Context
		ApiContextInitializer.init();

		// Instantiate Telegram Bots API
		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
			botsApi.registerBot(new TelegramLongPollingBot() {
				@Override
				public void onUpdateReceived(Update update) {
					SendMessage message = new SendMessage();
					message.setChatId("52773309").setText("test msg");
					try {
						execute(message);
					} catch (TelegramApiException e) {
						e.printStackTrace();
					}
				}

				@Override
				public String getBotUsername() {
					return "sri_test_bot";
				}

				@Override
				public String getBotToken() {
					return "410641159:AAEtLIS1VytWXBWFos0xFPg3Ak6nHZfFmnc";
				}
			});
		} catch (TelegramApiRequestException e) {
			log.error("TelegramApiRequestException : ", e);
			e.printStackTrace();
		}
		
		
	}
}
