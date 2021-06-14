package pl.zzpj.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;
import pl.zzpj.exceptions.RequestFailedException;
import pl.zzpj.model.Currency;

import java.math.BigDecimal;

@Service
public class CurrencyExchangeService {

    private static final int STATUS_OK = 200;

    public BigDecimal exchangeFromTo(Currency fromCurrencyIsoCode, Currency toCurrencyIsoCode) throws RequestFailedException, UnirestException {
        String url = "https://currency-exchange.p.rapidapi.com/exchange?to=" + toCurrencyIsoCode + "&from=" + fromCurrencyIsoCode;

        HttpResponse<String> response = Unirest.get(url)
                .header("x-rapidapi-key", "5de5aa1bf0mshadec9a0fed59f1ap1c0a15jsn30e216e106d2")
                .header("x-rapidapi-host", "currency-exchange.p.rapidapi.com")
                .asString();

        if (response.getStatus() == STATUS_OK) {
            return BigDecimal.valueOf(Double.parseDouble(response.getBody()));
        } else {
            throw new RequestFailedException("The request has failed");
        }
    }
}
