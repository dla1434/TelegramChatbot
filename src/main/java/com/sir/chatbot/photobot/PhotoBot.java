package com.sir.chatbot.photobot;

import java.util.Comparator;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhotoBot extends TelegramLongPollingBot {
	@Override
	public void onUpdateReceived(Update update) {
		// We check if the update has a message and the message has text
		if (update.hasMessage() && update.getMessage().hasText()) {
			// Set variables
			String message_text = update.getMessage().getText();
			long chat_id = update.getMessage().getChatId();
			SendMessage message = new SendMessage();			// Create a message object
			message.setChatId(chat_id).setText(message_text);	// object
					
			try {
				execute(message); // Sending our message object to user
			} catch (TelegramApiException e) {
				e.printStackTrace();
				log.error("TelegramApiException : ", e);
			}
		}
		else if( update.hasMessage() && update.getMessage().hasPhoto() ){
			long chat_id = update.getMessage().getChatId();
			
			List<PhotoSize> photos = update.getMessage().getPhoto();
			
			//Know file_id
			String f_id = photos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst().orElse(null).getFileId();
			
			//Know photo width
			int f_width = photos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst().orElse(null).getWidth();

			//Know photo height
			int f_height = photos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst().orElse(null).getHeight();
			
			// Set photo caption
			String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
			SendPhoto msg = new SendPhoto().setChatId(chat_id).setPhoto(f_id).setCaption(caption);
			
			try {
				//Call method to send the photo with caption
				sendPhoto(msg); 
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
