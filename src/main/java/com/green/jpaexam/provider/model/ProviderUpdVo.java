package com.green.jpaexam.provider.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProviderUpdVo {
    private long id;
    private String name;
    private String updatedAt;
}
