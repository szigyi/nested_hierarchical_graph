//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2016.03.09 at 09:10:49 AM CET
//

package hu.szigyi.nested.hierarchical.graph.domain.generated.org.graphdrawing.graphml.xmlns;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Complex type for the <edge> element.
 * 
 *
 * <p>
 * Java class for edge.type complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="edge.type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://graphml.graphdrawing.org/xmlns}desc" minOccurs="0"/>
 *         &lt;element ref="{http://graphml.graphdrawing.org/xmlns}data" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://graphml.graphdrawing.org/xmlns}graph" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://graphml.graphdrawing.org/xmlns}edge.extra.attrib"/>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="directed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="source" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="target" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="sourceport" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="targetport" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "edge.type", propOrder = { "desc", "data", "graph" })
public class EdgeType {

	protected String desc;
	protected List<DataType> data;
	protected GraphType graph;
	@XmlAttribute(name = "id")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NMTOKEN")
	protected String id;
	@XmlAttribute(name = "directed")
	protected Boolean directed;
	@XmlAttribute(name = "source", required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NMTOKEN")
	protected String source;
	@XmlAttribute(name = "target", required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NMTOKEN")
	protected String target;
	@XmlAttribute(name = "sourceport")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NMTOKEN")
	protected String sourceport;
	@XmlAttribute(name = "targetport")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NMTOKEN")
	protected String targetport;

	/**
	 * Gets the value of the desc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Sets the value of the desc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDesc(final String value) {
		desc = value;
	}

	/**
	 * Gets the value of the data property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the data property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getData().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link DataType }
	 * 
	 * 
	 */
	public List<DataType> getData() {
		if (data == null) {
			data = new ArrayList<DataType>();
		}
		return data;
	}

	/**
	 * Gets the value of the graph property.
	 * 
	 * @return possible object is {@link GraphType }
	 * 
	 */
	public GraphType getGraph() {
		return graph;
	}

	/**
	 * Sets the value of the graph property.
	 * 
	 * @param value
	 *            allowed object is {@link GraphType }
	 * 
	 */
	public void setGraph(final GraphType value) {
		graph = value;
	}

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setId(final String value) {
		id = value;
	}

	/**
	 * Gets the value of the directed property.
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isDirected() {
		return directed;
	}

	/**
	 * Sets the value of the directed property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setDirected(final Boolean value) {
		directed = value;
	}

	/**
	 * Gets the value of the source property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Sets the value of the source property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSource(final String value) {
		source = value;
	}

	/**
	 * Gets the value of the target property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * Sets the value of the target property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTarget(final String value) {
		target = value;
	}

	/**
	 * Gets the value of the sourceport property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSourceport() {
		return sourceport;
	}

	/**
	 * Sets the value of the sourceport property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSourceport(final String value) {
		sourceport = value;
	}

	/**
	 * Gets the value of the targetport property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTargetport() {
		return targetport;
	}

	/**
	 * Sets the value of the targetport property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTargetport(final String value) {
		targetport = value;
	}

}
