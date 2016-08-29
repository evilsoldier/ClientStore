package filters;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.annotation.Priority;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

import org.hibernate.engine.spi.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scalefocus.edu.db.model.Adress;
import com.scalefocus.edu.db.model.Client;
import com.scalefocus.edu.service.ClientStoreService;

@Component
@Provider
@Priority(value = 1)
@Path("/*")
public class ServerResponseFilter implements ContainerResponseFilter {

	@Context
	UriInfo uri;

	@Autowired
	ClientStoreService clientstoreservice;

	public ServerResponseFilter() {
		System.out.println("ServerResponseFilter initialization");
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws UnsupportedEncodingException {
		System.out.println("Response filter");
		String hash = "";
		CacheControl cc = new CacheControl();
		cc.setMaxAge(10);
		System.out.println("request headers -->" + requestContext.getHeaders().keySet());
		System.out.println("request type --> " + requestContext.getRequest().getMethod());

		// address
		if (responseContext.getEntity().getClass().equals(Adress.class)) {
			System.out.println("address");
			Adress adress = (Adress) responseContext.getEntity();
			try {
				hash = DatatypeConverter.printHexBinary(
						MessageDigest.getInstance("MD5").digest(String.valueOf(adress.hashCode()).getBytes("UTF-8")));
			} catch (NoSuchAlgorithmException e) {
				System.out.println("stroshi se");
			}
			MultivaluedMap<String, Object> headers = responseContext.getHeaders();
			if (!requestContext.getRequest().getMethod().equals("DELETE")) {
				UriBuilder ub = uri.getAbsolutePathBuilder();
				URI uri = ub.build(adress.getCLient().getId());
				ResponseBuilder responseBuilder = requestContext.getRequest()
						.evaluatePreconditions(new EntityTag(hash));
				if (responseBuilder == null) {
					headers.add(HttpHeaders.CACHE_CONTROL, cc);
					headers.add(HttpHeaders.ETAG, new EntityTag(hash));
					System.out.println("entity removed :)");
				} else {
					headers.add(HttpHeaders.ETAG, new EntityTag(hash));
					headers.add(HttpHeaders.CACHE_CONTROL, cc);
					headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
					System.out.println("else");
				}
			}
		}

		// List<Adress>
		if (responseContext.getEntity().getClass().toString()
				.equals("class org.hibernate.collection.internal.PersistentBag")) {
			System.out.println("addresses");
			Object allAdresses = responseContext.getEntity();
			try {
				hash = DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5")
						.digest(String.valueOf(allAdresses.hashCode()).getBytes("UTF-8")));
			} catch (NoSuchAlgorithmException e) {
				System.out.println("stroshi se");
			}
			MultivaluedMap<String, Object> headers = responseContext.getHeaders();
			headers.add(HttpHeaders.CACHE_CONTROL, cc);
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		}

		// Client
		if (responseContext.getEntity().getClass().equals(Client.class)) {

			System.out.println("client");
			Client client = (Client) responseContext.getEntity();
			try {
				hash = DatatypeConverter.printHexBinary(
						MessageDigest.getInstance("MD5").digest(String.valueOf(client.hashCode()).getBytes("UTF-8")));
			} catch (NoSuchAlgorithmException e) {
				System.out.println("stroshi se");
			}
			UriBuilder ub = uri.getAbsolutePathBuilder();
			URI uri = ub.build(client);
			ResponseBuilder responseBuilder = requestContext.getRequest().evaluatePreconditions(new EntityTag(hash));
			if (!requestContext.getRequest().getMethod().equals("DELETE")) {
				MultivaluedMap<String, Object> headers = responseContext.getHeaders();
				if (!responseContext.getHeaders().containsKey("Location")) {
					headers.add(HttpHeaders.LOCATION, uri);
				}
				if (responseBuilder == null) {
					headers.add(HttpHeaders.CACHE_CONTROL, cc);
					headers.add(HttpHeaders.ETAG, new EntityTag(hash));
					System.out.println("entity removed :)");
				} else {
					headers.add(HttpHeaders.ETAG, new EntityTag(hash));
					headers.add(HttpHeaders.CACHE_CONTROL, cc);
					headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
					System.out.println("else");
				}
			}
		}

		// List<Client>
		if (responseContext.getEntity().getClass().equals(ArrayList.class)) {
			System.out.println("clients");
			ArrayList<Client> allClients = (ArrayList<Client>) responseContext.getEntity();
			UriBuilder ub = uri.getAbsolutePathBuilder();
			URI uri = ub.build(allClients);
			try {
				hash = DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5")
						.digest(String.valueOf(allClients.hashCode()).getBytes("UTF-8")));
			} catch (NoSuchAlgorithmException e) {
				System.out.println("stroshi se");
			}
			MultivaluedMap<String, Object> headers = responseContext.getHeaders();
			headers.add(HttpHeaders.LOCATION, uri);
			ResponseBuilder responseBuilder = requestContext.getRequest().evaluatePreconditions(new EntityTag(hash));
			if (responseBuilder == null) {
				headers.add(HttpHeaders.CACHE_CONTROL, cc);
				headers.add(HttpHeaders.ETAG, new EntityTag(hash));
			} else {
				headers.add(HttpHeaders.ETAG, new EntityTag(hash));
				headers.add(HttpHeaders.CACHE_CONTROL, cc);
				headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
				System.out.println("else");
			}
		}
	}
}