package com.example.harujogak.user;

import com.example.harujogak.timetable.*;
import com.example.harujogak.schedule.ScheduleList;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class User {
    private static volatile User instance = null;
    private static String id, passWord, eMail;
    private static ArrayList<MyTimeTable> weekTable = new ArrayList<MyTimeTable>();   //주간 시간표 저장하는 리스트
    private static ArrayList<MyTimeTable> dateTable = new ArrayList<MyTimeTable>();   //일일 시간표 저장하는 리스트
    private static ScheduleList scheduleList = new ScheduleList();  //캘린더에 일정 저장하는 리스트

//    public User() {
//        this.weekTable = new ArrayList<>(7);
//        weekTable.add(new com.example.harujogak.MyTimeTable("월요일"));
//        weekTable.add(new com.example.harujogak.MyTimeTable("화요일"));
//        weekTable.add(new com.example.harujogak.MyTimeTable("수요일"));
//        weekTable.add(new com.example.harujogak.MyTimeTable("목요일"));
//        weekTable.add(new com.example.harujogak.MyTimeTable("금요일"));
//        weekTable.add(new com.example.harujogak.MyTimeTable("토요일"));
//        weekTable.add(new com.example.harujogak.MyTimeTable("일요일"));
//
//        this.dateTable = new ArrayList<>();
//        this.scheduleList = new ScheduleList();
//    } //임시 테스트용

    public User(String user_id, String user_pw) {
        this.id = user_id;
        this.passWord = user_pw;
        init();
    }

    private void init() {
        //Todo : firebase에 이 사용자 정보가 있으면 불러와서 저장
        // (...)
        // 아니면 초기화
        this.weekTable = new ArrayList<>(7);
        weekTable.add(new MyTimeTable("월요일"));
        weekTable.add(new MyTimeTable("화요일"));
        weekTable.add(new MyTimeTable("수요일"));
        weekTable.add(new MyTimeTable("목요일"));
        weekTable.add(new MyTimeTable("금요일"));
        weekTable.add(new MyTimeTable("토요일"));
        weekTable.add(new MyTimeTable("일요일"));

        this.dateTable = new ArrayList<>();
        this.scheduleList = new ScheduleList();
    }
}