package log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestLog {

	//싱글톤 패턴
	public static TestLog session = new TestLog();
	
	//세션 가져오기
	public static TestLog getSession() {
		return session;
	}

	public void logging(HttpServletRequest request) {
		
		//파일 패스 생성
		File file = new File("C:\\ExpertJava/logs/wepLog.log");
		
		//현재 날짜 받기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
		String date = sdf.format(Calendar.getInstance().getTime());
		
		//이전 경로 확인하기
		String []oldPath = request.getHeader("referer").split("/");
		String []oldCommend = oldPath[4].split("[?]");
		String screen =  oldCommend[0].replace(".do", "");

		//현재 정보 확인하기
		String []Path = request.getRequestURI().split("/");	
		String action = Path[2];

		//파라미터 얻기
		String params = "";	
		Enumeration<String> test = request.getParameterNames();
		while (test.hasMoreElements()) {
		    String name = (String) test.nextElement();
		    String value = request.getParameter(name);
		    
		    params += "{ " + name + ":" + value + "}, ";

		}
		
		//파라미터 받기
		String member_id = (String) request.getSession().getAttribute("member_id");
		String nickname = (String)request.getSession().getAttribute("nickname");
		String gender = (String) request.getSession().getAttribute("gender");
		
		try {		
			//버퍼 셋팅
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			//파일이 쓰기 가능한 상태면
			if(file.isFile() && file.canWrite()) {
				bw.write(
							"{ "
						+ 		"\"date\":\"" 			+ date 			+ "\", "
						+ 		"\"member_id\":\"" 		+ member_id		+ "\", "
						+ 		"\"nickname\":\"" 		+ nickname		+ "\", "
						+ 		"\"gender\":\"" 		+ gender	 	+ "\", "
						+ 		"\"screen\":\"" 		+ screen		+ "\", "
						+ 		"\"action\":\"" 		+ action 		+ "\", "	
						+ 		"\"params\":\"" 		+ params 		+ "\", "
						+ 	" },"	
				);
				
				bw.newLine();
				bw.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
