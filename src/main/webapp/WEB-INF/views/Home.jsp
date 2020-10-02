<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="fragments/header.jsp"%>
<%@include file="fragments/navigation.jsp"%>

<div class="jumbotron text-center">
    <h1>Welcome!</h1>
    <p               Local time: Fri 2020-10-02 09:30:49 CEST
           Universal time: Fri 2020-10-02 07:30:49 UTC 
                 RTC time: Fri 2020-10-02 07:30:50     
                Time zone: Europe/Zurich (CEST, +0200) 
System clock synchronized: no                          
              NTP service: inactive                    
          RTC in local TZ: no   ></p>
    <p>Our application doesn't have any users yet!</p>
    <p>0 questions have been asked!</p>
    <p>0 answers have been given!</p>
    <p>We are a QA webpage!</p>
</div>
<div class="d-flex justify-content-center">
    <button type="button" class="btn-primary btn-lg">Browse questions...</button>
</div>

<%@include file="fragments/footer.jsp"%>
