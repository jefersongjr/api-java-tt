package apijavatt.error;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date timeStamp;
	private String message;

	public ExceptionResponse(Date timeStamp, String message, String title) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getMessage() {
		return message;
	}

}
