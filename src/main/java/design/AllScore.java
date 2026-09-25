package design;

/**
 * すべてのスコアを取得するクラス
 */
public class AllScore {
	private String name;
	private int score;
	
	/**
	 * コンストラクタ
	 * @param name ユーザー名を設定する
	 * @param score　上記変数で指定したユーザーのスコア
	 */
	public AllScore(String name,int score) {
		this.name = name;
		this.score = score;
	}
	
	/**
	 * スコアを記録したユーザーを取得する
	 * @return name　ユーザー名　
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * スコアを記録したユーザーのセッター
	 * @param name　ユーザー名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * ユーザーのスコアを取得する
	 * @return score　ユーザーの取得したスコア
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * ユーザーのスコアのセッター
	 * @param score　ユーザの取得したスコア
	 */
	public void setScore(int score) {
		this.score = score;
	}
}
