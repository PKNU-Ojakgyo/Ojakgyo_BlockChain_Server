package com.example.BlockChain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BlockChainController {

    private final BlockChainService blockChainService;

    @PostMapping(value ="/blockchain", produces = "application/json; charset=UTF-8")
    public Object saveBlock(@RequestBody BlockChainContractDto request) throws Exception {
        blockChainService.saveBlock(request);
        return Map.of("result", "계약서 생성 성공");
    }

    @GetMapping (value ="/blockchain", produces = "application/json; charset=UTF-8")
    public Object getBlock(@RequestParam Long dealId) throws Exception {
        BlockChainContractDto blockChainContractDto = blockChainService.getBlock(dealId);
        return blockChainContractDto;
    }

    @GetMapping (value ="/test", produces = "application/json; charset=UTF-8")
    public Object test(@RequestBody BlockChainContractDto request) throws Exception {
        blockChainService.test(request);
        return Map.of("result", "계약서 생성 성공");
    }
}
