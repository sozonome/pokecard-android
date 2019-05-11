package id.ac.umn.AgustinusNathaniel.model;

public class PokeCard {
    private String cardName;
    private String imageUrl;
    private String imageUrlHiRes;

    private String supertype;
    private String subtype;

    private String series;
    private String set;
    private String setCode;


    public PokeCard(String cardName, String imageUrl, String imageUrlHiRes, String supertype, String subtype, String series, String set, String setCode) {
        this.cardName = cardName;
        this.imageUrl = imageUrl;
        this.imageUrlHiRes = imageUrlHiRes;

        this.supertype = supertype;
        this.subtype = subtype;

        this.series = series;
        this.set = set;
        this.setCode = setCode;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrlHiRes() {
        return imageUrlHiRes;
    }

    public void setImageUrlHiRes(String imageUrlHiRes) {
        this.imageUrlHiRes = imageUrlHiRes;
    }

    public String getSupertype() {
        return supertype;
    }

    public void setSupertype(String supertype) {
        this.supertype = supertype;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

}
