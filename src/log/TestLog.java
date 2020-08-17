package log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

public class TestLog {
	//싱글톤 패턴
	public static TestLog session = new TestLog();
	
	//세션 가져오기
	public static TestLog getSession() {
		return session;
	}
	public void logging(HttpServletRequest request) {
		
		//파일 패스 생성
		File file = new File("C:\\ExpertJava/logs/wepLog.csv");
		
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
		   
		    params += name + "=" + value + "&";
		}
		
		//파람이 없으면 널 처리
		if(params.equals("")) {
			params=null;
		}
		
		
		//파라미터 받기
		String member_id = (String) request.getSession().getAttribute("member_id");
		String nickname = (String)request.getSession().getAttribute("nickname");
		String gender = (String) request.getSession().getAttribute("gender");
		
		//컬럼명 쓰기 여부
		Boolean writColumnName = false;
		
		try {		
			
			//컬럼 name을 명시하기 위한 체크
			if(!file.isFile()) {
				writColumnName=true;
			}
			
			//버퍼 셋팅
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			
			//컬럼 name 작성(1회)
			if(writColumnName) {
				bw.write("date,member_id,nickname,gender,screen,action,params");
				bw.newLine();
			}
				
			//파일이 쓰기 가능한 상태면
			if(file.isFile() && file.canWrite()) {
				bw.write(		
							  date 			+ ","
							+ member_id 	+ ","
							+ nickname 		+ ","
							+ gender 		+ ","
							+ screen 		+ ","
							+ action 		+ ","
							+ params 		+ ","
				);
				
				bw.newLine();
				bw.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}