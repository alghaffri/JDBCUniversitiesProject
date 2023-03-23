package fetchData;

import java.util.List;

public class University {
    private String stateProvince;
    private List<String> domains;
    private String country;
    private List<String> webPages;
    private String name;
    private String alphaTwoCode;

    public University(String name, String country, String alphaTwoCode, String stateProvince, List<String> domains, List<String> webPages) {
        this.name = name;
        this.country = country;
        this.alphaTwoCode = alphaTwoCode;
        this.stateProvince = stateProvince;
        this.domains = domains;
        this.webPages = webPages;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getWebPages() {
        return webPages;
    }

    public void setWebPages(List<String> webPages) {
        this.webPages = webPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlphaTwoCode() {
        return alphaTwoCode;
    }

    public void setAlphaTwoCode(String alphaTwoCode) {
        this.alphaTwoCode = alphaTwoCode;
    }

    @Override
    public String toString() {
        return "University{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", alphaTwoCode='" + alphaTwoCode + '\'' +
                ", stateProvince='" + stateProvince + '\'' +
                ", domains=" + domains +
                ", webPages=" + webPages +
                '}';
    }
}
