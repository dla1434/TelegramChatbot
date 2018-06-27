package com.sir.chatbot.simple.send;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleSend {
	public static void main(String[] args) {
		try {
			TelegramBot myBot = new TelegramBot("sri_test_bot", "410641159:AAEtLIS1VytWXBWFos0xFPg3Ak6nHZfFmnc");
			SendMessage message = new SendMessage();
			message.setChatId("@sri_gram_test").setText("Test Msg Send");
			myBot.execute(message);
		} catch (TelegramApiException e) {
			log.error("TelegramApiException : {}", e);
		}
	}
}
