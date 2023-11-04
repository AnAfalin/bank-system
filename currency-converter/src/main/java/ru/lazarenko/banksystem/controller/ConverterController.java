package ru.lazarenko.banksystem.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lazarenko.banksystem.dto.ConvertRequestDto;
import ru.lazarenko.banksystem.service.CurrencyService;

import java.math.BigDecimal;

@Slf4j
@RequestMapping("/api/converter/")
@RequiredArgsConstructor
@RestController
public class ConverterController {
    private final CurrencyService currencyService;

    @PostMapping("/convert")
    public BigDecimal convert(@RequestBody ConvertRequestDto request) {
        log.info("Request for convert {} {} into {}",
                request.amount(), request.sourceCurrency(), request.resultCurrency());
        return currencyService.convert(request);
    }

}
