package com.dataart.maltahackaton.blockchain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class BlockchainCharity extends Contract {
    private static final String BINARY = "60606040526002805460a060020a60ff021916905560001960088190556000600955600c55341561002f57600080fd5b60405160c0806108e583398101604052808051919060200180519190602001805191906020018051919060200180519190602001805160008054600160a060020a03338116600160a060020a03199283168117909355600180548316909317909255600280549a909216991698909817909755505042603c90940293909301600355600591909155600655600755600455610816806100cf6000396000f3006060604052600436106100da5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630d3b150681146102ad5780631209b1f6146102d257806312ae74c6146102e55780632433b2a1146102f857806329dcb0cf1461030d5780632c906ba21461032057806377e1a9fc146103335780637b3e5e7b146103625780638da5cb5b146103755780639cdc9c0f14610388578063a631c3601461039b578063a9034ff9146103ae578063d0a753af146103c1578063e433a584146103f8578063f1f2c3191461041f575b6000806000806100e8610762565b60025460a060020a900460ff16156100ff57600080fd5b60035442111561010e57600080fd5b3494503393506007548581151561012157fe5b061561012c57600080fd5b6007548581151561013957fe5b049250600091505b828210156102a657600880546001019055604080519081016040526008548152600160a060020a0385166020820152600754600980549091019055600b805491925090600181016101928382610779565b600092835260209092208391600202018151815560208201516001918201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392831617905587166000908152600a602052604090208054909350915081016101fa8382610779565b600092835260209092208391600202018151815560208201518160010160006101000a815481600160a060020a030219169083600160a060020a031602179055505050507f3808dcec579c2bc05ae34288071d575efca12f2d30294c875b58c0e770802c59846008546007546040518084600160a060020a0316600160a060020a03168152602001838152602001828152602001935050505060405180910390a1600190910190610141565b5050505050005b34156102b857600080fd5b6102c0610441565b60405190815260200160405180910390f35b34156102dd57600080fd5b6102c0610447565b34156102f057600080fd5b6102c061044d565b341561030357600080fd5b61030b610453565b005b341561031857600080fd5b6102c06104c8565b341561032b57600080fd5b61030b6104ce565b341561033e57600080fd5b610346610667565b604051600160a060020a03909116815260200160405180910390f35b341561036d57600080fd5b6102c0610676565b341561038057600080fd5b61034661067c565b341561039357600080fd5b6102c061068b565b34156103a657600080fd5b6102c0610691565b34156103b957600080fd5b6102c0610697565b34156103cc57600080fd5b6103d760043561069d565b604051918252600160a060020a031660208201526040908101905180910390f35b341561040357600080fd5b61040b6106d2565b604051901515815260200160405180910390f35b341561042a57600080fd5b6103d7600160a060020a03600435166024356106e2565b60045481565b60075481565b60055481565b60005433600160a060020a0390811691161461046e57600080fd5b60025460a060020a900460ff16151560011461048957600080fd5b33600160a060020a03166108fc30600160a060020a0316319081150290604051600060405180830381858888f1935050505015156104c657600080fd5b565b60035481565b6000806000806000806104df610726565b600554600160a060020a03301631965060649087020494506006548511156105075760065494505b60045485870394506064908502049250600b600c5481548110151561052857fe5b6000918252602090912060016002909202010154600160a060020a031691508183156108fc0284604051600060405180830381858888f19350505050151561056f57600080fd5b7ffde2100491ce344243a183b10b9802396310b8006e3d8ddc42dcf3844bd8fe5c8284604051600160a060020a03909216825260208201526040908101905180910390a15060025482840390600160a060020a031681156108fc0282604051600060405180830381858888f1935050505015156105eb57600080fd5b6002547f7d5ad754d53ba2f294ac966572f88b0dc3e82ddf21bfa9816e42aec5ef89a77590600160a060020a031682604051600160a060020a03909216825260208201526040908101905180910390a150506002805474ff0000000000000000000000000000000000000000191660a060020a17905550505050565b600254600160a060020a031681565b60095481565b600154600160a060020a031681565b60065481565b60085481565b600c5481565b600b8054829081106106ab57fe5b600091825260209091206002909102018054600190910154909150600160a060020a031682565b60025460a060020a900460ff1681565b600a602052816000526040600020818154811015156106fd57fe5b600091825260209091206002909102018054600190910154909250600160a060020a0316905082565b600c546000191461073657600080fd5b600854444260405191825260208201526040908101905190819003902081151561075c57fe5b06600c55565b604080519081016040526000808252602082015290565b8154818355818115116107a5576002028160020283600052602060002091820191016107a591906107aa565b505050565b6107e791905b808211156107e3576000815560018101805473ffffffffffffffffffffffffffffffffffffffff191690556002016107b0565b5090565b905600a165627a7a723058203c50551553052574c5644559e5edcf89715a905b1656c63d27b333317f6089580029";

    protected BlockchainCharity(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BlockchainCharity(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<BuyTicketEventResponse> getBuyTicketEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("BuyTicket", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<BuyTicketEventResponse> responses = new ArrayList<BuyTicketEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            BuyTicketEventResponse typedResponse = new BuyTicketEventResponse();
            typedResponse.holder = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.ticketNumber = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.ticketPrice = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<BuyTicketEventResponse> buyTicketEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("BuyTicket", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, BuyTicketEventResponse>() {
            @Override
            public BuyTicketEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                BuyTicketEventResponse typedResponse = new BuyTicketEventResponse();
                typedResponse.holder = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.ticketNumber = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.ticketPrice = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public List<WinnerTransferEventResponse> getWinnerTransferEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("WinnerTransfer", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<WinnerTransferEventResponse> responses = new ArrayList<WinnerTransferEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            WinnerTransferEventResponse typedResponse = new WinnerTransferEventResponse();
            typedResponse.winner = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<WinnerTransferEventResponse> winnerTransferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("WinnerTransfer", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, WinnerTransferEventResponse>() {
            @Override
            public WinnerTransferEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                WinnerTransferEventResponse typedResponse = new WinnerTransferEventResponse();
                typedResponse.winner = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<CharityTransferEventResponse> getCharityTransferEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("CharityTransfer", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<CharityTransferEventResponse> responses = new ArrayList<CharityTransferEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            CharityTransferEventResponse typedResponse = new CharityTransferEventResponse();
            typedResponse.beneficiary = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CharityTransferEventResponse> charityTransferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("CharityTransfer", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, CharityTransferEventResponse>() {
            @Override
            public CharityTransferEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                CharityTransferEventResponse typedResponse = new CharityTransferEventResponse();
                typedResponse.beneficiary = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<SuccessEventResponse> getSuccessEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Success", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<SuccessEventResponse> responses = new ArrayList<SuccessEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            SuccessEventResponse typedResponse = new SuccessEventResponse();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SuccessEventResponse> successEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Success", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, SuccessEventResponse>() {
            @Override
            public SuccessEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                SuccessEventResponse typedResponse = new SuccessEventResponse();
                return typedResponse;
            }
        });
    }

    public RemoteCall<BigInteger> winnerRate() {
        Function function = new Function("winnerRate", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> ticketPrice() {
        Function function = new Function("ticketPrice", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> maintenanceFeeRate() {
        Function function = new Function("maintenanceFeeRate", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> withdrawOwnersAmount() {
        Function function = new Function(
                "withdrawOwnersAmount", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> deadline() {
        Function function = new Function("deadline", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> finishLottery() {
        Function function = new Function(
                "finishLottery", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> charityFund() {
        Function function = new Function("charityFund", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> amountRaised() {
        Function function = new Function("amountRaised", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> maxMaintenanceFee() {
        Function function = new Function("maxMaintenanceFee", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> currentTicketNumber() {
        Function function = new Function("currentTicketNumber", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> winnerTicketNumber() {
        Function function = new Function("winnerTicketNumber", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple2<BigInteger, String>> allTickets(BigInteger param0) {
        final Function function = new Function("allTickets", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple2<BigInteger, String>>(
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<Boolean> lotteryClosed() {
        Function function = new Function("lotteryClosed", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Tuple2<BigInteger, String>> holderTickets(String param0, BigInteger param1) {
        final Function function = new Function("holderTickets", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple2<BigInteger, String>>(
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public static RemoteCall<BlockchainCharity> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String charityAddress, BigInteger durationInMinutes, BigInteger feePercent, BigInteger maxFee, BigInteger priceForTheTicket, BigInteger winnerPercent) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(charityAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(durationInMinutes), 
                new org.web3j.abi.datatypes.generated.Uint256(feePercent), 
                new org.web3j.abi.datatypes.generated.Uint256(maxFee), 
                new org.web3j.abi.datatypes.generated.Uint256(priceForTheTicket), 
                new org.web3j.abi.datatypes.generated.Uint256(winnerPercent)));
        return deployRemoteCall(BlockchainCharity.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<BlockchainCharity> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String charityAddress, BigInteger durationInMinutes, BigInteger feePercent, BigInteger maxFee, BigInteger priceForTheTicket, BigInteger winnerPercent) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(charityAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(durationInMinutes), 
                new org.web3j.abi.datatypes.generated.Uint256(feePercent), 
                new org.web3j.abi.datatypes.generated.Uint256(maxFee), 
                new org.web3j.abi.datatypes.generated.Uint256(priceForTheTicket), 
                new org.web3j.abi.datatypes.generated.Uint256(winnerPercent)));
        return deployRemoteCall(BlockchainCharity.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static BlockchainCharity load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BlockchainCharity(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static BlockchainCharity load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BlockchainCharity(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class BuyTicketEventResponse {
        public String holder;

        public BigInteger ticketNumber;

        public BigInteger ticketPrice;
    }

    public static class WinnerTransferEventResponse {
        public String winner;

        public BigInteger amount;
    }

    public static class CharityTransferEventResponse {
        public String beneficiary;

        public BigInteger amount;
    }

    public static class SuccessEventResponse {
    }
}
