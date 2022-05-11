package com.mk.urlShorttenerApplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private  String url;

    @NotNull
    @Column(unique = true)
    private  String code;




}
