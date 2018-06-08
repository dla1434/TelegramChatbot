package com.sir.chatbot.photobot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhotoBot extends TelegramLongPollingBot {
	@Override
	public void onUpdateReceived(Update update) {
		// We check if the update has a message and the message has text
		if (update.hasMessage() && update.getMessage().hasText()) {
			String message_text = update.getMessage().getText();
			long chat_id = update.getMessage().getChatId();
			
			//response user's input msg
			if (message_text.equals("/start")) {
				SendMessage message = new SendMessage().setChatId(chat_id).setText(message_text);
				try {
					execute(message);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else if (message_text.equals("/pic")) {
				SendPhoto msg = new SendPhoto().setChatId(chat_id).setPhoto("AgADBQADBagxGww40FRRvHN3pUH_KMBF1jIABAL6oTLOw2LmyxYBAAEC").setCaption("Photo");
				try {
					sendPhoto(msg); // Call method to send the photo
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else if (message_text.equals("/markup")) {
				SendMessage message = new SendMessage().setChatId(chat_id).setText("Here is your keyboard");
				
				// Create ReplyKeyboardMarkup object
				ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
				
				// Create the keyboard (list of keyboard rows)
				List<KeyboardRow> keyboard = new ArrayList<>();
				
				// Create a keyboard row
				KeyboardRow row = new KeyboardRow();
				// Set each button, you can also use KeyboardButton objects if
				// you need something else than text
				row.add("Row 1 Button 1");
				row.add("Row 1 Button 2");
				row.add("Row 1 Button 3");
				
				// Create another keyboard row
				row = new KeyboardRow();
				// Set each button for the second line
				row.add("Row 2 Button 1");
				row.add("Row 2 Button 2");
				row.add("Row 2 Button 3");
				
				// Add the first row to the keyboard
				keyboard.add(row);
				
				// Add the second row to the keyboard
				keyboard.add(row);
				
				// Set the keyboard to the markup
				keyboardMarkup.setKeyboard(keyboard);
				
				// Add it to the message
				message.setReplyMarkup(keyboardMarkup);
				try {
					execute(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else {
				// Unknown command
				SendMessage message = new SendMessage() // Create a message
														// object object
						.setChatId(chat_id).setText("Unknown command");
				try {
					sendMessage(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
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
