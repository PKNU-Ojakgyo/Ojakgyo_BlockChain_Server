package com.example.BlockChain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlockChainContract {
    private Long dealId;
    private String repAndRes;
    private String note;
    private Long price;

}
