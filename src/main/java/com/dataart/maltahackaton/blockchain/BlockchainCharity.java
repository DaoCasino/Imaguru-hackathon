package com.dataart.maltahackaton.blockchain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
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
import org.web3j.tx.gas.ContractGasProvider;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.6.0.
 */
public class BlockchainCharity extends Contract {
    private static final String BINARY = "60806040526002805460a060020a60ff021916905560001960078190556000600855600d5534801561003057600080fd5b5060405160e0806108b083398101604090815281516020830151918301516060840151608085015160a086015160c09096015160008054600160a060020a03191633179055939592939192909160648183011461008c57600080fd5b6001805433600160a060020a03199182161790915560028054909116600160a060020a039890981697909717909655603c949094024201600355600492909255600555600655600955600a556107c9806100e76000396000f3006080604052600436106100da5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630d3b1506811461029a5780631209b1f6146102c157806312ae74c6146102d65780632433b2a1146102eb57806329dcb0cf146103025780632c906ba21461031757806376c3960d1461032c57806377e1a9fc146103415780637b3e5e7b146103725780638da5cb5b146103875780639cdc9c0f1461039c578063a631c360146103b1578063a9034ff9146103c6578063d0a753af146103db578063f1f2c31914610414575b60008060008060006100ea610786565b60025460a060020a900460ff161561010157600080fd5b3495503394506006548681151561011457fe5b061561011f57600080fd5b6006548681151561012c57fe5b0693506006548681151561013c57fe5b049250600091505b828210156102925750600780546001908101808355604080518082018252918252600160a060020a03808916602080850182815260068054600880549091019055600c8054808a0182556000918252885160029182027fdf6966c971051c3d54ec59162606531493a51404a002842f56009d7e5cf4a8c781019190915584517fdf6966c971051c3d54ec59162606531493a51404a002842f56009d7e5cf4a8c8909101805491891673ffffffffffffffffffffffffffffffffffffffff19928316179055868352600b86528883208054808d018255908452928690208a51939092029091019182559251980180549890951697909116969096179092559454935482519586529085019390935283810192909252905190917f3808dcec579c2bc05ae34288071d575efca12f2d30294c875b58c0e770802c59919081900360600190a1600190910190610144565b505050505050005b3480156102a657600080fd5b506102af610438565b60408051918252519081900360200190f35b3480156102cd57600080fd5b506102af61043e565b3480156102e257600080fd5b506102af610444565b3480156102f757600080fd5b5061030061044a565b005b34801561030e57600080fd5b506102af6104a5565b34801561032357600080fd5b506103006104ab565b34801561033857600080fd5b506102af610543565b34801561034d57600080fd5b50610356610549565b60408051600160a060020a039092168252519081900360200190f35b34801561037e57600080fd5b506102af610558565b34801561039357600080fd5b5061035661055e565b3480156103a857600080fd5b506102af61056d565b3480156103bd57600080fd5b506102af610573565b3480156103d257600080fd5b506102af610579565b3480156103e757600080fd5b506103f360043561057f565b60408051928352600160a060020a0390911660208301528051918290030190f35b34801561042057600080fd5b506103f3600160a060020a03600435166024356105b4565b60095481565b60065481565b60045481565b600054600160a060020a0316331461046157600080fd5b60025460a060020a900460ff16151560011461047c57600080fd5b6040513390303180156108fc02916000818181858888f1935050505015156104a357600080fd5b565b60035481565b60008060008060035442101515156104c257600080fd5b60025460a060020a900460ff16156104d957600080fd5b6104e16105f8565b6004543031945060649085020492506005548311156105005760055492505b828403915061050e82610632565b9050610519816106f8565b50506002805474ff0000000000000000000000000000000000000000191660a060020a1790555050565b600a5481565b600254600160a060020a031681565b60085481565b600154600160a060020a031681565b60055481565b60075481565b600d5481565b600c80548290811061058d57fe5b600091825260209091206002909102018054600190910154909150600160a060020a031682565b600b602052816000526040600020818154811015156105cf57fe5b600091825260209091206002909102018054600190910154909250600160a060020a0316905082565b600d546000191461060857600080fd5b60075460408051448152426020820152815190819003909101902081151561062c57fe5b07600d55565b6002546000908190819060a060020a900460ff16151560011461065457600080fd5b6009546064908502049150600c600d5481548110151561067057fe5b60009182526020822060016002909202010154604051600160a060020a039091169250829184156108fc02918591818181858888f1505060408051600160a060020a03861681526020810187905281517ffde2100491ce344243a183b10b9802396310b8006e3d8ddc42dcf3844bd8fe5c95509081900390910192509050a150909103919050565b60025460a060020a900460ff16151560011461071357600080fd5b600254604051600160a060020a039091169082156108fc029083906000818181858888f1505060025460408051600160a060020a0390921682526020820186905280517f7d5ad754d53ba2f294ac966572f88b0dc3e82ddf21bfa9816e42aec5ef89a7759550918290030192509050a150565b6040805180820190915260008082526020820152905600a165627a7a7230582057c45e863c3f0553dfe7f803d67619ebd35052a287d302252e0037d8c05790660029\n";

