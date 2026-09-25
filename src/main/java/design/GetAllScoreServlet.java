package design;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import design.AllScore;
import design.getAllScoreBean;

/**
 * Servlet implementation class GetAllScoreServlet
 */
@WebServlet("/GetAllScoreServlet")
public class GetAllScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GetAllScoreServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * GETリクエストが来たら実際にユーザーの全スコアを取得した後、JSPファイルに渡す
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)　リクエストの情報
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getAllScoreBean scoreBean = new getAllScoreBean();
		
		scoreBean.setAllScore();
		
		List<AllScore> scores = scoreBean.getScores();
		request.setAttribute("scores",scores);
		
		request.getRequestDispatcher("allscore.jsp").forward(request,response);
		
	}

	/**
	 * POST処理はなし
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)　リクエストの情報
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
