/*
 * Licensed to csti consulting 
 * You may obtain a copy of the License at
 *
 * http://www.csticonsulting.com
 * Copyright (c) 2006-Aug 24, 2010 Consultation CS-TI inc. 
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.salesmanager.core.entity.orders;

// Generated Mar 8, 2009 10:16:39 PM by Hibernate Tools 3.2.0.beta8

import java.math.BigDecimal;
import java.util.Date;

/**
 * OrdersProductsPricesSpecials generated by hbm2java
 */
public class OrderProductPriceSpecial implements java.io.Serializable {

	// Fields

	private long orderProductPrice;

	private BigDecimal specialNewProductPrice;

	private BigDecimal originalPrice;

	private Date orderProductPriceSpecialStartDate;

	private Date orderProductSpecialEndDate;

	private Integer orderProductSpecialDurationDays;

	// Constructors

	/** default constructor */
	public OrderProductPriceSpecial() {
	}

	/** minimal constructor */
	public OrderProductPriceSpecial(long orderProductPrice,
			BigDecimal specialNewProductPrice, BigDecimal originalPrice) {
		this.orderProductPrice = orderProductPrice;
		this.specialNewProductPrice = specialNewProductPrice;
		this.originalPrice = originalPrice;
	}

	/** full constructor */
	public OrderProductPriceSpecial(long orderProductPrice,
			BigDecimal specialNewProductPrice, BigDecimal originalPrice,
			Date orderProductPriceSpecialStartDate,
			Date orderProductSpecialEndDate,
			Integer orderProductSpecialDurationDays) {
		this.orderProductPrice = orderProductPrice;
		this.specialNewProductPrice = specialNewProductPrice;
		this.originalPrice = originalPrice;
		this.orderProductPriceSpecialStartDate = orderProductPriceSpecialStartDate;
		this.orderProductSpecialEndDate = orderProductSpecialEndDate;
		this.orderProductSpecialDurationDays = orderProductSpecialDurationDays;
	}

	// Property accessors
	public long getOrderProductPrice() {
		return this.orderProductPrice;
	}

	public void setOrderProductPrice(long orderProductPrice) {
		this.orderProductPrice = orderProductPrice;
	}

	public BigDecimal getSpecialNewProductPrice() {
		return this.specialNewProductPrice;
	}

	public void setSpecialNewProductPrice(BigDecimal specialNewProductPrice) {
		this.specialNewProductPrice = specialNewProductPrice;
	}

	public BigDecimal getOriginalPrice() {
		return this.originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Date getOrderProductPriceSpecialStartDate() {
		return this.orderProductPriceSpecialStartDate;
	}

	public void setOrderProductPriceSpecialStartDate(
			Date orderProductPriceSpecialStartDate) {
		this.orderProductPriceSpecialStartDate = orderProductPriceSpecialStartDate;
	}

	public Date getOrderProductSpecialEndDate() {
		return this.orderProductSpecialEndDate;
	}

	public void setOrderProductSpecialEndDate(Date orderProductSpecialEndDate) {
		this.orderProductSpecialEndDate = orderProductSpecialEndDate;
	}

	public Integer getOrderProductSpecialDurationDays() {
		return this.orderProductSpecialDurationDays;
	}

	public void setOrderProductSpecialDurationDays(
			Integer orderProductSpecialDurationDays) {
		this.orderProductSpecialDurationDays = orderProductSpecialDurationDays;
	}

}
