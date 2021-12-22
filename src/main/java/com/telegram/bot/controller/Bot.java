package com.telegram.bot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

        /**
         * Method for receiving messages.
         * @param update Contains a message from the user.
         */
        @Override
        public void onUpdateReceived(Update update) {
            String message = update.getMessage().getText();
            sendMsg(update.getMessage().getChatId().toString(), message);
        }

        /**
         * Method for creating a message and sending it.
         * @param chatId chat id
         * @param s The String that you want to send as a message.
         */
        public synchronized void sendMsg(String chatId, String s) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.enableMarkdown(true);
            sendMessage.setChatId(chatId);
            sendMessage.setText(s);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                System.out.println("Exception: "+ e.toString());
            }
        }

        public synchronized void setButtons(SendMessage sendMessage) {
            // Create a keyboard
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setSelective(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(false);

            // Create a list of keyboard rows
            List<KeyboardRow> keyboard = new ArrayList<>();
            // First keyboard row
            KeyboardRow keyboardFirstRow = new KeyboardRow();

            // Add buttons to the first keyboard row
            keyboardFirstRow.add(new KeyboardButton("Hi"));

            // Second keyboard row
            KeyboardRow keyboardSecondRow = new KeyboardRow();
            // Add the buttons to the second keyboard row
            keyboardSecondRow.add(new KeyboardButton("Help"));

            // Add all of the keyboard rows to the list
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            // and assign this list to our keyboard
            replyKeyboardMarkup.setKeyboard(keyboard);
        }
        /**
         * This method returns the bot's name, which was specified during registration.
         * @return bot name
         */
        @Override
        public String getBotUsername() {
            return "wiki_now_bot";
        }

        /**
         * This method returns the bot's token for communicating with the Telegram server
         * @return the bot's token
         */
        @Override
        public String getBotToken() {
            return "5051684343:AAEfvHhrLssbYOH9ZK0SWGqYlWzcmdMTBM8";
        }

    }
