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
}
