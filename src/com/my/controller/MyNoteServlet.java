package com.my.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.exception.FindException;
import com.my.service.QuestionService;
import com.my.vo.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "MyNoteServlet", value = "/mynote")
public class MyNoteServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        QuestionService service = new QuestionService();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jacksonMap = new HashMap<>();
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("loginInfo");
        int row = Integer.parseInt(request.getParameter("rownum"));
        try {
            Question question = service.mynoteById(id,row);
            jacksonMap.put("question_id",question.getQuestion_id());
            jacksonMap.put("content",question.getContent());
            jacksonMap.put("correct_answer",question.getCorrect_answer());
            jacksonMap.put("explanation",question.getExplanation());
            jacksonMap.put("correct_percent",((int)(((double)question.getCorrect_answer_count()/(double)question.getTotal_answer_count())*100)));
            jacksonMap.put("mn_total",question.getMn_total());
            String jsonStr = mapper.writeValueAsString(jacksonMap);
            out.print(jsonStr);
        } catch (FindException e) {
            out.print("{\"status\" : -1}");
        }
    }
}
