package com.market.pricingengine.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.market.pricingengine.exception.InvalidPricingInputException;
import com.market.pricingengine.model.Product;
import com.market.pricingengine.service.PricingService;
import com.market.pricingengine.utils.PricingUtils;

public class PriceSelector {

	public static void main(String[] args) {
		try {

			int lineCounter = 0;
			int productCounter = 0;
			int priceCounter = 0;
			final BufferedReader br = new BufferedReader(new FileReader("pricing_engine_input.txt"));

			// Parsing number of products of which price to be determined
			lineCounter++;
			final int noOfProducts = PricingUtils.getValidInt(br.readLine(), lineCounter);
			final Map<String, Product> products = new LinkedHashMap<String, Product>(noOfProducts);

			// Parsing the list of : Product name and its supply and demand
			// parameters.
			lineCounter++;
			String inputParsed = br.readLine();
			while (null != inputParsed && productCounter < noOfProducts) {

				final Product currentProduct = PricingUtils.formProdcut(inputParsed, lineCounter);
				products.put(currentProduct.getName(), currentProduct);
				productCounter++;
				lineCounter++;
				inputParsed = br.readLine();

			}

			// Parsing number of competitor prices for all products
			final int noOfPrices = PricingUtils.getValidInt(inputParsed, lineCounter);

			// Parsing the list of all competitor price of all products.
			lineCounter++;
			inputParsed = br.readLine();
			while (null != inputParsed && priceCounter < noOfPrices) {

				final String[] priceDescription = inputParsed.split(" ");

				if (null != priceDescription && priceDescription.length == 3) {
					final String productName = priceDescription[0];
					final Float price = PricingUtils.getValidFloat(priceDescription[2], lineCounter);

					products.get(productName).getCompetitorPrices().add(price);
					lineCounter++;
					inputParsed = br.readLine();
					priceCounter++;
				} else {
					throw new InvalidPricingInputException("Invalid input at line " + lineCounter
							+ ".It should be in syntax : ProductName Competitor Price");
				}
			}

			// Calling the service to determine the best price.
			final PricingService pricingService = new PricingService();
			pricingService.findBestPrice(products);
		}

		catch (InvalidPricingInputException inpe) {
			System.out.println(inpe.getMessage() + "!!!");
		}
		// handle exceptions
		catch (FileNotFoundException fnfe) {
			System.out.println("file not found");
		}

		catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

}
