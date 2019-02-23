package cn.tedu.store.service.ex;

/**
 * 用户名冲突异常
 * @author Administrator
 *
 */
public class UserConflictException extends ServiceException {
	private static final long serialVersionUID = 1L;

	public UserConflictException() {
		// TODO Auto-generated constructor stub
	}

	public UserConflictException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserConflictException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserConflictException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserConflictException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
