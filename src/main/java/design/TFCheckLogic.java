package design;

/**
 * 正誤判定を行うクラス
 */
public class TFCheckLogic {
	private String answer; //正答
	private String choice; //ユーザの回答
	
	/**
	 * コンストラクタ
	 */
	public TFCheckLogic(){
		;
	}
	
	/**
	 * 正答のセッター
	 * @param s 正答
	 */
	void setAnswer(String s) {
		answer=s;
	}
	/**
	 * 正答のゲッター
	 * @return 正答
	 */
	String getAnswer() {
		return answer;
	}
	
	/**
	 * ユーザの回答のセッター
	 * @param s ユーザーの回答
	 */
	void setChoice(String s) {
		choice=s;
	}
	/**
	 * ユーザーの回答のゲッター
	 * @return ユーザーの回答
	 */
	String getChoice() {
		return choice;
	}
	
	/**
	 * 正誤判定を行う
	 * @return 判定結果
	 */
	public boolean tfcheck() {
		if(answer.equals(choice)) { //正答とユーザの回答が一致していたら
			return true;
		}
		else {
			return false;
		}
	}
}
