<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--Referencia en linea-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <!--Referencia local-->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <title>TP Final - Inicio</title>
</head>
<body>
	<form class="form needs-validation" name="login" action="ServletLogin?Param=1" method="post">
            
            <div class="row">
                <div class="col-md-12 text-center">
                    <br>
                    <h1 class="text-primary">UTN - FRGP</h1>
                    <br>
                </div>
            </div>
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4 card">
                    <br>
                    <div class="form-label-group">
                        <input type="text" id="txtName" name="txtName" class="form-control" placeholder="Usuario" required autofocus>
                        <br>
                    </div>
                    
                    <div class="form-label-group">
                        <input type="password" id="txtPass" name="txtPass" class="form-control" placeholder="Clave" required>
                        <br>
                    </div>
                    <div class="align-items-center">
                        <button class="btn btn-lg btn-primary btn-block" type="submit" name="btnLogin">Inicio</button>
                    </div> 
                    <br>                   
                </div>
                <div class="col-4"></div>
            </div>
            
        </form>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

        <!--Sacar funcion de esta hoja, pone un visto en los input-->
        <script>
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function() {
              'use strict';
              window.addEventListener('load', function() {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function(form) {
                  form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                      event.preventDefault();
                      event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                  }, false);
                });
              }, false);
            })();
        </script>
</body>
<footer>
        <p class="mt-5 mb-3 text-muted text-center">TP integrador</p>
</footer>
</html>