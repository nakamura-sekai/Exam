package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // パラメータ取得
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String classNum = req.getParameter("classNum");
        int entYear = Integer.parseInt(req.getParameter("entYear"));

        // ログインユーザーから所属学校を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("user");
        School school = teacher.getSchool();

        // バリデーション（ここでは簡略に）
        Map<String, String> errors = new HashMap<>();
        if (no == null || no.isEmpty()) {
            errors.put("no", "学生番号は必須です");
        }
        if (name == null || name.isEmpty()) {
            errors.put("name", "氏名は必須です");
        }

        // エラーがあれば元の画面に戻す
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("school", school);
            RequestDispatcher rd = req.getRequestDispatcher("student_create.jsp");
            rd.forward(req, res);
            return;
        }

        // Studentオブジェクト生成
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setSchool(school);

        // DBに保存
        StudentDao sDao = new StudentDao();
        sDao.save(student);

        // 登録完了後は一覧などにフォワード（仮で完了画面に遷移）
        RequestDispatcher rd = req.getRequestDispatcher("student_create_done.jsp");
        rd.forward(req, res);
    }
}