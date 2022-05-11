package com.mk.urlShorttenerApplication.dto.converter;

import com.mk.urlShorttenerApplication.dto.ShortUrlRequest;
import com.mk.urlShorttenerApplication.model.ShortUrl;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlRequestConverter {

    public ShortUrl convertToEntity(ShortUrlRequest shortUrlRequest) {
        return ShortUrl.builder()
                .url(shortUrlRequest.getUrl())
                .code(shortUrlRequest.getCode())
                        .build();
    }


}
