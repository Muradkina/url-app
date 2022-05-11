package com.mk.urlShorttenerApplication.dto.converter;

import com.mk.urlShorttenerApplication.dto.ShortUrlDto;
import com.mk.urlShorttenerApplication.model.ShortUrl;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ShortUrlDtoConverter {
    public ShortUrlDto convertToDto(ShortUrl shortUrl) {
        return ShortUrlDto.builder()
                .id(shortUrl.getId())
                .url(shortUrl.getUrl())
                .code(shortUrl.getCode())
                .build();
    }

    public List<ShortUrlDto> convertToDto(List<ShortUrl> shortUrl) {
        return shortUrl.stream()
                .map(shortUrl1 -> convertToDto(shortUrl1))
                .collect(Collectors.toList());

    }
}