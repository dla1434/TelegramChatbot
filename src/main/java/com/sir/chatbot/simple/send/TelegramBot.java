package com.sir.chatbot.simple.send;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
	private String botUserName;
	private String botToken;

	public TelegramBot(String botUserName, String botToken){
		this.botUserName = botUserName;
		this.botToken = botToken;
	}
	
	@Override
	public void onUpdateReceived(Update update) {
		log.info("onUpdateReceived");
		
		if (update.hasMessage() && update.getMessage().hasText()) {
			String message_text = update.getMessage().getText();
			long chat_id = update.getMessage().getChatId();
			
			// Create a message object
//			SendMessage message = new SendMessage();
//			message.setChatId(chat_id).setText(message_text);
			
//			try {
//				execute(message); // Sending our message object to user
//			} catch (TelegramApiException e) {
//				e.printStackTrace();
//			}
		}
	}

	@Override
	public String getBotUsername() {
		log.info("getBotUsername : {}", this.botUserName);
		
		return this.botUserName;
	}

	@Override
	public String getBotToken() {
		log.info("getBotToken : {}", this.botToken);
		
		return this.botToken;
	}
}
