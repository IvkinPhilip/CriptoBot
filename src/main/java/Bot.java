import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public static final String RUSSIAN = "/RUSSIAN";
    public static final String ENGLISH = "/ENGLISH";
    public static final String DEUTSCH = "/DEUTSCH";
    public static final String RUSSIANLANGUAGE = "Язык - Русский";
    public static final String ENGLISHLANGUAGE = "Language - English";
    public static final String DEUTSCHLANGUAGE = "Sprache - Deutsch";

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "HacatoncryptocurrencyBot";
    }

    public String getBotToken() {
        return "1643671758:AAESRgwZtuBBDVHabunx414qrtAMSaL1gcU";
    }

    void setLanguage(Message message) {
        switch (message.getText().toUpperCase()) {
            case RUSSIAN:
                State.idLanguage = 1;
                sendText(message, RUSSIANLANGUAGE);
                break;
            case ENGLISH:
                State.idLanguage = 2;
                sendText(message, ENGLISHLANGUAGE);
                break;
            case DEUTSCH:
                State.idLanguage = 3;
                sendText(message, DEUTSCHLANGUAGE);
                break;
            default:
        }
    }

    public void setButtons(SendMessage sendText) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendText.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRowsList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardFirstRow.addAll(Container.buttonContainer.get(State.idLanguage));

        keyboardSecondRow.addAll(Container.cryptoButton);

        keyboardRowsList.add(keyboardFirstRow);
        keyboardRowsList.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowsList);
    }

    public void sendText(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            execute(sendMessage.setText(text));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Model model = new Model();
        Message message = update.getMessage();
        setLanguage(message);
        Information sender = Container.languageContainer.get(State.idLanguage);
        sender.sendInfo(message, model);
    }
}