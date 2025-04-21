package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // セッションから教員情報を取得
        Teacher teacher = (Teacher) request.getSession().getAttribute("user");
        School school = teacher.getSchool();

        // 現在の年を取得
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();

        // リクエストからパラメータ取得（フォームから選択された入学年度・クラス・在学フラグ）
        String entYear = request.getParameter("entYear");     // 入学年度
        String classNum = request.getParameter("classNum");   // クラス番号

        // 年度リスト作成（10年前〜10年後）
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year + 10; i++) {
            entYearSet.add(i);
        }

        // クラス番号リスト取得
        ClassNumDao cNumDao = new ClassNumDao();
        List<String> classNumSet = cNumDao.filter(school);

        // JSPに渡す（選択値の復元）
        request.setAttribute("school", school);
        request.setAttribute("entYear", entYear);
        request.setAttribute("classNum", classNum);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", classNumSet);

        // フォワード
        RequestDispatcher rd = request.getRequestDispatcher("student_create.jsp");
        rd.forward(request, response);
    }
}