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
public class WeatherBot {
	public static void main(String[] args) {
		// Initialize Api Context
		ApiContextInitializer.init();

		// Instantiate Telegram Bots API
		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
			MyAmazingBot myBot = new MyAmazingBot();
			
			botsApi.registerBot(new MyAmazingBot());
			
			//Send by chatId
			/*
			SendMessage message = new SendMessage();
			message.setChatId("52773309").setText("execute msg");
//			myBot.sendMessage(message);
			myBot.execute(message);
			*/
			
			//Send by @channelName
			SendMessage message = new SendMessage();
			message.setChatId("@sri_gram_test").setText("channel msg");
			myBot.execute(message);
		} catch (TelegramApiRequestException e) {
			log.error("TelegramApiRequestException : ", e);
			e.printStackTrace();
		} catch (TelegramApiException e) {
			log.error("TelegramApiException : ", e);
			e.printStackTrace();
		}
	}
}
