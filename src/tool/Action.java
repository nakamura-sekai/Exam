package tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//完成されてないクラス(抽象クラス)
//使用するときは継承が必要
public abstract class Action {
	//メソッド名execute
	public abstract void execute(
			HttpServletRequest request, HttpServletResponse response
		)throws Exception;
}
