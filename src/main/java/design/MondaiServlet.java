package design;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import design.TFCheckLogic;
import design.questionBean;
import design.scoreBean;

/**
 * Servlet implementation class MondaiServlet
 */
@WebServlet("/MondaiServlet")
public class MondaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * コンストラクタ
     */
    public MondaiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * GETリクエストが来たら、問題数と問題情報を更新して、qeustionview.jspに遷移する
	 * 10問終了後の場合は更新を行わず、resultpage.jspに遷移する
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションからユーザ情報を取り出す
		HttpSession session_score = request.getSession(true);
		scoreBean score = (scoreBean) session_score.getAttribute("score");
		RequestDispatcher dispatcher;
		//初期化
		dispatcher = request.getRequestDispatcher("");
		if(score.getQnum() < 5){ //ユーザの問題数が5未満だったら
			//問題数を増やす
			score.countQnum();
			//もう一度セッションに保存
			session_score.setAttribute("score",score);
			
			//インスタンスにランダムな問題を格納
			questionBean questions = new questionBean();
			questions.setRandomQuestions();
			//問題情報をセッションに保存
			HttpSession session_quiz = request.getSession();
			session_quiz.setAttribute("questions", questions); 
			//遷移先をクイズページにする
			dispatcher = request.getRequestDispatcher("viewquestion.jsp");
		}
		else { //ユーザの問題数が10以上だったら
			if(score.regScore()){
				//遷移先をリザルトページにする
				dispatcher = request.getRequestDispatcher("resultpage.jsp");
			}
		}
		//画面遷移
		dispatcher.forward(request, response);
	}

	/**
	 * POSTリクエストが来たら、正答とユーザの回答を取り出し、正誤判定を行う
	 * その後、判定に応じてcorrect.jspあるいはwrong.jspに遷移する
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//クイズページでユーザが選択肢を押した場合
		//セッションから問題情報を取り出す
		HttpSession session = request.getSession(true);
		questionBean questions = (questionBean) session.getAttribute("questions");

		//正誤判定(インスタンス)
		TFCheckLogic userans = new TFCheckLogic();
		//正答をセット
		userans.setAnswer(questions.getAnswer());
		//ユーザの回答をセット
		String choice = request.getParameter("choice");
		userans.setChoice(choice);
		//判定によって遷移先を変更
		RequestDispatcher dispatcher;
		if(userans.tfcheck()) { //正解
			//セッションからユーザ情報を取り出す
			HttpSession session_score = request.getSession(true);
			scoreBean score = (scoreBean) session_score.getAttribute("score");
			//得点を加算
			score.countScore();
			//もう一度セッションに保存
			session_score.setAttribute("score",score);
			//遷移先を正解ページにする
			dispatcher = request.getRequestDispatcher("correct.jsp");
		}else { //不正解
			//遷移先を不正解ページにする
			dispatcher = request.getRequestDispatcher("wrong.jsp");
		}
		//画面遷移
		dispatcher.forward(request, response);
	}

}
