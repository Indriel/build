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
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import data.Category;
import data.DayOfWeek;
import data.District;
import data.Month;
import data.User;
import data.WorkType;

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
		}
	}

	public User login(String user, String password) throws SQLException {
		// returns null if the login was unsuccessful
		User retValue = null;
		this.connect();
		String checkUserExistence = "select count(*) from mitarbeiter where name = ? and pwd = ?";
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

	public boolean newEmployee(String name, String pwd, String begin) {
		try {
			ResultSet rs = stmt.executeQuery("select max(id) from mitarbeiter");
			rs.next();
			int id = rs.getInt(1) + 1;

			PreparedStatement pstmt = con
					.prepareStatement("insert into mitarbeiter values(?,?,?,?,?)");

			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pwd);
			pstmt.setString(4, begin);
			pstmt.setString(5, null);

			pstmt.execute();

		} catch (Exception e) {
			System.out.println("Exception newEmployee(...): " + e.getMessage());
			return false;
		}
		return true;
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
			System.out.println(sqle.getMessage());
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
			System.out.println(sqle.getMessage());
		}

		return districts;
	}

	/*public Vector<User> getUsers() {

		Vector<User> tmp = new Vector<User>();

		try {
			ResultSet rs = stmt.executeQuery("select * from mitarbeiter");
			while (rs.next()) {
				tmp.add(new User(rs.getInt("id"), rs.getString("name")));
			}

		} catch (Exception e) {
			System.out.println("Exception getUsers() " + e.getMessage());
		}

		return tmp;
	}*/

	public void writeEmployeeActivity(
			int m_id,
			Date datum,
			int[] k_id,
			Time[] dauerVon,
			Time[] dauerBis,
			String[] text) {

		int activityCount = k_id.length;

		SimpleDateFormat df;
		String tagesdatum;
		String monat;

		df = new SimpleDateFormat( "yyyy-MM-dd" );
		df.setTimeZone( TimeZone.getDefault() );
		tagesdatum = df.format(datum);

		df = new SimpleDateFormat("yyyy-MM");
		monat = df.format(datum);

		for(int i = 0; i< activityCount; i++) {

			PreparedStatement insertStmt;
			String insertString = "INSERT INTO mitarb_taetigkt VALUES (?, ?, ?, ?, ?, ?,?)";

			//float dauerStunden = dauer[i];
			//dauerStunden /= 60;

			try {
		        insertStmt = con.prepareStatement(insertString);

		        insertStmt.setInt(1, m_id);
		        insertStmt.setString(2, monat + "-01");
		        insertStmt.setString(3, tagesdatum);
		        insertStmt.setTime(4, dauerVon[i]);
		        insertStmt.setTime(5, dauerBis[i]);
		        insertStmt.setInt(6, k_id[i]);
		        insertStmt.setString(7, text[i]);
		        insertStmt.execute();

			}
			catch(SQLException sqle) {
				System.out.println("2"+sqle.getMessage());
			}
		}

	}

	public void writeEmployeeActivity(int m_id, Date datum, int[] k_id,
			int[] dauer, String[] text) {

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
			String insertString = "INSERT INTO mitarb_taetigkt VALUES (?, ?, ?, ?, ?, ?)";

			float dauerStunden = dauer[i];
			dauerStunden /= 60;

			try {
				insertStmt = con.prepareStatement(insertString);

				insertStmt.setInt(1, m_id);
				insertStmt.setString(2, monat + "-01");
				insertStmt.setString(3, tagesdatum);
				insertStmt.setInt(4, k_id[i]);
				insertStmt.setFloat(5, dauerStunden);
				insertStmt.setString(6, text[i]);
				insertStmt.execute();

			} catch (SQLException sqle) {
				System.out.println(sqle.getMessage());
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

	/*public boolean newCategory(int category_id, String name, int district_id) {
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
	}*/

	public void updateComboBoxUser(DefaultComboBoxModel<User> dcbm) throws SQLException {
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
				k_id = rs.getInt("k_id");
				text = rs.getString("text");
				BigDecimal bd = rs.getBigDecimal("dauer");
				float f = bd.floatValue();
				f *= 60;
				dauer = (int) f;
				String dauerStr = Integer.toString(dauer);

				rs2 = stmt2.executeQuery("select * from kategorie where id = "
						+ k_id);
				rs2.next();
				kategorie = k_id + " " + rs2.getString("bezeichnung");
				b_id = rs2.getInt("b_id");

				ResultSet rs3 = stmt3
						.executeQuery("select * from bereich where id = "
								+ b_id);
				rs3.next();
				bereich = b_id + " " + rs3.getString("bezeichnung");

				rs2.close();
				rs3.close();

				Vector<String> temp = new Vector<String>();
				temp.addElement(bereich);
				temp.addElement(kategorie);
				temp.addElement(text);
				temp.addElement(dauerStr);

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
		df = new SimpleDateFormat( "yyyy-MM-dd" );
		df.setTimeZone( TimeZone.getDefault() );
		tagesdatum = df.format(date);

		try {
			ResultSet rs = stmt.executeQuery("select ueberstunden from mitarb_tag where id = " + m_id + " and tagesdatum = '"+ tagesdatum + "'");
			rs.next();
			/*BigDecimal bd = rs.getBigDecimal("ueberstunden");
			float ueberstunden = bd.floatValue();

			df = new SimpleDateFormat("yyyy-MM");
			monat = df.format(date);

			stmt.execute("update mitarb_monat set uebertrag = (uebertrag - " + ueberstunden + ") where id=" + m_id + " and datum ='" + monat + "-01'");
*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}