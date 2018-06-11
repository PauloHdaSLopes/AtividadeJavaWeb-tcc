<%
    String isSucess = request.getParameter("isSucess");
%>
<html>
    <head>
        <%@include file="../default/head.jsp" %>
    </head>
    <body class="nav-md">
        <div class="container body">
            <div class="main_container">

                <%@include file="../default/menu.jsp" %>
                <!-- page content -->
                <div class="right_col" role="main">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <%
                                    if(isSucess.equals("true")){
                                        
                                        out.println("<div class='alert alert-success alert-dismissable fade in' role='sucess'>Usuario Cadastrado com sucesso </div>");
                                        
                                    }else{
                                        out.println("<div class='alert alert-warning alert-dismissible fade in' role='alert'>Ocorreu um erro ao cadastrar o usuário</div>");
                                    }
                                %>
                                <ul class="nav navbar-right panel_toolbox">
                                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#">Settings 1</a>
                                            </li>
                                            <li><a href="#">Settings 2</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                                    </li>
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /page content -->
                <%@include file="../default/footer.jsp" %>
            </div>
        </div>
    </body>

    <!-- jQuery -->
    <script src="../vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="../vendors/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Custom Theme Scripts -->
    <script src="../build/js/custom.min.js"></script>

</html>