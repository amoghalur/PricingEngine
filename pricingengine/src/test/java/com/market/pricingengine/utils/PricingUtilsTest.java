package com.market.pricingengine.utils;

import org.junit.Test;

import static org.junit.Assert.*;

import com.market.pricingengine.exception.InvalidPricingInputException;
import com.market.pricingengine.model.MarketParameters;
import com.market.pricingengine.model.Product;
import com.market.pricingengine.utils.PricingUtils;

public class PricingUtilsTest {

	private String inParsedNormal = "mp3player H H";
	private int lineCounter = 2;

	@Test
	public void formProduct_With_Normal_Values()
			throws InvalidPricingInputException {

		Product actualProductFormed = PricingUtils.formProdcut(inParsedNormal,
				lineCounter);
		assertEquals("mp3player", actualProductFormed.getName());
		assertEquals(MarketParameters.HIGH, actualProductFormed.getSupply());
		assertEquals(MarketParameters.HIGH, actualProductFormed.getDemand());

	}

	@Test(expected = InvalidPricingInputException.class)
	public void formProduct_With_LessThan_three()
			throws InvalidPricingInputException {

		String inPars = "mp3Player";
		PricingUtils.formProdcut(inPars, lineCounter);
	}

	@Test(expected = InvalidPricingInputException.class)
	public void formProduct_Supply_invalid()
			throws InvalidPricingInputException {

		String inPars = "mp3Player G H";
		PricingUtils.formProdcut(inPars, lineCounter);
	}
	
	@Test(expected = InvalidPricingInputException.class)
	public void formProduct_Demand_invalid()
			throws InvalidPricingInputException {

		String inPars = "mp3Player G H";
		PricingUtils.formProdcut(inPars, lineCounter);
	}
	
	@Test
	public void getValidInt_valid() throws InvalidPricingInputException{
		String numberString = "9";
		PricingUtils.getValidInt(numberString, lineCounter);
	}
	
	@Test(expected = InvalidPricingInputException.class)
	public void getValidInt_invalid_zero() throws InvalidPricingInputException{
		String numberString = "0";
		PricingUtils.getValidInt(numberString, lineCounter);
	}


	@Test(expected = InvalidPricingInputException.class)
	public void getValidInt_Invalid() throws InvalidPricingInputException{
		String numberString = "invalid";
		PricingUtils.getValidInt(numberString, lineCounter);
	}
	
	@Test
	public void getValidFloat_valid() throws InvalidPricingInputException{
		String numberStr = "1.0f";
		PricingUtils.getValidFloat(numberStr, lineCounter);
	}
	
	
	@Test(expected = InvalidPricingInputException.class)
	public void getValidFloat_invalid() throws InvalidPricingInputException{
		String numberStr = "invalid";
		PricingUtils.getValidFloat(numberStr, lineCounter);
	}

}
