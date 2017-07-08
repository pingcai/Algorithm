public class _551_Student_Attendance_Record_I{
	public static void main(String[] ar){
		String str = "ALLALLALL";
		System.out.println(new _551_Student_Attendance_Record_I().checkRecord(str));
	}
	//不能出现 两个A 或者 连续的三个L
	//正则
    public boolean checkRecord(String s) {
		return !s.matches(".*A.*A.*|.*L{3,}.*");
    }
    //非正则
     public boolean checkRecord2(String s) {
		return !(s.indexOf('A')!=s.lastIndexOf('A') || s.contains("LLL"));
    }
}
