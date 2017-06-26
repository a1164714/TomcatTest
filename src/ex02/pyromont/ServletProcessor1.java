package ex02.pyromont;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class ServletProcessor1 {

	public void process(Request request, Response response) {
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf("/") + 1);
		/**
		 * 三个构造函数
		 * 在Servlet容器中查找Servlet类的目录称为仓库（repository）
		 * 1. URLClassLoader(URL[] urls) urls表明要在哪里查找类，"/"结尾为目录，默认指向Jar文件
		 * (1) URL(URL context,String spec,URLStreamHandler handler)
		 * (2) URL(String protocol,String host,String file) 
		 */
		URLClassLoader loader = null;
		try {
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPath = new File(Constants.WEB_ROOT);
			String repository = (new URL("file", null,
					classPath.getCanonicalPath() + File.separator)).toString();
			System.out.println("repository:" + repository);
			urls[0] = new URL(null, repository, streamHandler);
			loader = new URLClassLoader(urls);// 获取类加载器
		} catch (IOException e) {
			e.printStackTrace();
		}
		Class servletClass = null;
		try {
			servletClass = loader.loadClass(servletName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Servlet servlet = null;
		try {
			servlet = (Servlet) servletClass.newInstance();
			servlet.service((ServletRequest) request,
					(ServletResponse) response);
		} catch (InstantiationException | IllegalAccessException
				| ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
