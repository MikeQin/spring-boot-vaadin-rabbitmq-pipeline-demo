package app;

public final class Constants {
	
	// Exchange
	public static final String X_TOPIC_LOGS = "x.topic.logs";
	// Queues
	public static final String Q_LOGS_INFO = "q.logs.info";
	public static final String Q_LOGS_WARNING = "q.logs.warning";
	public static final String Q_LOGS_ERROR = "q.logs.error";
	
	// Views
	public static final String VIEW_DEFAULT = "";
	public static final String VIEW_STREAM = "stream";
	public static final String H2_DB = "console";
	public static final String REST_API = "events";
	
	public static final String INFO = "INFO";
	public static final String WARNING = "WARNING";
	public static final String ERROR = "ERROR";

	private Constants() {}
}
