package com.codelious.saludows.endpoint;

import org.springframework.ws.server.endpoint.AbstractDomPayloadEndpoint;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.codelious.saludows.servicio.SaludarService;

@SuppressWarnings("deprecation")
public class SaludarServiceEndPoint extends AbstractDomPayloadEndpoint {

	public static final String NAMESPACE = "http://localhost:18080/com/codelious/saludows/saludar-service";
	
	private SaludarService saludarService;
	
	public void setSaludarService(SaludarService saludarService) {
		this.saludarService = saludarService;
	}
	
	@Override
	protected Element invokeInternal(Element requestElement,
			Document responseDocument) throws Exception {
		
		// recibe un elemento de solicitud y un documento
		
		String solicitudString = buscarStringSolicitud(requestElement);
		String saludoString = invocaServicioRetornaRespuesta(solicitudString);
		Element respuestaXml = preparaRespuestaXml(responseDocument, saludoString);
		
		return respuestaXml;
	}

	private Element preparaRespuestaXml(Document responseDocument,
			String saludoString) {
		
		// prepara los nodos de la respuesta
		Element elementoRespuesta = responseDocument.createElementNS(NAMESPACE, "SaludoResponse");
		Element elementoSaludo = responseDocument.createElementNS(NAMESPACE, "saludo");
		// prepara el texto contenido en la respuesta
		Text textoRespuesta = responseDocument.createTextNode(saludoString);
		elementoRespuesta.appendChild(elementoSaludo);
		elementoSaludo.appendChild(textoRespuesta);
		
		return elementoRespuesta;
	}

	private String invocaServicioRetornaRespuesta(String solicitudString) {
		
		// invoca el servicio pasandole el nombre y retorna el saludo
		String saludoString = saludarService.getSaludo(solicitudString);
		
		return saludoString;
	}

	private String buscarStringSolicitud(Element requestElement) {
		
		// recupera el primer elemento llamado "nombre"
		Element elementoNombre = (Element) requestElement.getElementsByTagNameNS(NAMESPACE, "nombre").item(0);
		// obtiene el texto contenido
		String solicitudString = elementoNombre.getTextContent();
		
		return solicitudString;
	}
	
}
