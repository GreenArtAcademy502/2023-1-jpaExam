package com.green.jpaexam.provider;

import com.green.jpaexam.provider.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/provider")
public class ProviderController {
    private final ProviderService service;

    @PostMapping
    public ResponseEntity<ProviderInsVo> postProvider(@RequestBody ProviderInsDto dto) {
        ProviderInsVo vo = service.save(dto);
        return ResponseEntity.ok(vo);
    }

    @GetMapping
    public ResponseEntity<List<ProviderSelVo>> getProvider() {
        List<ProviderSelVo> list = service.select();
        return ResponseEntity.ok(list);
    }

    @PutMapping
    public ResponseEntity<ProviderUpdVo> putProvider(@RequestBody ProviderUpdDto dto) {
        ProviderUpdVo vo = service.update(dto);
        return ResponseEntity.ok(vo);
    }

    @DeleteMapping
    public ResponseEntity<Integer> deleteProvider(@RequestParam long id) {
        service.delete(id);
        return ResponseEntity.ok(1);
    }

}
