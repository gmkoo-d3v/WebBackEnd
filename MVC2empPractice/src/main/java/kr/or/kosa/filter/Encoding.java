package kr.or.kosa.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

@WebFilter(
	    description = "어노테이션 활용 필터 적용하기",
	    urlPatterns = {"/*"},
	    initParams = {@WebInitParam(name="encoding" , value="UTF-8")}
	  )
public class Encoding extends HttpFilter implements Filter {
    // 개발자가 만드는 코드
	private String encoding;

 public Encoding() {
     super();
     // TODO Auto-generated constructor stub
 }

	public void init(FilterConfig fConfig) throws ServletException {
		this.encoding = fConfig.getInitParameter("encoding");
		
	}
 
 
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// AOP 구현한것 관심 끼리 모아라
		if(request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(this.encoding);
		}
		System.out.println("web에 접근시 urlPatterns = {}, 통과 ");
		chain.doFilter(request, response);
		// 이걸로 모든 페이지 한글처리가 됨 그러니 서비스단에서 setCharacterEncoding 할필요가 없다.
	}



}
