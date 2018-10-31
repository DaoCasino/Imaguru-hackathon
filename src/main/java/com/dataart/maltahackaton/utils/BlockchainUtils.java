package com.dataart.maltahackaton.utils;

import com.dataart.maltahackaton.exception.MhException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.regex.Pattern;

public final class BlockchainUtils {

    public static final String WALLET_ADDRESS_PATTERN_STRING = "^0(?i)x[0-9a-fA-F]{40}$";
    public static final Pattern WALLET_ADDRESS_PATTERN = Pattern.compile(WALLET_ADDRESS_PATTERN_STRING);

    public static final String BLOCKCHAIN_STRINGS_DELIMITER = "~";
    public static final int BLOCKCHAIN_COMMENT_MAX_LENGTH = 255;

    public static final int MAX_INTEGERS = 100;
    public static final int DECIMALS = 18;
    public static final int VALIDATION_DECIMALS = 8;
    public static final int MAX_BALANCE_LENGTH = MAX_INTEGERS + DECIMALS;
    public static final MathContext BLOCKCHAIN_MATH_CONTEXT = new MathContext(MAX_BALANCE_LENGTH, RoundingMode.HALF_DOWN);
    public static final BigInteger CALCULATED_SHIFT_BIG_INT = BigInteger.TEN.pow(DECIMALS);
    public static final BigDecimal CALCULATED_SHIFT_BIG_DECIMALS = new BigDecimal(
            CALCULATED_SHIFT_BIG_INT, BLOCKCHAIN_MATH_CONTEXT);
    public static final BigInteger ONE_HUNDRED_PERCENT_WITH_CALCULATED_SHIFT = new BigInteger("100")
            .multiply(CALCULATED_SHIFT_BIG_INT);
    public static final String BLOCKCHAIN_MIN_AMOUNT = "0.000000000000000001";
    public static final String
            VALIDATION_BLOCKCHAIN_MIN_AMOUNT = "0.00000001";

    private BlockchainUtils() {
    }

    public static Credentials buildCredentials(String privateKey) {
        return Credentials.create(Numeric.toHexStringNoPrefix(new BigInteger(privateKey)));
    }

    public static BigInteger convertToBlockchainUnits(BigDecimal amount) {
        if (amount == null) {
            return null;
        }
        return amount.multiply(CALCULATED_SHIFT_BIG_DECIMALS).toBigInteger();
    }

    public static BigDecimal convertToUserUnits(BigInteger amount) {
        if (amount == null) {
            return null;
        }
        return new BigDecimal(amount, DECIMALS, BLOCKCHAIN_MATH_CONTEXT);
    }

    public static String generatePrivateKey() {
        try {
            return Keys.createEcKeyPair().getPrivateKey().toString();
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new MhException("Error during generation of Ethereum wallet private key.", e);
        }
    }

}
