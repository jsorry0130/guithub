<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <div class="bg-dark" id="sidebar-wrapper">
      <div class="sidebar-heading" style="color: white">Membership </div>
      <div class="list-group list-group-flush">
        <a href="/member/meminfo" class="list-group-item list-group-item-action bg-secondary" style="color: white">회원정보</a>
        <a href="/member/postlist?board=tab" class="list-group-item list-group-item-action bg-secondary" style="color: white">게시물 관리</a>
        <a href="/member/replylist?board=tab" class="list-group-item list-group-item-action bg-secondary" style="color: white">댓글 관리</a>
        <a href="/member/logout" class="list-group-item list-group-item-action bg-secondary" style="color: white">로그아웃</a>
        <a href="/member/withdraw" class="list-group-item list-group-item-action bg-secondary" style="color: white">회원탈퇴</a>
      </div>
    </div>