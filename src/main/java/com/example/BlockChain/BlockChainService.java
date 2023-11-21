package com.example.BlockChain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockChainService {

    private final BlockChain blockChain;

    public void saveBlock(BlockChainContractDto request) throws Exception {
        blockChain.createContract(request);
    }

    public BlockChainContractDto getBlock(Long dealId) throws Exception {
        BlockChainContractDto blockChainContractDto = blockChain.getContract(dealId);
        return blockChainContractDto;
    }

    public void test(BlockChainContractDto request) throws Exception {
        System.out.println(request.getNote());
        System.out.println(request.getRepAndRes());
        System.out.println(request.getDealId());
        System.out.println(request.getPrice());
    }
}
