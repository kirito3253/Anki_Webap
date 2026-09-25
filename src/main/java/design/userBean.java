package design;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * ユーザ情報を保存するクラス
 */
public class userBean {
	private int id;
	private String name;
	
	/**
	 * デフォルトコンストラクタ
	 */
	public userBean() {
		;
	}
	
	/** ユーザのidを設定する
	 *	@param ユーザのID  
	 */
	public void setId(int i) {
		id=i;
	}
	/**
	 * ユーザのIDを取得する
	 * @return ユーザのID
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * ユーザの名前を設定する
	 * @param s ユーザの名前
	 */
	public void setName(String s) {
		name=s;
	}
	/**
	 * ユーザの名前を取得する
	 * @return ユーザの名前
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * ユーザが存在するか確認する
	 * @return ユーザが存在する場合はtrue,しない場合はfalse
	 */
	public boolean exist() {
		try{
			//データベースへの接続
			Connection con = DBManager.getUserConnection();
			//ユーザ名が一致しているレコードを探す
			PreparedStatement smt = con.prepareStatement("SELECT * FROM Users WHERE name = ?;");
			smt.setString(1, name);
			ResultSet rs = smt.executeQuery();
			if(rs.next()) {  //行(ユーザ)が存在していたらtrue, そうでなかったらfalse
				id = rs.getInt("id"); //ユーザidを取得
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			// もし、上の try ブロックで例外などのエラーが起きた場合
			// ここに自動的にジャンプしてくる
			e.printStackTrace(); // データベース接続の例外エラー表示
			return false;
		}
		
	}
}

