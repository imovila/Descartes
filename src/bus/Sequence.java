package bus;

public class Sequence {
	static int idx;

	public static int getIdx() {
		return Sequence.idx++;
	}

	public static void setIdx(int idx) {
		Sequence.idx = idx;
	}
}
