import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;

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
                        sendText(message, Crypto.getCrypto(message.getText(), model, 3));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
