package leetcode;

public class _434_Number_of_Segments_in_a_String{
    public int countSegments(String s) {
        s = s.trim();
        return s.matches(" *")?0:s.split(" +").length;
    }
}
