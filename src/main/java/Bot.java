import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

abstract class Information extends Bot {
    public static final String TEXTLANGUAGE = "/russian - Русский \uD83C\uDDF7\uD83C\uDDFA\n" +
            "/english - English \uD83C\uDDEC\uD83C\uDDE7\n" +
            "/deutsch - Deutsch \uD83C\uDDE9\uD83C\uDDEA";

    abstract void sendInfo(Message message, Model model);

    abstract String getText(Model model);
}

class RussianSender extends Information {

    @Override
    void sendInfo(Message message, Model model) {
        sendRussian(message, model);
    }

    @Override
    String getText(Model model) {
        return "Нижняя граница стоимости инструмента в этой свечи = " + model.getName() + "\n" +
                "Верхняя граница стоимости инструмента в этой свечи = " + model.getName() + "\n" +
                "Стоимость инструмента на момент открытия этой свечи = " + model.getName() + "\n" +
                "Стоимость инструмента на момент закрытия этой свечи = " + model.getName() + "\n" +
                "Время открытия этой свечи = " + model.getName() + "\n";
    }

    public void sendRussian(Message message, Model model) {
        if (message != null && message.hasText()) {
            switch (message.getText().toUpperCase()) {
                case "ИНФОРМАЦИЯ":
                    sendText(message, "Информация");
                    break;
                case "/START":
                    sendText(message, "Приветсвую Вас");
                    break;
                case "ЯЗЫК":
                    sendText(message, "Выберете язык:\n" + TEXTLANGUAGE);
                    break;
                default:
                    try {
                        sendText(message, Crypto.getCrypto(message.getText(), model, State.idLanguage));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}

class EnglishSender extends Information {

    @Override
    void sendInfo(Message message, Model model) {
        sendEnglish(message, model);
    }

    @Override
    String getText(Model model) {
        return "The lower limit of the value of the instrument in this candle = " + model.getName() + "\n" +
                "The upper limit of the value of the instrument in this candle = " + model.getName() + "\n" +
                "The price of the instrument at the time of opening this candle = " + model.getName() + "\n" +
                "The price of the instrument at the close of this candle = " + model.getName() + "\n" +
                "The opening time of this candle = " + model.getName() + "\n";

    }

    public void sendEnglish(Message message, Model model) {
        if (message != null && message.hasText()) {
            switch (message.getText().toUpperCase()) {
                case "INFORMATION":
                    sendText(message, "Information");
                    break;
                case "LANGUAGE":
                    sendText(message, "Choose language:\n" + TEXTLANGUAGE);
                    break;
                default:
                    try {
                        sendText(message, Crypto.getCrypto(message.getText(), model, State.idLanguage));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}

class DeutschSender extends Information {

    @Override
    void sendInfo(Message message, Model model) {
        sendGermany(message, model);
    }

    @Override
    String getText(Model model) {
        return "Die untere Grenze der Werkzeugkosten in dieser Kerze = " + model.getName() + "\n" +
                "Die obere Grenze des Wertes des Instruments in dieser Kerze = " + model.getName() + "\n" +
                "Der Preis des Instruments zum Zeitpunkt des Öffnens dieser Kerze = " + model.getName() + "\n" +
                "Der Preis des Instruments am Ende dieser Kerze = " + model.getName() + "\n" +
                "Die Öffnungszeit dieser Kerze = " + model.getName() + "\n";
    }

    public void sendGermany(Message message, Model model) {
        if (message != null && message.hasText()) {
            switch (message.getText().toUpperCase()) {
                case "INFORMATION":
                    sendText(message, "Information");
                    break;
                case "SPRANCHE":
                    sendText(message, "Sprache auswählen:\n" + TEXTLANGUAGE);
                    break;
                default:
                    try {
                        sendText(message, Crypto.getCrypto(message.getText(), model, State.idLanguage));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}

class Container {
    final static List<KeyboardButton> cryptoButton = List.of(new KeyboardButton("BTC"), new KeyboardButton("ETH"),
            new KeyboardButton("BNB"), new KeyboardButton("DOGE"), new KeyboardButton("DOT"), new KeyboardButton("ADA"));
    final static Map<Integer, List<KeyboardButton>> buttonContainer = Map.of(1, List.of(new KeyboardButton("Информация"), new KeyboardButton("Язык")),
            2, List.of(new KeyboardButton("Information"), new KeyboardButton("Language")),
            3, List.of(new KeyboardButton("Information"), new KeyboardButton("Spranche")));
    static Map<Integer, Information> languageContainer = Map.of(1, new RussianSender(), 2, new EnglishSender(), 3, new DeutschSender());
}

class State {
    public static int idLanguage = 1;
}
