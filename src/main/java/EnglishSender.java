import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;

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

    public void sendEnglish (Message message, Model model) {
        if (message != null && message.hasText()) {
            switch (message.getText().toUpperCase()) {
                case "INFORMATION":
                    sendText(message, "Information");
                    break;
                case "LANGUAGE":
                    sendText(message, "Choose language:\n" + TEXTLANGUAGE);
                    break;
                case "/START":
                    sendText(message, "Hello");
                    break;
                default:
                    try {
                        sendText(message, Crypto.getCrypto(message.getText(), model, 2));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}


