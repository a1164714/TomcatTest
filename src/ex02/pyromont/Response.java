package ex02.pyromont;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class Response implements ServletResponse {
	private static final int BUFFER_SIZE = 1024;
	private OutputStream out;
	private Request request;
	private PrintWriter writer;

	public Response(OutputStream out) {
		this.out = out;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public void sendStaticResource() {
		byte[] bs = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		System.out
				.println("staticUri:" + Constants.WEB_ROOT + request.getUri());
		File file = new File(Constants.WEB_ROOT, request.getUri());
		try {
			if (file.exists()) {
				fis = new FileInputStream(file);
				int len = -1;
				while ((len = fis.read(bs, 0, BUFFER_SIZE)) != -1) {
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

	/** implement method **/
	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// 第二个参数传入true表示对println方法任何调用都会刷新输出，print则不会，所以到时需要修复这个bug
		writer = new PrintWriter(out, true);
		return writer;
	}

	@Override
	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetBuffer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBufferSize(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCharacterEncoding(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContentLength(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContentLengthLong(long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContentType(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLocale(Locale arg0) {
		// TODO Auto-generated method stub

	}
}
