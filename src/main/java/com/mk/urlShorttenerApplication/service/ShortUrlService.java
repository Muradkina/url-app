package com.mk.urlShorttenerApplication.service;

import com.mk.urlShorttenerApplication.exception.CodeAlreadyExists;
import com.mk.urlShorttenerApplication.exception.ShortUrlNotFoundException;
import com.mk.urlShorttenerApplication.model.ShortUrl;
import com.mk.urlShorttenerApplication.repository.ShortUrlRepo;
import com.mk.urlShorttenerApplication.util.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortUrlService {
    private final ShortUrlRepo shortUrlRepo;
    private final RandomStringGenerator randomStringGenerator;

    public ShortUrlService(ShortUrlRepo shortUrlRepo, RandomStringGenerator randomStringGenerator) {
        this.shortUrlRepo = shortUrlRepo;
        this.randomStringGenerator = randomStringGenerator;
    }

    public List<ShortUrl> getAllShortUrl() {
        return shortUrlRepo.findAll(); //Database de kayıtlı tüm verileri dönderir
    }

    public ShortUrl getUrlByCode(String code) {
        return shortUrlRepo.findAllByCode(code)
                .orElseThrow(() -> new ShortUrlNotFoundException("Url not found!")
                );

    }

    public ShortUrl create(ShortUrl shortUrl) {
        if (shortUrl.getCode() == null || shortUrl.getCode().isEmpty()) {
            shortUrl.setCode(generateCode());
            //isPresent değer var mı yok mu . değer varsa true döndürür
        } else if (shortUrlRepo.findAllByCode(shortUrl.getCode()).isPresent()) {
            throw new CodeAlreadyExists("code already exists");

        }
        shortUrl.setCode(shortUrl.getCode().toLowerCase());
        return shortUrlRepo.save(shortUrl);
    }
    private String generateCode(){
        String code ;
        do {
            code =randomStringGenerator.generateRandomString();

        }
        while (shortUrlRepo.findAllByCode(code).isPresent());

        return code;
    }
}
