import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Crypto {
    public static String getCrypto(String message, Model model, int idLanguage) throws IOException {
        URL url;
        switch (message.toUpperCase()) {
            case "BTC":
                url = new URL("https://api.coincap.io/v2/assets/bitcoin");
                break;
            case "ETH":
                url = new URL("https://api.coincap.io/v2/assets/ethereum");
                break;
            case "BNB":
                url = new URL("https://api.coincap.io/v2/assets/binance-coin");
                break;
            case "DOGE":
                url = new URL("https://api.coincap.io/v2/assets/dogecoin");
                break;
            case "ADA":
                url = new URL("https://api.coincap.io/v2/assets/cardano");
                break;
            case "DOT":
                url = new URL("https://api.coincap.io/v2/assets/polkadot");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + message);
        }

        Scanner in = new Scanner((InputStream) url.getContent());
        StringBuilder result = new StringBuilder();
        while (in.hasNext()) {
            result.append(in.nextLine());
        }

        JSONObject object = new JSONObject(result.toString());
        JSONObject data = object.getJSONObject("data");
        model.setSymbol(data.getString("symbol"));
        model.setName(data.getString("name"));
        model.setSupply(data.getDouble("supply"));
        if (data.isNull("maxSupply")) {
            model.setMaxSupply(0);
        }
        model.setMarketCapUsd(data.getDouble("marketCapUsd"));
        model.setVolumeUsd24Hr(data.getDouble("volumeUsd24Hr"));
        model.setPriceUsd(data.getDouble("priceUsd"));
        model.setChangePercent24Hr(data.getDouble("changePercent24Hr"));
        if (data.isNull("vwap24Hr")) {
            model.setVwap24Hr(0);
        }
        return Container.languageContainer.get(idLanguage).getText(model);
    }
}
