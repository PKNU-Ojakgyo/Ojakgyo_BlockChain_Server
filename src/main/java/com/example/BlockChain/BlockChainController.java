package com.example.BlockChain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contract")
public class BlockChainController {

    private final BlockChainService blockChainService;

    @PostMapping(value ="/blockchain", produces = "application/json; charset=UTF-8")
    public Object saveBlock(@RequestBody BlockChainContract request) throws Exception {
        blockChainService.saveBlock(request);
        return Map.of("result", "계약서 생성 성공");
    }

    @GetMapping (value ="/blockchain", produces = "application/json; charset=UTF-8")
    public Object getBlock(@RequestParam Long dealId) throws Exception {
        BlockChainContract blockChainContract = blockChainService.getBlock(dealId);
        return blockChainContract;
    }
}
