package design;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 * 問題情報を扱うクラス
 */
public class questionBean {
	private int id;
	private String question;
	private String choice_a;
	private String choice_b;
	private String choice_c;
	private String choice_d;
	private String answer;
	
	/**
	 * コンストラクタ
	 */
	public questionBean(){
		;
	}
	
	/**
	 * 問題番号のセッター
	 * @param i 問題番号
	 */
	public void setId(int i) {
		id=i;
	}
	/**
	 * 問題番号のゲッター
	 * @return 問題番号
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * 問題文のセッター
	 * @param s 問題文
	 */
	public void setQuestion(String s) {
		question = s;
	}
	/**
	 * 問題文のゲッター
	 * @return 問題文
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * 選択肢Aのセッター
	 * @param s 選択肢A
	 */
	public void setChoice_a(String s) {
		choice_a = s;
	}
	/**
	 * 選択肢Aのゲッター
	 * @return 選択肢A
	 */
	public String getChoice_a() {
		return choice_a;
	}

	/**
	 * 選択肢Bのセッター
	 * @param s 選択肢B
	 */
	public void setChoice_b(String s) {
		choice_b = s;
	}
	/**
	 * 選択肢Bのゲッター
	 * @return 選択肢B
	 */
	public String getChoice_b() {
		return choice_b;
	}

	/**
	 * 選択肢Cのセッター
	 * @param s 選択肢C
	 */
	public void setChoice_c(String s) {
		choice_c = s;
	}
	/**
	 * 選択肢Cのゲッター
	 * @return 選択肢C
	 */
	public String getChoice_c() {
		return choice_c;
	}

	/**
	 * 選択肢Dのセッター
	 * @param s 選択肢D
	 */
	public void setChoice_d(String s) {
		choice_d = s;
	}
	/**
	 * 選択肢Dのゲッター
	 * @return 選択肢D
	 */
	public String getChoice_d() {
		return choice_d;
	}
	
	/**
	 * 正答のセッター
	 * @param s 正答
	 */
	public void setAnswer(String s) {
		answer = s;
	}
	/**
	 * 正答のゲッター
	 * @return 正答
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * データベースからランダムな問題を取り出して、セットする
	 */
	public void setRandomQuestions() {
		try {
			// データベースへのコネクションを取得
			Connection con = DBManager.getUserConnection();
			
			// 問題数をカウント
			PreparedStatement smt_num = con.prepareStatement("SELECT COUNT(*) as max_quiz_num FROM Questions;");
			ResultSet rs_num = smt_num.executeQuery();
			rs_num.next();
			int num = rs_num.getInt("max_quiz_num");
			//コネクションを閉じる
			rs_num.close();
			smt_num.close();
			
			// ランダムな問題番号(ID)を生成
			Random rand = new Random();
			int index = rand.nextInt(num)+1;
			
			//IDが一致する一行を取り出す
			PreparedStatement smt_quiz = con.prepareStatement("SELECT * FROM Questions WHERE id = ?;");
			smt_quiz.setInt(1, index);
			ResultSet rs_quiz = smt_quiz.executeQuery();
			
			rs_quiz.next();
			id = (rs_quiz.getInt("Id"));
			question = (rs_quiz.getString("Question"));
			choice_a = (rs_quiz.getString("Choice_a"));
			choice_b = (rs_quiz.getString("Choice_b"));
			choice_c = (rs_quiz.getString("Choice_c"));
			choice_d = (rs_quiz.getString("Choice_d"));
			answer = (rs_quiz.getString("Answer"));
			//コネクションを閉じる
			rs_quiz.close();
			smt_quiz.close();
			con.close();
		} catch (Exception e) {
			// もし、上の try ブロックで例外などのエラーが起きた場合
			// ここに自動的にジャンプしてくる
			e.printStackTrace(); // データベース接続の例外エラー表示
		}
	}
	
	public String getCorrectChoice(){
		String correct_choice = "";
		if(answer.equals("A")){
			correct_choice = choice_a;
		}
		else if(answer.equals("B")){
			correct_choice = choice_b;
		}
		else if(answer.equals("C")){
			correct_choice = choice_c;
		}
		else{
			correct_choice = choice_d;
		}
		return correct_choice;
	}
}
