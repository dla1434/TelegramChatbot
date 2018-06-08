package com.sir.chatbot.emoji;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmojiBot extends TelegramLongPollingBot {
	String recentPhotoFileId = null;
	
	@Override
	public void onUpdateReceived(Update update) {
		// We check if the update has a message and the message has text
		if (update.hasMessage() && update.getMessage().hasText()) {
			String message_text = update.getMessage().getText();
			long chat_id = update.getMessage().getChatId();
			
			try{
				//response user's input msg
				if (message_text.equals("/start")) {
					SendMessage message = new SendMessage().setChatId(chat_id).setText(message_text);
					execute(message);
				} else if (message_text.equals("/who am i")) {
					// Set variables
					String user_first_name = update.getMessage().getChat().getFirstName();
					String user_last_name = update.getMessage().getChat().getLastName();
					String user_username = update.getMessage().getChat().getUserName();
					long user_id = update.getMessage().getChat().getId();
					
					String infoMsg = log(user_first_name, user_last_name, Long.toString(user_id), message_text, message_text);
					
					SendMessage message = new SendMessage().setChatId(chat_id).setText(infoMsg);
					execute(message);
				} 
			} catch (TelegramApiException e) {
				log.error("TelegramApiException : ", e);
			} catch (Exception e){
				log.error("Exception : ", e);
			}
		}
		else if( update.hasMessage() && update.getMessage().hasPhoto() ){
			long chat_id = update.getMessage().getChatId();
			
			List<PhotoSize> photos = update.getMessage().getPhoto();
			
			//Know file_id
			String f_id = photos.stream().sorted(Comparator.comparing(PhotoSize::getFileSize).reversed()).findFirst().orElse(null).getFileId();
			recentPhotoFileId = f_id;
			
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
	
	private String log(String first_name, String last_name, String user_id, String txt, String bot_answer) {
		StringBuffer sb = new StringBuffer();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		sb.append("\n ---------------------------- log ----------------------------")
			.append(dateFormat.format(date)).append("\r\n")
			.append("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt).append("\r\n")
			.append("Bot answer: \n Text - " + bot_answer);
			
		log.info(sb.toString());
		
		return sb.toString();
	}
}
