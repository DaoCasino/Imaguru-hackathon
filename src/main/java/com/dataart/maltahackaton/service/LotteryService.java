package com.dataart.maltahackaton.service;

import com.dataart.maltahackaton.domain.Lottery;
import com.dataart.maltahackaton.domain.LotteryStatus;
import com.dataart.maltahackaton.domain.dto.LotteryDto;
import com.dataart.maltahackaton.exception.MhException;
import com.dataart.maltahackaton.repository.LotteryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotteryService {

    private final LotteryRepository lotteryRepository;
    private final ModelMapper modelMapper;

    public LotteryService(LotteryRepository lotteryRepository, ModelMapper modelMapper) {
        this.lotteryRepository = lotteryRepository;
        this.modelMapper = modelMapper;
    }

    public List<LotteryDto> getAll() {
        return modelMapper.map(lotteryRepository.findAll(), new TypeToken<List<LotteryDto>>() {
        }.getType());
    }

    public LotteryDto createLottery(LotteryDto createRequest) {
        Lottery lottery = modelMapper.map(createRequest, Lottery.class);
        lottery.setStatus(LotteryStatus.PENDING);
        lottery.setContractAddress("");
        lotteryRepository.save(lottery);
        return modelMapper.map(lottery, LotteryDto.class);
    }

    public LotteryDto getLotteryById(Long id) {
        return modelMapper.map(lotteryRepository.findById(id)
                .orElseThrow(() -> new MhException("Lottery not found")), LotteryDto.class);
    }
}
