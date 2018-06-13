package com.lsq.community.controller.view;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于作未ueditor的控制器跳转
 */
public class UEditorServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger(UEditorServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("UEditorServlet 起作用了----------------------");
        req.getRequestDispatcher("/WEB-INF/util/ueditor/jsp/controller.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("UEditorServlet 起作用了----------------------");
        req.getRequestDispatcher("/WEB-INF/util/ueditor/jsp/controller.jsp").forward(req,resp);
    }
}
