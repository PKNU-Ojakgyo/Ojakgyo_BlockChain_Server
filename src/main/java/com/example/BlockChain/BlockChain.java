package com.example.BlockChain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.sdk.NetworkConfig;
import org.hyperledger.fabric.sdk.security.CryptoPrimitives;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class BlockChain {

    private final ObjectMapper mapper = new ObjectMapper();

    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "false");
    }

    // helper function for getting connected to the gateway
    public static Gateway connect() throws Exception{
        // Load a file system based wallet for managing identities.
        Path walletPath = Paths.get("wallet");
        Wallet wallet = Wallet.createFileSystemWallet(walletPath);
        // load a CCP
        Path networkConfigPath = Paths.get("C:\\Users\\a0107\\IdeaProjects\\Ojakgyo\\src\\main\\resources\\connection\\organization-msp.json");
        InputStream is = new FileInputStream(networkConfigPath.toFile());
        NetworkConfig ccp = NetworkConfig.fromJsonStream(is);
        String mspId = ccp.getClientOrganization().getMspId();

        // load the exported user
        Path userPath = Paths.get("C:\\Users\\a0107\\IdeaProjects\\Ojakgyo\\src\\main\\resources\\connection\\ca-user.json");
        is = new FileInputStream(userPath.toFile());
        JsonObject userObject = (JsonObject) Json.createReader(is).read();
        String userId = userObject.getString("name");

        boolean userExists = wallet.exists(userId);
        if (!userExists) {
            CryptoPrimitives crypto = new CryptoPrimitives();
            Wallet.Identity user = Wallet.Identity.createIdentity(mspId,
                    new String(Base64.getDecoder().decode(userObject.getString("cert"))),
                    crypto.bytesToPrivateKey(Base64.getDecoder().decode(userObject.getString("key"))));
            wallet.put(userId, user);
        }


        Gateway.Builder builder = Gateway.createBuilder()
                .identity(wallet, userId)
                .networkConfig(networkConfigPath)
                .discovery(true);

        return builder.connect();
    }

    public BlockChainContractDto getContract(Long dealId) throws Exception {
        // create a gateway connection
        BlockChainContractDto blockChainContractDto;
        try (Gateway gateway = connect()) {

            // get the network and contract
            Network network = gateway.getNetwork("channel1");
            Contract contract = network.getContract("Ojakgyocc");

            byte[] result = contract.evaluateTransaction("ReadAsset", String.valueOf(dealId));
            blockChainContractDto = mapper.readValue(result, BlockChainContractDto.class);
            System.exit(0);
        }

        return blockChainContractDto;
    }


    public void createContract(BlockChainContractDto blockChainContractDto) throws Exception {
        try (Gateway gateway = connect()) {
            // get the network and contract
            Network network = gateway.getNetwork("channel1");
            Contract contract = network.getContract("Ojakgyocc");

            byte[] result = contract.evaluateTransaction("CreateAsset", String.valueOf(blockChainContractDto.getDealId())
                    , blockChainContractDto.getRepAndRes(), blockChainContractDto.getNote(), String.valueOf(blockChainContractDto.getPrice()));
            System.out.println(new String(result));
            System.exit(0);
        }
    }


}
