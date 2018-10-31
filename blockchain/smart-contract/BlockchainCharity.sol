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

    event FundTransfer(address backer, uint amount);
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

        require(amount >= ticketPrice);

        amountToReturn = amount % ticketPrice;
        ticketAmount = amount / ticketPrice;

        for (uint i = 0; i < ticketAmount; i++) {
            currentTicketNumber++;
            Ticket ticket = Ticket(currentTicketNumber, holder);
            allTickets.push(ticket);
            holderTickets[holder].push(ticket);
        }

        if (amountToReturn > 0) {
            // TODO: return
        }


        balanceOf[msg.sender] += amount;
        amountRaised += amount;
        tokenReward.transfer(msg.sender, amount / price);
        emit FundTransfer(msg.sender, amount, true);
    }

    modifier afterDeadline() {if (now >= deadline) _;}

    /**
     * Check if goal was reached
     *
     * Checks if the goal or time limit has been reached and ends the campaign
     */
    function checkGoalReached() public afterDeadline {
        if (amountRaised >= fundingGoal) {
            fundingGoalReached = true;
            emit GoalReached(beneficiary, amountRaised);
        }
        crowdsaleClosed = true;
    }


    /**
     * Withdraw the funds
     *
     * Checks to see if goal or time limit has been reached, and if so, and the funding goal was reached,
     * sends the entire amount to the beneficiary. If goal was not reached, each contributor can withdraw
     * the amount they contributed.
     */
    function safeWithdrawal() public afterDeadline {
        if (!fundingGoalReached) {
            uint amount = balanceOf[msg.sender];
            balanceOf[msg.sender] = 0;
            if (amount > 0) {
                if (msg.sender.send(amount)) {
                    emit FundTransfer(msg.sender, amount, false);
                } else {
                    balanceOf[msg.sender] = amount;
                }
            }
        }

        if (fundingGoalReached && beneficiary == msg.sender) {
            if (beneficiary.send(amountRaised)) {
                emit FundTransfer(beneficiary, amountRaised, false);
            } else {
                //If we fail to send the funds to beneficiary, unlock funders balance
                fundingGoalReached = false;
            }
        }
    }
}
