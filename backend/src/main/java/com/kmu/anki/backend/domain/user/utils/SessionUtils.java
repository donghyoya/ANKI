package com.kmu.anki.backend.domain.user.utils;

import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtils {

    public static LanguageCode getLanaguageCode(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        return (LanguageCode) session.getAttribute("languageCode");
    }

    public static Integer getTodayStudyWords(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        return (Integer) session.getAttribute("todayStudyWords");
    }

    public static Integer getTodayReviewWords(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        return (Integer) session.getAttribute("todayReviewWords");
    }

    public static void setUserOptions(HttpServletRequest request, Integer todayStudyWords, Integer todayReviewWords, LanguageCode languageCode){
        HttpSession session = request.getSession(false);
        session.setAttribute("todayStudyWords", todayStudyWords);
        session.setAttribute("todayReviewWords", todayReviewWords);
        session.setAttribute("languageCode", languageCode);

    }

}
