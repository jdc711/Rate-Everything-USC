<%@ page import = "java.sql.*" %>
<%@ page import = "uscratings.*" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href = "mainCSS.css">
<link href="https://fonts.googleapis.com/css?family=Merriweather&display=swap" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
</head>
<body  class = "text-center" style="background-image: url('images/uscCampusTransparentTwo.png');background-repeat:no-repeat;background-size: cover; ">
<%ArrayList<Review> reviews = (ArrayList<Review>)session.getAttribute("chosenReview");
SearchResult Results = (SearchResult)session.getAttribute("SearchResults");

int idx = (Integer)session.getAttribute("idx");
%>
<div class="container">
  <img src="images/uscLogo.png" id = "logo"class="img-fluid" alt="Responsive image">
   <a href = "main.jsp">Back To Home Page</a>
  <br>
  <br>
  
  <h2 style=" font-family: 'Merriweather', serif; color:black;">Reviews for <%=Results.getResults().get(idx).getCategory()%> <%=Results.getResults().get(idx).getName()%> </h2>
 
 <table>
 
 <tr style="font-size:32px">
 
 <td><h3>Overall Rating: <%=Results.getResults().get(idx).getRating()%></h3> </td>
 <td></td>
 <td></td>
 <td></td>
 <td></td>
 <td></td><td></td><td></td><td></td><td></td><td></td>
 
 <td><br><br></td>
 </tr>
 </table>
  
  
  
  
  
  
  
  
 <table>
 <%for (int i = 0; i < reviews.size(); i++){
%>
 <tr style="font-size:32px">
 <td>Date of Review: Jan 1 2000</td>
 <td></td>
 <td></td>
 <td></td>
 <td></td>
 <td></td><td></td><td></td><td></td><td></td><td></td>
 <td>Rating: <%=reviews.get(i).getRating()%></td>
 <td></td>
 <td></td>
 <td></td>
 <td></td>
 <td></td><td></td><td></td><td></td><td></td><td><br></td>
 
 <td >Review:</td><td style="font-size:29px"> <%=reviews.get(i).getDescription()%></td>
 <td></td>
 <td></td>
 <td></td>
 <td></td>
 <td></td><td></td><td></td><td></td><td></td><td></td>
 
 <td><br><br></td>
 </tr>
 <%} %>
 
 </table>
 

</div>



</body> 
</html>