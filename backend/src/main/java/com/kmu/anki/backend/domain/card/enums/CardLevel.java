package com.kmu.anki.backend.domain.card.enums;

/**
 * API 대응을 위해 관례를 깨고 소문자로 사용
 */
public enum CardLevel {
    easy,
    normal,
    hard;

    public static String  fromCsv(String data){
        if(data.equals("고급")){
            return hard.toString();
        }else if(data.equals("중급")){
            return normal.toString();
        }else if (data.equals("초급")){
            return easy.toString();
        }
        return null;
    }
}
