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
<body  class = "text-center" style="background-image: url('images/uscCampusTransparent2.png');background-repeat:no-repeat;background-size: cover; ">

<div class="container">
  <img src="images/uscLogo.png" id = "logo"class="img-fluid" alt="Responsive image">
  <br>
  <br>
 
  <h2 style=" font-family: 'Merriweather', serif; color:black;">Find Rating of Someone or Something from USC!</h2>
 
  
  
  <%if (request.getAttribute("error") != null || request.getAttribute("error") == ""){ %>
			<h2><%= request.getAttribute("error") %></h2>
			<%} %>
  
  <form method="GET" action="searchResultsServlet">
    <div class="form-group">
      <label for="text" style=" font-family: 'Merriweather', serif; font-size:30px;" >Name:</label>
      <input name = "name" type="text" class="form-control" id="name" placeholder="Enter name">
    </div>
    <button type="submit" class="btn btn-light" style=" font-family: 'Merriweather', serif;">Submit</button>
  </form>
  
  
  <br>
  <br>
  <h2 style=" font-family: 'Merriweather', serif;color:black;">Or, leave a Review for someone or something at USC</h2>
 <br>
  <form method="POST" action="LeaveReview.jsp">
    <button type="submit" class="btn btn-light" style=" font-family: 'Merriweather', serif;color:black;">Leave Review</button>
  </form>
</div>



</body> 
</html>