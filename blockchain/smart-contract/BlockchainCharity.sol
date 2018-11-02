pragma solidity ^0.4.18;

contract owned {
    address public owner;

    function owned() public {
        owner = msg.sender;
    }

    modifier onlyOwner {
        require(msg.sender == owner);
        _;
    }
}

contract CharityLottery is owned {

    address public charityFund;

    bool public lotteryClosed = false;
    uint public deadline;

    uint public winnerRate;
    uint public maintenanceFeeRate;
    uint public maxMaintenanceFee;
    uint public ticketPrice;
    int public currentTicketNumber = - 1;
    uint public amountRaised = 0;

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

    modifier winnerNotChosen() {
        require(winnerTicketNumber == - 1);
        _;
    }

    function CharityLottery(
        address charityAddress,
        uint durationInMinutes,
        uint feePercent,
        uint maxFee,
        uint priceForTheTicket,
        uint winnerPercent
    ) public {
        owner = msg.sender;
        charityFund = charityAddress;
        deadline = now + durationInMinutes * 1 minutes;
        maintenanceFeeRate = feePercent;
        maxMaintenanceFee = maxFee;
        ticketPrice = priceForTheTicket;
        winnerRate = winnerPercent;
    }

    function() payable public {
        require(!lotteryClosed);
        require(now < deadline);

        uint amount = msg.value;
        address holder = msg.sender;

        require(amount % ticketPrice == 0);

        uint ticketAmount = amount / ticketPrice;

        for (uint i = 0; i < ticketAmount; i++) {
            currentTicketNumber++;
            Ticket memory ticket = Ticket(uint(currentTicketNumber), holder);

            amountRaised += ticketPrice;
            allTickets.push(ticket);
            holderTickets[holder].push(ticket);
            BuyTicket(holder, uint(currentTicketNumber), ticketPrice);
        }
    }

    function finishLottery() public nonFinishedLottery isReachedDeadline {
        chooseWinner();

        uint balance = address(this).balance;
        uint feeAmount = balance * maintenanceFeeRate / 100;

        if (feeAmount > maxMaintenanceFee) {
            feeAmount = maxMaintenanceFee;
        }

        uint giveAwayAmount = balance - feeAmount;

        // Handling winner
        uint winnerAmount = giveAwayAmount * winnerRate / 100;
        address winnerAddress = allTickets[uint(winnerTicketNumber)].holder;
        winnerAddress.transfer(winnerAmount);
        WinnerTransfer(winnerAddress, winnerAmount);

        // Handling charity
        uint charityDonationAmount = giveAwayAmount - winnerAmount;
        charityFund.transfer(charityDonationAmount);
        CharityTransfer(charityFund, charityDonationAmount);

        lotteryClosed = true;
    }

    function chooseWinner() internal winnerNotChosen {
        winnerTicketNumber = int(uint(keccak256(block.difficulty, block.timestamp)) % uint(currentTicketNumber));
    }

    function withdrawOwnersAmount() public onlyOwner finishedLottery {
        require(msg.sender.send(address(this).balance));
    }
}
