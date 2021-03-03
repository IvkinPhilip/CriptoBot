import org.telegram.telegrambots.meta.api.objects.Message;

abstract class Information extends Bot {
    public static final String TEXTLANGUAGE = "/russian - Русский \uD83C\uDDF7\uD83C\uDDFA\n" +
            "/english - English \uD83C\uDDEC\uD83C\uDDE7\n" +
            "/deutsch - Deutsch \uD83C\uDDE9\uD83C\uDDEA";

    abstract void sendInfo(Message message, Model model);

    abstract String getText(Model model);
}
