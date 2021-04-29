
public class Calendar {
	
	public static void main(String[] args) {
		String[][] calendar = {{"volunteer", "delivery", null, null, "doctor", null, "soccer"},
		{null, "exam 1", null, "mechanic", null, null, "soccer"},
		{"volunteer", "off work", null, "birthday", null, "concert", null},
		{null, "exam 2", null, null, "doctor", null, "soccer"},
		{"visit family", null, null, null, null, null, null}};
		
		int eventsPerWeek, eventsPerDay;
		
		for (int i = 0; i < calendar.length; i++) {
			eventsPerWeek = 0;
			for (int j = 0; j < calendar[0].length; j++) {
				if (calendar[i][j]!=null) {
					System.out.println("Week: " + (i + 1) + ", Day: " + (j + 1) +
					", Event: " + calendar[i][j]);
					eventsPerWeek++;
				}
			}
			System.out.println("Events week " + (i + 1) + ": " + eventsPerWeek);
		}
		
		for (int i = 0; i < calendar[0].length; i++) {
			eventsPerDay = 0;
			for (int j = 0; j < calendar.length; j++) {
				if (calendar[j][i]!=null) {
					eventsPerDay++;
				}
			}
			System.out.println("Events day " + (i + 1) + ": " + eventsPerDay);
		}
		
	}

}
