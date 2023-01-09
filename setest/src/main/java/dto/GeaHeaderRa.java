package dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ：songdalin
 * @date ：2022/12/23 下午 2:50
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Header")
public class GeaHeaderRa {

	@JacksonXmlProperty(localName = "AdviceHeader")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<GeaAdviceHeader> adviceHeader;

	@JacksonXmlProperty(localName = "Reference")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<GeaReference> reference;

/*	@JacksonXmlProperty(localName = "Date")
	@JacksonXmlElementWrapper
	private List<GeaDateRa> date;

	@JacksonXmlProperty(localName = "Address")
	@JacksonXmlElementWrapper
	private List<GeaHeaderAddressRa> address;*/


}
