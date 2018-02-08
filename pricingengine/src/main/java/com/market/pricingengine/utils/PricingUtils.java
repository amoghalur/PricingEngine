package com.market.pricingengine.utils;

import com.market.pricingengine.exception.InvalidPricingInputException;
import com.market.pricingengine.model.MarketParameters;
import com.market.pricingengine.model.Product;

public class PricingUtils {

	public static Product formProdcut(String inParsed, int lineCounter) throws InvalidPricingInputException {

		final String[] parsedArr = inParsed.split(" ");
		final Product product = new Product();

		if (null != parsedArr && parsedArr.length == 3) {

			product.setName(parsedArr[0]);
			product.setSupply(determineMarketParameters(parsedArr[1], lineCounter));
			product.setDemand(determineMarketParameters(parsedArr[2], lineCounter));

		} else {
			throw new InvalidPricingInputException(
					"Invalid input at line " + lineCounter + ".It should be in syntax : ProductName Suply Demand");
		}

		return product;

	}

	private static MarketParameters determineMarketParameters(String inParam, int lineCounter)
			throws InvalidPricingInputException {

		MarketParameters marketParam = null;

		if ("H".equals(inParam)) {
			marketParam = MarketParameters.HIGH;
		} else if ("L".equals(inParam)) {
			marketParam = MarketParameters.LOW;
		} else {
			throw new InvalidPricingInputException("Invalid input at line " + lineCounter + ".Expected input : H or L");
		}

		return marketParam;
	}

	public static int getValidInt(String numberString, int lineCounter) throws InvalidPricingInputException {

		int noOfProducts;
		try {
			noOfProducts = Integer.parseInt(numberString);
			if (noOfProducts < 1) {
				throw new InvalidPricingInputException(
						"Invalid input at line " + lineCounter + ". Expecting a number greater than 0");
			}
		} catch (NumberFormatException nf) {
			throw new InvalidPricingInputException(
					"Invalid input at line " + lineCounter + ". Expecting a number greater than 0");
		}

		return noOfProducts;
	}

	public static float getValidFloat(String numberString, int lineCounter) throws InvalidPricingInputException {

		float price;
		try {
			price = Float.parseFloat(numberString);
			if (price < 0.0f) {
				throw new InvalidPricingInputException(
						"Invalid input at line " + lineCounter + ". Expecting it to be greater than 0.00");
			}
		} catch (NumberFormatException nf) {
			throw new InvalidPricingInputException(
					"Invalid input at line " + lineCounter + ". Expecting a number greater than 0.00");
		}

		return price;
	}

}
