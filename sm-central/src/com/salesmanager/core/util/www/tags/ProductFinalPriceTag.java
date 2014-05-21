package com.salesmanager.core.util.www.tags;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import com.salesmanager.core.entity.catalog.Product;
import com.salesmanager.core.entity.merchant.MerchantStore;
import com.salesmanager.core.util.CurrencyUtil;
import com.salesmanager.core.util.ProductUtil;


public class ProductFinalPriceTag extends TagSupport {
	
	private Product product;
	private boolean displayCurrency;
	
	
	public int doStartTag() throws JspException {
		try {



			HttpServletRequest request = (HttpServletRequest) pageContext
			.getRequest();
	
			HttpSession session = request.getSession();
			
			Locale locale = (Locale) request.getAttribute("LOCALE");
			
			MerchantStore store = (MerchantStore)session.getAttribute("STORE");

			BigDecimal price = ProductUtil.determinePrice(product, locale, store.getCurrency());

			
			String pprice = null;
			
			if(this.displayCurrency) {
				pprice = CurrencyUtil.displayFormatedAmountWithCurrency(price, store.getCurrency());
			} else {
				pprice = CurrencyUtil.displayFormatedAmountNoCurrency(price, store.getCurrency());
			}
			

			pageContext.getOut().print(pprice);
	
	if(this.displayCurrency) {
		pprice = CurrencyUtil.displayFormatedAmountWithCurrency(price, store.getCurrency());
	} else {
		pprice = CurrencyUtil.displayFormatedAmountNoCurrency(price, store.getCurrency());
	}
	
	
	
	pageContext.getOut().print(pprice);
			
		} catch (Exception ex) {
			//log.error(ex);
		}
		return SKIP_BODY;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public boolean isDisplayCurrency() {
		return displayCurrency;
	}

	public void setDisplayCurrency(boolean displayCurrency) {
		this.displayCurrency = displayCurrency;
	}

}