    public static final String FUNC_WINNERRATE = "winnerRate";

    public static final String FUNC_TICKETPRICE = "ticketPrice";

    public static final String FUNC_MAINTENANCEFEERATE = "maintenanceFeeRate";

    public static final String FUNC_WITHDRAWOWNERSAMOUNT = "withdrawOwnersAmount";

    public static final String FUNC_DEADLINE = "deadline";

    public static final String FUNC_FINISHLOTTERY = "finishLottery";

    public static final String FUNC_CHARITYRATE = "charityRate";

    public static final String FUNC_CHARITYFUND = "charityFund";

    public static final String FUNC_AMOUNTRAISED = "amountRaised";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_MAXMAINTENANCEFEE = "maxMaintenanceFee";

    public static final String FUNC_CURRENTTICKETNUMBER = "currentTicketNumber";

    public static final String FUNC_WINNERTICKETNUMBER = "winnerTicketNumber";

    public static final String FUNC_ALLTICKETS = "allTickets";

    public static final String FUNC_HOLDERTICKETS = "holderTickets";

    public static final Event BUYTICKET_EVENT = new Event("BuyTicket", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event WINNERTRANSFER_EVENT = new Event("WinnerTransfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CHARITYTRANSFER_EVENT = new Event("CharityTransfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected BlockchainCharity(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BlockchainCharity(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected BlockchainCharity(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected BlockchainCharity(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> winnerRate() {
        final Function function = new Function(FUNC_WINNERRATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> ticketPrice() {
        final Function function = new Function(FUNC_TICKETPRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> maintenanceFeeRate() {
        final Function function = new Function(FUNC_MAINTENANCEFEERATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> withdrawOwnersAmount() {
        final Function function = new Function(
                FUNC_WITHDRAWOWNERSAMOUNT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> deadline() {
        final Function function = new Function(FUNC_DEADLINE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> finishLottery() {
        final Function function = new Function(
                FUNC_FINISHLOTTERY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> charityRate() {
        final Function function = new Function(FUNC_CHARITYRATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> charityFund() {
        final Function function = new Function(FUNC_CHARITYFUND, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> amountRaised() {
        final Function function = new Function(FUNC_AMOUNTRAISED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> maxMaintenanceFee() {
        final Function function = new Function(FUNC_MAXMAINTENANCEFEE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> currentTicketNumber() {
        final Function function = new Function(FUNC_CURRENTTICKETNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> winnerTicketNumber() {
        final Function function = new Function(FUNC_WINNERTICKETNUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple2<BigInteger, String>> allTickets(BigInteger param0) {
        final Function function = new Function(FUNC_ALLTICKETS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple2<BigInteger, String>>(
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<Tuple2<BigInteger, String>> holderTickets(String param0, BigInteger param1) {
        final Function function = new Function(FUNC_HOLDERTICKETS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple2<BigInteger, String>>(
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public static RemoteCall<BlockchainCharity> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String charityAddress, BigInteger durationInMinutes, BigInteger feePercent, BigInteger maxFee, BigInteger priceForTheTicket, BigInteger winnerPercent, BigInteger charityPercent) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(charityAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(durationInMinutes), 
                new org.web3j.abi.datatypes.generated.Uint256(feePercent), 
                new org.web3j.abi.datatypes.generated.Uint256(maxFee), 
                new org.web3j.abi.datatypes.generated.Uint256(priceForTheTicket), 
                new org.web3j.abi.datatypes.generated.Uint256(winnerPercent), 
                new org.web3j.abi.datatypes.generated.Uint256(charityPercent)));
        return deployRemoteCall(BlockchainCharity.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<BlockchainCharity> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String charityAddress, BigInteger durationInMinutes, BigInteger feePercent, BigInteger maxFee, BigInteger priceForTheTicket, BigInteger winnerPercent, BigInteger charityPercent) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(charityAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(durationInMinutes), 
                new org.web3j.abi.datatypes.generated.Uint256(feePercent), 
                new org.web3j.abi.datatypes.generated.Uint256(maxFee), 
                new org.web3j.abi.datatypes.generated.Uint256(priceForTheTicket), 
                new org.web3j.abi.datatypes.generated.Uint256(winnerPercent), 
                new org.web3j.abi.datatypes.generated.Uint256(charityPercent)));
        return deployRemoteCall(BlockchainCharity.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<BlockchainCharity> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String charityAddress, BigInteger durationInMinutes, BigInteger feePercent, BigInteger maxFee, BigInteger priceForTheTicket, BigInteger winnerPercent, BigInteger charityPercent) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(charityAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(durationInMinutes), 
                new org.web3j.abi.datatypes.generated.Uint256(feePercent), 
                new org.web3j.abi.datatypes.generated.Uint256(maxFee), 
                new org.web3j.abi.datatypes.generated.Uint256(priceForTheTicket), 
                new org.web3j.abi.datatypes.generated.Uint256(winnerPercent), 
                new org.web3j.abi.datatypes.generated.Uint256(charityPercent)));
        return deployRemoteCall(BlockchainCharity.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<BlockchainCharity> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String charityAddress, BigInteger durationInMinutes, BigInteger feePercent, BigInteger maxFee, BigInteger priceForTheTicket, BigInteger winnerPercent, BigInteger charityPercent) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(charityAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(durationInMinutes), 
                new org.web3j.abi.datatypes.generated.Uint256(feePercent), 
                new org.web3j.abi.datatypes.generated.Uint256(maxFee), 
                new org.web3j.abi.datatypes.generated.Uint256(priceForTheTicket), 
                new org.web3j.abi.datatypes.generated.Uint256(winnerPercent), 
                new org.web3j.abi.datatypes.generated.Uint256(charityPercent)));
        return deployRemoteCall(BlockchainCharity.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<BuyTicketEventResponse> getBuyTicketEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(BUYTICKET_EVENT, transactionReceipt);
        ArrayList<BuyTicketEventResponse> responses = new ArrayList<BuyTicketEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BuyTicketEventResponse typedResponse = new BuyTicketEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.holder = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.ticketNumber = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.ticketPrice = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<BuyTicketEventResponse> buyTicketEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, BuyTicketEventResponse>() {
            @Override
            public BuyTicketEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BUYTICKET_EVENT, log);
                BuyTicketEventResponse typedResponse = new BuyTicketEventResponse();
                typedResponse.log = log;
                typedResponse.holder = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.ticketNumber = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.ticketPrice = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<BuyTicketEventResponse> buyTicketEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BUYTICKET_EVENT));
        return buyTicketEventObservable(filter);
    }

    public List<WinnerTransferEventResponse> getWinnerTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(WINNERTRANSFER_EVENT, transactionReceipt);
        ArrayList<WinnerTransferEventResponse> responses = new ArrayList<WinnerTransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            WinnerTransferEventResponse typedResponse = new WinnerTransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.winner = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<WinnerTransferEventResponse> winnerTransferEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, WinnerTransferEventResponse>() {
            @Override
            public WinnerTransferEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(WINNERTRANSFER_EVENT, log);
                WinnerTransferEventResponse typedResponse = new WinnerTransferEventResponse();
                typedResponse.log = log;
                typedResponse.winner = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<WinnerTransferEventResponse> winnerTransferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WINNERTRANSFER_EVENT));
        return winnerTransferEventObservable(filter);
    }

    public List<CharityTransferEventResponse> getCharityTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHARITYTRANSFER_EVENT, transactionReceipt);
        ArrayList<CharityTransferEventResponse> responses = new ArrayList<CharityTransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CharityTransferEventResponse typedResponse = new CharityTransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.beneficiary = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<CharityTransferEventResponse> charityTransferEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, CharityTransferEventResponse>() {
            @Override
            public CharityTransferEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CHARITYTRANSFER_EVENT, log);
                CharityTransferEventResponse typedResponse = new CharityTransferEventResponse();
                typedResponse.log = log;
                typedResponse.beneficiary = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<CharityTransferEventResponse> charityTransferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHARITYTRANSFER_EVENT));
        return charityTransferEventObservable(filter);
    }

    @Deprecated
    public static BlockchainCharity load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BlockchainCharity(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static BlockchainCharity load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BlockchainCharity(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static BlockchainCharity load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new BlockchainCharity(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static BlockchainCharity load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new BlockchainCharity(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class BuyTicketEventResponse {
        public Log log;

        public String holder;

        public BigInteger ticketNumber;

        public BigInteger ticketPrice;
    }

    public static class WinnerTransferEventResponse {
        public Log log;

        public String winner;

        public BigInteger amount;
    }

    public static class CharityTransferEventResponse {
        public Log log;

        public String beneficiary;

        public BigInteger amount;
    }
}
