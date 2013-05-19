package data;

public class ZusammenfassungDaten {
			private int d_id;
			private String d_name;
			private float hours;
			private int percentage;
			
			
			public ZusammenfassungDaten(int d_id, String d_name, float hours) {
				super();
				this.d_id = d_id;
				this.d_name = d_name;
				this.hours = hours;
			}
			public int getD_id() {
				return d_id;
			}
			public void setD_id(int d_id) {
				this.d_id = d_id;
			}
			public String getD_name() {
				return d_name;
			}
			public void setD_name(String d_name) {
				this.d_name = d_name;
			}
			public float getHours() {
				return hours;
			}
			public void setHours(float hours) {
				this.hours = hours;
			}
			public int getPercentage() {
				return percentage;
			}
			public void setPercentage(int percentage) {
				this.percentage = percentage;
			}
			@Override
			public String toString() {
				return "ZusammenfassungDaten [d_id=" + d_id + ", d_name="
						+ d_name + ", hours=" + hours + ", percentage="
						+ percentage + "]";
			}
			
			
}
