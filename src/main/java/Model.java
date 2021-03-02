public class Model {

    private String symbol;
    private String name;
    private double supply;
    private double maxSupply;
    private double marketCapUsd;
    private double volumeUsd24Hr;
    private double priceUsd;
    private double changePercent24Hr;
    private double vwap24Hr;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSupply() {
        return supply;
    }

    public void setSupply(double supply) {
        this.supply = supply;
    }

    public double getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(double maxSupply) {
        this.maxSupply = maxSupply;
    }

    public double getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(double marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public double getVolumeUsd24Hr() {
        return volumeUsd24Hr;
    }

    public void setVolumeUsd24Hr(double volumeUsd24Hr) {
        this.volumeUsd24Hr = volumeUsd24Hr;
    }

    public double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public double getChangePercent24Hr() {
        return changePercent24Hr;
    }

    public void setChangePercent24Hr(double changePercent24Hr) {
        this.changePercent24Hr = changePercent24Hr;
    }

    public double getVwap24Hr() {
        return vwap24Hr;
    }

    public void setVwap24Hr(double vwap24Hr) {
        this.vwap24Hr = vwap24Hr;
    }
}
