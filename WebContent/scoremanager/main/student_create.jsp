<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">
    学生情報登録
  </c:param>

  <c:param name="scripts">
    <!-- 必要ならJSをここに -->
  </c:param>

  <c:param name="content">
    <section class="me-4">

      <!-- 見出し -->
      <h2 class="h3 mb-4 fw-bold bg-secondary bg-opacity-10 py-2 px-4 rounded">
        学生情報登録
      </h2>

      <!-- エラーメッセージ -->
      <c:if test="${not empty errors}">
        <div class="alert alert-danger w-75">
          <ul class="mb-0">
            <c:forEach var="err" items="${errors.values()}">
              <li>${err}</li>
            </c:forEach>
          </ul>
        </div>
      </c:if>

      <!-- 入力フォーム -->
      <form action="StudentCreateExecute.action" method="post" class="w-75 ps-4">

      <!-- 入学年度 -->
        <div class="mb-3">
          <label for="entYear" class="form-label">入学年度</label>
          <select class="form-select" id="entYear" name="entYear">
            <option value="0">--------</option>
            <c:forEach var="year" items="${ent_year_set}">
              <option value="${year}" <c:if test="${year == entYear}">selected</c:if>>${year}</option>
            </c:forEach>
          </select>
        </div>

        <!-- 学生番号 -->
        <div class="mb-3">
          <label for="no" class="form-label">学生番号</label>
          <input type="text" class="form-control" id="no" name="no" value="${param.no}" />
        </div>

        <!-- 氏名 -->
        <div class="mb-3">
          <label for="name" class="form-label">氏名</label>
          <input type="text" class="form-control" id="name" name="name" value="${param.name}" />
        </div>


        <!-- クラス -->
        <div class="mb-4">
          <label for="classNum" class="form-label">クラス</label>
          <select class="form-select" id="classNum" name="classNum">
            <option value="0">--------</option>
            <c:forEach var="num" items="${class_num_set}">
              <option value="${num}" <c:if test="${num == classNum}">selected</c:if>>${num}</option>
            </c:forEach>
          </select>
        </div>

        <!-- 登録ボタン -->
        <div class="mb-2">
          <button type="submit" class="btn btn-secondary btn-sm w-auto px-4">登録して終了</button>
        </div>

        <a href="StudentListServlet" class="text-decoration-none text-primary">戻る</a>


      </form>
    </section>
  </c:param>
</c:import>
