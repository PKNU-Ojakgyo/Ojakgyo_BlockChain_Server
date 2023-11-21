package com.example.BlockChain;

import lombok.Data;

@Data
public class BlockChainContractDto {
    public BlockChainContractDto() {
        // 기본 생성자 추가
    }
    private Long dealId;
    private String repAndRes;
    private String note;
    private Long price;

}
