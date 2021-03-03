import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;

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
