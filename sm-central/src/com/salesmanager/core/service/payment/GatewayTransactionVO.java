/*
 * Licensed to csti consulting 
 * You may obtain a copy of the License at
 *
 * http://www.csticonsulting.com
 * Copyright (c) 2006-3 Sep, 2010 Consultation CS-TI inc. 
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.salesmanager.core.service.payment;

import com.salesmanager.core.entity.payment.MerchantPaymentGatewayTrx;

public class GatewayTransactionVO extends SalesManagerTransactionVO {


	private String internalGatewayOrderId;//orderid created in payment gateway
	private MerchantPaymentGatewayTrx transactionDetails ;
	private int type;//capture=2 / pre-authorization=1 (PaymentConstants)



	private String creditcard;
	private String expirydate;
	private String creditcardtype;
	
	private String transactionMessage;



	public String getInternalGatewayOrderId() {
		return internalGatewayOrderId;
	}
	public void setInternalGatewayOrderId(String internalGatewayOrderId) {
		this.internalGatewayOrderId = internalGatewayOrderId;
	}
	public MerchantPaymentGatewayTrx getTransactionDetails() {
		return transactionDetails;
	}
	public void setTransactionDetails(MerchantPaymentGatewayTrx transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

	public String toString() {
		return new StringBuffer().append(" orderid ").append(getOrderID())
		.append("\r\n").append(" internal order id ").append(this.internalGatewayOrderId)
		.append("\r\n").append(" transaction id ").append(this.getTransactionID())
		.append("\r\n").append(" transaction type ").append(this.type)
		.toString();
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCreditcard() {
		return creditcard;
	}
	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}
	public String getCreditcardtype() {
		return creditcardtype;
	}
	public void setCreditcardtype(String creditcardtype) {
		this.creditcardtype = creditcardtype;
	}
	public String getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}
	public String getTransactionMessage() {
		return transactionMessage;
	}
	public void setTransactionMessage(String transactionMessage) {
		this.transactionMessage = transactionMessage;
	}


}
