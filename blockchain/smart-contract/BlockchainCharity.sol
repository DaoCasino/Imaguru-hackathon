pragma solidity ^0.4.25;

import "github.com/oraclize/ethereum-api/oraclizeAPI.sol";

contract owned {
    address public owner;

    constructor() public {
        owner = msg.sender;
    }

    modifier onlyOwner {
        require(msg.sender == owner);
        _;
    }
}

contract CharityLottery is owned, usingOraclize {

    uint oraclizeRandomBytesAmount = 7;
    uint oraclizeDelay = 0;
    uint callbackGas = 200000; // TODO: check gas consumption

    mapping(bytes32 => bool) validOraclizeIds;

    address public owner;
    address public charityFund;

    bool lotteryClosed = false;
    uint public deadline;

    uint public maintenanceFeeRate;
    uint public maxMaintenanceFee;
    uint public ticketPrice;
    int public currentTicketNumber = - 1;
    uint public amountRaised = 0;

    uint public winnerRate;
    uint public charityRate;

    struct Ticket {
        uint ticketNumber;
        address holder;
    }

    mapping(address => Ticket[]) public holderTickets;
    Ticket[] public allTickets;

    int public winnerTicketNumber = - 1; // use -1 for undefined

    event BuyTicket(address holder, uint ticketNumber, uint ticketPrice);
    event WinnerTransfer(address winner, uint amount);
    event CharityTransfer(address beneficiary, uint amount);

    modifier finishedLottery() {
        require(lotteryClosed == true);
        _;
    }

    modifier nonFinishedLottery() {
        require(lotteryClosed == false);
        _;
    }

    modifier isReachedDeadline() {
        require(now >= deadline);
        _;
    }

    function CharityLottery(
        address charityAddress,
        uint durationInMinutes,
        uint feePercent,
        uint maxFee,
        uint priceForTheTicket,
        uint winnerPercent,
        uint charityPercent
    ) public {
        require(winnerPercent + charityPercent == 100);

        owner = msg.sender;
        charityFund = charityAddress;
        deadline = now + durationInMinutes * 1 minutes;
        maintenanceFeeRate = feePercent;
        maxMaintenanceFee = maxFee;
        ticketPrice = priceForTheTicket;
        winnerRate = winnerPercent;
        charityRate = charityPercent;
    }

    function() payable {
        require(!lotteryClosed);

        uint amount = msg.value;
        address holder = msg.sender;

        require(amount % ticketPrice == 0);

        uint amountToReturn = amount % ticketPrice;
        uint ticketAmount = amount / ticketPrice;

        for (uint i = 0; i < ticketAmount; i++) {
            currentTicketNumber++;
            Ticket memory ticket = Ticket(uint(currentTicketNumber), holder);

            amountRaised += ticketPrice;
            allTickets.push(ticket);
            holderTickets[holder].push(ticket);
            emit BuyTicket(holder, uint(currentTicketNumber), ticketPrice);
        }
    }

    function requestTicketWinnerNumber() public isReachedDeadline nonFinishedLottery {
        oraclize_setProof(proofType_Ledger);

        bytes32 queryId = oraclize_newRandomDSQuery(oraclizeDelay, oraclizeRandomBytesAmount, callbackGas);

        // Needed to check in callback function
        validOraclizeIds[queryId] = true;
    }

    /*
        Oraclize proof callback.
        Called when random number is generated
    */
    function __callback(bytes32 _queryId, string _result, bytes _proof)
    {
        if (msg.sender != oraclize_cbAddress()
        || !validOraclizeIds[_queryId]
        || oraclize_randomDS_proofVerify__returnCode(_queryId, _result, _proof) == 0) {
            throw;
        } else {
            int maxRange = currentTicketNumber;
            winnerTicketNumber = int(sha3(_result)) % maxRange;

            finishLottery();
        }
    }

    function finishLottery() internal nonFinishedLottery isReachedDeadline {
        uint balance = address(this).balance;
        uint feeAmount = balance * maintenanceFeeRate / 100;

        if (feeAmount > maxMaintenanceFee) {
            feeAmount = maxMaintenanceFee;
        }

        uint giveAwayAmount = balance - feeAmount;

        uint charityDonationAmount = calculateAndSendWinnerAmount(giveAwayAmount);
        calculateAndSendCharityAmount(charityDonationAmount);

        lotteryClosed = true;
    }

    function calculateAndSendWinnerAmount(uint giveAwayAmount) internal finishedLottery returns (uint charityDonationAmount) {
        uint winnerAmount = giveAwayAmount * winnerRate / 100;
        address winnerAddress = allTickets[uint(winnerTicketNumber)].holder;
        winnerAddress.send(winnerAmount);
        emit WinnerTransfer(winnerAddress, winnerAmount);
        return giveAwayAmount - winnerAmount;
    }

    function calculateAndSendCharityAmount(uint charityDonationAmount) internal finishedLottery {
        charityFund.send(charityDonationAmount);
        emit CharityTransfer(charityFund, charityDonationAmount);
    }

    function withdrawOwnersAmount() onlyOwner finishedLottery {
        require(msg.sender.send(address(this).balance));
    }
}
