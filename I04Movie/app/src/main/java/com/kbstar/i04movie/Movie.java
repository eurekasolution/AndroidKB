package com.kbstar.i04movie;

public class Movie {
    String rnum;
    String rank;
    String rankInten;
    String rankOldAndNew;
    String movieCd;
    String movieNm;
    String openDt;
    String salesAmt;
    String slaesShare;
    String salesInten;
    String salesChange;
    String salesAcc;
    String audiCnt;
    String audiInten;
    String audiChange;
    String audiAcc;
    String scrnCnt;
    String showCnt;

    public String getTitle()
    {
        return this.movieNm;
    }

    public String getAudiCount()
    {
        return this.audiCnt;
    }
}
