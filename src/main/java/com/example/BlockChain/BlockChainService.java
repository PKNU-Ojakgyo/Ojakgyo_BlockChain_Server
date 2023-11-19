package com.example.BlockChain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockChainService {

    private final BlockChain blockChain;

    public void saveBlock(BlockChainContract request) throws Exception {
        blockChain.createContract(request);
    }

    public BlockChainContract getBlock(Long dealId) throws Exception {
        BlockChainContract blockChainContract = blockChain.getContract(dealId);
        return blockChainContract;
    }
}
