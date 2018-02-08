package com.market.pricingengine.model;

import java.util.ArrayList;
import java.util.List;

public class Product {

	private String name;
	private MarketParameters supply;
	private MarketParameters demand;
	private List<Float> competitorPrices = new ArrayList<Float>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MarketParameters getSupply() {
		return supply;
	}

	public void setSupply(MarketParameters supply) {
		this.supply = supply;
	}

	public MarketParameters getDemand() {
		return demand;
	}

	public void setDemand(MarketParameters demand) {
		this.demand = demand;
	}

	public List<Float> getCompetitorPrices() {
		return competitorPrices;
	}

	public void setCompetitorPrices(List<Float> competitorPrices) {
		this.competitorPrices = competitorPrices;
	}

}
