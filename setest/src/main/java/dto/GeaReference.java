package dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：songdalin
 * @date ：2022/12/23 下午 2:45
 * @description：
 * @modified By：
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "Reference")
public class GeaReference {

	@JacksonXmlProperty(localName = "ReferenceQual")
	private String referenceQual;

	@JacksonXmlProperty(localName = "ReferenceID")
	private String referenceID;
}
