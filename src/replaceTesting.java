@SuppressWarnings("unused")
public class replaceTesting {
	
	public static void main(String[] args) {
		String out = "";
		String l = "Jeg hader rigtigt meget når det her fucking lort meget aldrig fucking virker, og det virker meget! tit ikke ";
		String[] r = {"hella","skrt","braat","blyat","maymay","xdddd"};
		String[] arr = l.split(" ");
		String pik = "meget";
		for(String s : arr) {
			int ir = (int) (Math.random()*r.length);
			if(s.toLowerCase().matches("meget\\S") || s.toLowerCase().matches("meget")) {
				s = r[ir];
			}
			out += s + " ";
		}
		System.out.println(out);
	}
	
}
