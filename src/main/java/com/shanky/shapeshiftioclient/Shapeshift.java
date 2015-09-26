/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shanky.shapeshiftioclient;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author shanky
 */
public final class Shapeshift {

    private Properties prop = new Properties();
    private InputStream input = null;
    private String filename = "config.properties";
    private ClassLoader loader;
    private String hostURL;
    private String tmpURL;

    public Shapeshift() throws IOException, FileNotFoundException {
        loader = Thread.currentThread().getContextClassLoader();
        input = loader.getResourceAsStream(filename);
        prop.load(input);
    }

    public void setHostURL(String URL) throws IOException {
        this.hostURL=URL;
    }

    public String getHostURL() {
        return hostURL;
    }

    private String readProperty(String property) {
        String value = prop.getProperty(property);
        return value;
    }

    public JsonNode getRate(String pair) throws UnirestException, IOException {
        tmpURL = hostURL + readProperty("rate") + pair;
        JsonNode response = sendRequest(tmpURL);
        return response;
    }

    public JsonNode getDepositLimit(String pair) throws UnirestException, IOException {
        tmpURL = hostURL + readProperty("depositLimit") + pair;
        JsonNode response = sendRequest(tmpURL);
        return response;
    }

    public JsonNode getMarketInfo(String pair) throws UnirestException, IOException {
        tmpURL = hostURL + readProperty("marketInfo") + pair;
        JsonNode response = sendRequest(tmpURL);
        return response;
    }

    public JsonNode getRecentTransactions(int max) throws UnirestException, IOException {
        tmpURL = hostURL + readProperty("recentTx") + max;
        JsonNode response = sendRequest(tmpURL);
        return response;
    }

    public JsonNode getDepositStatus(String address) throws UnirestException, IOException {
        tmpURL = hostURL + readProperty("depositStatus") + address;
        JsonNode response = sendRequest(tmpURL);
        return response;
    }

    public JsonNode getTimeRemaining(String address) throws UnirestException, IOException {
        tmpURL = hostURL + readProperty("timeRemaining") + address;
        JsonNode response = sendRequest(tmpURL);
        return response;
    }

    public JsonNode getCoins() throws UnirestException, IOException {
        tmpURL = hostURL + readProperty("supportedCoins");
        JsonNode response = sendRequest(tmpURL);
        return response;
    }

    public JsonNode validateAddress(String address, String coinsymbol) throws UnirestException, IOException {
        tmpURL = hostURL + readProperty("validateAddress");
        JsonNode response = sendRequest(tmpURL);
        return response;
    }

    public JsonNode doTransaction(String withdrawal, String pair, String returnAddress) throws UnirestException, IOException {
        tmpURL = hostURL + readProperty("transaction") + "/" + withdrawal + "/" + pair + "/" + returnAddress;
        JsonNode response = sendRequest(tmpURL);
        return response;
    }

    public JsonNode getReceipt(String email, String txid) throws UnirestException, IOException {
        tmpURL = hostURL + readProperty("receipt") + "/" + email + "/" + txid;
        JsonNode response = sendRequest(tmpURL);
        return response;
    }

    public JsonNode FixedTransaction(String amount, String pair, String withdrawal, String returnAddress) throws UnirestException, IOException {
        tmpURL = hostURL + readProperty("fixedTransaction") + "/" + amount + "/" + pair + "/" + withdrawal + "/" + returnAddress;
        JsonNode response = sendRequest(tmpURL);
        return response;
    }

    public JsonNode QuotedRate(String amount, String pair) throws UnirestException, IOException {
        tmpURL = hostURL + readProperty("fixedTransaction") + "/" + amount + "/" + pair;
        JsonNode response = sendRequest(tmpURL);
        return response;
    }

    public JsonNode CancelTransaction(String address) throws UnirestException, IOException {
        tmpURL = hostURL + readProperty("cancelTransaction") + "/" + address;
        JsonNode response = sendRequest(tmpURL);
        return response;
    }

    private JsonNode sendRequest(String url) throws UnirestException, IOException {
        JsonNode jsonResponse = Unirest.get(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson()
                .getBody();
        Unirest.shutdown();
        return jsonResponse;

    }
}
