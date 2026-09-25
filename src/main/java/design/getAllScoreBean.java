package design;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 全ユーザーの取得したスコアを一覧表示する
 */
public class getAllScoreBean {
	private List<AllScore> scores;
	
	/**
	 * コンストラクタ
	 */
	public getAllScoreBean() {
		scores = new ArrayList<>();
	}
	
	/**
	 * 実際にスコアを取得する処理
	 * コンストラクタで作成した配列にname,scoreが順に追加される
	 */
	public void setAllScore() {
		try {
			Connection con = DBManager.getUserConnection();
			
			PreparedStatement smtAllScore = con.prepareStatement("SELECT Users.name,Scores.score FROM Users JOIN Scores ON Users.id = Scores.user_id;");
			ResultSet queryResultAllScore = smtAllScore.executeQuery();
			
			while(queryResultAllScore.next()) {
				String name = queryResultAllScore.getString("name");
				int score = queryResultAllScore.getInt("score");
				
				scores.add(new AllScore(name,score));
			}
			
			queryResultAllScore.close();
			smtAllScore.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 実際にスコアを取得する。ゲッターメソッド
	 * @return scores　ユーザーとスコアの情報の二次元配列
	 */
	public List<AllScore> getScores(){
		return scores;
	}
} 