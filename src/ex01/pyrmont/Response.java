package ex01.pyrmont;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {
	private static final int BUFFER_SIZE = 1024;
	private OutputStream out;
	private Request request;

	public Response(OutputStream out) {
		this.out = out;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void sendStaticResource() {
		byte[] bs = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		System.out.println("staticUri:"+HttpServer.WEB_ROOT+ request.getUri());
		File file = new File(HttpServer.WEB_ROOT,request.getUri());
		try {
			if (file.exists()) {
				fis = new FileInputStream(file);
				int len = -1;
				while ((len = fis.read(bs,0,BUFFER_SIZE)) != -1) {
					out.write(bs, 0, len);
				}
			} else {
				String errorMessage = "HTTP/1.1 404 File Not Found\r\n"
						+ "Content-Type: text/html\r\n"
						+ "Content-Length: 23\r\n" + "\r\n"
						+ "<h1>File Not Found</h1>";
				out.write(errorMessage.getBytes());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
