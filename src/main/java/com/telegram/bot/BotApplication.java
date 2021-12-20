package com.telegram.bot;

import com.telegram.bot.controller.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class BotApplication {

    public static void main(String[] args) {
        try {
            System.out.println(1);
            TelegramBotsApi telegramBotApi = new TelegramBotsApi(DefaultBotSession.class);
            System.out.println(2);
            telegramBotApi.registerBot(new Bot());
            System.out.println(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
