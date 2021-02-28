package com.my.service;

import com.my.dao.FeedbackDAO;
import com.my.dao.FeedbackDAOOracle;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.vo.Feedback;
import com.my.vo.Qa;
import com.my.vo.Report;
import java.util.List;

public class FeedbackService {
    private FeedbackDAO dao = new FeedbackDAOOracle();
    public Qa qaById(String user_id, int rownum) throws FindException{
        return dao.UserQaById(user_id,rownum);
    }
    public Qa findById(String qa_id, int n, int s) throws FindException{
        return dao.QaByQaId(qa_id, n, s);
    }
    public List<Qa> qaAll(int page, int num) throws FindException{
        return dao.QaAll(page,num);
    }

    public Report findReportById(String report_id,int n, int s) throws FindException{
        return dao.ReportById(report_id,  n,  s);
    }

    public List<Report> reportAll(int page, int num) throws  FindException{
        return dao.ReportAll(page,num);
    }

    public List<Feedback> feedbacksById(String user_id) throws Exception{
        return dao.selectFeedbackById(user_id);
    }

    public void  addReportSol(String report_id, String content) throws ModifyException{
        dao.ReportSolUpdate(report_id,content);
    }
}
