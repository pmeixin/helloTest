
package com.unisinsight.vdp.core.manager.integration.vms.pre.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getVideoFile complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getVideoFile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arg1" type="{http://webservice.arges.com/}ftpServer" minOccurs="0"/>
 *         &lt;element name="arg2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arg3" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="arg4" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="arg5" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="arg6" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="arg7" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getVideoFile", propOrder = {
    "arg0",
    "arg1",
    "arg2",
    "arg3",
    "arg4",
    "arg5",
    "arg6",
    "arg7"
})
public class GetVideoFile {

    protected String arg0;
    protected FtpServer arg1;
    protected String arg2;
    protected int arg3;
    protected int arg4;
    protected long arg5;
    protected long arg6;
    protected int arg7;

    /**
     * 获取arg0属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArg0() {
        return arg0;
    }

    /**
     * 设置arg0属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArg0(String value) {
        this.arg0 = value;
    }

    /**
     * 获取arg1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FtpServer }
     *     
     */
    public FtpServer getArg1() {
        return arg1;
    }

    /**
     * 设置arg1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FtpServer }
     *     
     */
    public void setArg1(FtpServer value) {
        this.arg1 = value;
    }

    /**
     * 获取arg2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArg2() {
        return arg2;
    }

    /**
     * 设置arg2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArg2(String value) {
        this.arg2 = value;
    }

    /**
     * 获取arg3属性的值。
     * 
     */
    public int getArg3() {
        return arg3;
    }

    /**
     * 设置arg3属性的值。
     * 
     */
    public void setArg3(int value) {
        this.arg3 = value;
    }

    /**
     * 获取arg4属性的值。
     * 
     */
    public int getArg4() {
        return arg4;
    }

    /**
     * 设置arg4属性的值。
     * 
     */
    public void setArg4(int value) {
        this.arg4 = value;
    }

    /**
     * 获取arg5属性的值。
     * 
     */
    public long getArg5() {
        return arg5;
    }

    /**
     * 设置arg5属性的值。
     * 
     */
    public void setArg5(long value) {
        this.arg5 = value;
    }

    /**
     * 获取arg6属性的值。
     * 
     */
    public long getArg6() {
        return arg6;
    }

    /**
     * 设置arg6属性的值。
     * 
     */
    public void setArg6(long value) {
        this.arg6 = value;
    }

    /**
     * 获取arg7属性的值。
     * 
     */
    public int getArg7() {
        return arg7;
    }

    /**
     * 设置arg7属性的值。
     * 
     */
    public void setArg7(int value) {
        this.arg7 = value;
    }

}
