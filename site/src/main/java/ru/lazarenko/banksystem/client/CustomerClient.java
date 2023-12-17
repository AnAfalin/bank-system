package ru.lazarenko.banksystem.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.lazarenko.model.ClientRegisterRequest;

@Component
@RequiredArgsConstructor
public class CustomerClient {
    private final static String POST_CLIENT_REGISTRATION = "/api/customer-service/clients";
    private final RestTemplate restTemplate;

    @Value("${address.customer-service}")
    private String customerServiceAddress;

    public void reg(ClientRegisterRequest request) {
        String url = customerServiceAddress.concat(POST_CLIENT_REGISTRATION);
        restTemplate.postForLocation(url, request);
    }
}
