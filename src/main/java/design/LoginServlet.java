package design;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import design.scoreBean;
import design.userBean;

/**
 * Servlet implementation class LoginServlet
 * ユーザーのログイン処理を行うサーブレットクラス
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * デフォルトコンストラクタ
	 * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * フォームから送信されたユーザ名を取得後、userBeanインスタンスを作成し設定。
	 * ユーザーが存在するか確認し、存在すれば新しいscoreBeanインスタンスを作成し、各値を初期化。
	 * 存在しなければuser.notexist.htmlにリクエストを転送
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォームから送信されたユーザ名を取得
		String inputName = request.getParameter("input_name");
		//userBeanインスタンスの作成
		userBean user = new userBean();
		user.setName(inputName); //ユーザー名を設定
		//遷移先の初期化
		RequestDispatcher dispatcher = request.getRequestDispatcher("");
		//データベース内にユーザーが存在するか確認
		if (user.exist()) {
			scoreBean score = new scoreBean();
			score.setQnum(0); //ユーザの問題数
			score.setUserid(user.getId()); //ユーザID
			score.setScore(0); //得点
			
			//変更部分
			//ユーザ情報をセッションに保存
			HttpSession session_user = request.getSession();
			session_user.setAttribute("user", user);
			
			//スコア情報をセッションに保存
			HttpSession session_score = request.getSession();
			session_score.setAttribute("score", score); 
			//クイズページに遷移
			dispatcher = request.getRequestDispatcher("/MondaiServlet");
		}else {
			//存在しない場合、user_notexist.htmlに遷移
			dispatcher = request.getRequestDispatcher("user_notexist.html");
		}
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
