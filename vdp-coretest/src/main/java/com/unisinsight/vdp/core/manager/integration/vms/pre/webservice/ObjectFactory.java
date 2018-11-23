
package com.unisinsight.vdp.core.manager.integration.vms.pre.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.unisinsight.vdp.core.manager.integration.vms.pre.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetVideoFile_QNAME = new QName("http://webservice.arges.com/", "getVideoFile");
    private final static QName _GetVideoFileResponse_QNAME = new QName("http://webservice.arges.com/", "getVideoFileResponse");
    private final static QName _CameraCapture_QNAME = new QName("http://webservice.arges.com/", "cameraCapture");
    private final static QName _GetRecordPlayUrlResponse_QNAME = new QName("http://webservice.arges.com/", "getRecordPlayUrlResponse");
    private final static QName _GetCapture_QNAME = new QName("http://webservice.arges.com/", "getCapture");
    private final static QName _GetCaptureResponse_QNAME = new QName("http://webservice.arges.com/", "getCaptureResponse");
    private final static QName _CameraCaptureResponse_QNAME = new QName("http://webservice.arges.com/", "cameraCaptureResponse");
    private final static QName _GetMediaPalyUrlResponse_QNAME = new QName("http://webservice.arges.com/", "getMediaPalyUrlResponse");
    private final static QName _GetRecordPlayUrl_QNAME = new QName("http://webservice.arges.com/", "getRecordPlayUrl");
    private final static QName _GetTreeNode_QNAME = new QName("http://webservice.arges.com/", "getTreeNode");
    private final static QName _GetMediaPalyUrl_QNAME = new QName("http://webservice.arges.com/", "getMediaPalyUrl");
    private final static QName _GetTreeNodeResponse_QNAME = new QName("http://webservice.arges.com/", "getTreeNodeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.unisinsight.vdp.core.manager.integration.vms.pre.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetRecordPlayUrl }
     * 
     */
    public GetRecordPlayUrl createGetRecordPlayUrl() {
        return new GetRecordPlayUrl();
    }

    /**
     * Create an instance of {@link GetTreeNode }
     * 
     */
    public GetTreeNode createGetTreeNode() {
        return new GetTreeNode();
    }

    /**
     * Create an instance of {@link CameraCaptureResponse }
     * 
     */
    public CameraCaptureResponse createCameraCaptureResponse() {
        return new CameraCaptureResponse();
    }

    /**
     * Create an instance of {@link GetMediaPalyUrlResponse }
     * 
     */
    public GetMediaPalyUrlResponse createGetMediaPalyUrlResponse() {
        return new GetMediaPalyUrlResponse();
    }

    /**
     * Create an instance of {@link GetCaptureResponse }
     * 
     */
    public GetCaptureResponse createGetCaptureResponse() {
        return new GetCaptureResponse();
    }

    /**
     * Create an instance of {@link GetMediaPalyUrl }
     * 
     */
    public GetMediaPalyUrl createGetMediaPalyUrl() {
        return new GetMediaPalyUrl();
    }

    /**
     * Create an instance of {@link GetTreeNodeResponse }
     * 
     */
    public GetTreeNodeResponse createGetTreeNodeResponse() {
        return new GetTreeNodeResponse();
    }

    /**
     * Create an instance of {@link CameraCapture }
     * 
     */
    public CameraCapture createCameraCapture() {
        return new CameraCapture();
    }

    /**
     * Create an instance of {@link GetVideoFileResponse }
     * 
     */
    public GetVideoFileResponse createGetVideoFileResponse() {
        return new GetVideoFileResponse();
    }

    /**
     * Create an instance of {@link GetVideoFile }
     * 
     */
    public GetVideoFile createGetVideoFile() {
        return new GetVideoFile();
    }

    /**
     * Create an instance of {@link GetCapture }
     * 
     */
    public GetCapture createGetCapture() {
        return new GetCapture();
    }

    /**
     * Create an instance of {@link GetRecordPlayUrlResponse }
     * 
     */
    public GetRecordPlayUrlResponse createGetRecordPlayUrlResponse() {
        return new GetRecordPlayUrlResponse();
    }

    /**
     * Create an instance of {@link FtpServer }
     * 
     */
    public FtpServer createFtpServer() {
        return new FtpServer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVideoFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.arges.com/", name = "getVideoFile")
    public JAXBElement<GetVideoFile> createGetVideoFile(GetVideoFile value) {
        return new JAXBElement<GetVideoFile>(_GetVideoFile_QNAME, GetVideoFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetVideoFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.arges.com/", name = "getVideoFileResponse")
    public JAXBElement<GetVideoFileResponse> createGetVideoFileResponse(GetVideoFileResponse value) {
        return new JAXBElement<GetVideoFileResponse>(_GetVideoFileResponse_QNAME, GetVideoFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CameraCapture }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.arges.com/", name = "cameraCapture")
    public JAXBElement<CameraCapture> createCameraCapture(CameraCapture value) {
        return new JAXBElement<CameraCapture>(_CameraCapture_QNAME, CameraCapture.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRecordPlayUrlResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.arges.com/", name = "getRecordPlayUrlResponse")
    public JAXBElement<GetRecordPlayUrlResponse> createGetRecordPlayUrlResponse(GetRecordPlayUrlResponse value) {
        return new JAXBElement<GetRecordPlayUrlResponse>(_GetRecordPlayUrlResponse_QNAME, GetRecordPlayUrlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCapture }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.arges.com/", name = "getCapture")
    public JAXBElement<GetCapture> createGetCapture(GetCapture value) {
        return new JAXBElement<GetCapture>(_GetCapture_QNAME, GetCapture.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCaptureResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.arges.com/", name = "getCaptureResponse")
    public JAXBElement<GetCaptureResponse> createGetCaptureResponse(GetCaptureResponse value) {
        return new JAXBElement<GetCaptureResponse>(_GetCaptureResponse_QNAME, GetCaptureResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CameraCaptureResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.arges.com/", name = "cameraCaptureResponse")
    public JAXBElement<CameraCaptureResponse> createCameraCaptureResponse(CameraCaptureResponse value) {
        return new JAXBElement<CameraCaptureResponse>(_CameraCaptureResponse_QNAME, CameraCaptureResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMediaPalyUrlResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.arges.com/", name = "getMediaPalyUrlResponse")
    public JAXBElement<GetMediaPalyUrlResponse> createGetMediaPalyUrlResponse(GetMediaPalyUrlResponse value) {
        return new JAXBElement<GetMediaPalyUrlResponse>(_GetMediaPalyUrlResponse_QNAME, GetMediaPalyUrlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRecordPlayUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.arges.com/", name = "getRecordPlayUrl")
    public JAXBElement<GetRecordPlayUrl> createGetRecordPlayUrl(GetRecordPlayUrl value) {
        return new JAXBElement<GetRecordPlayUrl>(_GetRecordPlayUrl_QNAME, GetRecordPlayUrl.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTreeNode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.arges.com/", name = "getTreeNode")
    public JAXBElement<GetTreeNode> createGetTreeNode(GetTreeNode value) {
        return new JAXBElement<GetTreeNode>(_GetTreeNode_QNAME, GetTreeNode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMediaPalyUrl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.arges.com/", name = "getMediaPalyUrl")
    public JAXBElement<GetMediaPalyUrl> createGetMediaPalyUrl(GetMediaPalyUrl value) {
        return new JAXBElement<GetMediaPalyUrl>(_GetMediaPalyUrl_QNAME, GetMediaPalyUrl.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTreeNodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.arges.com/", name = "getTreeNodeResponse")
    public JAXBElement<GetTreeNodeResponse> createGetTreeNodeResponse(GetTreeNodeResponse value) {
        return new JAXBElement<GetTreeNodeResponse>(_GetTreeNodeResponse_QNAME, GetTreeNodeResponse.class, null, value);
    }

}
