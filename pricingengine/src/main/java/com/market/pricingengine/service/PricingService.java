package com.market.pricingengine.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.market.pricingengine.model.MarketParameters;
import com.market.pricingengine.model.Product;

public class PricingService {

	public void findBestPrice(Map<String, Product> products){
		
		Set<String> productKeys = products.keySet();
		
		for(String key: productKeys){
			final Product currentProduct = products.get(key);
			float competitorPrice = findBestCompetitorPrice(currentProduct);
			float bestPrice = findOurBestPrice(currentProduct, competitorPrice);
			System.out.println(currentProduct.getName() + " " + bestPrice);
		}
		
	}

	private float findOurBestPrice(Product product, float competitorPrice) {
		
		final MarketParameters supply = product.getSupply();
		final MarketParameters demand = product.getDemand();
		float ourBestPrice = 0.0f;
		
		
		if(supply.equals(MarketParameters.HIGH) && demand.equals(MarketParameters.HIGH)){
			ourBestPrice = competitorPrice;
		}else if(supply.equals(MarketParameters.LOW) && demand.equals(MarketParameters.LOW)){
			ourBestPrice = competitorPrice + (competitorPrice * 0.1f);
		}else if(supply.equals(MarketParameters.LOW) && demand.equals(MarketParameters.HIGH)){
			ourBestPrice = competitorPrice + (competitorPrice * 0.05f);
		}else if(supply.equals(MarketParameters.HIGH) && demand.equals(MarketParameters.LOW)){
			ourBestPrice = competitorPrice - (competitorPrice * 0.05f);
		}
		
		return ourBestPrice;
	}

	private float findBestCompetitorPrice(Product product) {
		
		List<Float> priceList = product.getCompetitorPrices();
		float averagePrice = findAveragePrice(priceList);
		float avgPriceLowerBound = averagePrice * 0.5f;
		float avgPriceUpperBound = averagePrice * 1.5f;
		
		return findLowestCommonPrice(priceList, avgPriceLowerBound, avgPriceUpperBound);
	}

	private float findLowestCommonPrice(List<Float> priceList, float avgPriceLowerBound, float avgPriceUpperBound) {
		
		final Map<Float, Integer> commonMap = new HashMap<Float, Integer>();
		
		for(float price: priceList){
			if(avgPriceLowerBound <= price && price <= avgPriceUpperBound){
				int count = 1;
				if(commonMap.containsKey(price)){
					count += commonMap.get(price);
				}
				commonMap.put(price, count);
			}
		}
		
		final Set<Float> priceKeys = commonMap.keySet();
		float mostRepeatedLowPrice = 0.0f;
		int mostNumberOfTimesOccured = 0;
		
		for(float price : priceKeys){
			
			final int noOfTimesOccured = commonMap.get(price);
			
			if(noOfTimesOccured > mostNumberOfTimesOccured){
				mostRepeatedLowPrice = price;
				mostNumberOfTimesOccured = noOfTimesOccured;
			}else  if(noOfTimesOccured == mostNumberOfTimesOccured){
				if(price < mostRepeatedLowPrice){
					mostRepeatedLowPrice = price;
				}
			}
			
		}
		
		return mostRepeatedLowPrice;
		
	}

	private float findAveragePrice(List<Float> priceList) {
	
		float sum = 0.0f;
		
		for(float price : priceList){
			sum += price;
		}
		
		return sum/priceList.size();
	}
	
}
