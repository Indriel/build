package database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import data.Category;
import data.DayOfWeek;
import data.District;
import data.MitarbeiterMonat;
import data.MitarbeiterTag;
import data.Mitarbeitertaetigkeit;
import data.Month;
import data.User;
import data.WorkType;
import data.ZusammenfassungDaten;

public class MySQL {

	private static MySQL mySQL = null;
	private Connection con = null;
	private Statement stmt = null;

	public static MySQL getInstance() {
		if (mySQL == null) {
			mySQL = new MySQL();
			mySQL.connect();
		}
		return mySQL;
	}

	private MySQL() {
	}

	private void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/build_new";

			con = DriverManager.getConnection(url, "root", "");
			stmt = con.createStatement();

		} catch (Exception e) {
			System.out.println("Exception connect(): " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User login(String user, String password) throws SQLException {
		// returns null if the login was unsuccessful
		User retValue = null;
		this.connect();
		String checkUserExistence = "select count(*) from mitarbeiter where name = ? and pwd = ? and ang_bis is null";
		String getUser = "select id, name from mitarbeiter where name=? and pwd = ?";
		PreparedStatement pps = this.con.prepareStatement(checkUserExistence);
		pps.setString(1, user);
		pps.setString(2, password);
		ResultSet rs = pps.executeQuery();
		rs.next();
		if (rs.getInt(1) != 0) {
			pps = this.con.prepareStatement(getUser);
			pps.setString(1, user);
			pps.setString(2, password);
			rs = pps.executeQuery();
			rs.next();
			retValue = new User(rs.getInt(1), rs.getString(2));
		}

		return retValue;
	}

	public Vector<WorkType> getWorkType() {

		Vector<WorkType> tmp = new Vector<WorkType>();

		try {
			ResultSet rs = stmt.executeQuery("select * from taetigkeit");
			while (rs.next()) {
				tmp.add(new WorkType(rs.getInt("id"), rs
						.getString("bezeichnung")));
			}

		} catch (Exception e) {
			System.out.println("Exception getWorkType() " + e.getMessage());
		}

		return tmp;
	}

	public Vector<User> getUsers() throws SQLException {
		Vector<User> users = new Vector<User>();

		PreparedStatement pps = this.con
				.prepareStatement("select id,name from mitarbeiter");
		ResultSet rs = pps.executeQuery();
		while (rs.next()) {
			users.add(new User(rs.getInt(1), rs.getString(2)));
		}

		return users;
	}

	public boolean setPassword(int id, char[] password) {
		String pwd = new String(password);
		try {
			PreparedStatement pstmt = con
					.prepareStatement("update mitarbeiter set pwd = ? where id = ?");
			pstmt.setString(1, pwd);
			pstmt.setInt(2, id);
			pstmt.execute();
		} catch (Exception e) {
			System.out
					.println("Exception setPassword(int id, char[] password): "
							+ e.getMessage());
			return false;
		}

		return true;
	}

	public int newEmployee(String name, String pwd, Date d) throws SQLException {
		this.connect();
		
		int id = this.getMaxUserId();
		java.sql.Date ang_von = new java.sql.Date(d.getTime());
		java.sql.Date ang_bis = null;
		Time standvon = new Time(7, 30, 0);
		Time standbis = new Time(16,0,0);
		int pause = 30;
		int sollstd = 8;
		
		String ins_mitarbeiter = "insert into mitarbeiter(id,name,pwd,ang_von,ang_bis,stand_von,stand_bis,stand_pause) values(?,?,?,?,?,?,?,?)";
		String ins_sollStd = "insert into m_sollstunden(m_id,sollst_tag,datum) values(?,?,?)";
		
		PreparedStatement pps = this.con.prepareStatement(ins_mitarbeiter);
		pps.setInt(1, id);
		pps.setString(2, name);
		pps.setString(3, pwd);
		pps.setDate(4, ang_von);
		pps.setDate(5, ang_bis);
		pps.setTime(6, standvon);
		pps.setTime(7, standbis);
		pps.setInt(8, pause);
		
		pps.executeUpdate();
		
		pps = this.con.prepareStatement(ins_sollStd);
		pps.setInt(1, id);
		pps.setInt(2, sollstd);
		pps.setDate(3, ang_von);
		
		return pps.executeUpdate();
	}

	private int getMaxUserId() throws SQLException {
		this.connect();
		String stm = "select max(id) from mitarbeiter";
		PreparedStatement pps = this.con.prepareStatement(stm);
		ResultSet rs = pps.executeQuery();
		rs.next();
		return rs.getInt(1) + 1;
	}

	public Vector<Category> getCategories(int id) {
		Vector<Category> categories = new Vector<Category>();

		Statement stmt;
		ResultSet rs;
		Category category;

		try {
			stmt = con.createStatement();
			stmt.execute("select * from kategorie where b_id = " + id);
			rs = stmt.getResultSet();

			while (rs.next()) {
				String name = rs.getString("bezeichnung");
				int c_id = rs.getInt("id");
				category = new Category(c_id, name);
				categories.addElement(category);
			}
		}

		catch (SQLException sqle) {
			System.out.println("1"+sqle.getMessage());
		}

		return categories;
	}

	public Vector<District> getDistricts() {
		Vector<District> districts = new Vector<District>();

		Statement stmt;
		ResultSet rs;
		District district;

		try {
			stmt = con.createStatement();
			stmt.execute("select * from bereich");
			rs = stmt.getResultSet();

			while (rs.next()) {
				String name = rs.getString("bezeichnung");
				int id = rs.getInt("id");
				district = new District(id, name);
				districts.addElement(district);
			}
		}

		catch (SQLException sqle) {
			System.out.println("2"+sqle.getMessage());
		}

		return districts;
	}

	/*
	 * public Vector<User> getUsers() {
	 * 
	 * Vector<User> tmp = new Vector<User>();
	 * 
	 * try { ResultSet rs = stmt.executeQuery("select * from mitarbeiter");
	 * while (rs.next()) { tmp.add(new User(rs.getInt("id"),
	 * rs.getString("name"))); }
	 * 
	 * } catch (Exception e) { System.out.println("Exception getUsers() " +
	 * e.getMessage()); }
	 * 
	 * return tmp; }
	 */

	public boolean writeEmployeeDay(int m_id, Date datum, int fromMinute,
			int fromHour, int toMinute, int toHour, int pause, int t_id) {
		String tagesdatum;
		String monat;
		String von;
		String bis;
		SimpleDateFormat df;
		float pauseStunden = pause;
		int sollstd;
		float ueberstunden;

		df = new SimpleDateFormat("yyyy-MM-dd");
		df.setTimeZone(TimeZone.getDefault());
		tagesdatum = df.format(datum);

		df = new SimpleDateFormat("yyyy-MM");
		monat = df.format(datum);

		try {
			ResultSet rs = stmt
					.executeQuery("select sollst_tag from m_sollstunden where m_id = "
							+ m_id);
			rs.next();
			sollstd = rs.getInt("sollst_tag");
		} catch (Exception e) {
			System.out.println("Fatal error: sollstd_tag not available! "
					+ e.getMessage());
			return false;
		}

		if (t_id == 1) {
			von = tagesdatum + " " + fromHour + ":" + fromMinute + ":00";
			bis = tagesdatum + " " + toHour + ":" + toMinute + ":00";

			pauseStunden /= 60;

			ueberstunden = (float) (((toHour * 60 + toMinute) / 60.0
					- (fromHour * 60 + fromMinute) / 60.0 - pauseStunden) - sollstd);
		}

		else if (t_id == 3 || t_id == 4 || t_id == 5) {
			von = "1111-11-11 00:00:00";
			bis = "1111-11-11 00:00:00";
			pauseStunden = 0;
			pauseStunden /= 60;
			ueberstunden = 0;
		}

		else {
			von = "1111-11-11 00:00:00";
			bis = "1111-11-11 00:00:00";
			pauseStunden = 0;
			pauseStunden /= 60;
			ueberstunden = -sollstd;
		}
		try {
			ResultSet rs = stmt
					.executeQuery("select count(*) from mitarb_monat where id="
							+ m_id + " and datum ='" + monat + "-01'");
			rs.next();
			if (rs.getInt(1) != 0) {
				ResultSet rs1 = stmt
						.executeQuery("select uebertrag from mitarb_monat where id="
								+ m_id + " and datum ='" + monat + "-01'");
				rs1.next();
				BigDecimal bd = rs1.getBigDecimal("uebertrag");
				float uebertrag = bd.floatValue();
				uebertrag = uebertrag + ueberstunden;

				stmt.execute("update mitarb_monat set uebertrag = " + uebertrag
						+ " where id=" + m_id + " and datum ='" + monat
						+ "-01'");
			}

			else {
				stmt.execute("insert into mitarb_monat values('" + monat
						+ "-01', " + m_id + "," + ueberstunden + ")");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement insertStmt;
		String insertString = "INSERT INTO mitarb_tag VALUES (?, ?, ?, ?, ?, ?, ?, ? )";

		try {
			insertStmt = con.prepareStatement(insertString);
			insertStmt.setInt(1, m_id);
			insertStmt.setString(2, monat + "-01");
			insertStmt.setString(3, tagesdatum);
			insertStmt.setString(4, von);
			insertStmt.setString(5, bis);
			insertStmt.setInt(6, t_id);
			insertStmt.setFloat(7, pauseStunden * 60);
			insertStmt.setFloat(8, ueberstunden*60);
			System.out.println(ueberstunden);
			insertStmt.execute();
		} catch (SQLException sqle) {
			System.out.println("4" + sqle.getMessage());
			return false;
		}

		return true;
	}

	public void writeEmployeeActivity(int m_id, Date datum, int[] k_id,
			Time[] dauerVon, String[] text) {

		int activityCount = k_id.length;

		SimpleDateFormat df;
		String tagesdatum;
		String monat;

		df = new SimpleDateFormat("yyyy-MM-dd");
		df.setTimeZone(TimeZone.getDefault());
		tagesdatum = df.format(datum);

		df = new SimpleDateFormat("yyyy-MM");
		monat = df.format(datum);

		for (int i = 0; i < activityCount; i++) {

			PreparedStatement insertStmt;
			String insertString = "INSERT INTO mitarb_taetigkt(m_id,datum,tagesdatum,dauer,b_id,text) VALUES (?, ?, ?, ?, ? ,?)";

			// float dauerStunden = dauer[i];
			// dauerStunden /= 60;

			try {
				insertStmt = con.prepareStatement(insertString);
				System.out.println(m_id);
				insertStmt.setInt(1, m_id);
				insertStmt.setString(2, monat + "-01");
				insertStmt.setString(3, tagesdatum);
				insertStmt.setInt(4, (dauerVon[i].getHours()*60+dauerVon[i].getMinutes()));
				insertStmt.setInt(5, k_id[i]);
				insertStmt.setString(6, text[i]);
				insertStmt.execute();

			} catch (SQLException sqle) {
				System.out.println("3"+sqle.getMessage());
			}
		}

	}

	public Vector<Month> getMonthByUser(int m_id) {

		Vector<Month> tmp = new Vector<Month>();

		try {
			ResultSet rs = stmt
					.executeQuery("select distinct datum from mitarb_tag where id = "
							+ m_id);
			while (rs.next()) {
				Month month = new Month(rs.getString("datum"));
				tmp.addElement(month);
			}

		} catch (Exception e) {
			System.out.println("Exception getMonthByUser() " + e.getMessage());
		}
		return tmp;
	}

	public Vector<Month> getMonthByAllUsers() {

		Vector<Month> tmp = new Vector<Month>();

		try {
			ResultSet rs = stmt
					.executeQuery("select distinct datum from mitarb_tag");
			while (rs.next()) {
				Month month = new Month(rs.getString("datum"));
				tmp.addElement(month);
			}

		} catch (Exception e) {
			System.out.println("Exception getMonthByUser() " + e.getMessage());
		}
		return tmp;
	}

	public void getMitarb_taetigkt(int m_id, String month) {
		System.out.println(m_id + " " + month);
		System.out.println("----------------");
		try {
			ResultSet rs = stmt
					.executeQuery("select * from mitarb_taetigkt where m_id = "
							+ m_id + " and date_format(datum,'%Y-%m-%d') = '"
							+ month + "'");
			while (rs.next()) {
				System.out.println(rs.getString("tagesdatum") + " "
						+ rs.getInt("m_id"));
			}

		} catch (Exception e) {
			System.out.println("getMitarb_taetigkt(int m_id, String month) "
					+ e.getMessage());
		}
	}

	public Vector<Vector<String>> getMitarb_tag(User u, String month) {
		int m_id = u.getId();
		Vector<Vector<String>> tmp = new Vector<Vector<String>>();

		try {

			HashMap<Integer, String> hm = new HashMap<Integer, String>();
			ResultSet rsTaetigkeit = stmt
					.executeQuery("select * from taetigkeit");
			while (rsTaetigkeit.next()) {
				hm.put(rsTaetigkeit.getInt("id"),
						rsTaetigkeit.getString("bezeichnung"));
			}

			Vector<String> headLine = new Vector<String>();
			headLine.add(u.getName());
			String mon[] = month.split("-");
			headLine.add(mon[0] + "-" + mon[1]);
			tmp.add(headLine);

			headLine = new Vector<String>();
			headLine.add("Datum");
			headLine.add("Tag");
			headLine.add("Arbeitsbeginn");
			headLine.add("Arbeitsende");
			headLine.add("Pause");
			headLine.add("Ist Arbeitsstunden");
			headLine.add("Ueberstunden");
			headLine.add("Zeitausgleich");
			headLine.add("Anmerkung");
			tmp.add(headLine);

			ResultSet rs = stmt
					.executeQuery("select * from mitarb_tag where id = " + m_id
							+ " and date_format(datum,'%Y-%m-%d') = '" + month
							+ "'");
			while (rs.next()) {
				Vector<String> line = new Vector<String>();

				// 1, 2, 3
				String partDay[] = rs.getString("tagesdatum").split("-");
				line.add(partDay[2]);

				// Mo, Di, Mi, ...
				DayOfWeek dow = new DayOfWeek(rs.getString("tagesdatum"));
				line.add(dow.getDayOfWeek());

				// Begin
				String partBegin[] = rs.getString("von").split(" ");
				String partBeginTime[] = partBegin[1].split(":");
				line.add(partBeginTime[0] + ":" + partBeginTime[1]);

				// End
				String partEnd[] = rs.getString("bis").split(" ");
				String partEndTime[] = partEnd[1].split(":");
				line.add(partEndTime[0] + ":" + partEndTime[1]);

				// Pause
				BigDecimal bd = rs.getBigDecimal("pause");
				float f = bd.floatValue();
				f *= 60;
				int pauseMin = (int) f;
				int pauseH = pauseMin / 60;
				pauseMin = pauseMin - (pauseH * 60);
				line.add(this.convertTime(pauseH + ":" + pauseMin));

				// Ist
				bd = rs.getBigDecimal("pause");
				f = bd.floatValue();
				f *= 60;
				pauseMin = (int) f;

				int begin = Integer.parseInt(partBeginTime[0]) * 60
						+ Integer.parseInt(partBeginTime[1]);
				int end = Integer.parseInt(partEndTime[0]) * 60
						+ Integer.parseInt(partEndTime[1]);
				int timeMin = (end - begin) - pauseMin;
				int timeH = timeMin / 60;
				timeMin = timeMin - (timeH * 60);

				line.add(this.convertTime(timeH + ":" + timeMin));

				// Überstunden Zeitausgleich
				bd = rs.getBigDecimal("ueberstunden");
				f = bd.floatValue();
				f *= 60;
				int ueberstundenMin = (int) f;
				if (ueberstundenMin < 0) {
					ueberstundenMin *= -1;
					int ueberstundenH = ueberstundenMin / 60;
					ueberstundenMin = ueberstundenMin - (ueberstundenH * 60);
					line.add("");
					line.add(this.convertTime(ueberstundenH + ":"
							+ ueberstundenMin));
					// System.out.println("zeitausgleich: " + ueberstundenH +
					// ":" + ueberstundenMin);
				} else {
					int ueberstundenH = ueberstundenMin / 60;
					ueberstundenMin = ueberstundenMin - (ueberstundenH * 60);
					line.add(this.convertTime(ueberstundenH + ":"
							+ ueberstundenMin));
					line.add("");
					// System.out.println("überstunden: " + ueberstundenH + ":"
					// + ueberstundenMin);
				}

				// Anmerkung
				line.add(hm.get(rs.getInt("t_id")));
				tmp.add(line);
			}
			/**
			 * Berechnung Zeile Gesamt im aktuellen Monat
			 */
			Vector<String> line = new Vector<String>();
			for (int i = 0; i < 5; i++)
				line.add("");
			line.add("Gesamt im Monat:");

			rs = stmt.executeQuery("select * from mitarb_monat where id = "
					+ m_id + " and date_format(datum,'%Y-%m-%d') = '" + month
					+ "'");
			if (rs.next()) {
				BigDecimal bd = rs.getBigDecimal("uebertrag");
				float f = bd.floatValue();
				int min = (int) (f * 60);
				int h = min / 60;
				min = Math.abs(min - (h * 60));

				if (f >= 0) {
					line.add(this.convertTime(h + ":" + min));
					line.add("");
				} else {
					line.add("");
					line.add(this.convertTime(h + ":" + min));
				}

			}
			line.add("");
			tmp.add(line);
			/**
			 * Berechnung Zeile Gesamt inkl. aller Monate vor dem aktuellen
			 * Monat
			 */
			line = new Vector<String>();
			for (int i = 0; i < 5; i++)
				line.add("");
			line.add("Gesamt bis jetzt:");

			rs = stmt.executeQuery("select * from mitarb_monat where id = "
					+ m_id + " and date_format(datum,'%Y-%m-%d') <= '" + month
					+ "'");
			float f = (float) 0.0;
			while (rs.next()) {
				BigDecimal bd = rs.getBigDecimal("uebertrag");
				f += bd.floatValue();
			}
			int min = (int) (f * 60);
			int h = min / 60;
			min = Math.abs(min - (h * 60));

			if (f >= 0) {
				line.add(this.convertTime(h + ":" + min));
				line.add("");
			} else {
				line.add("");
				line.add(this.convertTime(h + ":" + min));
			}

			line.add("");
			tmp.add(line);
		} catch (Exception e) {
			System.out.println("getMitarb_tag(int m_id, String month) "
					+ e.getMessage());
		}

		return tmp;
	}

	public Vector<Vector<String>> getAllMitarb_tag(String month) {
		Vector<Vector<String>> tmp = new Vector<Vector<String>>();

		try {

			HashMap<Integer, String> hm = new HashMap<Integer, String>();
			ResultSet rsTaetigkeit = stmt
					.executeQuery("select * from taetigkeit");
			while (rsTaetigkeit.next()) {
				hm.put(rsTaetigkeit.getInt("id"),
						rsTaetigkeit.getString("bezeichnung"));
			}

			HashMap<Integer, String> hmUser = new HashMap<Integer, String>();
			ResultSet rsUser = stmt.executeQuery("select * from mitarbeiter");
			while (rsUser.next()) {
				hmUser.put(rsUser.getInt("id"), rsUser.getString("name"));
			}

			Vector<String> headLine = new Vector<String>();
			headLine.add("Alle ");
			String mon[] = month.split("-");
			headLine.add(mon[0] + "-" + mon[1]);
			tmp.add(headLine);

			headLine = new Vector<String>();
			headLine.add("Mitarbeiter");
			headLine.add("Datum");
			headLine.add("Tag");
			headLine.add("Arbeitsbeginn");
			headLine.add("Arbeitsende");
			headLine.add("Pause");
			headLine.add("Ist Arbeitsstunden");
			headLine.add("Überstunden");
			headLine.add("Zeitausgleich");
			headLine.add("Anmerkung");
			tmp.add(headLine);

			ResultSet rs = stmt
					.executeQuery("select * from mitarb_tag where date_format(datum,'%Y-%m-%d') = '"
							+ month + "' order by id, tagesdatum");
			while (rs.next()) {
				Vector<String> line = new Vector<String>();
				// Mitarbeiter
				line.add(hmUser.get(rs.getInt("id")));

				// 1, 2, 3
				String partDay[] = rs.getString("tagesdatum").split("-");
				line.add(partDay[2]);

				// Mo, Di, Mi, ...
				DayOfWeek dow = new DayOfWeek(rs.getString("tagesdatum"));
				line.add(dow.getDayOfWeek());

				// Begin
				String partBegin[] = rs.getString("von").split(" ");
				String partBeginTime[] = partBegin[1].split(":");
				line.add(partBeginTime[0] + ":" + partBeginTime[1]);

				// End
				String partEnd[] = rs.getString("bis").split(" ");
				String partEndTime[] = partEnd[1].split(":");
				line.add(partEndTime[0] + ":" + partEndTime[1]);

				// Pause
				BigDecimal bd = rs.getBigDecimal("pause");
				float f = bd.floatValue();
				f *= 60;
				int pauseMin = (int) f;
				int pauseH = pauseMin / 60;
				pauseMin = pauseMin - (pauseH * 60);
				line.add(this.convertTime(pauseH + ":" + pauseMin));
				// System.out.println(pauseH + ":" + pauseMin);

				// Ist
				bd = rs.getBigDecimal("pause");
				f = bd.floatValue();
				f *= 60;
				pauseMin = (int) f;

				int begin = Integer.parseInt(partBeginTime[0]) * 60
						+ Integer.parseInt(partBeginTime[1]);
				int end = Integer.parseInt(partEndTime[0]) * 60
						+ Integer.parseInt(partEndTime[1]);
				int timeMin = (end - begin) - pauseMin;
				int timeH = timeMin / 60;
				timeMin = timeMin - (timeH * 60);

				line.add(this.convertTime(timeH + ":" + timeMin));
				// line.add(timeH + ":" + timeMin);

				// Überstunden Zeitausgleich
				bd = rs.getBigDecimal("ueberstunden");
				f = bd.floatValue();
				f *= 60;
				int ueberstundenMin = (int) f;
				if (ueberstundenMin < 0) {
					ueberstundenMin *= -1;
					int ueberstundenH = ueberstundenMin / 60;
					ueberstundenMin = ueberstundenMin - (ueberstundenH * 60);
					line.add("");
					line.add(this.convertTime(ueberstundenH + ":"
							+ ueberstundenMin));
					// System.out.println("zeitausgleich: " + ueberstundenH +
					// ":" + ueberstundenMin);
				} else {
					int ueberstundenH = ueberstundenMin / 60;
					ueberstundenMin = ueberstundenMin - (ueberstundenH * 60);
					line.add(this.convertTime(ueberstundenH + ":"
							+ ueberstundenMin));
					line.add("");
					// System.out.println("überstunden: " + ueberstundenH + ":"
					// + ueberstundenMin);
				}

				// Anmerkung
				line.add(hm.get(rs.getInt("t_id")));
				tmp.add(line);
			}

		} catch (Exception e) {
			System.out.println("getMitarb_tag(int m_id, String month) "
					+ e.getMessage());
		}

		return tmp;
	}

	private String convertTime(String t) {
		String tmp;
		String part[] = t.split(":");
		if (part[0].length() == 1)
			tmp = "0" + part[0];
		else
			tmp = part[0];

		if (part[1].length() == 1)
			tmp = tmp + ":0" + part[1];
		else
			tmp = tmp + ":" + part[1];

		return tmp;
	}

	public boolean resetPassword(int m_id, String pwd) {
		try {
			stmt.execute("update mitarbeiter set pwd = '" + pwd
					+ "' where id = " + m_id);
			return true;
		} catch (Exception e) {
			System.out.println("Exception resetPassword(int m_id, String pwd) "
					+ e.getMessage());
		}
		return false;
	}

	public boolean setTermination(int m_id, String end) {
		try {
			stmt.execute("update mitarbeiter set ang_bis = '" + end
					+ "' where id = " + m_id);
			return true;
		} catch (Exception e) {
			System.out.println("setTermination(int m_id, String end) "
					+ e.getMessage());
		}
		return false;
	}

	public boolean newDistrict(int id, String name) {
		try {
			ResultSet rs = stmt
					.executeQuery("select * from bereich where id = " + id);
			boolean x = true;
			while (rs.next()) {
				x = false;
			}

			if (x) {
				try {
					stmt.execute("insert into bereich values(" + id + ",'"
							+ name + "')");
				} catch (Exception e) {
					x = false;
				}
			}
			return x;

		} catch (Exception e) {
			System.out.println("newDistrict(int id, String name) "
					+ e.getMessage());
		}
		return false;
	}

	public boolean newCategory(int category_id, String name, int district_id) {
		try {
			ResultSet rs = stmt
					.executeQuery("select * from kategorie where id = "
							+ category_id + " and b_id = " + district_id);
			boolean x = true;
			while (rs.next()) {
				x = false;
			}

			if (x) {
				stmt.execute("insert into kategorie values(" + category_id
						+ ",'" + name + "'," + district_id + " )");
			}
			return x;

		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("newCategory(int category_id, String name, int district_id) "
							+ e.getMessage());
		}
		return false;
	}

	public void updateComboBoxUser(DefaultComboBoxModel<User> dcbm)
			throws SQLException {
		dcbm.removeAllElements();
		for (Iterator<User> i = getUsers().iterator(); i.hasNext();) {
			User tmpUser = i.next();
			if (tmpUser.getName().compareTo("admin") != 0)
				dcbm.addElement(tmpUser);
		}
	}

	public boolean entryAlreadyExists(int id, Date date) {

		String tagesdatum;
		SimpleDateFormat df;
		df = new SimpleDateFormat("yyyy-MM-dd");
		df.setTimeZone(TimeZone.getDefault());
		tagesdatum = df.format(date);

		try {
			ResultSet rs = stmt
					.executeQuery("select count(*) from mitarb_tag where id='"
							+ id + "' and tagesdatum='" + tagesdatum + "'");
			rs.next();
			if (rs.getInt(1) != 0)
				return true;
			else
				return false;

		} catch (Exception e) {
			return false;
		}
	}

	public Vector<Vector<String>> getActivities(int id, Date date) {
		Vector<Vector<String>> values = new Vector<Vector<String>>();

		String bereich;
		String kategorie;
		String text;
		int dauer;

		int k_id;
		int b_id;

		String tagesdatum;
		SimpleDateFormat df;
		df = new SimpleDateFormat("yyyy-MM-dd");
		df.setTimeZone(TimeZone.getDefault());
		tagesdatum = df.format(date);

		try {
			Statement stmt2 = con.createStatement();
			Statement stmt3 = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("select * from mitarb_taetigkt where m_id = "
							+ id + " and tagesdatum = '" + tagesdatum + "'");
			ResultSet rs2;
			while (rs.next()) {
				k_id = rs.getInt("b_id");
				text = rs.getString("text");
				dauer = rs.getInt("dauer");

				ResultSet rs3 = stmt3
						.executeQuery("select * from bereich where id = "
								+ k_id);
				rs3.next();
				bereich = k_id + " " + rs3.getString("bezeichnung");

				// rs2.close();
				rs3.close();

				Vector<String> temp = new Vector<String>();
				temp.addElement(bereich);
				// temp.addElement(kategorie);
				temp.addElement(text);
				int hour=dauer/60;
				int minute=dauer%60;
				String show="";
				if(hour<10)
					show=show+"0"+hour;
				else
					show=show+hour;
				show=show+":";
				if(minute<10)
					show=show+"0"+minute;
				else
					show=show+minute;
				temp.addElement(show);
				show="";

				values.addElement(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return values;
		}
		return values;
	}

	public void deleteWorkDay(int id, Date date) {

		String tagesdatum;
		SimpleDateFormat df;
		df = new SimpleDateFormat("yyyy-MM-dd");
		df.setTimeZone(TimeZone.getDefault());
		tagesdatum = df.format(date);

		try {
			stmt.execute("delete from mitarb_tag where id = " + id
					+ " and tagesdatum = '" + tagesdatum + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteActivities(int id, Date date) {

		String tagesdatum;
		SimpleDateFormat df;
		df = new SimpleDateFormat("yyyy-MM-dd");
		df.setTimeZone(TimeZone.getDefault());
		tagesdatum = df.format(date);

		try {
			stmt.execute("delete from mitarb_taetigkt where m_id = " + id
					+ " and tagesdatum = '" + tagesdatum + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void resetWorkDay(int m_id, Date date) {

		String monat;
		String tagesdatum;
		SimpleDateFormat df;
		df = new SimpleDateFormat("yyyy-MM-dd");
		df.setTimeZone(TimeZone.getDefault());
		tagesdatum = df.format(date);

		try {
			ResultSet rs = stmt
					.executeQuery("select ueberstunden from mitarb_tag where id = "
							+ m_id + " and tagesdatum = '" + tagesdatum + "'");
			rs.next();
			/*
			 * BigDecimal bd = rs.getBigDecimal("ueberstunden"); float
			 * ueberstunden = bd.floatValue();
			 * 
			 * df = new SimpleDateFormat("yyyy-MM"); monat = df.format(date);
			 * 
			 * stmt.execute("update mitarb_monat set uebertrag = (uebertrag - "
			 * + ueberstunden + ") where id=" + m_id + " and datum ='" + monat +
			 * "-01'");
			 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Time[] getStandardTimes(int id) {
		Time[] ret = new Time[3];
		try {
			ResultSet rs = stmt
					.executeQuery("SELECT `stand_von` , `stand_bis` FROM `mitarbeiter` where id = "
							+ id);
			while (rs.next()) {
				ret[0] = rs.getTime("stand_von");
				ret[1] = rs.getTime("stand_bis");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ret;
	}

	public int getPause(int id) {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			ResultSet rs = stmt
					.executeQuery("SELECT `stand_pause` FROM `mitarbeiter` where id = "
							+ id);
			while (rs.next()) {
				ret = rs.getInt("stand_pause");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ret;
	}

	public boolean checkPasswordCorrect(String string) throws SQLException,
			WrongPasswordException {
		boolean retValue = false;
		String stm = "select count(*) from mitarbeiter where pwd = ?";
		this.connect();
		PreparedStatement pps = this.con.prepareStatement(stm);
		pps.setString(1, string);
		ResultSet rs = pps.executeQuery();
		rs.next();
		if (rs.getInt(1) == 0) {
			throw new WrongPasswordException("Old Password wrong");
		} else
			retValue = true;
		return retValue;
	}

	public int setNewStandardWorkingTime(User u, Time von, Time bis, int pause)
			throws SQLException {
		this.connect();
		String stm = "update mitarbeiter set stand_von=?, stand_bis=?, stand_pause=? where id=?";
		PreparedStatement pps = this.con.prepareStatement(stm);
		pps.setTime(1, von);
		pps.setTime(2, bis);
		pps.setInt(3, pause);
		pps.setInt(4, u.getId());
		return pps.executeUpdate();
	}

	public int setNewSollStd(User loggedInUser, java.util.Date changeDate,
			int newSollStd) throws SQLException {
		this.connect();
		String stm = "insert into m_sollstunden(m_id, sollst_tag, datum) values(?,?,?)";
		java.sql.Date sdate = new java.sql.Date(changeDate.getTime());
		PreparedStatement pps = this.con.prepareStatement(stm);
		pps.setInt(1, loggedInUser.getId());
		pps.setDate(3, sdate);
		pps.setInt(2, newSollStd);
		return pps.executeUpdate();
	}

	public Vector<MitarbeiterMonat> getMitarbeiterMonatForAuswertung(User user,
			Date von, Date bis) throws SQLException {
		Calendar createNewDate = GregorianCalendar.getInstance();
		Calendar handleInputs = GregorianCalendar.getInstance();
		java.sql.Date vons;
		java.sql.Date biss;
		Vector<MitarbeiterMonat> retValue = new Vector<MitarbeiterMonat>();
		// ---------------
		handleInputs.setTime(von);
		createNewDate.set(handleInputs.get(Calendar.YEAR),
				handleInputs.get(Calendar.MONTH), 1);
		vons = new java.sql.Date(createNewDate.getTimeInMillis());
		handleInputs.setTime(bis);
		createNewDate.set(handleInputs.get(Calendar.YEAR),
				handleInputs.get(Calendar.MONTH), 1);
		biss = new java.sql.Date(createNewDate.getTimeInMillis());
		this.connect();
		String stm = "select * from mitarb_monat where id = ? and datum between ? and ?";
		// -----------------------
		PreparedStatement pps = this.con.prepareStatement(stm);
		pps.setInt(1, user.getId());
		pps.setDate(2, vons);
		pps.setDate(3, biss);
		ResultSet rs = pps.executeQuery();
		while (rs.next()) {
			retValue.add(new MitarbeiterMonat(rs.getInt(2), rs.getDate(1), rs
					.getFloat(3)));
		}
		return retValue;
	}

	public Vector<MitarbeiterTag> getMitarbeiterTagForAuswertung(User user,
			Date monatsdatum) throws SQLException {
		this.connect();
		Vector<MitarbeiterTag> retValue = new Vector<MitarbeiterTag>();
		java.sql.Date monatsds = new java.sql.Date(monatsdatum.getTime());
		String stm = "select * from mitarb_tag where id = ? and datum = ?";
		String stmsollstd = "select sollst_tag from m_sollstunden where m_id = ? and datum <= ? order by datum desc";
		PreparedStatement pps = this.con.prepareStatement(stm);
		PreparedStatement pps2;
		pps.setInt(1, user.getId());
		pps.setDate(2, monatsds);
		ResultSet rs = pps.executeQuery();
		ResultSet rs2;
		while (rs.next()) {
			pps2 = this.con.prepareStatement(stmsollstd);
			pps2.setInt(1, user.getId());
			pps2.setDate(2, rs.getDate("tagesdatum"));
			rs2 = pps2.executeQuery();
			rs2.next();
			System.out.println(rs2.getInt("sollst_tag"));
			retValue.add(new MitarbeiterTag(rs.getInt("id"), rs
					.getDate("datum"), rs.getDate("tagesdatum"), rs
					.getInt("t_id"), rs.getTime("von"), rs.getTime("bis"), rs
					.getInt("pause"), rs2.getInt("sollst_tag"), rs.getFloat("ueberstunden")/60));
		}
		return retValue;
	}

	public String getActivity(int taetigkeits_id) throws SQLException {
		this.connect();
		String stm = "select bezeichnung from taetigkeit where id = ?";
		PreparedStatement pps = this.con.prepareStatement(stm);
		pps.setInt(1, taetigkeits_id);
		ResultSet rs = pps.executeQuery();
		rs.next();
		return rs.getString(1);
	}

	public float getUebertragsSumme(Date datum) throws SQLException {
		java.sql.Date d = new java.sql.Date(datum.getTime());
		this.connect();
		String stm = "select sum(uebertrag) from mitarb_monat where datum < ?";
		PreparedStatement pps = this.con.prepareStatement(stm);
		pps.setDate(1, d);
		ResultSet rs = pps.executeQuery();
		rs.next();
		return rs.getFloat(1);
	}

	public Vector<ZusammenfassungDaten> getHoursOfAllMitarbeiterForZfs(Date von, Date bis, String key) throws SQLException {
		// TODO Auto-generated method stub
		java.sql.Date sqlvon=new java.sql.Date(von.getTime());
		java.sql.Date sqlbis=new java.sql.Date(bis.getTime());
		Vector<District>vecDistrict = this.getDistricts();
		Iterator<District>i=vecDistrict.iterator();
		int s=0;
		float totalhours=0;
		Vector<ZusammenfassungDaten> vecZfs=new Vector<ZusammenfassungDaten>();
		while(i.hasNext()){
		District d=i.next();
		String stm= "select sum(dauer) from mitarb_taetigkt where datum >= ? and datum <= ? and b_id = ? and text like ?";
		PreparedStatement pps = this.con.prepareStatement(stm);
		pps.setDate(1, sqlvon);
		pps.setDate(2, sqlbis);
		pps.setInt(3, d.getId());
		pps.setString(4, key);
		ResultSet rs = pps.executeQuery();
		while(rs.next()){
			s++;
			System.out.println(s);
			float hours=rs.getInt(1)/60;
			totalhours=totalhours+hours;
			vecZfs.add(new ZusammenfassungDaten(d.getId(),d.getName(),hours) );
		}
		}
		Iterator<ZusammenfassungDaten> id=vecZfs.iterator();
		while(id.hasNext()){
			ZusammenfassungDaten zd=id.next();
			zd.setPercentage((int)((zd.getHours()/totalhours)*100));	
			System.out.println(zd);
		}
		return vecZfs;
	}
	
	public Vector<ZusammenfassungDaten> getHoursOfSpecialMitarbeiterForZfs(Date von, Date bis,User usr, String key) throws SQLException {
		java.sql.Date sqlvon=new java.sql.Date(von.getTime());
		java.sql.Date sqlbis=new java.sql.Date(bis.getTime());
		Vector<District>vecDistrict = this.getDistricts();
		Iterator<District>i=vecDistrict.iterator();
		int s=0;
		float totalhours=0;
		Vector<ZusammenfassungDaten> vecZfs=new Vector<ZusammenfassungDaten>();
		while(i.hasNext()){
		District d=i.next();
		String stm= "select sum(dauer) from mitarb_taetigkt where datum >= ? and datum <= ? and b_id = ? and m_id = ? and text like ?";
		PreparedStatement pps = this.con.prepareStatement(stm);
		pps.setDate(1, sqlvon);
		pps.setDate(2, sqlbis);
		pps.setInt(3, d.getId());
		pps.setInt(4, usr.getId());
		pps.setString(5, key);
		ResultSet rs = pps.executeQuery();
		while(rs.next()){
			s++;
			System.out.println(s);
			float hours=rs.getInt(1)/60;
			totalhours=totalhours+hours;
			vecZfs.add(new ZusammenfassungDaten(d.getId(),d.getName(),hours) );
		}
		}
		Iterator<ZusammenfassungDaten> id=vecZfs.iterator();
		while(id.hasNext()){
			ZusammenfassungDaten zd=id.next();
			zd.setPercentage((int)((zd.getHours()/totalhours)*100));	
			System.out.println(zd);
		}
		return vecZfs;
	}

	/*-------------------------------------------------------------------------------------*/
	
	public int getMaxDistrictId() throws SQLException {
		this.connect();
		String stm = "select max(id) from bereich";
		PreparedStatement pps = this.con.prepareStatement(stm);
		ResultSet rs = pps.executeQuery();
		rs.next();
		return rs.getInt(1) + 1;
	}

	public int deleteDistrict(int dId) throws SQLException, DistrictInUseException {
		this.connect();
		int retValue = 0;
		String check = "select count(*) from mitarb_taetigkt where b_id = ?";
		String del = "delete from bereich where id = ?";
		PreparedStatement pps = this.con.prepareStatement(check);
		ResultSet rs = pps.executeQuery();
		if(rs.getInt(1) != 0)
			throw new DistrictInUseException("Bereich darf nicht gelöscht werden");
		else {
			pps = this.con.prepareStatement(del);
			retValue = pps.executeUpdate();
		}
		return retValue;
	}

	public int updateDistrict(int dId, String newName) throws SQLException {
		this.connect();
		String update = "update bereich set bezeichnung = ? where id = ?";
		PreparedStatement pps = this.con.prepareStatement(update);
		pps.setString(1, newName);
		pps.setInt(2, dId);
		
		return pps.executeUpdate();
	}

	public int newTaetigkeit(String newTaetigkeit) throws SQLException {
		this.connect();
		int id = this.getMaxTaetigkeitsId();
		String ins = "insert into taetigkeit(id,bezeichung) values (?,?)";
		PreparedStatement pps = this.con.prepareStatement(ins);
		pps.setInt(1, id);
		pps.setString(2, newTaetigkeit);
		return pps.executeUpdate();
	}

	private int getMaxTaetigkeitsId() throws SQLException {
		this.connect();
		String stm = "select max(id) from taetigkeit";
		PreparedStatement pps = this.con.prepareStatement(stm);
		ResultSet rs = pps.executeQuery();
		rs.next();
		return rs.getInt(1) + 1;
	}

	public int updateTaetigkeit(int id, String newName) throws SQLException {
		this.connect();
		String update = "update taetigkeit set bezeichnung = ? where id = ?";
		PreparedStatement pps = this.con.prepareStatement(update);
		pps.setString(1, newName);
		pps.setInt(2, id);
		
		return pps.executeUpdate();
	}

	public int deleteTaetigkeit(int id) throws DistrictInUseException, SQLException {
		this.connect();
		int retValue = 0;
		String check = "select count(*) from mitarb_tag where t_id = ?";
		String del = "delete from taetigkeit where id = ?";
		PreparedStatement pps = this.con.prepareStatement(check);
		ResultSet rs = pps.executeQuery();
		if(rs.getInt(1) != 0)
			throw new DistrictInUseException("Taetigkeit darf nicht gelöscht werden");
		else {
			pps = this.con.prepareStatement(del);
			retValue = pps.executeUpdate();
		}
		return retValue;
	}

	public int disableUser(User disableUser, Date disableDate) throws SQLException {
		this.connect();
		java.sql.Date dDate = new java.sql.Date(disableDate.getTime());
		String disable = "update mitarbeiter set ang_bis = ? where id = ?";
		PreparedStatement pps = this.con.prepareStatement(disable);
		pps.setDate(1, dDate);
		pps.setInt(2, disableUser.getId());
		
		return pps.executeUpdate();
	}

	public int setNewStandardWorkingTimeForAll(int pause, Time von, Time bis) throws SQLException {
		this.connect();
		java.sql.Date vons = new java.sql.Date(von.getTime());
		java.sql.Date biss = new java.sql.Date(bis.getTime());
		String set = "update mitarbeiter set stand_von = ?, stand_bis = ?, stand_pause = ?";
		PreparedStatement pps = this.con.prepareStatement(set);
		pps.setDate(1, vons);
		pps.setDate(2, biss);
		pps.setInt(3, pause);
		
		return pps.executeUpdate();
	}

	public int setNewSollStdAll(int newSollStd, Date changeDate) throws SQLException {
		this.connect();
		int retValue = 0;
		java.sql.Date cD = new java.sql.Date(changeDate.getTime());
		
		String set = "insert into m_sollstunden(m_id, sollst_tag, datum) values(?,?,?)";
		Vector<User> us = this.getUsers();
		
		PreparedStatement pps;
		for(User u : us) {
			pps = this.con.prepareStatement(set);
			pps.setInt(1, u.getId());
			pps.setInt(2, newSollStd);
			pps.setDate(3, cD);
			retValue = pps.executeUpdate();
		}
		return retValue;
	}
	
	public Vector<Mitarbeitertaetigkeit> getMitarbeiterTaetigkeitenforAuswertung(int m_id, int b_id, Date von, Date bis, String key) throws SQLException {
		Vector<Mitarbeitertaetigkeit> retValue = new Vector<Mitarbeitertaetigkeit>();
		java.sql.Date vons = new java.sql.Date(von.getTime());
		java.sql.Date biss = new java.sql.Date(bis.getTime());
		String stm = "select tagesdatum, text, dauer from mitarb_taetigkt where m_id = ? and b_id = ? and text like ? and tagesdatum between ? and ?";
		PreparedStatement pps = this.con.prepareStatement(stm);
		pps.setInt(1, m_id);
		pps.setInt(2, b_id);
		pps.setString(3, key);
		pps.setDate(4, vons);
		pps.setDate(5, biss);
		ResultSet rs = pps.executeQuery();
		while(rs.next()) {
			Mitarbeitertaetigkeit mt = new Mitarbeitertaetigkeit(rs.getDate(1), rs.getString(2), rs.getInt(3));
			retValue.add(mt);
		}
		
		return retValue;
	}
	
	 
}