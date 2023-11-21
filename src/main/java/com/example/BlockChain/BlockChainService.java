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
        BlockChainContract blockChainContract = blockChain.getContract(dealId);
        BlockChainContractDto blockChainContractDto = new BlockChainContractDto();
        blockChainContractDto.setNote(blockChainContract.getNote());
        blockChainContractDto.setPrice(Long.valueOf(blockChainContract.getPrice()));
        blockChainContractDto.setDealId(Long.valueOf(blockChainContract.getDealID()));
        blockChainContractDto.setRepAndRes(blockChainContract.getRepAndRes());
        return blockChainContractDto;
    }
}
