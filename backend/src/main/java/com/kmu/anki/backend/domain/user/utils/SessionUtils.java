package com.kmu.anki.backend.domain.user.utils;

import com.kmu.anki.backend.domain.card.enums.LanguageCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class SessionUtils {

    public static Optional<LanguageCode> getLanaguageCode(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return Optional.empty();
        }
        return Optional.of((LanguageCode) session.getAttribute("languageCode"));
    }

    public static Optional<Integer> getTodayStudyWords(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return Optional.empty();
        }
        return Optional.of((Integer) session.getAttribute("todayStudyWords"));
    }

    public static Optional<Integer> getTodayReviewWords(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return Optional.empty();
        }
        return Optional.of((Integer) session.getAttribute("todayReviewWords"));
    }

    public static void setUserOptions(HttpServletRequest request, Integer todayStudyWords, Integer todayReviewWords, LanguageCode languageCode){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.setAttribute("todayStudyWords", todayStudyWords);
            session.setAttribute("todayReviewWords", todayReviewWords);
            session.setAttribute("languageCode", languageCode);
        }
    }

}
