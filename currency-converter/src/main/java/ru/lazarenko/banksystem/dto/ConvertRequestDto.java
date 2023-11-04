package ru.lazarenko.banksystem.dto;

import ru.lazarenko.banksystem.enums.Currency;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record ConvertRequestDto(
        @NotNull(message = "Source currency cannot be null")
        Currency sourceCurrency,

        @NotNull(message = "Amount currency cannot be null")
        @Min(value = 1, message = "Min amount is 1")
        Integer amount,

        @NotNull(message = "Result currency cannot be null")
        Currency resultCurrency
) {}
