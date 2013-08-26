package com.appfactory.service.request;

import android.content.Context;

/**
 * A base interface for a service request to servers.
 */
public interface ServiceRequest {
	/**
	 * Gets the Json form of the request
	 * 
	 * @return The Json form of the request
	 */
	String getJson();

	/**
	 * Gets the string representation of the request in a given {@link Context}.
	 * 
	 * @return The string representation of the request in a given
	 *         {@link Context}.
	 */
	String toString();

}
