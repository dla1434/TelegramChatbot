package com.sir.chatbot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyAmazingBot extends TelegramLongPollingBot {
	@Override
	public void onUpdateReceived(Update update) {
		log.info("onUpdateReceived");
		
		if (update.hasMessage() && update.getMessage().hasText()) {
			String message_text = update.getMessage().getText();
			long chat_id = update.getMessage().getChatId();
			
			// Create a message object
			SendMessage message = new SendMessage();
			message.setChatId(chat_id).setText(message_text);
			
			try {
				execute(message); // Sending our message object to user
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getBotUsername() {
		log.info("getBotUsername");
		
		return "sri_test_bot";
	}

	@Override
	public String getBotToken() {
		log.info("getBotToken");
		
		return "410641159:AAEtLIS1VytWXBWFos0xFPg3Ak6nHZfFmnc";
	}
}
