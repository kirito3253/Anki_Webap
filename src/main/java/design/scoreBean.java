package design;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
/**
 * ユーザの問題数、ID、得点を扱うクラス
 */
public class scoreBean {
	private int qnum;
	private int user_id;
	private int score;
	
	/**
	 * コンストラクタ
	 */
	public scoreBean() {
		;
	}
	
	/** 
	 * 問題数のセッター
	 * @param i ユーザの問題数
	 */
	public void setQnum(int i) {
		qnum = i;
	}
	
	/**
	 * 問題数のゲッター
	 * @return ユーザの問題数
	 */
	public int getQnum() {
		return qnum;
	}
	
	/**
	 * ユーザの問題数をインクリメント
	 */
	public void countQnum() {
		qnum = qnum + 1;
	}
	
	/**
	 * ユーザIDのセッター
	 * @param i ユーザID
	 */
	public void setUserid(int i) {
		user_id = i;
	}
	
	/**
	 * ユーザIDのゲッター
	 * @return ユーザID
	 */
	public int getUserid() {
		return user_id;
	}
	
	/**
	 * 得点のセッター
	 * @param i 得点
	 */
	public void setScore(int i) {
		score = i;
	}
	
	/**
	 * 得点のゲッター
	 * @return 得点
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * 得点をインクリメント
	 */
	public void countScore() {
		score = score + 1;
	}
	
	/**
	 * スコアをデータベースに登録する
	 * @return 正常に登録できた場合はtrue,できなかった場合はfalse
	 */
	public boolean regScore() {
		try {
			//データベースに接続
			Connection con = DBManager.getUserConnection();
			//INSERT文の実行
			PreparedStatement smt = con.prepareStatement("INSERT INTO Scores(user_id, score) VALUES(?,?);");
			smt.setInt(1, user_id);
			smt.setInt(2, score);
			smt.executeUpdate();
			//コネクションを閉じる
			smt.close();
			con.close();
			return true;
		} catch (Exception e) {
			// もし、上の try ブロックで例外などのエラーが起きた場合
			// ここに自動的にジャンプしてくる
			e.printStackTrace(); // データベース接続の例外エラー表示
			return false;		}

	}

}
