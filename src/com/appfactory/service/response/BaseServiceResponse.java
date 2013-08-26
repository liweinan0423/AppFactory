package com.appfactory.service.response;


/**
 * Base class for a  {@link ServiceResponse} 
 */
public class BaseServiceResponse implements ServiceResponse {
	
	private String errorCode;
	private String description;

	/**
	 * @return errorCode, 0 - success
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return Success or description of error message
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return " [errorCode=" + errorCode + ", description="
				+ description + "]";
	}
}
