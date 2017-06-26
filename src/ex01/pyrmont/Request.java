package ex01.pyrmont;

import java.io.IOException;
import java.io.InputStream;

public class Request {
	private InputStream in;
	private String uri;

	public Request(InputStream in) {
		this.in = in;
	}

	public void parse() {
		StringBuilder request = new StringBuilder();
		int i;
		byte[] buffer = new byte[1024];
		try {
			i = in.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			i = -1;
		}
		for (int j = 0; j < i; j++) {
			request.append((char) buffer[j]);
		}
		System.out.println("requestString:" + request.toString());
		uri = parseUri(request.toString());
	}

	private String parseUri(String requestString) {
		int index1, index2;
		index1 = requestString.indexOf(' ');// 请求方法 uri 协议/版本
		if (index1 != -1) {
			index2 = requestString.indexOf(' ', index1 + 1);// 从index1索引开始找出第二个分隔符
			if (index2 > index1) {
				return requestString.substring(index1 + 1, index2);
			}
		}
		return null;
	}

	public String getUri() {
		return uri;
	}
}
