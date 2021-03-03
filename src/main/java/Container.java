import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import java.util.List;
import java.util.Map;

class Container {
    final static List<KeyboardButton> cryptoButton = List.of(new KeyboardButton("BTC"), new KeyboardButton("ETH"),
            new KeyboardButton("BNB"), new KeyboardButton("DOGE"), new KeyboardButton("DOT"), new KeyboardButton("ADA"));
    final static Map<Integer, List<KeyboardButton>> buttonContainer = Map.of(1, List.of(new KeyboardButton("Информация"), new KeyboardButton("Язык")),
            2, List.of(new KeyboardButton("Information"), new KeyboardButton("Language")),
            3, List.of(new KeyboardButton("Information"), new KeyboardButton("Spranche")));
    static Map<Integer, Information> languageContainer = Map.of(1, new RussianSender(), 2, new EnglishSender(), 3, new DeutschSender());
}
