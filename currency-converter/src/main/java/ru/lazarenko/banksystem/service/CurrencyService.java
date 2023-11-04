package ru.lazarenko.banksystem.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lazarenko.banksystem.dto.ConvertRequestDto;
import ru.lazarenko.banksystem.enums.Currency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringJoiner;

@Service
public class CurrencyService {
    @Value("${exchanger.url}")
    private String urlExchanger;

    @Value("${exchanger.access-key}")
    private String accessKey;

    public BigDecimal convert(ConvertRequestDto request) {
        BigDecimal rate = getConversionRate(request.sourceCurrency(), request.resultCurrency());
        return new BigDecimal(request.amount()).multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getConversionRate(Currency sourceCurrency, Currency resultCurrency) {
        String urlRequest = createUrlGetConversionRate(sourceCurrency, resultCurrency);
        try {
            URL url = new URL(urlRequest);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            JsonElement jsonElement = JsonParser.parseString(response.toString());
            return jsonElement.getAsJsonObject().get("conversion_rate").getAsBigDecimal();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createUrlGetConversionRate(Currency sourceCurrency, Currency resultCurrency) {
        StringJoiner url = new StringJoiner("/");
        url.add(urlExchanger);
        url.add(accessKey);
        url.add("pair");
        url.add(sourceCurrency.toString());
        url.add(resultCurrency.toString());
        return url.toString();
    }
}
