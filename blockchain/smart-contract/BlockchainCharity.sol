pragma solidity ^0.4.18;

contract CharityLottery {

    address public owner;
    address public beneficiary;

    bool lotteryClosed = false;
    uint public deadline;

    uint public maintenanceFeePercent;
    uint public maxMaintenanceFee;
    uint public ticketPrice;
    uint public amountRaised;
    uint public currentTicketNumber = 0;

    struct Ticket {
        uint ticketNumber;
        address holder;
    }

    mapping(address => Ticket[]) public holderTickets;
    Ticket[] public allTickets;

    event BuyTicket(address holder, uint ticketNumber, uint ticketPrice);
    event WinnerTransfer(address winner, uint amount);
    event BeneficiaryTransfer(address beneficiary, uint amount);

    function CharityLottery(
        address beneficiaryAddress,
        uint durationInMinutes,
        uint feePercent,
        uint maxFee,
        uint priceForTheTicket

    ) public {
        owner = msg.sender;
        beneficiary = beneficiaryAddress;
        deadline = now + durationInMinutes * 1 minutes;
        maintenanceFeePercent = feePercent;
        maxMaintenanceFee = maxFee;
        ticketPrice = priceForTheTicket;
    }

    function() payable public {
        require(!lotteryClosed);

        uint amount = msg.value;
        address holder = msg.sender;

        require(amount % ticketPrice == 0);

        amountToReturn = amount % ticketPrice;
        ticketAmount = amount / ticketPrice;

        for (uint i = 0; i < ticketAmount; i++) {
            currentTicketNumber++;
            Ticket ticket = Ticket(currentTicketNumber, holder);

            allTickets.push(ticket);
            holderTickets[holder].push(ticket);
            emit BuyTicket(holder, currentTicketNumber, ticketPrice);
        }
    }

    modifier isReadyToBeFinished() {
        if (lotteryClosed == false && now >= deadline) _;
    }

    function finishLottery() payable public isReadyToBeFinished {

        // TODO: Choose random winner (winners)
        // TODO: send money to charity
        // TODO: send all left money to owner
        lotteryClosed = true;
    }
}
