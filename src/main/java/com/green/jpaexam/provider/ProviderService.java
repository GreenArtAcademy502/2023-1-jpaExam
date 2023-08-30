package com.green.jpaexam.provider;

import com.green.jpaexam.entity.ProviderEntity;
import com.green.jpaexam.provider.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProviderService {
    private final ProviderRepository rep;


    public ProviderInsVo save(ProviderInsDto dto) {
        ProviderEntity entity = ProviderEntity.builder()
                .name(dto.getName())
                .build();

        rep.save(entity);

        return ProviderInsVo.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt().toString())
                .build();
    }

    public List<ProviderSelVo> select() {
        List<ProviderEntity> list = rep.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return list.stream().map(
                item -> ProviderSelVo.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .build()
        ).toList();
    }

    public ProviderUpdVo update(ProviderUpdDto dto) {
        Optional<ProviderEntity> optEntity = rep.findById(dto.getId());

        ProviderEntity entity = optEntity.get();
        entity.setName(dto.getName());

        rep.save(entity);

        return ProviderUpdVo.builder()
                .id(entity.getId())
                .name(entity.getName())
                .updatedAt(entity.getUpdatedAt().toString())
                .build();
    }

    public void delete(long id) {
        rep.deleteById(id);
    }

}
