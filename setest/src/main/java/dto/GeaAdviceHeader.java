package dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：songdalin
 * @date ：2022/12/23 下午 2:47
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "AdviceHeader")
public class GeaAdviceHeader {

	@JacksonXmlProperty(localName = "TradingPartnerId")
	private String tradingPartnerId;

	@JacksonXmlProperty(localName = "DocumentId")
	private String documentId;

/*	@JacksonXmlProperty(localName = "TransactionHandlingCode")
	private String transactionHandlingCode;

	@JacksonXmlProperty(localName = "MonetaryAmount")
	private String monetaryAmount;

	@JacksonXmlProperty(localName = "CreditDebitFlag")
	private String creditDebitFlag;

	@JacksonXmlProperty(localName = "PaymentMethodCode")
	private String paymentMethodCode;

	@JacksonXmlProperty(localName = "CurrencyCode")
	private String currencyCode;*/


}
