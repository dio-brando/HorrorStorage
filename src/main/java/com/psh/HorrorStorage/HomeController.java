package com.psh.HorrorStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
	
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		Connection conn = null;
		Statement state = null;
		ResultSet set = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "psh";
		String pwd = "1111";
		String sql = "select * from contents";
		// String sql = "select * from test";
		// String sql = "select count(idx) from contents";
//     int size = template.query(sql, new BeanPropertyRowMapper()).size();
     List<DTOcontent> array = template.query(sql, new BeanPropertyRowMapper<DTOcontent>(DTOcontent.class));
     for( DTOcontent str :array) {
     	logger.info( "strig : " + str.getTitle());
     	model.addAttribute("title", str.getTitle());
     }
//     logger.info("열길이 : " + size);
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
			state = conn.createStatement();
			set = state.executeQuery(sql);
			ResultSetMetaData meta = set.getMetaData();
			logger.info("열길이 : " + set.getRow());
			logger.info("컬럼길이 : " + meta.getColumnCount());

			while (set.next()) {
				// String title = set.ge(0);
				// System.out.println("제목 : " + title);
				// logger.info("제목 : " + title);
				// model.addAttribute("title", title);
			}
		} catch (Exception e) {
			logger.info("에러 : " + e.getMessage());
			System.out.println("에러 : " + e.getMessage());
		} finally {
			try {
				set.close();
				state.close();
				conn.close();
			} catch (Exception e) {

			}

		}

		return "home";
	}

}
