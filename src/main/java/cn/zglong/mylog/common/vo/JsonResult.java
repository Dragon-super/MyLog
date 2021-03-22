package cn.zglong.mylog.common.vo;

/**
 * 	封装控制层返回到客户端的数据
 * 	state 1表示正常数据2表示异常数据
 * @author 20181128
 *
 */
public class JsonResult {
	/** 我们自己的服务端向客户端输出的状态码 */
	// 1:trus 0:false
	private int state = 1;
	/** 状态码state对应的信息 */
	private String message;
	/** 业务层返回给控制层的具体信息 */
	private Object data;

	public JsonResult() {
		super();
	}

	public JsonResult(Object data) {
		super();
		this.data = data;
	}

	public JsonResult(int state) {
		super();
		this.state = state;
	}

	public JsonResult(int state,String message){
		this.state = state;
		this.message = message;

	}
	public JsonResult(String message) {
		super();
		this.message = message;
	}

	public JsonResult(int state, String message, Object data) {
		super();
		this.state = state;
		this.message = message;
		this.data = data;
	}

	public JsonResult(Throwable e) {
		this.state = 0;
		this.message = e.getMessage();
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/*@Override
	public String toString() {
		return "JsonResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}*/

}

