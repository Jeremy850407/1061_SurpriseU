package u.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.google.gson.Gson;

import u.model.LoginVerification;


//@WebServlet("/LoginVerificationServlet")
public class LoginVerificationServlet extends HttpServlet {
	public LoginVerificationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String state = request.getParameter("state");
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		Gson gson = new Gson();
		HashMap loginResult = new HashMap();	// 登入結果

		DataSource datasource = (DataSource) getServletContext().getAttribute("db");

		LoginVerification login = new LoginVerification();
		
		// 登入
		if(state.equals("login")){
			System.out.println("LoginVerification state : " + state + "account : " + account + "password : " + password);

			loginResult = login.verification(datasource, account, password);
	
			// 回傳json型態
			System.out.println("loginResult : " + loginResult);
			response.getWriter().write(gson.toJson(loginResult));
		}
		
	}
}
