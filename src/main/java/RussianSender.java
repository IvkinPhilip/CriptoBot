import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;

class RussianSender extends Information {

    @Override
    void sendInfo(Message message, Model model) {
        sendRussian(message, model);
    }

    @Override
    String getText(Model model) {
        return "Символ = " + model.getSymbol() + "\n" +
                "Название = " + model.getName() + "\n" +
                "Количество активов = " + model.getSupply() + "\n" +
                "Общее количество выпущенных активов = " + model.getMaxSupply() + "\n" +
                "Активы * цена = " + model.getMarketCapUsd() + "\n" +
                "Обьем торгов за последние 24 часа = " + model.getVolumeUsd24Hr() + "\n" +
                "Цена в реальном времени = " + model.getPriceUsd() + "\n" +
                "Изменение направления и значения цены = " + model.getChangePercent24Hr() + "\n" +
                "Средневзвешанная цена за последние 24 часа = " + model.getVwap24Hr() + "\n";
    }

    public void sendRussian(Message message, Model model) {
        if (message != null && message.hasText()) {
            switch (message.getText().toUpperCase()) {
                case "ИНФОРМАЦИЯ":
                    sendText(message, "Информация");
                    break;
                case "ЯЗЫК":
                    sendText(message, "Выберете язык:\n" + TEXTLANGUAGE);
                    break;
                default:
                    try {
                        sendText(message, Crypto.getCrypto(message.getText(), model, 1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
