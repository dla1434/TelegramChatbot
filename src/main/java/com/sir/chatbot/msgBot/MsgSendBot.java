package com.sir.chatbot.msgBot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MsgSendBot extends TelegramLongPollingBot {
	@Override
	public void onUpdateReceived(Update update) {
		log.info("onUpdateReceived");
		log.info("hasMessage : {}, hasText : {}", update.hasMessage());
		log.info("hasMessage : {}, getMessage : {}", update.getMessage());
//		log.info("hasMessage : {}, hasText : {}", update.hasMessage(), update.getMessage().hasText());
		
		if (update.hasMessage() && update.getMessage().hasText()) {
			log.info("onUpdateReceived hasText");
			
			String message_text = update.getMessage().getText();
			long chat_id = update.getMessage().getChatId();
			
			// Create a message object
			SendMessage message = new SendMessage();
			message.setChatId(chat_id).setText(message_text);
			
			try {
				execute(message); // Sending our message object to user
				
				log.info("execute : {}", message);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
		else{
			log.info("onUpdateReceived else");
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
