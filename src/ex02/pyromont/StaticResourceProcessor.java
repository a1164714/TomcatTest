package ex02.pyromont;


public class StaticResourceProcessor {
	public void process(Request request, Response response) {
		response.sendStaticResource();
	}
}
