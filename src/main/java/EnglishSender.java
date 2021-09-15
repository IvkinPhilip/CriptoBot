import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;

class EnglishSender extends Information {

    @Override
    void sendInfo(Message message, Model model) {
        sendEnglish(message, model);
    }

    @Override
    String getText(Model model) {
        return "most common symbol = " + model.getSymbol() + "\n" +
                "proper name for asset = " + model.getName() + "\n" +
                "available supply for trading = " + model.getSupply() + "\n" +
                "total quantity of asset issued = " + model.getMaxSupply() + "\n" +
                "supply x price = " + model.getMarketCapUsd() + "\n" +
                "quantity of trading volume represented in USD over the last 24 hours = " + model.getVolumeUsd24Hr() + "\n" +
                "volume-weighted price based on real-time market data, translated to USD = " + model.getPriceUsd() + "\n" +
                "the direction and value change in the last 24 hours = " + model.getChangePercent24Hr() + "\n" +
                "Volume Weighted Average Price in the last 24 hours = " + model.getVwap24Hr() + "\n";

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

