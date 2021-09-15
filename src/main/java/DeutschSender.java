import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;

class DeutschSender extends Information {

    @Override
    void sendInfo(Message message, Model model) {
        sendGermany(message, model);
    }

    @Override
    String getText(Model model) {
        return "häufigste symbol = " + model.getSymbol() + "\n" +
                "eigenname für Asset = " + model.getName() + "\n" +
                "verfügbares Angebot für den Handel = " + model.getSupply() + "\n" +
                "gesamtmenge der ausgegebenen Vermögenswerte = " + model.getMaxSupply() + "\n" +
                "supply x Preis = " + model.getMarketCapUsd() + "\n" +
                "menge des in USD vertretenen Handelsvolumens in den letzten 24 Stunden = " + model.getVolumeUsd24Hr() + "\n" +
                "volumengewichteter Preis basierend auf Echtzeit-Marktdaten, übersetzt in USD = " + model.getPriceUsd() + "\n" +
                "die Richtung und der Wert ändern sich in den letzten 24 Stunden = " + model.getChangePercent24Hr() + "\n" +
                "Средневзвешанная цена за последние 24 часа = " + model.getVwap24Hr() + "\n";
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
