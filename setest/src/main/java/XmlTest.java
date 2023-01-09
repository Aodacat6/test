import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dto.GeaRemittanceRa;
import dto.Room;

/**
 * @author ：songdalin
 * @date ：2022/12/29 上午 10:24
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class XmlTest {

    public static void main(String[] args) throws JsonProcessingException {
/*        String data = "<Room1><Seat1><Student1><Id>11</Id></Student1></Seat1></Room1>";
        XmlMapper xmlMapper = XmlMapperUtil.getXmlMapper();
        Room room = xmlMapper.readValue(data, Room.class);
        System.out.println(room.getSeat().getStudent().get(0).getId());*/

        String xml = "<Remittance>\n" +
                "    <Header>\n" +
                "        <AdviceHeader>\n" +
                "            <TradingPartnerId>E3AALLHAIERSING</TradingPartnerId>\n" +
                "            <DocumentId>123KKD332LK</DocumentId>\n" +
                "        </AdviceHeader>\n" +
                "        <References>\n" +
                "            <ReferenceQual>06</ReferenceQual>\n" +
                "            <ReferenceID>HA362_SINGAPORE101_SG</ReferenceID>\n" +
                "        </References>\n" +
                "        <References>\n" +
                "            <ReferenceQual>LO</ReferenceQual>\n" +
                "            <ReferenceID>NONE</ReferenceID>\n" +
                "        </References>\n" +
                "        <References>\n" +
                "            <ReferenceQual>AH</ReferenceQual>\n" +
                "            <ReferenceID>20010000</ReferenceID>\n" +
                "        </References>\n" +
                "        <Date>\n" +
                "            <DateTimeQualifier>002</DateTimeQualifier>\n" +
                "            <Date1>2023-01-18</Date1>\n" +
                "        </Date>\n" +
                "        <Address>\n" +
                "            <AddressTypeCode>BY</AddressTypeCode>\n" +
                "            <AddressName>Haier US Appliance Solutions, Inc. DBA GE Appliances</AddressName>\n" +
                "        </Address>\n" +
                "        <Address>\n" +
                "            <AddressTypeCode>VN</AddressTypeCode>\n" +
                "            <LocationCodeQualifier>92</LocationCodeQualifier>\n" +
                "            <AddressLocationNumber>HA362</AddressLocationNumber>\n" +
                "            <AddressName>>HAINER SINGAPORE INVESTMENT PTE LTD</AddressName>\n" +
                "        </Address>\n" +
                "    </Header>\n" +
                "    <Entities>\n" +
                "        <Entity>\n" +
                "            <EntityLine>\n" +
                "                <AssignedNumber>sdfsdf2222</AssignedNumber>\n" +
                "            </EntityLine>\n" +
                "            <RemittanceAdvice>\n" +
                "                <RemittanceLine>\n" +
                "                    <DocNumberTypeCode>06</DocNumberTypeCode>\n" +
                "                    <DocNumber>sdfsdf2222</DocNumber>\n" +
                "                    <OrigAmount>236.56</OrigAmount>\n" +
                "                </RemittanceLine>\n" +
                "            </RemittanceAdvice>\n" +
                "        </Entity>\n" +
                "        <Entity>\n" +
                "            <EntityLine>\n" +
                "                <AssignedNumber>sdfsdf52555</AssignedNumber>\n" +
                "            </EntityLine>\n" +
                "            <RemittanceAdvice>\n" +
                "                <RemittanceLine>\n" +
                "                    <DocNumberTypeCode>01</DocNumberTypeCode>\n" +
                "                    <DocNumber>sdfsdf52555</DocNumber>\n" +
                "                    <OrigAmount>1000</OrigAmount>\n" +
                "                </RemittanceLine>\n" +
                "            </RemittanceAdvice>\n" +
                "        </Entity>\n" +
                "    </Entities>\n" +
                "</Remittance>";


        String xml2 = "<Remittance>\n" +
                "    <Header>\n" +
                "        <AdviceHeader>\n" +
                "            <TradingPartnerId>E3AALLHAIERSING</TradingPartnerId>\n" +
                "            <DocumentId>123KKD332LK</DocumentId>\n" +
                "        </AdviceHeader>\n" +
                "        <Reference>\n" +
                "            <ReferenceQual>0611a</ReferenceQual>\n" +
                "            <ReferenceID>HA362_SINGAPORE101_SG</ReferenceID>\n" +
                "        </Reference>\n" +
                "        <Reference>\n" +
                "            <ReferenceQual>LO</ReferenceQual>\n" +
                "            <ReferenceID>NONE</ReferenceID>\n" +
                "        </Reference>\n" +
                "        <Reference>\n" +
                "            <ReferenceQual>AH</ReferenceQual>\n" +
                "            <ReferenceID>20010000</ReferenceID>\n" +
                "        </Reference>\n" +
/*                "        <Date>\n" +
                "            <DateTimeQualifier>002</DateTimeQualifier>\n" +
                "            <Date1>2023-01-18</Date1>\n" +
                "        </Date>\n" +
                "        <Address>\n" +
                "            <AddressTypeCode>BY</AddressTypeCode>\n" +
                "            <AddressName>Haier US Appliance Solutions, Inc. DBA GE Appliances</AddressName>\n" +
                "        </Address>\n" +
                "        <Address>\n" +
                "            <AddressTypeCode>VN</AddressTypeCode>\n" +
                "            <LocationCodeQualifier>92</LocationCodeQualifier>\n" +
                "            <AddressLocationNumber>HA362</AddressLocationNumber>\n" +
                "            <AddressName>>HAINER SINGAPORE INVESTMENT PTE LTD</AddressName>\n" +
                "        </Address>\n" +*/
                "    </Header>\n" +
                "</Remittance>";
        XmlMapper xmlMapper = XmlMapperUtil.getXmlMapper();
        GeaRemittanceRa geaRemittanceRa = xmlMapper.readValue(xml2, GeaRemittanceRa.class);
        //System.out.println(geaRemittanceRa.getHeader().getAdviceHeader().get(0).getDocumentId());
        System.out.println(geaRemittanceRa.getHeader());
    }

}
