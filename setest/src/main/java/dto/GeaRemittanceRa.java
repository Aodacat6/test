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
 * @date ：2022/12/23 下午 3:02
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Remittance")
public class GeaRemittanceRa {

	@JacksonXmlProperty(localName = "Header")
	private GeaHeaderRa header;

/*	@JacksonXmlProperty(localName = "Entities")
	@JacksonXmlElementWrapper
	private List<GeaEntities> entities;*/
}
