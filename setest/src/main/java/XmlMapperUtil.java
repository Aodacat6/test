import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * @author wangysh
 * @date 2022/09/30 13:27
 */
public class XmlMapperUtil {
	private static XmlMapper xmlMapper = new XmlMapper();
	private XmlMapperUtil(){}

	public static XmlMapper getXmlMapper(){
		return xmlMapper;
	}
}
